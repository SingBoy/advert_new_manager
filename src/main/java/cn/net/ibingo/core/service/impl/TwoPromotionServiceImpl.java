package cn.net.ibingo.core.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.pagination.model.SimplePaginatedList;
import cn.net.ibingo.common.utils.CodeUtils;
import cn.net.ibingo.core.dao.FristChannelMapper;
import cn.net.ibingo.core.dao.TwoChannelMapper;
import cn.net.ibingo.core.dao.TwoPromotionMapper;
import cn.net.ibingo.core.model.FristChannel;
import cn.net.ibingo.core.model.Param;
import cn.net.ibingo.core.model.TwoChannel;
import cn.net.ibingo.core.model.TwoPromotion;
import cn.net.ibingo.core.query.TwoPromotionQueryBean;
import cn.net.ibingo.core.service.TwoPromotionService;

@Service
public class TwoPromotionServiceImpl implements TwoPromotionService {
	
	@Autowired
	private TwoPromotionMapper twoPromotionMapper;
	
	@Autowired
	private TwoChannelMapper twoChannelMapper;
	
	@Autowired
	private FristChannelMapper fristChannelMapper;
	
	@Override
	public PaginationList<TwoPromotion> list(TwoPromotionQueryBean queryBean) {
		queryBean.setPageSize(50);
		Integer totalCount = twoPromotionMapper.selectCountByQueryBean(queryBean);
		List<TwoPromotion> list = twoPromotionMapper.selectByQueryBean(queryBean);
		SimplePaginatedList<TwoPromotion> pList = new SimplePaginatedList<TwoPromotion>(list, queryBean.getCurrentPage(), queryBean.getPageSize(), totalCount);
		return pList;
	}

	@Override
	public boolean save(Integer pid) {
		TwoPromotion twoPromotion = new TwoPromotion();
		twoPromotion.setPid(pid);
		String linkIdMax = twoPromotionMapper.selectMaxCode();
		if(linkIdMax == null){
			twoPromotion.setLinkId("0001");
		}else{
			twoPromotion.setLinkId(CodeUtils.getCode(linkIdMax));
		}
		twoPromotion.setCreateDate(new Date());
		twoPromotion.setModifyDate(new Date());
/*		//转json
		Param param = new Param();
		param.setTwoChannelId(pid);
		param.setLinkId(twoPromotion.getLinkId());
		//一级渠道ID
		TwoChannel twoChannel = twoChannelMapper.selectByPrimaryKey(pid);
		param.setFristChannelId(twoChannel.getPid());
		String url = JSON.toJSONString(param);
		twoPromotion.setPromotionUrl("http://u.nicegame.me/api/subscribe?params="+url);*/
		TwoChannel twoChannel = twoChannelMapper.selectByPrimaryKey(pid);
		FristChannel fristChannel = fristChannelMapper.selectByPrimaryKey(twoChannel.getPid());
		String promotionUrl = "http://u.nicegame.me/api/subscribe?c1="+fristChannel.getCode()+"&c2="+twoChannel.getCode()+"&type="+twoPromotion.getLinkId();
		twoPromotion.setPromotionUrl(promotionUrl);
		return twoPromotionMapper.insertSelective(twoPromotion) >0 ;
	}

	@Override
	public boolean delete(Integer id) {
		return twoPromotionMapper.deleteByPrimaryKey(id) > 0;
	}

}
