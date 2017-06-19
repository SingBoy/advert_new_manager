package cn.net.ibingo.core.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.pagination.model.SimplePaginatedList;
import cn.net.ibingo.core.dao.NotifyAnalysisMapper;
import cn.net.ibingo.core.dao.NotifyChannelAnalysisMapper;
import cn.net.ibingo.core.model.NotifyChannelAnalysis;
import cn.net.ibingo.core.query.NotifyChannelAnalysisQueryBean;
import cn.net.ibingo.core.service.NotifyChannelAnalysisService;

@Service
public class NotifyChannelAnalysisServiceImpl implements  NotifyChannelAnalysisService{
	
	@Autowired
	private NotifyChannelAnalysisMapper channelMapper;
	
	@Autowired
	private NotifyAnalysisMapper analysisMapper;

	@Override
	public PaginationList<NotifyChannelAnalysis> list(NotifyChannelAnalysisQueryBean queryBean) {
		queryBean.setPageSize(50);
		Integer totalCount = channelMapper.selectCountByQueryBean(queryBean);
		List<NotifyChannelAnalysis> list = channelMapper.selectByQueryBean(queryBean);
		SimplePaginatedList<NotifyChannelAnalysis> pList = new SimplePaginatedList<NotifyChannelAnalysis>(list, queryBean.getCurrentPage(), queryBean.getPageSize(), totalCount);
		return pList;
	}

	@Override
	public PaginationList<NotifyChannelAnalysis> list1(NotifyChannelAnalysisQueryBean queryBean) {
			queryBean.setPageSize(50);
			Integer totalCount = channelMapper.selectCountByQueryBean1(queryBean);
			List<NotifyChannelAnalysis> list = channelMapper.selectByQueryBean1(queryBean);
			SimplePaginatedList<NotifyChannelAnalysis> pList = new SimplePaginatedList<NotifyChannelAnalysis>(list, queryBean.getCurrentPage(), queryBean.getPageSize(), totalCount);
			return pList;
	}

	@Override
	public PaginationList<NotifyChannelAnalysis> list2(NotifyChannelAnalysisQueryBean queryBean) {
		queryBean.setPageSize(50);
		Integer totalCount = channelMapper.selectCountByQueryBean2(queryBean);
		List<NotifyChannelAnalysis> list = channelMapper.selectByQueryBean2(queryBean);
		SimplePaginatedList<NotifyChannelAnalysis> pList = new SimplePaginatedList<NotifyChannelAnalysis>(list, queryBean.getCurrentPage(), queryBean.getPageSize(), totalCount);
		return pList;
	}
	
	@Override
	public int selectSum(NotifyChannelAnalysis notifyChannelAnalysis) {
		Integer nums = channelMapper.selectSum(notifyChannelAnalysis);
		if(nums == null){
			return 0 ;
		}else{
			return nums ;
		}
	}
	
}
