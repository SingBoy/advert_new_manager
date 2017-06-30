package cn.net.ibingo.core.service;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.core.model.AdvertisersStatistics;
import cn.net.ibingo.core.model.OfferStatistics;
import cn.net.ibingo.core.query.AdvertisersStatisticsQueryBean;
import cn.net.ibingo.core.query.OfferStatisticsQueryBean;

public interface OfferStatisticsService {
	
	public PaginationList<OfferStatistics> list(OfferStatisticsQueryBean queryBean);

}
