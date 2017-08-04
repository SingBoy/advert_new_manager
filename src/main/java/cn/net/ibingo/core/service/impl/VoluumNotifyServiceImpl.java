package cn.net.ibingo.core.service.impl;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.pagination.model.SimplePaginatedList;
import cn.net.ibingo.core.dao.TimezoneCountryMapper;
import cn.net.ibingo.core.dao.VoluumNotifyMapper;
import cn.net.ibingo.core.model.TimezoneCountry;
import cn.net.ibingo.core.model.VoluumNotify;
import cn.net.ibingo.core.query.VoluumNotifyQueryBean;
import cn.net.ibingo.core.service.VoluumNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class VoluumNotifyServiceImpl implements VoluumNotifyService {

	@Autowired
	public VoluumNotifyMapper voluumNotifyMapper;

	@Autowired
	public TimezoneCountryMapper timezoneCountryMapper;

	@Override
	public int insertNotify(VoluumNotify voluumNotify) {
		return voluumNotifyMapper.insertSelective(voluumNotify);
	}

	@Override
	public PaginationList<VoluumNotify> list(VoluumNotifyQueryBean queryBean) {
		queryBean.setPageSize(50);
		Integer totalCount = voluumNotifyMapper.selectCountByQueryBean(queryBean);
		List<VoluumNotify> list = voluumNotifyMapper.selectByQueryBean(queryBean);
		SimplePaginatedList<VoluumNotify> pList = new SimplePaginatedList<VoluumNotify>(list, queryBean.getCurrentPage(), queryBean.getPageSize(), totalCount);
		return pList;
	}

	@Override
	public int updateDataType(String clickId) {
		return voluumNotifyMapper.updateDataType(clickId);
	}

	@Override
	public int delete(int id) {
		return voluumNotifyMapper.delete(id);
	}

	@Override
	public boolean selectCountByClickId(String clickId) {
		int count = voluumNotifyMapper.selectCountByClickId(clickId);
		if(count>0){
			return false;
		}
		return true;
	}
}
