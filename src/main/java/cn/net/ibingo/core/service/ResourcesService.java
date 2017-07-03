package cn.net.ibingo.core.service;

import java.util.List;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.core.model.Resources;
import cn.net.ibingo.core.query.ResourcesQueryBean;

public interface ResourcesService {

	public PaginationList<Resources> list(ResourcesQueryBean queryBean);
	
	public Resources get(Integer id);
	
	public void saveOrUpdate(Resources resources);

	public boolean delete(Integer id);
	
	public List<Resources> selectByAdsId(Integer adsId);
	
	public boolean	selectByResources(Resources resources);
	
	public void update(Resources resources);
	
	public int selectCount();
	
	public List<Resources> selectAll(Integer adsId);

	public Resources selectByOfferId(String offerId);

	public int updateAliasName(Resources resources);

	public int updateCallbackStatus(Resources resources);
}
