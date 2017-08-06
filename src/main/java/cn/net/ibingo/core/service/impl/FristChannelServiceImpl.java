package cn.net.ibingo.core.service.impl;

import java.util.*;

import cn.net.ibingo.core.dao.DistributionRateMapper;
import cn.net.ibingo.core.dao.ResourcesMapper;
import cn.net.ibingo.core.model.DistributionRate;
import cn.net.ibingo.core.model.Resources;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.common.pagination.model.SimplePaginatedList;
import cn.net.ibingo.common.utils.CodeUtils;
//import cn.net.ibingo.core.dao.AnalysisChannelMapper;
import cn.net.ibingo.core.dao.FristChannelMapper;
import cn.net.ibingo.core.dao.FristPromotionMapper;
import cn.net.ibingo.core.model.FristChannel;
import cn.net.ibingo.core.query.FristChannelQueryBean;
import cn.net.ibingo.core.redis.FristChannelRedis;
import cn.net.ibingo.core.service.FristChannelService;
import cn.net.ibingo.core.service.VoluumTrafficSourceService;
import freemarker.template.utility.StringUtil;

@Service
public class FristChannelServiceImpl implements FristChannelService {
	
	@Autowired
	private FristChannelMapper fristChannelMapper;
	
	@Autowired
	private FristPromotionMapper fristPromotionMapper;

	@Autowired
	private ResourcesMapper resourcesMapper;
	
	//@Autowired
	//private AnalysisChannelMapper analysisChannelMapper;
	
	@Autowired
	private FristChannelRedis fristChannelRedis;
	
	@Autowired
	private VoluumTrafficSourceService voluumTrafficSourceService;

    @Autowired
    private DistributionRateMapper distributionRateMapper;

	@Override
	public PaginationList<FristChannel> list(FristChannelQueryBean queryBean) {
		queryBean.setPageSize(50);
		Integer totalCount = fristChannelMapper.selectCountByQueryBean(queryBean);
		List<FristChannel> list = fristChannelMapper.selectByQueryBean(queryBean);
		SimplePaginatedList<FristChannel> pList = new SimplePaginatedList<FristChannel>(list, queryBean.getCurrentPage(), queryBean.getPageSize(), totalCount);
		return pList;
	}

	@Override
	public FristChannel get(Integer id) {
		return fristChannelMapper.selectByPrimaryKey(id);
	}

	@Override
	public void saveOrUpdate(FristChannel fristChannel) {
		if(fristChannel.getId() != null){
			FristChannel record = fristChannelMapper.selectByPrimaryKey(fristChannel.getId());
			fristChannel.setModifyDate(new Date());
			fristChannel.setCode(record.getCode());
			fristChannelMapper.updateByPrimaryKeySelective(fristChannel);


			//更新Voluum上该渠道的信息
			FristChannel fristChannel1 = get(fristChannel.getId());
			if(!StringUtils.isEmpty(fristChannel1.getVoluumTrafficSourceId())){
				voluumTrafficSourceService.updateTrafficSource(fristChannel1);
			}

		}else{
			String MaxCode = fristChannelMapper.selectMaxCode();
			fristChannel.setCreateDate(new Date());
			fristChannel.setModifyDate(new Date());
			if(MaxCode == null){
				fristChannel.setCode("0001");
			}else{
				fristChannel.setCode(CodeUtils.getCode(MaxCode));
			}
			fristChannelMapper.insertSelective(fristChannel);
			//将渠道数据上传到Voluum平台
			String voluumTrafficSourceId = voluumTrafficSourceService.addTrafficSource(fristChannel);
			if(!StringUtils.isEmpty(voluumTrafficSourceId)){
				fristChannel.setVoluumTrafficSourceId(voluumTrafficSourceId);
				//将Voluum平台的渠道数据的id回绑定到系统中
				fristChannelMapper.updateVoluumTrafficSourceIdByCode(fristChannel);
				setAllOfferRate(voluumTrafficSourceId);
			}
			
		}
		//fristChannelRedis.delete(fristChannel.getCode());
		//fristChannelRedis.add(fristChannel);
	}

