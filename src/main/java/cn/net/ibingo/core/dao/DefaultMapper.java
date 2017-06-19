package cn.net.ibingo.core.dao;

import java.util.List;
import cn.net.ibingo.core.model.Default;
import cn.net.ibingo.core.query.DefaultQueryBean;

public interface DefaultMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Default record);

    int insertSelective(Default record);

    Default selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Default record);

    int updateByPrimaryKey(Default record);
    
    int selectCountByQueryBean(DefaultQueryBean queryBean);
    
    List<Default> selectByQueryBean(DefaultQueryBean queryBean);
    
    int updateStatus();
    
}