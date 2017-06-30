package cn.net.ibingo.core.service.impl;

import cn.net.ibingo.core.dao.DistributionRateMapper;
import cn.net.ibingo.core.model.DistributionRate;
import cn.net.ibingo.core.service.DistributionRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//import cn.net.ibingo.core.dao.AnalysisAdvertisersMapper;

@Service
public class DistributionRateServiceImpl implements DistributionRateService {
	
	@Autowired
	private DistributionRateMapper distributionRateMapper;

	@Override
	public List<DistributionRate> selectByTrafficeId(String trafficId) {
		return distributionRateMapper.selectByTrafficeId(trafficId);
	}

	@Override
	public Float selectByTrafficIdAndOfferId(String voluumTrafficSourceId, String voluumOfferId) {
		return distributionRateMapper.selectByTrafficIdAndOfferId(voluumTrafficSourceId,voluumOfferId);
	}

	@Override
	public List<DistributionRate> selectAll() {
		return distributionRateMapper.selectAll();
	}
}
