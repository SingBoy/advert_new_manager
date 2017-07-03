package cn.net.ibingo.core.dao;

import cn.net.ibingo.core.model.AdvertisersStatistics;
import cn.net.ibingo.core.model.OfferStatistics;
import cn.net.ibingo.core.query.AdvertisersStatisticsQueryBean;
import cn.net.ibingo.core.query.OfferStatisticsQueryBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OfferStatisticsMapper {

    List<Integer> selectCountByQueryBean(OfferStatisticsQueryBean queryBean);

    List<OfferStatistics> selectByQueryBean(OfferStatisticsQueryBean queryBean);

    public int insertOfferStatistics(List<OfferStatistics> offerList);

    public int updateOfferStatistics(List<OfferStatistics> offerList);

    public List<OfferStatistics> selectOldOfferStatistics(@Param("startDate") String startDate, @Param("endDate")String endDate, @Param("country")String country);

}

