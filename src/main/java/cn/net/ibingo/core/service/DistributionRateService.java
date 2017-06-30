package cn.net.ibingo.core.service;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.core.model.Advertisers;
import cn.net.ibingo.core.model.DistributionRate;
import cn.net.ibingo.core.query.AdvertisersQueryBean;

import java.util.List;

public interface DistributionRateService {

	public List<DistributionRate> selectByTrafficeId(String trafficId);

	public Float selectByTrafficIdAndOfferId(String voluumTrafficSourceId,String voluumOfferId);

	public List<DistributionRate> selectAll();
	
}
