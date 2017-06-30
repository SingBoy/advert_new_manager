package cn.net.ibingo.core.service;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.core.model.AdvertisersStatistics;
import cn.net.ibingo.core.model.TrafficSourceStatistics;
import cn.net.ibingo.core.query.AdvertisersStatisticsQueryBean;
import cn.net.ibingo.core.query.TrafficSourceStatisticsQueryBean;

public interface TrafficSourceStatisticsService {
	
	public PaginationList<TrafficSourceStatistics> list(TrafficSourceStatisticsQueryBean queryBean);

	public PaginationList<TrafficSourceStatistics> rateList(TrafficSourceStatisticsQueryBean queryBean);

}
