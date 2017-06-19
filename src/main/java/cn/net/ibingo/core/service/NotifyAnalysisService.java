package cn.net.ibingo.core.service;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.core.model.NotifyAnalysis;
import cn.net.ibingo.core.query.NotifyAnalysisQueryBean;

public interface NotifyAnalysisService {
	
	public PaginationList<NotifyAnalysis> list(NotifyAnalysisQueryBean queryBean);
	
	public NotifyAnalysis selectSum(NotifyAnalysis notifyAnalysis);
	
}
