package cn.net.ibingo.core.service;

import java.util.Map;

import cn.net.ibingo.core.model.FristChannel;

public interface VoluumTrafficSourceService {


	public String addTrafficSource(FristChannel fristChannel);
	
	public void updateTrafficSource(FristChannel fristChannel);
	
	public void deleteTracfficSource(String id);
	
	public Map<String,Object> createSessionByAccount();

}
