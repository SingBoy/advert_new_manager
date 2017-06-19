package cn.net.ibingo.core.service.impl;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.pagination.model.SimplePaginatedList;
import cn.net.ibingo.common.utils.DateUtils;
import cn.net.ibingo.core.dao.*;
import cn.net.ibingo.core.model.*;
import cn.net.ibingo.core.query.NotifyAnalysisQueryBean;
import cn.net.ibingo.core.service.NotifyAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RawDataAnalysisQuartzServiceImpl {

    @Autowired
    private TimezoneCountryMapper timezoneCountryMapper;

    //@Autowired
    //private NotifySuccessRawDataMapper notifySuccessRawDataMapper;

    //@Autowired
    //private SubscribeSuccessRawDataMapper subscribeSuccessRawDataMapper;

    //@Autowired
    //private SubscribeFailureRawDataMapper subscribeFailureRawDataMapper;

    @Autowired
    private NotifyAnalysisMapper notifyAnalysisMapper;

    @Autowired
    private NotifyChannelAnalysisMapper notifyChannelAnalysisMapper;

    @Autowired
    private SubscribeSuccessAnalysisMapper subscribeSuccessAnalysisMapper;

    //@Autowired
    //private SubscribeFailureAnalysisMapper subscribeFailureAnalysisMapper;

    //@Autowired
    //private SubscribeIpFailureAnalysisMapper subscribeIpFailureAnalysisMapper;

    public List<TimezoneCountry>  queryCoutryList(){
        List<TimezoneCountry> countryList = timezoneCountryMapper.queryCoutryList();
        return countryList;
    }

    public void quartzAnalysis(){
        List<TimezoneCountry> countryList = timezoneCountryMapper.queryCoutryList();
        String utcDate = DateUtils.getUTCTimeStr();
        //TODO 此处根据时区换算出开始、结束时间
        NotifyAnalysis notifyAnalysis = null;
        SubscribeSuccessAnalysis subscribeSuccessAnalysis = null;
        Map<String,Object> paramsMap = new HashMap<String,Object>();
        if (countryList != null && countryList.size() > 0) {
            for (TimezoneCountry timezoneCountry : countryList) {
                paramsMap.put("country",timezoneCountry.getCountryIso());
                quartzNotifyRawDataAnalysis(paramsMap,notifyAnalysis);
            }
        }
    }

    /**
     * 解析回调原始数据并保存到数据库回调数据解析表中
     */
    public void quartzNotifyRawDataAnalysis(Map<String,Object> paramsMap,NotifyAnalysis notifyAnalysis){
            List<NotifySuccessRawData> notifyList =  null;//notifySuccessRawDataMapper.selectNotifyByCountry(paramsMap);
            if(notifyList != null && notifyList.size() > 0){
                for(NotifySuccessRawData NotifySuccessRawData : notifyList){
                    notifyAnalysis = new NotifyAnalysis();
                    notifyAnalysis.setFirstchannelid("");
                    notifyAnalysis.setSecchannelid("");
                    notifyAnalysis.setIndex(1L);
                    notifyAnalysis.setAdvertisersname("");
                    notifyAnalysis.setAdvertisersname("");
                    notifyAnalysis.setResourcesname("");
                    notifyAnalysis.setIpoprator("");
                    notifyAnalysis.setType("");
                    notifyAnalysis.setPrice("");
                    notifyAnalysis.setCount(1L);
                    notifyAnalysis.setDate(new Date());
                    notifyAnalysis.setCountry("");
                    notifyAnalysisMapper.insertSelective(notifyAnalysis);
                }

            }
    }


}
