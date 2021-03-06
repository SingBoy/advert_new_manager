package cn.net.ibingo.core.service.impl;

import cn.net.ibingo.common.utils.ConstantConfig;
import cn.net.ibingo.common.utils.DateUtils;
import cn.net.ibingo.core.dao.*;
import cn.net.ibingo.core.model.*;
import cn.net.ibingo.core.service.NotifyDataQuartzStatisticsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

/**
 * 定时统计回调数据
 */
@Service
public class NotifyDataQuartzStatisticsServiceImpl implements NotifyDataQuartzStatisticsService {
    private static Logger log = Logger.getLogger(String.valueOf(NotifyDataQuartzStatisticsServiceImpl.class));
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

  //手动统计
    /*public void statisticsList() {
        List<TimezoneCountry> countryTimezoneList = timezoneCountryMapper.queryCoutryList();
        if (countryTimezoneList != null && countryTimezoneList.size() > 0) {
            String startDate = "";
            String endDate = "";
            Integer zone = null;
            TimeZone oldZone = TimeZone.getTimeZone("GMT");
            TimeZone newZone = null;
            Date yesDay = null;
            String [] strArray = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17"};
            for(int i = 0;i<strArray.length;i++){
                yesDay =   DateUtils.getYesterdayByDate(DateUtils.parseStringToDate("2017-08-"+strArray[i]));
                    for (TimezoneCountry tc : countryTimezoneList) {
                    zone = tc.getTimezone() / 100;
                    //计算时区
                    if (zone != null && zone > 0) {
                        newZone = TimeZone.getTimeZone("GMT-" + zone);
                    } else {
                        newZone = TimeZone.getTimeZone("GMT+" + Math.abs(zone));
                    }
                    //根据各个国家的时区 统计该国家对应的数量
                    startDate = DateUtils.changeTimeZone(yesDay, oldZone, newZone);
                    endDate = DateUtils.dateAddOne(startDate);
                    //accordOfferStatistics(yesDay,startDate, endDate, tc.getCountryIso());
                    accordTrafficSourceStatistics(yesDay,startDate, endDate, tc.getCountryIso());
                    *//*accordAdvertisersStatistics(yesDay,startDate, endDate, tc.getCountryIso());
                    accordTrafficSourceRateStatistics(yesDay,startDate, endDate, tc.getCountryIso());*//*
                }
            }
            log.info("--------hans-------statistics compalete-----------------"+DateUtils.formatDateTimeAll(new Date()));
        }
    }*/

    /**
     * 每天凌晨2:59开始，每7个小时1次
     */
    //@Scheduled(cron = "0 59 2/7 * * ?")
    @Scheduled(cron = "0 15 18 * * ?")
    public void quartzAnalysis() {
        List<TimezoneCountry> countryTimezoneList = timezoneCountryMapper.queryCoutryList();
        if (countryTimezoneList != null && countryTimezoneList.size() > 0) {
            String startDate = "";
            String endDate = "";
            Integer zone = null;
            TimeZone oldZone = TimeZone.getTimeZone("GMT");
            TimeZone newZone = null;
            Date yesDay = DateUtils.parseDateTime1("2017-08-14 00:00:00");//DateUtils.getYesterday();
            for (TimezoneCountry tc : countryTimezoneList) {
                zone = tc.getTimezone() / 100;
                //计算时区
                if (zone != null && zone > 0) {
                    newZone = TimeZone.getTimeZone("GMT-" + zone);
                } else {
                    newZone = TimeZone.getTimeZone("GMT+" + Math.abs(zone));
                }
                //根据各个国家的时区 统计该国家对应的数量
                startDate = DateUtils.changeTimeZone(yesDay, oldZone, newZone);
                endDate = DateUtils.dateAddOne(startDate);
                //accordOfferStatistics(yesDay,startDate, endDate, tc.getCountryIso());
                accordTrafficSourceStatistics(yesDay,startDate, endDate, tc.getCountryIso());
                //accordTrafficSourceRateStatistics(yesDay,startDate, endDate, tc.getCountryIso());
                //accordAdvertisersStatistics(yesDay,startDate, endDate, tc.getCountryIso());
            }
            log.info("---------------statistics compalete-----------------"+DateUtils.formatDateTimeAll(new Date()));
        }
    }


