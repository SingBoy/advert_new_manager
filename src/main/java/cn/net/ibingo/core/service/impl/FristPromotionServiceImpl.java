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
import cn.net.ibingo.core.dao.FristPromotionMapper;
import cn.net.ibingo.core.model.FristChannel;
import cn.net.ibingo.core.model.FristPromotion;
import cn.net.ibingo.core.model.Param;
import cn.net.ibingo.core.query.FristPromotionQueryBean;
import cn.net.ibingo.core.service.FristChannelService;
import cn.net.ibingo.core.service.FristPromotionService;

@Service
public class FristPromotionServiceImpl implements FristPromotionService {

	@Autowired
	private FristPromotionMapper fristPromotionMapper;
	
	@Autowired
	private FristChannelMapper fristChannelMapper;
	
	@Override
	public PaginationList<FristPromotion> list(FristPromotionQueryBean queryBean) {
		queryBean.setPageSize(50);
		Integer totalCount = fristPromotionMapper.selectCountByQueryBean(queryBean);
		List<FristPromotion> list = fristPromotionMapper.selectByQueryBean(queryBean);
		SimplePaginatedList<FristPromotion> pList = new SimplePaginatedList<FristPromotion>(list, queryBean.getCurrentPage(), queryBean.getPageSize(), totalCount);
		return pList;
	}

	@Override
	public boolean save(Integer pid) {
		FristPromotion fristPromotion = new FristPromotion();
		fristPromotion.setPid(pid);
		String linkIdMax = fristPromotionMapper.selectMaxCode();
		if(linkIdMax == null){
			fristPromotion.setLinkId("0001");
		}else{
			fristPromotion.setLinkId(CodeUtils.getCode(linkIdMax));
		}
		fristPromotion.setCreateDate(new Date());
		fristPromotion.setModifyDate(new Date());
		//转json
/*		Param param = new Param();
		param.setFristChannelId(pid);
		param.setLinkId(fristPromotion.getLinkId());
		String url = JSON.toJSONString(param);
		fristPromotion.setPromotionUrl("http://u.nicegame.me/api/subscribe?params="+url);*/
		FristChannel fristChannel = fristChannelMapper.selectByPrimaryKey(pid);
		String promotionUrl = "http://u.nicegame.me/api/subscribe?c1="+fristChannel.getCode()+"&c2=0000&type="+fristPromotion.getLinkId();
		fristPromotion.setPromotionUrl(promotionUrl);
		return fristPromotionMapper.insertSelective(fristPromotion) >0 ;
	}

	@Override
	public boolean delete(Integer id) {
		return fristPromotionMapper.deleteByPrimaryKey(id) > 0;
	}

}
