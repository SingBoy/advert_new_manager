package cn.net.ibingo.core.controller;

import cn.net.ibingo.common.controller.BaseController;
import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.utils.ConstantConfig;
import cn.net.ibingo.common.utils.HttpUtil;
import cn.net.ibingo.core.model.*;
import cn.net.ibingo.core.query.AdvertisersQueryBean;
import cn.net.ibingo.core.query.TrafficSourceStatisticsQueryBean;
import cn.net.ibingo.core.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Controller
@RequestMapping("/trafficSourceStatistics")
public class TrafficSourceStatisticsController extends BaseController {
    private static Logger log = Logger.getLogger(String.valueOf(TrafficSourceStatisticsController.class));

    @Autowired
    private TrafficSourceStatisticsService trafficSourceStatisticsService;

    @Autowired
    private AdvertisersService advertisersService;

    @Autowired
    private ResourcesService resourcesService;

    @Autowired
    private MccService mccService;

    @Autowired
    private FristChannelService fristChannelService;

    @Autowired
    private DistributionRateService distributionRateService;

    @RequestMapping(value = "/list")
    public String list(HttpSession session, TrafficSourceStatisticsQueryBean queryBean, ModelMap modelMap) {
        queryBean.setTwoTree("2");

        List<Mcc> mccList = mccService.selectAll();
        modelMap.addAttribute(ConstantConfig.LISTMCC, mccList);

        List<FristChannel> listFristChannel = fristChannelService.selectAll();
        modelMap.addAttribute(ConstantConfig.LISTFRISTCHANNEL, listFristChannel);

        User user = new User();
        user = (User) session.getAttribute(ConstantConfig.USER);
        List<Resources> listResources = resourcesService.selectAll(null);
        modelMap.addAttribute(ConstantConfig.LISTRESOURCES, listResources);

        PaginationList<TrafficSourceStatistics> pageDataList = trafficSourceStatisticsService.list(queryBean);
        modelMap.addAttribute(ConstantConfig.PAGE_DATA_LIST, pageDataList);
        modelMap.addAttribute(ConstantConfig.QUERYBEAN, queryBean);
        return "pager/trafficSourceStatistics/list";
    }

    @RequestMapping(value = "/rateList")
    public String rateList(HttpSession session, TrafficSourceStatisticsQueryBean queryBean, ModelMap modelMap) {
        queryBean.setTwoTree("3");
        List<Mcc> mccList = mccService.selectAll();
        modelMap.addAttribute(ConstantConfig.LISTMCC, mccList);

        List<Resources> listResources = resourcesService.selectAll(null);
        modelMap.addAttribute(ConstantConfig.LISTRESOURCES, listResources);

        User user = new User();
        user = (User) session.getAttribute(ConstantConfig.USER);
        if (user != null && user.getUserRoleId() != null && ConstantConfig.TRAFFIC_MANAGER.equals(user.getUserRole())) {
            FristChannel channel = fristChannelService.get(user.getUserRoleId());
            if (channel != null) {
                queryBean.setTrafficSourceId(channel.getVoluumTrafficSourceId());
            } else {
                queryBean.setTrafficSourceId("000");
            }
        }else{
            List<FristChannel> listFristChannel = fristChannelService.selectAll();
            modelMap.addAttribute(ConstantConfig.LISTFRISTCHANNEL, listFristChannel);
        }

        List<DistributionRate> distributionRateList = distributionRateService.selectAll();
        Map<String,Float> rateMap = new HashMap<String,Float>();
        if (distributionRateList != null && distributionRateList.size() > 0) {
            for(DistributionRate rate : distributionRateList){
                rateMap.put(rate.getVoluumOfferId()+"_"+rate.getVoluumTrafficSourceId(),rate.getSubscriptionRate());
            }
        }

        PaginationList<TrafficSourceStatistics> pageDataList = trafficSourceStatisticsService.list(queryBean);
        countConversNum(rateMap,pageDataList);

        modelMap.addAttribute(ConstantConfig.PAGE_DATA_LIST, pageDataList);
        modelMap.addAttribute(ConstantConfig.QUERYBEAN, queryBean);
        return "pager/trafficSourceStatistics/ratelist";
    }

    /**
     * 根据渠道和广告之间的分配比例计算转化数
     */
    public void countConversNum(Map<String,Float> rateMap, PaginationList<TrafficSourceStatistics> pageDataList ){
        if(rateMap != null && pageDataList != null && pageDataList.getTotalSize()>0){
            //获取查询出来的数据list
                List<TrafficSourceStatistics> pageDatas  = pageDataList.getPageRecords();
            //遍历数据将订阅量与对应的订阅量分配比例相乘，得到结果
            Float rate = null;
            for(TrafficSourceStatistics tss : pageDatas){
                rate = rateMap.get(tss.getOfferId()+"_"+tss.getTrafficSourceId());
                if(rate !=null && rate !=1){
                    //计算按比例分配之后的订阅量且向上取整
                    int count = (int)(Math.ceil(tss.getConversNum()*rate));
                    tss.setConversNum(count);
                }
            }
        }

    }

}
