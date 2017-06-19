package cn.net.ibingo.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.net.ibingo.core.dao.ResourcesMccMapper;
import cn.net.ibingo.core.model.ResourcesMcc;
import cn.net.ibingo.core.service.ResourcesMccService;

@Service
public class ResourcesMccServiceImpl implements ResourcesMccService {
	
	@Autowired
	private ResourcesMccMapper resourcesMccMapper;

	@Override
	public int selectCount(ResourcesMcc record) {
		return resourcesMccMapper.selectCount(record);
	}
}
