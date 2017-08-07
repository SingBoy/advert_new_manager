package cn.net.ibingo.core.service;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.core.model.Default;
import cn.net.ibingo.core.model.HtmlLog;
import cn.net.ibingo.core.query.DefaultQueryBean;

public interface DefaultService {
	
	public PaginationList<Default> list(DefaultQueryBean queryBean);
	
	public Default get(Integer id);
	
	public boolean saveOrUpdate(Default record);

	public boolean delete(Integer id);
	
	public boolean enable(Integer id);
	
}
