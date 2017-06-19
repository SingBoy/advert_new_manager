package cn.net.ibingo.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.pagination.model.SimplePaginatedList;
import cn.net.ibingo.common.utils.CodeUtils;
import cn.net.ibingo.core.dao.AnalysisChannelMapper;
import cn.net.ibingo.core.dao.TwoChannelMapper;
import cn.net.ibingo.core.dao.TwoPromotionMapper;
import cn.net.ibingo.core.model.TwoChannel;
import cn.net.ibingo.core.query.TwoChannelQueryBean;
import cn.net.ibingo.core.service.TwoChannelService;

@Service
public class TwoChannelServiceImpl implements TwoChannelService {
	
	@Autowired
	private TwoChannelMapper twoChannelMapper;
	
	@Autowired
	private TwoPromotionMapper twoPromotionMapper;
	
	@Autowired
	private AnalysisChannelMapper analysisChannelMapper;

	@Override
	public PaginationList<TwoChannel> list(TwoChannelQueryBean queryBean) {
		queryBean.setPageSize(50);
		Integer totalCount = twoChannelMapper.selectCountByQueryBean(queryBean);
		List<TwoChannel> list = twoChannelMapper.selectByQueryBean(queryBean);
		SimplePaginatedList<TwoChannel> pList = new SimplePaginatedList<TwoChannel>(list, queryBean.getCurrentPage(), queryBean.getPageSize(), totalCount);
		return pList;
	}

	@Override
	public TwoChannel get(Integer id) {
		return twoChannelMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean saveOrUpdate(TwoChannel twoChannel) {
		if(twoChannel.getId() != null){
			twoChannel.setModifyDate(new Date());
			return twoChannelMapper.updateByPrimaryKeySelective(twoChannel) > 0;
		}else{
			String MaxCode = twoChannelMapper.selectMaxCode();
			twoChannel.setCreateDate(new Date());
			twoChannel.setModifyDate(new Date());
			if(MaxCode == null){
				twoChannel.setCode("0001");
			}else{
				twoChannel.setCode(CodeUtils.getCode(MaxCode));
			}
			return twoChannelMapper.insertSelective(twoChannel) > 0;
		}
	}

	@Override
	public boolean delete(Integer id) {
		twoPromotionMapper.deleteByPid(id);
		TwoChannel twoChannel = twoChannelMapper.selectByPrimaryKey(id);
		analysisChannelMapper.deleteByTwoCode(twoChannel.getCode());
		return twoChannelMapper.deleteByPrimaryKey(id) > 0;
	}

	@Override
	public boolean selectByName(TwoChannel twoChannel) {
		List<TwoChannel> list = new ArrayList<TwoChannel>();
		if(twoChannel.getId() != null){
			TwoChannel record = twoChannelMapper.selectByPrimaryKey(twoChannel.getId());
			if(twoChannel.getName().equals(record.getName())){
				return true;
			}else{
				list = twoChannelMapper.selectByName(twoChannel);
			}
		}else{
			list = twoChannelMapper.selectByName(twoChannel);
		}
		if(list.size() == 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<TwoChannel> selectByPid(Integer pid) {
		return twoChannelMapper.selectByPid(pid);
	}
	
}
