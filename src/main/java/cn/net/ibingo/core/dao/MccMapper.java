package cn.net.ibingo.core.dao;

import java.util.List;

import cn.net.ibingo.core.model.Mcc;
import cn.net.ibingo.core.query.MccQueryBean;

public interface MccMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Mcc record);

    int insertSelective(Mcc record);

    Mcc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Mcc record);

    int updateByPrimaryKey(Mcc record);
    
    int selectCountByQueryBean(MccQueryBean queryBean);
    
    List<Mcc> selectByQueryBean(MccQueryBean queryBean);
    
    List<Mcc> selectByMcc(Mcc record);
    
    List<Mcc> selectAll();

	List<String> selectAllName();
    
}