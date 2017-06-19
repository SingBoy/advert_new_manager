package cn.net.ibingo.core.dao;

import java.util.List;
import cn.net.ibingo.core.model.TimezoneCountry;
import cn.net.ibingo.core.query.TimezoneCountryQueryBean;

public interface TimezoneCountryMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(TimezoneCountry record);

    int insertSelective(TimezoneCountry record);

    TimezoneCountry selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TimezoneCountry record);

    int updateByPrimaryKey(TimezoneCountry record);
    
    List<TimezoneCountry> selectByISO(String countryIso);
    
    int selectCountByQueryBean(TimezoneCountryQueryBean queryBean);
    
    List<TimezoneCountry> selectByQueryBean(TimezoneCountryQueryBean queryBean);

    List<TimezoneCountry> queryCoutryList();
    
}