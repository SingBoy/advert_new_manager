package cn.net.ibingo.core.dao;

import java.util.List;

import cn.net.ibingo.core.model.Advertisers;
import cn.net.ibingo.core.query.AdvertisersQueryBean;

public interface AdvertisersMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Advertisers record);

    int insertSelective(Advertisers record);

    Advertisers selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Advertisers record);

    int updateByPrimaryKey(Advertisers record);
    
    int selectCountByQueryBean(AdvertisersQueryBean queryBean);
    
    List<Advertisers> selectByQueryBean(AdvertisersQueryBean queryBean);
    
    List<Advertisers> selectByAdvertisers(Advertisers record);
    
    List<Advertisers> selectAll();
    
    int selectCount();
    
    String selectMaxCode();
    
    List<Advertisers> selectBean();

    String selectBeanByAffiliateNetworkId(String affiliateNetworkId);
}