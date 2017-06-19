package cn.net.ibingo.core.dao;

import java.util.List;

import cn.net.ibingo.core.model.ResourcesMnc;

public interface ResourcesMncMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(ResourcesMnc record);

    int insertSelective(ResourcesMnc record);

    ResourcesMnc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ResourcesMnc record);

    int updateByPrimaryKey(ResourcesMnc record);
    
    List<ResourcesMnc> selectByPid(Integer pid);
    
    int deleteByResourceId(Integer pid);
    
    List<ResourcesMnc> selectByResourceId(Integer pid);
    
    int deleteByMccId(Integer mccId);
    
    List<ResourcesMnc> selectByMccId(Integer mccId);
    
    int selectCount(ResourcesMnc record);
    
}