package cn.net.ibingo.core.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.net.ibingo.core.model.Mnc;
import cn.net.ibingo.core.query.MncQueryBean;

public interface MncMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Mnc record);

    int insertSelective(Mnc record);

    Mnc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Mnc record);

    int updateByPrimaryKey(Mnc record);
    
    List<Mnc> selectByPid(Integer pid);
    
    int deleteByPid(Integer pid);
    
    List<Mnc> selectByMnc(Mnc record);

	List<String> selectNameByCountryName(@Param("countryName") String countryName);
	
	List<Mnc> selectByCountry(String country);

	List<Mnc> selectByPid2(Integer pid);
	
    int selectCountByQueryBean(MncQueryBean queryBean);
    
    List<Mnc> selectByQueryBean(MncQueryBean queryBean);
	
}