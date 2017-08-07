package cn.net.ibingo.core.service.impl;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.core.dao.HtmlLogMapper;
import cn.net.ibingo.core.model.Default;
import cn.net.ibingo.core.model.HtmlLog;
import cn.net.ibingo.core.query.DefaultQueryBean;
import cn.net.ibingo.core.service.DefaultService;
import cn.net.ibingo.core.service.HtmlLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class HtmlLogServiceImpl implements HtmlLogService {

	@Autowired
	protected HtmlLogMapper htmlLogMapper;




	@Override
	public boolean saveOrUpdate(HtmlLog record) {
		record.setCreateDate(new Date());
		return htmlLogMapper.insertSelective(record) > 0;
	}



}
