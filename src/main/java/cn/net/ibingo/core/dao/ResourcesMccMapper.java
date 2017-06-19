package cn.net.ibingo.core.dao;

import java.util.List;

import cn.net.ibingo.core.model.ResourcesMcc;

public interface ResourcesMccMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(ResourcesMcc record);

    int insertSelective(ResourcesMcc record);

    ResourcesMcc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ResourcesMcc record);

    int updateByPrimaryKey(ResourcesMcc record);
    
    List<ResourcesMcc> selectByPid(Integer pid);
    
    int deleteByPid(Integer pid);
    
    int deleteByMccId(Integer mccId);
    
    List<ResourcesMcc> selectByResourcesMcc(ResourcesMcc record);
    
    int selectCount(ResourcesMcc record);
    
}