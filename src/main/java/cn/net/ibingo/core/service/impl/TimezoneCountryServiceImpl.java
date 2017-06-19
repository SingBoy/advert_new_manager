package cn.net.ibingo.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.pagination.model.SimplePaginatedList;
import cn.net.ibingo.core.dao.MccMapper;
import cn.net.ibingo.core.dao.TimezoneCountryMapper;
import cn.net.ibingo.core.model.Mcc;
import cn.net.ibingo.core.model.TimezoneCountry;
import cn.net.ibingo.core.query.TimezoneCountryQueryBean;
import cn.net.ibingo.core.service.TimezoneCountryService;

@Service
public class TimezoneCountryServiceImpl implements TimezoneCountryService{

	@Autowired
	private TimezoneCountryMapper timezoneCountryMapper;
	
	@Autowired
	private MccMapper mccMapper;
	
	@Override
	public PaginationList<TimezoneCountry> list(TimezoneCountryQueryBean queryBean) {
		queryBean.setPageSize(50);
		Integer totalCount = timezoneCountryMapper.selectCountByQueryBean(queryBean);
		List<TimezoneCountry> list = timezoneCountryMapper.selectByQueryBean(queryBean);
		SimplePaginatedList<TimezoneCountry> pList = new SimplePaginatedList<TimezoneCountry>(list, queryBean.getCurrentPage(), queryBean.getPageSize(), totalCount);
		return pList;
	}

	@Override
	public TimezoneCountry get(Integer id) {
		return timezoneCountryMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean saveOrUpdate(TimezoneCountry timezoneCountry) {
		Mcc mcc = mccMapper.selectByPrimaryKey(timezoneCountry.getCountryId());
		timezoneCountry.setCountryZhCn(mcc.getCountry());
		timezoneCountry.setCountryIso(mcc.getIso());
		if(timezoneCountry.getId() != null){
			return timezoneCountryMapper.updateByPrimaryKeySelective(timezoneCountry) > 0;
		}else{
			return timezoneCountryMapper.insertSelective(timezoneCountry) > 0;
		}
	}

	@Override
	public boolean delete(Integer id) {
		return timezoneCountryMapper.deleteByPrimaryKey(id) > 0;
	}

	@Override
	public boolean selectByISO(TimezoneCountry timezoneCountry) {
		List<TimezoneCountry> list = new ArrayList<TimezoneCountry>();
		Mcc mcc = mccMapper.selectByPrimaryKey(timezoneCountry.getCountryId());
		if(timezoneCountry.getId() != null){
			TimezoneCountry t = timezoneCountryMapper.selectByPrimaryKey(timezoneCountry.getId());
			if(t.getCountryIso().equals(mcc.getIso())){
				return true;
			}else{
				list = timezoneCountryMapper.selectByISO(mcc.getIso());
			}
		}else{
			list = timezoneCountryMapper.selectByISO(mcc.getIso());
		}
		
		if(list.size() == 0){
			return true;
		}else{
			return false;
		}
	}
	
}
