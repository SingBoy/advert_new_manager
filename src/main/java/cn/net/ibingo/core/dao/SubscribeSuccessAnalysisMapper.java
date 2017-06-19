package cn.net.ibingo.core.dao;

import cn.net.ibingo.core.model.SubscribeSuccessAnalysis;

public interface SubscribeSuccessAnalysisMapper {
	
	SubscribeSuccessAnalysis selectSum(SubscribeSuccessAnalysis record);

	int insertSelective(SubscribeSuccessAnalysis record);
	
}