package cn.net.ibingo.core.controller;

import cn.net.ibingo.common.controller.BaseController;
import cn.net.ibingo.common.utils.ConstantConfig;
import cn.net.ibingo.common.utils.HttpUtil;
import cn.net.ibingo.core.model.Advertisers;
import cn.net.ibingo.core.model.FristChannel;
import cn.net.ibingo.core.model.Resources;
import cn.net.ibingo.core.model.VoluumNotify;
import cn.net.ibingo.core.redis.DistributeRedisFactory;
import cn.net.ibingo.core.redis.StatusRedisFactory;
import cn.net.ibingo.core.redis.model.DistributeRedisModel;
import cn.net.ibingo.core.redis.model.StatusRedisModel;
import cn.net.ibingo.core.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by yuxiangjie on 2017-06-09.
 */
@Controller
@RequestMapping("/voluum")
public class VoluumNotifyController extends BaseController {

    private static Logger log = Logger.getLogger(String.valueOf(VoluumNotifyController.class));
    @Autowired
    public VoluumNotifyService voluumNotifyService;

    @Autowired
    private AdvertisersService advertisersService;

    @Autowired
    private ResourcesService resourcesService;

    @Autowired
    public FristChannelService fristChannelService;

    @Autowired
    public DistributionRateService distributionRateService;

    @Autowired
    private StatusRedisFactory statusRedisFactory;

    @Autowired
    private DistributeRedisFactory distributeRedisFactory;

    private  HttpUtil http = new HttpUtil();


    /**
     * j接受Voluum平台的回调数据
     * @param notify
     */
	@RequestMapping("/notify")
    @ResponseBody
    public void voluumNotify(VoluumNotify notify){//http://m.nicegame.me/am/voluum/notify?trafficSourceId={trafficsource.id}&offerId={offer.id}&campaignId={campaign.id}&clickId={click.id}&payout={payout}&country={country}&p1={var1}
        if (notify != null && !StringUtils.isEmpty(notify.getClickId()) && !StringUtils.isEmpty(notify.getOfferId()) && !StringUtils.isEmpty(notify.getTrafficSourceId())){
            Integer callState = 0;
            Integer dailyLimit = 0;

            //查询资源名称、是否支持回调
            Resources resources = resourcesService.selectByOfferId(notify.getOfferId());
            if(resources != null){
                notify.setOfferName(resources.getName());
                callState = resources.getCallbackStatus();
                dailyLimit = resources.getDailyLimit();
            }
            //查询广告主名称
            if(resources != null && resources.getAdsId() != null ){
                Advertisers advertiser = advertisersService.get(resources.getAdsId());
                if(advertiser != null){
                    notify.setAffiliateNetworkName(advertiser.getName());
                    notify.setAffiliateNetworkId(advertiser.getVoluumAffiliateNetworkId());
                }
            }
            //查询渠道名称、回调地址
            FristChannel fristChannel = fristChannelService.selectByTrafficSourceId(notify.getTrafficSourceId());
            if(fristChannel != null){
                notify.setTrafficSourceName(fristChannel.getName());
            }

            //根据渠道id和offerId查询分配比例
            Float rate = distributionRateService.selectByTrafficIdAndOfferId(notify.getTrafficSourceId(),notify.getOfferId());
            notify.setCreateDate(new Date());
            int count = voluumNotifyService.insertNotify(notify);
            if(count > 0){
                try{
                    sendStatusByMessage(notify,fristChannel,callState,rate,dailyLimit);
                }catch (Exception e){

                }
            }
        }
    }

