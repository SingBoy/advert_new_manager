package cn.net.ibingo.core.dao;

import java.util.List;
import cn.net.ibingo.core.model.IpArea;
import cn.net.ibingo.core.query.IpAreaQueryBean;

public interface IpAreaMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(IpArea record);

    int insertSelective(IpArea record);

    IpArea selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IpArea record);

    int updateByPrimaryKey(IpArea record);
    
    int selectCountByQueryBean(IpAreaQueryBean queryBean);
    
    List<IpArea> selectByQueryBean(IpAreaQueryBean queryBean);
    
    List<IpArea> selectByIpArea(IpArea record);
    
    int updateByCountry(IpArea record);
    
    int updateByOperator(IpArea record);

}