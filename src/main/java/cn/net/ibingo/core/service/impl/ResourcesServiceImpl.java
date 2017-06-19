package cn.net.ibingo.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.pagination.model.SimplePaginatedList;
import cn.net.ibingo.core.dao.AdvertisersMapper;
import cn.net.ibingo.core.dao.AnalysisAdvertisersMapper;
import cn.net.ibingo.core.dao.MccMapper;
import cn.net.ibingo.core.dao.MncMapper;
import cn.net.ibingo.core.dao.ResourcesMapper;
import cn.net.ibingo.core.dao.ResourcesMccMapper;
import cn.net.ibingo.core.dao.ResourcesMncMapper;
import cn.net.ibingo.core.model.Mcc;
import cn.net.ibingo.core.model.Mnc;
import cn.net.ibingo.core.model.Resources;
import cn.net.ibingo.core.model.ResourcesMcc;
import cn.net.ibingo.core.model.ResourcesMnc;
import cn.net.ibingo.core.query.ResourcesQueryBean;
import cn.net.ibingo.core.redis.ResourcesRedis;
import cn.net.ibingo.core.service.ResourcesService;

@Service
public class ResourcesServiceImpl implements ResourcesService {
	
	@Autowired
	private ResourcesMapper resourcesMapper;
	
	@Autowired
	private AdvertisersMapper advertisersMapper;
	
	@Autowired
	private ResourcesMccMapper resourcesMccMapper;
	
	@Autowired
	private ResourcesMncMapper resourcesMncMapper;
	
	@Autowired
	private AnalysisAdvertisersMapper analysisAdvertisersMapper;

	@Autowired
	private MccMapper mccMapper;
	
	@Autowired
	private MncMapper mncMapper;
	
	@Autowired
	private ResourcesRedis resourcesRedis;
	
/*	@Override
	public PaginationList<Resources> list(ResourcesQueryBean queryBean) {
		queryBean.setPageSize(50);
		Integer totalCount = resourcesMapper.selectCountByQueryBean(queryBean);
		List<Resources> list = resourcesMapper.selectByQueryBean(queryBean);
		List<Resources> listResources = new ArrayList<Resources>();
		if(list != null){
			for (Resources resources : list) {
				Advertisers advertisers = advertisersMapper.selectByPrimaryKey(resources.getAdsId());
				resources.setAdsName(advertisers.getName());
				List<ResourcesMcc> listResourcesMcc = resourcesMccMapper.selectByPid(resources.getId());
				StringBuffer mccGroup = new StringBuffer();
				StringBuffer mncGroup = new StringBuffer();
				if(listResourcesMcc != null){
					for (ResourcesMcc resourcesMcc : listResourcesMcc) {
						if(!mccGroup.toString().contains(resourcesMcc.getCountry())){
							mccGroup.append(resourcesMcc.getCountry()+",");
						}
						if(resourcesMcc.getMccId() != null){
							List<ResourcesMnc> listResourcesMnc = resourcesMncMapper.selectByPid(resourcesMcc.getId());
							if(listResourcesMnc != null){
								for (ResourcesMnc resourcesMnc : listResourcesMnc) {
									if(!mncGroup.toString().contains(resourcesMnc.getOperator())){
										mncGroup.append(resourcesMnc.getOperator()+",");
									}
								}
							}
						}
					}
				}
				if(mccGroup.length() > 0){
					resources.setMccGroup(mccGroup.substring(0, mccGroup.length()-1));
				}
				if(mncGroup.length() > 0){
					resources.setMncGroup(mncGroup.substring(0, mncGroup.length()-1));
				}
				listResources.add(resources);
			}
		}
		SimplePaginatedList<Resources> pList = new SimplePaginatedList<Resources>(listResources, queryBean.getCurrentPage(), queryBean.getPageSize(), totalCount);
		return pList;
	}*/

	
	@Override
	public PaginationList<Resources> list(ResourcesQueryBean queryBean) {
		queryBean.setPageSize(50);
		Integer totalCount = resourcesMapper.selectCountByQueryBean(queryBean);
		List<Resources> list = resourcesMapper.selectByQueryBean(queryBean);
		SimplePaginatedList<Resources> pList = new SimplePaginatedList<Resources>(list, queryBean.getCurrentPage(), queryBean.getPageSize(), totalCount);
		return pList;
	}
	
