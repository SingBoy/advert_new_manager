package cn.net.ibingo.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.net.ibingo.core.dao.ResourcesMncMapper;
import cn.net.ibingo.core.model.ResourcesMnc;
import cn.net.ibingo.core.service.ResourcesMncService;

@Service
public class ResourcesMncServiceImpl implements ResourcesMncService {
	
	@Autowired
	private ResourcesMncMapper resourcesMncMapper;

	@Override
	public int selectCount(ResourcesMnc record) {
		return resourcesMncMapper.selectCount(record);
	}
}
