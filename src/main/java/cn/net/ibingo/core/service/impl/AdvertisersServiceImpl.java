package cn.net.ibingo.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.net.ibingo.core.dao.ResourcesMccMapper;
import cn.net.ibingo.core.model.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.pagination.model.SimplePaginatedList;
import cn.net.ibingo.common.utils.CodeUtils;
import cn.net.ibingo.core.dao.AdvertisersMapper;
import cn.net.ibingo.core.dao.AnalysisAdvertisersMapper;
import cn.net.ibingo.core.dao.ResourcesMapper;
import cn.net.ibingo.core.model.Advertisers;
import cn.net.ibingo.core.query.AdvertisersQueryBean;
import cn.net.ibingo.core.service.AdvertisersService;

@Service
public class AdvertisersServiceImpl implements AdvertisersService {
	
	@Autowired
	private AdvertisersMapper advertisersMapper;
	
	@Autowired
	private ResourcesMapper resourcesMapper;
	
	@Autowired
	private AnalysisAdvertisersMapper analysisAdvertisersMapper;

	@Autowired
	private ResourcesMccMapper resourcesMccMapper;

	@Override
	public PaginationList<Advertisers> list(AdvertisersQueryBean queryBean) {
		queryBean.setPageSize(50);
		Integer totalCount = advertisersMapper.selectCountByQueryBean(queryBean);
		List<Advertisers> list = advertisersMapper.selectByQueryBean(queryBean);
		SimplePaginatedList<Advertisers> pList = new SimplePaginatedList<Advertisers>(list, queryBean.getCurrentPage(), queryBean.getPageSize(), totalCount);
		return pList;
	}

	@Override
	public Advertisers get(Integer id) {
		return advertisersMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean saveOrUpdate(Advertisers advertisers) {
		if(advertisers.getId() != null){
			advertisers.setModifyDate(new Date());
			return advertisersMapper.updateByPrimaryKeySelective(advertisers) > 0;
		}else{
			String maxCode = advertisersMapper.selectMaxCode();
			if(maxCode == null){
				advertisers.setCode("0001");
			}else{
				advertisers.setCode(CodeUtils.getCode(maxCode));
			}
			advertisers.setCreateDate(new Date());
			advertisers.setModifyDate(new Date());
			return advertisersMapper.insertSelective(advertisers) > 0;
		}
	}

	@Override
	public boolean delete(Integer id) {
		analysisAdvertisersMapper.deleteByAdsId(id);
		List<Resources> resourcesList = resourcesMapper.selectByAdsId(id);
		if(resourcesList != null && resourcesList.size()>0){
			for(Resources re :resourcesList){
				resourcesMccMapper.deleteByPid(re.getId());
			}
		}
		resourcesMapper.deleteByAdsId(id);
		return advertisersMapper.deleteByPrimaryKey(id) > 0;
	}

	@Override
	public boolean selectByAdvertisers(Advertisers advertisers) {
		List<Advertisers> list = new ArrayList<Advertisers>();
		if(advertisers.getId() != null){
			Advertisers record = advertisersMapper.selectByPrimaryKey(advertisers.getId());
			if((advertisers.getName() != null && advertisers.getName().equals(record.getName())) 
					|| (advertisers.getCode() != null && advertisers.getCode().equals(record.getCode()))){
				return true;
			}else{
				list = advertisersMapper.selectByAdvertisers(advertisers);
			}
		}else{
			list = advertisersMapper.selectByAdvertisers(advertisers);
		}
		if(list.size() == 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<Advertisers> selectAll() {
		return advertisersMapper.selectAll();
	}

	@Override
	public int selectCount() {
		return advertisersMapper.selectCount();
	}

	@Override
	public String selectBeanByAffiliateNetworkId(String affiliateNetworkId) {
		return advertisersMapper.selectBeanByAffiliateNetworkId(affiliateNetworkId);
	}
}