    /**
     * 获取发送与否的状态位
     * @param message
     * @param iscallback
     * @param  @return
     */
    private VoluumNotify sendStatusByMessage(VoluumNotify message, FristChannel fristChannel, Integer iscallback, Float subscriptionRate,Integer dayLimit) {
        int status = 0;// 发送状态位：0没发送，1发送
        try {
            // 支持回调
            if(iscallback != null && iscallback == 1 && StringUtils.isNotBlank(fristChannel.getCallbackUrl())){
                // 订阅分成比例为0时不回调
                if(subscriptionRate != null && subscriptionRate != 0){
                    //是否超过日限量
                        // 订阅分成比例为1时全部回调
                        if(subscriptionRate == 1){
                            status = 1;
                            try {
                                http.sendGet(this.jumpCallbackUrl(message,fristChannel));
                                //当下发渠道时，修改数据类型
                                voluumNotifyService.updateDataType(message.getClickId());
                            } catch (Exception e) {
                                status = 2;
                            }
                        } else {// 订阅分成比例大于0小于1时
                            sysDownTraffic(message,fristChannel,status,subscriptionRate);
                        }
                    }
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    /**
     * 订阅分成比例大于0小于1时 增加同步 下发渠道数据
     * @param message
     * @param fristChannel
     * @param status
     * @param subscriptionRate
     */
    public synchronized void sysDownTraffic(VoluumNotify message, FristChannel fristChannel, int status, Float subscriptionRate){
        StatusRedisModel statusRedisModel = new StatusRedisModel();
        statusRedisModel.setOfferId(message.getOfferId());
        statusRedisModel.setTrafficId(message.getTrafficSourceId());
        // 获取随机数
        Set<Integer> statusSet = this.getStatusSet(statusRedisModel,subscriptionRate);
        // 获取移位指针
        DistributeRedisModel distributeRedisModel = new DistributeRedisModel();
        distributeRedisModel.setDate(new Date());
        distributeRedisModel.setOfferId(message.getOfferId());
        distributeRedisModel.setTrafficId(message.getTrafficSourceId());
        Integer count = distributeRedisFactory.get(distributeRedisModel);
        if(count == null || count == 0 || count == ConstantConfig.DEFAULT_NUMBER){
            count = 1;
        } else if(count == ConstantConfig.DEFAULT_NUMBER - 1){
            count ++;
            statusRedisFactory.delete(statusRedisModel);
        } else {
            count ++;
        }
        if(statusSet.contains(count)){
            status = 0;
        } else {
            try {
                http.sendGet(this.jumpCallbackUrl(message,fristChannel));
                //当下发渠道时，修改数据类型
                voluumNotifyService.updateDataType(message.getClickId());
            } catch (Exception e) {
                status = 2;
            }
            status = 1;
        }
        String ss = status != 0 ? "down":"not down";
        log.info("------------count--------------"+count+"-------------status==========="+ss );
        distributeRedisModel.setCount(count);
        distributeRedisFactory.add(distributeRedisModel);
    }
    /**
     * 获取不发送的随机数
     * @param
     * @param subscriptionRate
     * @return
     */
    private Set<Integer> getStatusSet(StatusRedisModel statusRedisModel, Float subscriptionRate) {
        StatusRedisModel tmpStatusRedisModel = statusRedisFactory.get(statusRedisModel);
        Set<Integer> statusSet=new HashSet<Integer>();
        // 缓存中不存在，随机取1-100中的n个数
        if( tmpStatusRedisModel == null){
            while(true){
                 statusSet.add((int)(Math.random()*100+1));
                if( statusSet.size() == (100-subscriptionRate*100))
                    break;
            }
            statusRedisModel.setStatusSet(statusSet);
            statusRedisFactory.add(statusRedisModel);
        } else {
            statusSet = tmpStatusRedisModel.getStatusSet();
        }
        return statusSet;
    }
    /**
     * 回调地址跳转
     * @param message
     */
    private String jumpCallbackUrl(VoluumNotify message,FristChannel fristChannel) throws IOException {
        if(!StringUtils.isEmpty(fristChannel.getCallbackUrl())){
            StringBuffer sbf = new StringBuffer();
            sbf.append(fristChannel.getCallbackUrl());
            if(fristChannel.getCallbackUrl().indexOf("?") != -1){
                sbf.append("&");
            } else {
                sbf.append("?");
            }
            if(!StringUtils.isEmpty(message.getP1())){
                sbf.append(fristChannel.getP1()+"="+message.getP1()+"&");
            }
            if(!StringUtils.isEmpty(message.getP2())){
                sbf.append(fristChannel.getP2()+"="+message.getP2()+"&");
            }
            if(!StringUtils.isEmpty(message.getP3())){
                sbf.append(fristChannel.getP3()+"="+message.getP3()+"&");
            }
            if(!StringUtils.isEmpty(message.getP4())){
                sbf.append(fristChannel.getP4()+"="+message.getP4()+"&");
            }
            if(!StringUtils.isEmpty(message.getP5())){
                sbf.append(fristChannel.getP5()+"="+message.getP5()+"&");
            }
            String resultStr = sbf.toString();
            resultStr = resultStr.substring(0,resultStr.length()-1);
            return resultStr;
        }
        return "";
    }

}