	@Override
	public Resources get(Integer id) {
		Resources resources = resourcesMapper.selectByPrimaryKey(id);
		List<ResourcesMnc> list = resourcesMncMapper.selectByResourceId(id);
		//String mncGroup = "";
		String mccGroup = "";
		if(list != null) {
			for (ResourcesMnc resourcesMnc : list) {
				//${mcc.country}_${mnc.operator}
				//mncGroup = mncGroup + resourcesMnc.getOperator()+",";
				if(!mccGroup.contains(resourcesMnc.getCountry()+"_"+resourcesMnc.getOperator())){
					mccGroup = mccGroup + resourcesMnc.getCountry()+"_"+resourcesMnc.getOperator() + ",";
				}
			}
		}
		//resources.setMncGroup(mncGroup);
		resources.setMccGroup(mccGroup);
		return resources;
	}

	@Override
	public void saveOrUpdate(Resources resources) {
		//参数为空设置默认
		//资源修改和删除
		if(resources.getIsSupportParam() == null){
			resources.setIsSupportParam(1);
		}
		resources.setModifyDate(new Date());
		if(resources.getId() != null){
			resourcesMapper.updateByPrimaryKeySelective(resources);
			//删除资源运营商
			resourcesMncMapper.deleteByResourceId(resources.getId());
			//删除资源国家
			resourcesMccMapper.deleteByPid(resources.getId());
			//resourcesRedis.delete(resources.getId().toString());
		}else{
			resources.setCreateDate(new Date());
			resourcesMapper.insertSelective(resources);
		}
		//resourcesRedis.add(resources);
/*		int num = resources.getMccMnc().split(",").length;
		for (int i = 0; i < num; i++) {
			String mccMnc = resources.getMccMnc().split(",")[i];
			
			Mcc mcc = new Mcc();
			mcc.setCountry(mccMnc.split("_")[0]);
			List<Mcc> listMc = mccMapper.selectByMcc(mcc);
			for (Mcc mc : listMc) {
				//插入国家
				ResourcesMcc resourcesMcc = new ResourcesMcc();
				resourcesMcc.setPid(resources.getId());
				resourcesMcc.setMccId(mc.getId());
				resourcesMccMapper.insertSelective(resourcesMcc);
				//运营商
				Mnc mnc = new Mnc();
				mnc.setPid(mc.getId());
				mnc.setOperator(mccMnc.split("_")[1]);
				List<Mnc> listMn = mncMapper.selectByMnc(mnc);
				for (Mnc mn : listMn) {
					//资源运营商
					ResourcesMnc resourcesMnc = new ResourcesMnc();
					resourcesMnc.setPid(resourcesMcc.getId());
					resourcesMnc.setMncId(mn.getId());
					resourcesMncMapper.insertSelective(resourcesMnc);
				}
			}
		}*/
		
	}

	@Override
	public boolean delete(Integer id) {
		analysisAdvertisersMapper.deleteByResId(id);
		//删除资源运营商
		resourcesMncMapper.deleteByResourceId(id);
		//删除资源国家
		resourcesMccMapper.deleteByPid(id);
		//resourcesRedis.delete(id.toString());
		//删除资源
		return resourcesMapper.deleteByPrimaryKey(id) > 0;
	}

	@Override
	public List<Resources> selectByAdsId(Integer adsId) {
		return resourcesMapper.selectByAdsId(adsId);
	}

	@Override
	public boolean selectByResources(Resources resources) {
		List<Resources> list = new ArrayList<Resources>();
		if(resources.getId() != null){
			Resources record = resourcesMapper.selectByPrimaryKey(resources.getId());
			if(resources.getName().equals(record.getName())){
				return true;
			}else{
				list = resourcesMapper.selectByResources(resources);
			}
		}else{
			list = resourcesMapper.selectByResources(resources);
		}
		if(list.size() == 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void update(Resources resources) {
		resources.setModifyDate(new Date());
		resourcesMapper.updateByPrimaryKeySelective(resources);
	}

	@Override
	public int selectCount() {
		return resourcesMapper.selectCount();
	}

	@Override
	public List<Resources> selectAll(Integer adsId) {
		return resourcesMapper.selectAll(adsId);
	}

	@Override
	public Resources selectByOfferId(String offerId) {
		return resourcesMapper.selectByOfferId(offerId);
	}
}
