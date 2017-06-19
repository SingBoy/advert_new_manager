package cn.net.ibingo.core.service.impl;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.pagination.model.SimplePaginatedList;
import cn.net.ibingo.common.utils.CodeUtils;
import cn.net.ibingo.core.dao.AdvertisersMapper;
import cn.net.ibingo.core.dao.AnalysisAdvertisersMapper;
import cn.net.ibingo.core.dao.DistributionRateMapper;
import cn.net.ibingo.core.dao.ResourcesMapper;
import cn.net.ibingo.core.model.Advertisers;
import cn.net.ibingo.core.model.DistributionRate;
import cn.net.ibingo.core.query.AdvertisersQueryBean;
import cn.net.ibingo.core.service.AdvertisersService;
import cn.net.ibingo.core.service.DistributionRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DistributionRateServiceImpl implements DistributionRateService {
	
	@Autowired
	private DistributionRateMapper distributionRateMapper;

	@Override
	public List<DistributionRate> selectAll(String id) {
		return distributionRateMapper.selectByPrimaryKey(id);
	}

	@Override
	public Float selectByTrafficIdAndOfferId(String voluumTrafficSourceId, String voluumOfferId) {
		return distributionRateMapper.selectByTrafficIdAndOfferId(voluumTrafficSourceId,voluumOfferId);
	}
}
