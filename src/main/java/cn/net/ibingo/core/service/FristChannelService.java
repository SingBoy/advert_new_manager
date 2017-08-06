package cn.net.ibingo.core.service;

import java.util.List;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.core.model.FristChannel;
import cn.net.ibingo.core.query.FristChannelQueryBean;

public interface FristChannelService {

	public PaginationList<FristChannel> list(FristChannelQueryBean queryBean);
	
	public FristChannel get(Integer id);
	
	public void saveOrUpdate(FristChannel fristChannel);

	public boolean delete(Integer id);
	
	public List<FristChannel> selectAll();
	
	public boolean selectByName(FristChannel record);
	
	public FristChannel selectByCode(String code);
	
	public int selectCount();

	public FristChannel selectByTrafficSourceId(String trafficSourceId);

	public void insertChannelAndResour(String distribution,String trafficId);
	
}
