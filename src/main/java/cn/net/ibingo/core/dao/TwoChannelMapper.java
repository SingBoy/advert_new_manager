package cn.net.ibingo.core.dao;

import java.util.List;
import cn.net.ibingo.core.model.TwoChannel;
import cn.net.ibingo.core.query.TwoChannelQueryBean;

public interface TwoChannelMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(TwoChannel record);

    int insertSelective(TwoChannel record);

    TwoChannel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TwoChannel record);

    int updateByPrimaryKey(TwoChannel record);
    
    String selectMaxCode();
    
    int selectCountByQueryBean(TwoChannelQueryBean queryBean);
    
    List<TwoChannel> selectByQueryBean(TwoChannelQueryBean queryBean);
    
    List<TwoChannel> selectByName(TwoChannel record);
    
    List<TwoChannel> selectByPid(Integer pid);
}