package cn.net.ibingo.core.service;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.core.model.NotifyChannelAnalysis;
import cn.net.ibingo.core.query.NotifyChannelAnalysisQueryBean;

public interface NotifyChannelAnalysisService {
	
	public PaginationList<NotifyChannelAnalysis> list(NotifyChannelAnalysisQueryBean queryBean);
	
	public PaginationList<NotifyChannelAnalysis> list1(NotifyChannelAnalysisQueryBean queryBean);
	
	public PaginationList<NotifyChannelAnalysis> list2(NotifyChannelAnalysisQueryBean queryBean);
	
	public int selectSum(NotifyChannelAnalysis notifyChannelAnalysis);
}
