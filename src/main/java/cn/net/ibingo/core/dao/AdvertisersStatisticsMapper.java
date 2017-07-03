package cn.net.ibingo.core.dao;

import cn.net.ibingo.core.model.Advertisers;
import cn.net.ibingo.core.model.AdvertisersStatistics;
import cn.net.ibingo.core.model.TrafficSourceStatistics;
import cn.net.ibingo.core.query.AdvertisersQueryBean;
import cn.net.ibingo.core.query.AdvertisersStatisticsQueryBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdvertisersStatisticsMapper {

    List<Integer> selectCountByQueryBean(AdvertisersStatisticsQueryBean queryBean);

    List<AdvertisersStatistics> selectByQueryBean(AdvertisersStatisticsQueryBean queryBean);

    public List<AdvertisersStatistics> selectOldAdvertisersStatistics(@Param("startDate") String startDate, @Param("endDate")String endDate, @Param("country")String country);

    public int insertAdvertisersStatistics(List<AdvertisersStatistics> advertList);

    public int updateAdvertisersStatistics(List<AdvertisersStatistics> advertList);

}

