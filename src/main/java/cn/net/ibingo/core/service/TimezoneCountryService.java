package cn.net.ibingo.core.service;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.core.model.TimezoneCountry;
import cn.net.ibingo.core.query.TimezoneCountryQueryBean;

public interface TimezoneCountryService {
	
	public PaginationList<TimezoneCountry> list(TimezoneCountryQueryBean queryBean);
	
	public TimezoneCountry get(Integer id);
	
	public boolean saveOrUpdate(TimezoneCountry timezoneCountry);

	public boolean delete(Integer id);
	
	public boolean selectByISO(TimezoneCountry timezoneCountry);
	
}