	@Override
	public boolean delete(Integer id) {
		fristPromotionMapper.deleteByPid(id);
		FristChannel fristChannel = fristChannelMapper.selectByPrimaryKey(id);
		//analysisChannelMapper.deleteByFristCode(fristChannel.getCode());
		//fristChannelRedis.delete(fristChannel.getCode());
		//删除Voluum平台上的渠道信息
		if(!StringUtils.isEmpty(fristChannel.getVoluumTrafficSourceId())){
			voluumTrafficSourceService.deleteTracfficSource(fristChannel.getVoluumTrafficSourceId());
		}
		return fristChannelMapper.deleteByPrimaryKey(id) > 0;
	}

	@Override
	public List<FristChannel> selectAll() {
		return fristChannelMapper.selectAll();
	}

	@Override
	public boolean selectByName(FristChannel fristChannel) {
		List<FristChannel> list = new ArrayList<FristChannel>();
		if(fristChannel.getId() != null){
			FristChannel record = fristChannelMapper.selectByPrimaryKey(fristChannel.getId());
			if(fristChannel.getName().equals(record.getName())){
				return true;
			}else{
				list = fristChannelMapper.selectByName(fristChannel);
			}
		}else{
			list = fristChannelMapper.selectByName(fristChannel);
		}
		if(list.size() == 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public FristChannel selectByCode(String code) {
		return fristChannelMapper.selectByCode(code);
	}

	@Override
	public int selectCount() {
		return fristChannelMapper.selectCount();
	}

	@Override
	public FristChannel selectByTrafficSourceId(String trafficSourceId) {
		return fristChannelMapper.selectByTrafficSourceId(trafficSourceId);
	}
	/**
	 * 保存渠道和广告之间的分配比例
	 * @param distribution
	 * @param trafficId
	 * @param
	 */
	@Override
	public void insertChannelAndResour(String distribution,String trafficId){
		//单渠道更新时，先删除渠道和广告对应的比例。然后重新添加
		distributionRateMapper.deleteDistributionByTrafficId(trafficId);

		Map<Integer,Resources> resourcesMap = new HashMap<>();
		//获取所有的广告
		List<Resources> resourcesList = resourcesMapper.selectAll(null);
		if(resourcesList != null && resourcesList.size()>0){
			for (Resources resources :resourcesList){
				resourcesMap.put(resources.getId(),resources);
			}
		}
		//拆分成数组
		String [] strDisArray = distribution.split("&");

		List<DistributionRate> distributionRateList = new ArrayList<DistributionRate>();
		if(strDisArray !=null && strDisArray.length>0){
			DistributionRate dr = null;
			for(int i = 0;i<strDisArray.length;i++){
				if(!StringUtils.isEmpty(strDisArray[i])){
					JSONObject obj = new JSONObject().fromObject(strDisArray[i]);//将json字符串转换为json对象
					Map map = (Map)obj;
					//将json对象转换为java对象
					dr = new DistributionRate();
					Integer id = Integer.valueOf(map.get("id")!=null?map.get("id").toString():"");
					dr.setVoluumOfferId(resourcesMap.get(id).getVoluumOfferId());
					dr.setSubscriptionRate(Float.valueOf(map.get("rate").toString()));
					//DistributionRate dr = (DistributionRate)JSONObject.toBean(obj,DistributionRate.class);
					dr.setVoluumTrafficSourceId(trafficId);
					distributionRateList.add(dr);
				}
			}
			if(distributionRateList.size()>0){
				distributionRateMapper.insertBatchDistribution(distributionRateList);
			}
		}
	}

	/**
	 * 同步渠道时默认设置与广告的分配比例为1dd
	 * @param trafficId
	 */
	public void setAllOfferRate(String trafficId){
		List<Resources> listResources = resourcesMapper.selectAll(null);//.selectAll(null);
		List<DistributionRate> distributionRateList = new ArrayList<DistributionRate>();
		if(listResources != null && listResources.size()>0){
			DistributionRate dr = null;
			for (Resources resources :listResources){
				dr = new DistributionRate();
				dr.setVoluumTrafficSourceId(trafficId);
				dr.setVoluumOfferId(resources.getVoluumOfferId());
				dr.setSubscriptionRate(1F);
				distributionRateList.add(dr);
			}
			if(distributionRateList.size()>0){
				distributionRateMapper.insertBatchDistribution(distributionRateList);
			}
		}
	}
}
