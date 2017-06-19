package cn.net.ibingo.core.service;

import java.util.List;
import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.core.model.Advertisers;
import cn.net.ibingo.core.query.AdvertisersQueryBean;

public interface AdvertisersService {
	
	public PaginationList<Advertisers> list(AdvertisersQueryBean queryBean);
	
	public Advertisers get(Integer id);
	
	public boolean saveOrUpdate(Advertisers advertisers);

	public boolean delete(Integer id);
	
	public boolean selectByAdvertisers(Advertisers advertisers);
	
	public List<Advertisers> selectAll();
	
	public int selectCount();

	public String selectBeanByAffiliateNetworkId(String affiliateNetworkId);
	
}
