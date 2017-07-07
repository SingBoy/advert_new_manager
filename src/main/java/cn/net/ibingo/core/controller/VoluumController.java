package cn.net.ibingo.core.controller;

import cn.net.ibingo.common.controller.BaseController;
import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.utils.ConstantConfig;
import cn.net.ibingo.common.utils.HttpUtil;
import cn.net.ibingo.core.model.*;
import cn.net.ibingo.core.query.ResourcesQueryBean;
import cn.net.ibingo.core.query.VoluumNotifyQueryBean;
import cn.net.ibingo.core.redis.DistributeRedisFactory;
import cn.net.ibingo.core.redis.StatusRedisFactory;
import cn.net.ibingo.core.redis.model.DistributeRedisModel;
import cn.net.ibingo.core.redis.model.StatusRedisModel;
import cn.net.ibingo.core.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by yuxiangjie on 2017-06-09.
 */
@Controller
@RequestMapping("/voluumList")
public class VoluumController extends BaseController {

    private static Logger log = Logger.getLogger(String.valueOf(VoluumController.class));
    @Autowired
    public VoluumNotifyService voluumNotifyService;

    @Autowired
    private AdvertisersService advertisersService;

    @Autowired
    public FristChannelService fristChannelService;


    @RequestMapping(value = "/list")
    public String list(VoluumNotifyQueryBean queryBean, HttpSession session, ModelMap modelMap){
        User user = (User) session.getAttribute(ConstantConfig.USER);
        boolean flg = true;
        PaginationList<VoluumNotify> pageDataList = null;
        if(user != null && user.getUserRole().equals(2)) {
            Advertisers ad = advertisersService.get(user.getUserRoleId());
            if (ad != null && !StringUtils.isEmpty(ad.getVoluumAffiliateNetworkId()))
                queryBean.setAffilicateId(ad.getVoluumAffiliateNetworkId());
                pageDataList = voluumNotifyService.list(queryBean);
        }else if(user != null && user.getUserRole().equals(3)){
            FristChannel fc = fristChannelService.get(user.getUserRoleId());
            if (fc != null && !StringUtils.isEmpty(fc.getVoluumTrafficSourceId())) {
                queryBean.setTrafficId(fc.getVoluumTrafficSourceId());
                queryBean.setDataType(1);
                pageDataList = voluumNotifyService.list(queryBean);
            }
        }else{
            pageDataList = voluumNotifyService.list(queryBean);
        }
        modelMap.addAttribute(ConstantConfig.PAGE_DATA_LIST, pageDataList);
        modelMap.addAttribute(ConstantConfig.QUERYBEAN,queryBean);
        return "pager/voluum/list";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Integer id, ResourcesQueryBean queryBean)
            throws UnsupportedEncodingException {
        voluumNotifyService.delete(id);
        return "redirect:/voluumList/list?keyword="+(java.net.URLEncoder.encode(queryBean.getKeyword(),"UTF-8"))+
                "&currentPage="+queryBean.getCurrentPage()+
                "&pageSize="+queryBean.getPageSize();
    }
	

}
