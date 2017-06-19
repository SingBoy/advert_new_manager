package cn.net.ibingo.core.dao;

import java.util.List;
import cn.net.ibingo.core.model.FristChannel;
import cn.net.ibingo.core.query.FristChannelQueryBean;

public interface FristChannelMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(FristChannel record);

    int insertSelective(FristChannel record);

    FristChannel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FristChannel record);

    int updateByPrimaryKey(FristChannel record);
    
    int selectCountByQueryBean(FristChannelQueryBean queryBean);
    
    List<FristChannel> selectByQueryBean(FristChannelQueryBean queryBean);
    
    List<FristChannel> selectAll();
    
    String selectMaxCode();
    
    List<FristChannel> selectByName(FristChannel record);
    
    FristChannel selectByCode(String code);
    
    int selectCount();
    
    int updateVoluumTrafficSourceIdByCode(FristChannel fristChannel);

    FristChannel selectByTrafficSourceId(String trafficSourceId);

}