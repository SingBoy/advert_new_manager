package cn.net.ibingo.core.service;

import java.util.List;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.core.model.IpArea;
import cn.net.ibingo.core.query.IpAreaQueryBean;

public interface IpAreaService {
	
	public PaginationList<IpArea> list(IpAreaQueryBean queryBean);
	
	public IpArea get(Integer id);
	
	public void saveOrUpdate(IpArea ipArea);
	
	public void save(IpArea ipArea);

	public boolean delete(Integer id);
	
	public boolean selectByIpArea(IpArea ipArea);
	
	public int selectByCO(IpArea ipArea);
	
	public List<IpArea> selectByArea(IpArea ipArea);
	
}
