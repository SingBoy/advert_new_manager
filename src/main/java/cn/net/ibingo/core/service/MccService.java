package cn.net.ibingo.core.service;

import java.util.List;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.core.model.Mcc;
import cn.net.ibingo.core.query.MccQueryBean;

public interface MccService {

	public PaginationList<Mcc> list(MccQueryBean queryBean);
	
	public Mcc get(Integer id);
	
	public boolean saveOrUpdate(Mcc mcc);

	public boolean delete(Integer id);
	
	public Boolean selectByMcc(Mcc mcc);
	
	public List<Mcc> selectAll();

	public List<Mcc> selectAllName();
	
}