    /**
     * 按照不同广告统计数据
     *
     * @param startDate
     * @param endDate
     */
    public void accordOfferStatistics(Date date,String startDate, String endDate, String country) {
        //根据时间、国家统计回调数据
        List<VoluumNotify> voluumList = voluumNotifyMapper.selectOfferStatistics(startDate, endDate, country);
        List<OfferStatistics> addOfferList = new ArrayList<OfferStatistics>();
        List<OfferStatistics> updateOfferList = new ArrayList<OfferStatistics>();
        OfferStatistics offer = null;
        Map<String, OfferStatistics> oldOfferStatisMap = new HashMap<String, OfferStatistics>();
        if (voluumList != null && voluumList.size() > 0) {
            //根据时间、国家查询当天已存在的广告统计数据
            List<OfferStatistics> oldOfferStatisticsList = offerStatisticsMapper.selectOldOfferStatistics(startDate, endDate, country);

            Date curDate = new Date();
            //当广告统计表中有当天的数据时
            if (oldOfferStatisticsList != null && oldOfferStatisticsList.size() > 0) {
                for (OfferStatistics oldOffer : oldOfferStatisticsList) {
                    oldOfferStatisMap.put(oldOffer.getOfferId() + "_" + oldOffer.getCountry() + "_" + DateUtils.formatDateToString(oldOffer.getDate()), oldOffer);
                }
                String key = "";
                for (VoluumNotify voluum : voluumList) {
                    key = voluum.getOfferId() + "_" + voluum.getCountry() + "_" + DateUtils.formatDateToString(date);
                    if (!oldOfferStatisMap.containsKey(key)) {
                        offer = new OfferStatistics();
                        offer.setCountry(voluum.getCountry());
                        offer.setOfferId(voluum.getOfferId());
                        offer.setOfferName(voluum.getOfferName());
                        offer.setOfferNameAlias(voluum.getOfferNameAlias());
                        offer.setConversNum(voluum.getConversNum());
                        offer.setDate(date);
                        offer.setCreateDate(curDate);
                        addOfferList.add(offer);
                    } else {
                        offer = oldOfferStatisMap.get(key);
                        offer.setConversNum(voluum.getConversNum());
                        offer.setModifyDate(curDate);
                        updateOfferList.add(offer);
                    }
                }
                //当广告统计表中有当天的数据时（当天第一次统计）
            } else {
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
                    offer.setCreateDate(curDate);
                    addOfferList.add(offer);
                }
            }

        }
        if (addOfferList.size() > 0) {
            offerStatisticsMapper.insertOfferStatistics(addOfferList);
        }
        if (updateOfferList.size() > 0) {
            offerStatisticsMapper.updateOfferStatistics(updateOfferList);
        }
    }


    /**
     * 按照不同渠道、国家统计数据
     * @param startDate
     * @param endDate
     */
    public void accordTrafficSourceStatistics(Date date,String startDate, String endDate, String country) {
        List<VoluumNotify> voluumList = voluumNotifyMapper.selectTrafficSourceStatistics(startDate, endDate, country);
        List<TrafficSourceStatistics> addTrafficList = new ArrayList<TrafficSourceStatistics>();
        List<TrafficSourceStatistics> updateTrafficList = new ArrayList<TrafficSourceStatistics>();
        Map<String, TrafficSourceStatistics> oldTrafficSourceStatisticsMap = new HashMap<String, TrafficSourceStatistics>();
        TrafficSourceStatistics traffic = null;
        if (voluumList != null && voluumList.size() > 0) {

            Date curDate = new Date();
            //根据时间、国家查询当天已存在的广告统计数据
            List<TrafficSourceStatistics> oldTrafficStatisticsList = trafficSourceStatisticsMapper.selectOldTrafficSourceStatistics(startDate, endDate, country);
            if (oldTrafficStatisticsList != null && oldTrafficStatisticsList.size() > 0) {
                for (TrafficSourceStatistics oldTraffic : oldTrafficStatisticsList) {
                    oldTrafficSourceStatisticsMap.put(oldTraffic.getOfferId() + "_" + oldTraffic.getTrafficSourceId() + "_" + oldTraffic.getCountry() + "_" + DateUtils.formatDateToString(oldTraffic.getDate()), oldTraffic);
                }
                String key = "";
                for (VoluumNotify voluum : voluumList) {
                    key = voluum.getOfferId() + "_" + voluum.getTrafficSourceId() + "_" + voluum.getCountry() + "_" + DateUtils.formatDateToString(date);
                    if (!oldTrafficSourceStatisticsMap.containsKey(key)) {
                        traffic = new TrafficSourceStatistics();
                        traffic.setCountry(voluum.getCountry());
                        traffic.setOfferId(voluum.getOfferId());
                        traffic.setOfferName(voluum.getOfferName());
                        traffic.setOfferNameAlias(voluum.getOfferNameAlias());
                        traffic.setTrafficSourceId(voluum.getTrafficSourceId());
                        traffic.setTrafficSourceName(voluum.getTrafficSourceName());
                        traffic.setConversNum(voluum.getConversNum());
                        traffic.setDate(date);
                        traffic.setCreateDate(curDate);
                        addTrafficList.add(traffic);
                    } else {
                        traffic = oldTrafficSourceStatisticsMap.get(key);
                        traffic.setConversNum(voluum.getConversNum());
                        traffic.setModifyDate(curDate);
                        updateTrafficList.add(traffic);
                    }
                }
            } else {
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
                    traffic.setCreateDate(curDate);
                    addTrafficList.add(traffic);
                }
            }
            //保存渠道统计结果
            if (addTrafficList.size() > 0) {
                trafficSourceStatisticsMapper.insertTrafficSourceStatistics(addTrafficList);
            }

            if (updateTrafficList.size() > 0) {
                trafficSourceStatisticsMapper.updateTrafficSourceStatistics(updateTrafficList);
            }
            //保存渠道经过分配比例计算之后的统计结果
            /*if(trafficRateList.size()>0){
                trafficSourceStatisticsMapper.insertTrafficSourceRateStatistics(trafficRateList);
            }*/
        }
    }



    /**
     * 按照不同渠道、国家、成功下发给渠道的
     * @param startDate
     * @param endDate
     */
    public void accordTrafficSourceRateStatistics(Date date,String startDate, String endDate, String country) {
        List<VoluumNotify> voluumList = voluumNotifyMapper.selectTrafficSourceRateStatistics(startDate, endDate, country, "1");
        List<TrafficSourceStatistics> addTrafficList = new ArrayList<TrafficSourceStatistics>();
        List<TrafficSourceStatistics> updateTrafficList = new ArrayList<TrafficSourceStatistics>();
        Map<String, TrafficSourceStatistics> oldTrafficSourceStatisticsMap = new HashMap<String, TrafficSourceStatistics>();
        TrafficSourceStatistics traffic = null;
        if (voluumList != null && voluumList.size() > 0) {

            Date curDate = new Date();
            //根据时间、国家查询当天已存在的广告统计数据
            List<TrafficSourceStatistics> oldTrafficStatisticsList = trafficSourceStatisticsMapper.selectOldTrafficSourceRateStatistics(startDate, endDate, country);
            if (oldTrafficStatisticsList != null && oldTrafficStatisticsList.size() > 0) {
                for (TrafficSourceStatistics oldTraffic : oldTrafficStatisticsList) {
                    oldTrafficSourceStatisticsMap.put(oldTraffic.getOfferId() + "_" + oldTraffic.getTrafficSourceId() + "_" + oldTraffic.getCountry() + "_" + DateUtils.formatDateToString(oldTraffic.getDate()), oldTraffic);
                }
                String key = "";
                for (VoluumNotify voluum : voluumList) {
                    key = voluum.getOfferId() + "_" + voluum.getTrafficSourceId() + "_" + voluum.getCountry() + "_" + DateUtils.formatDateToString(date);
                    if (!oldTrafficSourceStatisticsMap.containsKey(key)) {
                        traffic = new TrafficSourceStatistics();
                        traffic.setCountry(voluum.getCountry());
                        traffic.setOfferId(voluum.getOfferId());
                        traffic.setOfferName(voluum.getOfferName());
                        traffic.setOfferNameAlias(voluum.getOfferNameAlias());
                        traffic.setTrafficSourceId(voluum.getTrafficSourceId());
                        traffic.setTrafficSourceName(voluum.getTrafficSourceName());
                        traffic.setConversNum(voluum.getConversNum());
                        traffic.setDate(date);
                        traffic.setCreateDate(curDate);
                        addTrafficList.add(traffic);
                    } else {
                        traffic = oldTrafficSourceStatisticsMap.get(key);
                        traffic.setConversNum(voluum.getConversNum());
                        traffic.setModifyDate(curDate);
                        updateTrafficList.add(traffic);
                    }
                }
            } else {
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
                    traffic.setCreateDate(curDate);
                    addTrafficList.add(traffic);
                }
            }
            //保存渠道统计结果
            if (addTrafficList.size() > 0) {
                trafficSourceStatisticsMapper.insertTrafficSourceRateStatistics(addTrafficList);
            }

            if (updateTrafficList.size() > 0) {
                trafficSourceStatisticsMapper.updateTrafficSourceRateStatistics(updateTrafficList);
            }
            //保存渠道经过分配比例计算之后的统计结果
            /*if(trafficRateList.size()>0){
                trafficSourceStatisticsMapper.insertTrafficSourceRateStatistics(trafficRateList);
            }*/
        }
    }


    /**
     * 按照不同广告主统计数据
     *
     * @param startDate
     * @param endDate
     */
    public void accordAdvertisersStatistics(Date date,String startDate, String endDate, String country) {
        List<VoluumNotify> voluumList = voluumNotifyMapper.selectAdvertisersStatistics(startDate, endDate, country);
        List<AdvertisersStatistics> addAdvertList = new ArrayList<AdvertisersStatistics>();
        List<AdvertisersStatistics> updateAdvertList = new ArrayList<AdvertisersStatistics>();
        Map<String, AdvertisersStatistics> oldAdvertisersStatisticsMap = new HashMap<String, AdvertisersStatistics>();
        AdvertisersStatistics advert = null;
        if (voluumList != null && voluumList.size() > 0) {

            Date curDate = new Date();
            //根据时间、国家查询当天已存在的广告统计数据
            List<AdvertisersStatistics> oldTrafficStatisticsList = advertisersStatisticsMapper.selectOldAdvertisersStatistics(startDate, endDate, country);
            if (oldTrafficStatisticsList != null && oldTrafficStatisticsList.size() > 0) {
                for (AdvertisersStatistics oldTraffic : oldTrafficStatisticsList) {
                    oldAdvertisersStatisticsMap.put(oldTraffic.getOfferId() + "_" + oldTraffic.getAffiliateNetworkId() + "_" + oldTraffic.getCountry() + "_" + DateUtils.formatDateToString(oldTraffic.getDate()), oldTraffic);
                }
                String key = "";
                for (VoluumNotify voluum : voluumList) {
                    key = voluum.getOfferId() + "_" + voluum.getAffiliateNetworkId() + "_" + voluum.getCountry() + "_" + DateUtils.formatDateToString(date);
                    if(!oldAdvertisersStatisticsMap.containsKey(key)){
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
                        advert.setCreateDate(curDate);
                        addAdvertList.add(advert);
                    }else{
                        advert = oldAdvertisersStatisticsMap.get(key);
                        advert.setConversNum(voluum.getConversNum());
                        advert.setModifyDate(curDate);
                        updateAdvertList.add(advert);
                    }
                }
            } else {
                for (VoluumNotify voluum : voluumList) {
                    advert = new AdvertisersStatistics();
                    advert.setCountry(voluum.getCountry());
                    advert.setOfferId(voluum.getOfferId());
                    advert.setOfferName(voluum.getOfferName());
                    advert.setOfferNameAlias(voluum.getOfferNameAlias());
                    advert.setAffiliateNetworkId(voluum.getAffiliateNetworkId());
                    advert.setAffiliateNetworkName(voluum.getAffiliateNetworkName());
                    advert.setConversNum(voluum.getConversNum());
                    advert.setDate(date);
                    advert.setCreateDate(curDate);
                    addAdvertList.add(advert);
                }

            }
            if (addAdvertList.size() > 0) {
                advertisersStatisticsMapper.insertAdvertisersStatistics(addAdvertList);
            }
            if (updateAdvertList.size() > 0) {
                advertisersStatisticsMapper.updateAdvertisersStatistics(updateAdvertList);
            }

        }

    }
}