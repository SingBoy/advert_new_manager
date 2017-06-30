package cn.net.ibingo.core.service;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.core.model.Advertisers;
import cn.net.ibingo.core.model.AdvertisersStatistics;
import cn.net.ibingo.core.query.AdvertisersQueryBean;
import cn.net.ibingo.core.query.AdvertisersStatisticsQueryBean;

import java.util.List;

public interface AdvertisersStatisticsService {
	
	public PaginationList<AdvertisersStatistics> list(AdvertisersStatisticsQueryBean queryBean);

}
