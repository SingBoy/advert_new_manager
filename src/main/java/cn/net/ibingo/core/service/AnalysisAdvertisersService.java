package cn.net.ibingo.core.service;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.core.model.AnalysisAdvertisers;
import cn.net.ibingo.core.query.AnalysisAdvertisersQueryBean;

public interface AnalysisAdvertisersService {
	
	public PaginationList<AnalysisAdvertisers> list(AnalysisAdvertisersQueryBean queryBean);
	
	public AnalysisAdvertisers get(Integer id);
	
	public boolean saveOrUpdate(AnalysisAdvertisers analysisAdvertisers);

	public boolean delete(Integer id);
	
	public AnalysisAdvertisers selectSum(AnalysisAdvertisers record);
}
