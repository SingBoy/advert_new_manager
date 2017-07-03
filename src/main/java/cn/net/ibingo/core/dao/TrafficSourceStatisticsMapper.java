package cn.net.ibingo.core.dao;

import cn.net.ibingo.core.model.Advertisers;
import cn.net.ibingo.core.model.OfferStatistics;
import cn.net.ibingo.core.model.TrafficSourceStatistics;
import cn.net.ibingo.core.query.AdvertisersQueryBean;
import cn.net.ibingo.core.query.TrafficSourceStatisticsQueryBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TrafficSourceStatisticsMapper {

    List<Integer> selectCountByQueryBean(TrafficSourceStatisticsQueryBean queryBean);

    List<TrafficSourceStatistics> selectByQueryBean(TrafficSourceStatisticsQueryBean queryBean);

    List<Integer> selectRateCountByQueryBean(TrafficSourceStatisticsQueryBean queryBean);

    List<TrafficSourceStatistics> selectRateByQueryBean(TrafficSourceStatisticsQueryBean queryBean);

    public List<TrafficSourceStatistics> selectOldTrafficSourceStatistics(@Param("startDate") String startDate, @Param("endDate")String endDate, @Param("country")String country);

    public int insertTrafficSourceStatistics(List<TrafficSourceStatistics> trafficList);

    public int updateTrafficSourceStatistics(List<TrafficSourceStatistics> trafficList);

    public int insertTrafficSourceRateStatistics(List<TrafficSourceStatistics> trafficRateList);
}