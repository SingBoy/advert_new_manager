package cn.net.ibingo.core.service.impl;

import cn.net.ibingo.common.utils.DateUtils;
import cn.net.ibingo.core.dao.*;
import cn.net.ibingo.core.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 定时统计回调数据
 */
@Service
public class NotifyDataQuartzStatisticsServiceImpl {

    @Autowired
    private TimezoneCountryMapper timezoneCountryMapper;

    @Autowired
    private AdvertisersStatisticsMapper advertisersStatisticsMapper;

    @Autowired
    private OfferStatisticsMapper offerStatisticsMapper;

    @Autowired
    private TrafficSourceStatisticsMapper trafficSourceStatisticsMapper;

    @Autowired
    private VoluumNotifyMapper voluumNotifyMapper;


    public List<TimezoneCountry> queryCoutryList() {
        List<TimezoneCountry> countryList = timezoneCountryMapper.queryCoutryList();
        return countryList;
    }


    //@Scheduled(cron = "0/15 * * * * *")
    //@Scheduled(cron = "0 40 17 * * ?")
    public void quartzAnalysis() {
        System.out.print("-------------");
        for(int i=24 ;i<31 ;i++){
            String date = "2017-06-"+i;//DateUtils.formatDateToString(DateUtils.getYesterday());
            String startDate = date+" 00:00:00";
            String endDate = date+" 23:59:59";
            accordOfferStatistics(startDate,endDate);
            accordTrafficSourceStatistics(startDate,endDate);
            accordAdvertisersStatistics(startDate,endDate);
       }

    }


    /**
     * 按照不同广告统计数据
     * @param startDate
     * @param endDate
     */
    public void accordOfferStatistics(String startDate, String endDate) {
        List<VoluumNotify> voluumList = voluumNotifyMapper.selectOfferStatistics(startDate, endDate);
        List<OfferStatistics> offerList = new ArrayList<OfferStatistics>();
        OfferStatistics offer = null;
        if (voluumList != null && voluumList.size() > 0) {
            Date date = DateUtils.parseDateTime1(startDate);
            for (VoluumNotify voluum : voluumList) {
                offer = new OfferStatistics();
                offer.setCountry(voluum.getCountry());
                offer.setOfferId(voluum.getOfferId());
                offer.setOfferName(voluum.getOfferName());
                offer.setOfferNameAlias(voluum.getOfferNameAlias());
                offer.setTrafficSourceId(voluum.getTrafficSourceId());
                offer.setTrafficSourceName(voluum.getTrafficSourceName());
                offer.setConversNum(voluum.getConversNum());
                offer.setDate(date);
                offer.setCreateDate(new Date());
                offerList.add(offer);
            }
        }
        if (offerList.size() > 0) {
            offerStatisticsMapper.insertOfferStatistics(offerList);
        }
    }


    /**
     * 按照不同渠道统计数据
     * 按照不同渠道与广告的比例分配计算后统计数据
     * @param startDate
     * @param endDate
     */
    public void accordTrafficSourceStatistics(String startDate, String endDate) {
        List<VoluumNotify> voluumList = voluumNotifyMapper.selectTrafficSourceStatistics(startDate, endDate);
        List<TrafficSourceStatistics> trafficList = new ArrayList<TrafficSourceStatistics>();
        List<TrafficSourceStatistics> trafficRateList = new ArrayList<TrafficSourceStatistics>();
        TrafficSourceStatistics traffic = null;
        TrafficSourceStatistics trafficRate = null;
        Float rate = null;
        if (voluumList != null && voluumList.size() > 0) {
            Date date = DateUtils.parseDateTime1(startDate);
            for (VoluumNotify voluum : voluumList) {
                traffic = new TrafficSourceStatistics();
                traffic.setCountry(voluum.getCountry());
                traffic.setOfferId(voluum.getOfferId());
                traffic.setOfferName(voluum.getOfferName());
                traffic.setOfferNameAlias(voluum.getOfferNameAlias());
                traffic.setTrafficSourceId(voluum.getTrafficSourceId());
                traffic.setTrafficSourceName(voluum.getTrafficSourceName());
                traffic.setConversNum(voluum.getConversNum());
                traffic.setDate(date);
                traffic.setCreateDate(new Date());
                trafficList.add(traffic);
            }
            //保存渠道统计结果
            if(trafficList.size()>0){
                trafficSourceStatisticsMapper.insertTrafficSourceStatistics(trafficList);
            }
            //保存渠道经过分配比例计算之后的统计结果
            if(trafficRateList.size()>0){
                trafficSourceStatisticsMapper.insertTrafficSourceRateStatistics(trafficRateList);
            }
        }
    }


    /**
     *  按照不同广告主统计数据
     * @param startDate
     * @param endDate
     */
    public void accordAdvertisersStatistics(String startDate, String endDate) {
        List<VoluumNotify> voluumList = voluumNotifyMapper.selectAdvertisersStatistics(startDate, endDate);
        List<AdvertisersStatistics> advertList = new ArrayList<AdvertisersStatistics>();
        AdvertisersStatistics advert = null;
        if (voluumList != null && voluumList.size() > 0) {
            Date date = DateUtils.parseDateTime1(startDate);
            for (VoluumNotify voluum : voluumList) {
                advert = new AdvertisersStatistics();
                advert.setCountry(voluum.getCountry());
                advert.setOfferId(voluum.getOfferId());
                advert.setOfferName(voluum.getOfferName());
                advert.setOfferNameAlias(voluum.getOfferNameAlias());
                advert.setAffiliateNetworkId(voluum.getAffiliateNetworkId());
                advert.setAffiliateNetworkName(voluum.getAffiliateNetworkName());
                advert.setTrafficSourceId(voluum.getTrafficSourceId());
                advert.setTrafficSourceName(voluum.getTrafficSourceName());
                advert.setConversNum(voluum.getConversNum());
                advert.setDate(date);
                advert.setCreateDate(new Date());
                advertList.add(advert);
            }
        }
        if (advertList.size() > 0) {
            advertisersStatisticsMapper.insertAdvertisersStatistics(advertList);
        }

    }

}
