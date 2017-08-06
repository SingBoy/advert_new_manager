package cn.net.ibingo.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.net.ibingo.core.model.Resources;
import cn.net.ibingo.core.query.ResourcesQueryBean;

public interface ResourcesMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Resources record);

    int insertSelective(Resources record);

    Resources selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Resources record);

    int updateByPrimaryKey(Resources record);
    
    int selectCountByQueryBean(ResourcesQueryBean queryBean);
    
    List<Resources> selectByQueryBean(ResourcesQueryBean queryBean);
    
    List<Resources> selectByAdsId(Integer adsId);
    
    int deleteByAdsId(Integer adsId);
    
    List<Resources> selectByResources(Resources record);
    
    int selectCount();
    
    List<Resources> selectAll(@Param("adsId") Integer adsId);
    
    int deleteByMccId(Integer mccId);
    
    List<Resources> selectBean();
    
    Integer selectIdByVoluumIdBean(@Param("voluumOfferId") String voluumOfferId);

    Resources selectByOfferId(String offerId);

    int updateAliasName(Resources resources);

    int updateCallbackStatus(Resources resources);

}