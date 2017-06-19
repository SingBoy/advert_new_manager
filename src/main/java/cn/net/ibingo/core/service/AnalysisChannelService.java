package cn.net.ibingo.core.service;

import java.util.List;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.core.model.AnalysisChannel;
import cn.net.ibingo.core.query.AnalysisChannelQueryBean;

public interface AnalysisChannelService {
	
	public PaginationList<AnalysisChannel> list(AnalysisChannelQueryBean queryBean);
	
	public AnalysisChannel get(Integer id);
	
	public boolean saveOrUpdate(AnalysisChannel analysisChannel);

	public boolean delete(Integer id);
	
	public List<AnalysisChannel> selectLinkCode();
	
	public AnalysisChannel selectSum (AnalysisChannel record);
}
