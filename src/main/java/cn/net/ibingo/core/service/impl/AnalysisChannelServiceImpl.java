package cn.net.ibingo.core.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.pagination.model.SimplePaginatedList;
import cn.net.ibingo.core.dao.AnalysisChannelMapper;
import cn.net.ibingo.core.model.AnalysisChannel;
import cn.net.ibingo.core.query.AnalysisChannelQueryBean;
import cn.net.ibingo.core.service.AnalysisChannelService;

@Service
public class AnalysisChannelServiceImpl implements AnalysisChannelService {
	
	@Autowired
	private AnalysisChannelMapper analysisChannelMapper;

	@Override
	public PaginationList<AnalysisChannel> list(AnalysisChannelQueryBean queryBean) {
		Integer totalCount = analysisChannelMapper.selectCountByQueryBean(queryBean);
		List<AnalysisChannel> list = analysisChannelMapper.selectByQueryBean(queryBean);
		SimplePaginatedList<AnalysisChannel> pList = new SimplePaginatedList<AnalysisChannel>(list, queryBean.getCurrentPage(), queryBean.getPageSize(), totalCount);
		return pList;
	}

	@Override
	public AnalysisChannel get(Integer id) {
		return analysisChannelMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean saveOrUpdate(AnalysisChannel analysisChannel) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<AnalysisChannel> selectLinkCode() {
		return analysisChannelMapper.selectLinkCode();
	}

	@Override
	public AnalysisChannel selectSum(AnalysisChannel record) {
		return analysisChannelMapper.selectSum(record);
	}
}
