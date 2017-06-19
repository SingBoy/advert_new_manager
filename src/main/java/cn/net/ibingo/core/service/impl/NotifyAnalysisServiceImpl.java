package cn.net.ibingo.core.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.pagination.model.SimplePaginatedList;
import cn.net.ibingo.core.dao.NotifyAnalysisMapper;
import cn.net.ibingo.core.model.NotifyAnalysis;
import cn.net.ibingo.core.query.NotifyAnalysisQueryBean;
import cn.net.ibingo.core.service.NotifyAnalysisService;

@Service
public class NotifyAnalysisServiceImpl implements NotifyAnalysisService {
	
	@Autowired
	private NotifyAnalysisMapper analysisMapper;

	@Override
	public PaginationList<NotifyAnalysis> list(NotifyAnalysisQueryBean queryBean) {
		queryBean.setPageSize(50);
		Integer totalCount = analysisMapper.selectCountByQueryBean(queryBean);
		List<NotifyAnalysis> list = analysisMapper.selectByQueryBean(queryBean);
		SimplePaginatedList<NotifyAnalysis> pList = new SimplePaginatedList<NotifyAnalysis>(list, queryBean.getCurrentPage(), queryBean.getPageSize(), totalCount);
		return pList;
	}

	@Override
	public NotifyAnalysis selectSum(NotifyAnalysis notifyAnalysis) {
		return analysisMapper.selectSum(notifyAnalysis);
	}
	
}
