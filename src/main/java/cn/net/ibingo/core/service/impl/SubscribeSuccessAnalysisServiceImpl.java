package cn.net.ibingo.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.net.ibingo.core.dao.SubscribeSuccessAnalysisMapper;
import cn.net.ibingo.core.model.SubscribeSuccessAnalysis;
import cn.net.ibingo.core.service.SubscribeSuccessAnalysisService;

@Service
public class SubscribeSuccessAnalysisServiceImpl implements SubscribeSuccessAnalysisService {

	@Autowired
	private SubscribeSuccessAnalysisMapper subscribeSuccessAnalysisMapper;
	
	@Override
	public SubscribeSuccessAnalysis selectSum(SubscribeSuccessAnalysis subscribeSuccessAnalysis) {
		// TODO Auto-generated method stub
		return subscribeSuccessAnalysisMapper.selectSum(subscribeSuccessAnalysis);
	}

}
