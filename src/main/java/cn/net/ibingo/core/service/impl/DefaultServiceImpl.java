package cn.net.ibingo.core.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.pagination.model.SimplePaginatedList;
import cn.net.ibingo.core.dao.DefaultMapper;
import cn.net.ibingo.core.model.Default;
import cn.net.ibingo.core.query.DefaultQueryBean;
import cn.net.ibingo.core.service.DefaultService;

@Service
public class DefaultServiceImpl implements DefaultService{
	
	@Autowired
	private DefaultMapper defaultMapper;

	@Override
	public PaginationList<Default> list(DefaultQueryBean queryBean) {
		queryBean.setPageSize(50);
		Integer totalCount = defaultMapper.selectCountByQueryBean(queryBean);
		List<Default> list = defaultMapper.selectByQueryBean(queryBean);
		SimplePaginatedList<Default> pList = new SimplePaginatedList<Default>(list, queryBean.getCurrentPage(), queryBean.getPageSize(), totalCount);
		return pList;
	}

	@Override
	public Default get(Integer id) {
		return defaultMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean saveOrUpdate(Default record) {
		if(record.getId() != null){
			record.setModifyDate(new Date());
			return defaultMapper.updateByPrimaryKeySelective(record) > 0;
		}else{
			record.setCreateDate(new Date());
			record.setModifyDate(new Date());
			return defaultMapper.insertSelective(record) > 0;
		}
	}

	@Override
	public boolean delete(Integer id) {
		return defaultMapper.deleteByPrimaryKey(id) > 0;
	}

	@Override
	public boolean enable(Integer id) {
		Default d = defaultMapper.selectByPrimaryKey(id);
		if(d.getStatus() == 1){
			d.setModifyDate(new Date());
			d.setStatus(0);
		}else{
			defaultMapper.updateStatus();
			d.setModifyDate(new Date());
			d.setStatus(1);
		}
		return defaultMapper.updateByPrimaryKeySelective(d) >0;
	}
	
}
