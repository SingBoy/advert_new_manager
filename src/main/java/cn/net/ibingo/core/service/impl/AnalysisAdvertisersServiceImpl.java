package cn.net.ibingo.core.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.pagination.model.SimplePaginatedList;
import cn.net.ibingo.core.dao.AnalysisAdvertisersMapper;
import cn.net.ibingo.core.model.AnalysisAdvertisers;
import cn.net.ibingo.core.query.AnalysisAdvertisersQueryBean;
import cn.net.ibingo.core.service.AnalysisAdvertisersService;

@Service
public class AnalysisAdvertisersServiceImpl implements AnalysisAdvertisersService {
	
	@Autowired
	private AnalysisAdvertisersMapper analysisAdvertisersMapper;

	@Override
	public PaginationList<AnalysisAdvertisers> list(AnalysisAdvertisersQueryBean queryBean) {
		Integer totalCount = analysisAdvertisersMapper.selectCountByQueryBean(queryBean);
		List<AnalysisAdvertisers> list = analysisAdvertisersMapper.selectByQueryBean(queryBean);
		SimplePaginatedList<AnalysisAdvertisers> pList = new SimplePaginatedList<AnalysisAdvertisers>(list, queryBean.getCurrentPage(), queryBean.getPageSize(), totalCount);
		return pList;
	}

	@Override
	public AnalysisAdvertisers get(Integer id) {
		return analysisAdvertisersMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean saveOrUpdate(AnalysisAdvertisers analysisAdvertisers) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AnalysisAdvertisers selectSum(AnalysisAdvertisers record) {
		return analysisAdvertisersMapper.selectSum(record);
	}
}
