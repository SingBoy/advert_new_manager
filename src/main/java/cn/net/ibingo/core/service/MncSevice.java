package cn.net.ibingo.core.service;

import java.util.List;
import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.core.model.Mnc;
import cn.net.ibingo.core.query.MncQueryBean;

public interface MncSevice {
	
	public PaginationList<Mnc> list(MncQueryBean queryBean);
	
	public List<Mnc> selectByPid(Integer pid);

	public List<Mnc> selectByPid2(Integer pid);

	public List<String> selectAllName(String countryName);
	
	public List<Mnc> selectByCountry(String country);
	
}
