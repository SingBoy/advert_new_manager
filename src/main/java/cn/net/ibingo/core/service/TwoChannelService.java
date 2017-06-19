package cn.net.ibingo.core.service;

import java.util.List;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.core.model.TwoChannel;
import cn.net.ibingo.core.query.TwoChannelQueryBean;

public interface TwoChannelService {
	
	public PaginationList<TwoChannel> list(TwoChannelQueryBean queryBean);
	
	public TwoChannel get(Integer id);
	
	public boolean saveOrUpdate(TwoChannel twoChannel);

	public boolean delete(Integer id);

	public boolean selectByName(TwoChannel twoChannel);
	
	public List<TwoChannel> selectByPid(Integer pid);
	
}
