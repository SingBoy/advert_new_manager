package cn.net.ibingo.core.dao;

import cn.net.ibingo.core.model.Advertisers;
import cn.net.ibingo.core.model.AdvertisersStatistics;
import cn.net.ibingo.core.query.AdvertisersQueryBean;
import cn.net.ibingo.core.query.AdvertisersStatisticsQueryBean;

import java.util.List;

public interface AdvertisersStatisticsMapper {

    List<Integer> selectCountByQueryBean(AdvertisersStatisticsQueryBean queryBean);

    List<AdvertisersStatistics> selectByQueryBean(AdvertisersStatisticsQueryBean queryBean);

    public int insertAdvertisersStatistics(List<AdvertisersStatistics> advertList);

}

