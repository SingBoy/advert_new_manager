package cn.net.ibingo.core.service.impl;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import cn.net.ibingo.common.utils.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.net.ibingo.core.dao.AdvertisersMapper;
import cn.net.ibingo.core.dao.ResourcesMapper;
import cn.net.ibingo.core.dao.ResourcesMccMapper;
import cn.net.ibingo.core.model.Advertisers;
import cn.net.ibingo.core.model.Mcc;
import cn.net.ibingo.core.model.Resources;
import cn.net.ibingo.core.model.ResourcesMcc;
import cn.net.ibingo.core.service.MccService;
import cn.net.ibingo.core.service.VoluumOfferService;
import cn.net.ibingo.core.service.VoluumTrafficSourceService;

@Service
public class VoluumOfferServiceImpl implements VoluumOfferService {

	private static Logger log = Logger.getLogger(String.valueOf(VoluumOfferServiceImpl.class));

	@Autowired
	private VoluumTrafficSourceService voluumTrafficSourceServiceImpl;
	
	@Autowired
	private ResourcesMapper resourcesMapper;
	
	@Autowired
	private AdvertisersMapper advertisersMapper;
	
	@Autowired
	private ResourcesMccMapper resourcesMccMapper;
	
	@Autowired
	private MccService mccService;
	
	private  HttpUtil httpUtil = new HttpUtil();

	/**
	 * 从Voluum平台获取所有offer，判断offer在系统中是否存在，如果存在则更新，否则就新增
	 */
	@Override
	public void getOffersAndInsert() {
		 Map<String,Resources> voluumOfferIdMap = new HashMap<String,Resources>();
		 //获取本系统中的所有广告
		 List<Resources> resourceList = resourcesMapper.selectBean();
		 if(resourceList !=null && resourceList.size() > 0){
			 //在所有广告的以<voluumofferid,广告>的结构存入map中
			 for(Resources resources : resourceList){
				 voluumOfferIdMap.put(resources.getVoluumOfferId(),resources);
			 }
		 }
		 
		//国家
		Map<String,Integer> mccCodeMap = new HashMap<String,Integer>();
		List<Mcc> listMcc = mccService.selectAll();
		 if(listMcc != null && listMcc.size() > 0){
			 for(Mcc mcc : listMcc){
				//在所有广告主的以<iso,id>的结构存入map中
				 mccCodeMap.put(mcc.getIso(), mcc.getId());
			 }
		 }

		 //广告主
		Map<String,Advertisers> voluumAdverIdMap = new HashMap<String,Advertisers>();
		List<Advertisers> adverList = advertisersMapper.selectBean();
		 if(adverList != null && adverList.size() > 0){
			 for(Advertisers advertisers : adverList){
				//在所有广告主的以<voluumAdverid,adver>的结构存入map中
				 voluumAdverIdMap.put(advertisers.getVoluumAffiliateNetworkId(), advertisers);
			 }
		 }
		 
		 //获取会话session
		 Map<String, Object> paramsToken = voluumTrafficSourceServiceImpl.createSessionByAccount();
		 Map<String, Object> params = new HashMap<String, Object>();
		 params.put("includeDeleted", false);
		 try {
			//根据条件查询Voluum上所有的offer数据
			 String	 sr = httpUtil.sendHttpClientGet(ConstantConfig.VOLUUM_URL+"offer",params,paramsToken);
			//HttpRespons sr  = httpUtil.sendGet(ConstantConfig.VOLUUM_URL+"offer",params,paramsToken);
			//if(sr != null && ConstantConfig.HTTP_CODE_200==sr.getCode()){
			 if(!StringUtils.isEmpty(sr)) {
				 JSONObject jsStr = JSONObject.fromObject(sr);
				 if (jsStr != null) {
					 JSONArray jsonArray = JSONArray.fromObject(jsStr.get("offers"));
					 if (jsonArray != null && jsonArray.size() > 0) {
						 JSONObject jo = null;
						 Resources resources = null;
						 ResourcesMcc mcc = null;
						 for (int i = 0; i < jsonArray.size(); i++) {
							 jo = jsonArray.getJSONObject(i);
							 //根据voluum平台的id判断该数据是否存在于本系统中
							 //存在则更新，否则插入
							 if (jo != null && jo.size() > 0 ) {
							 	if( !voluumOfferIdMap.containsKey(jo.getString("id"))) {
									resources = new Resources();
									resources.setName(jo.getString("name"));
									JSONObject jAds = JSONObject.fromObject(jo.get("affiliateNetwork"));
									//adsId数据库设置不为空。当adsId为空时不保存
									if (jAds != null && jAds.size() > 0 && voluumAdverIdMap.containsKey(jAds.get("id"))) {
										resources.setAdsId(voluumAdverIdMap.get(jAds.get("id")).getId());
										resources.setTargetLink(jo.getString("url"));
										resources.setIsSupportParam(1);
										resources.setStatus(1);
										JSONObject jPay = JSONObject.fromObject(jo.get("payout"));
										if (jPay != null && jPay.size() > 0 && jPay.get("value") != null)
											resources.setPrice(Float.valueOf(jPay.get("value").toString()));
										else
											resources.setPrice(0.0F);
										resources.setCreateDate(DateUtils.jsonDateToDate(jo.getString("createdTime")));
										resources.setModifyDate(DateUtils.jsonDateToDate(jo.getString("updatedTime")));
										resources.setVoluumOfferId(jo.getString("id"));
										resourcesMapper.insertSelective(resources);
										//根据Voluum平台的offer的id查询插入到本系统之后数据的id
										Integer resourceId = resourcesMapper.selectIdByVoluumIdBean(jo.getString("id"));
										JSONObject jcou = JSONObject.fromObject(jo.get("country"));
										//将广告的id和国家的id绑定
										if (resourceId != null && jcou != null && jcou.size() > 0 && jcou.get("code") != null) {
											String code = jcou.getString("code");
											mcc = new ResourcesMcc();
											mcc.setMccId(mccCodeMap.get(code));
											mcc.setPid(resourceId);
											resourcesMccMapper.insert(mcc);
										}
									}
							 } else {
								 resources = voluumOfferIdMap.get(jo.getString("id"));
								 resources.setName(jo.getString("name"));
								 JSONObject jAds = JSONObject.fromObject(jo.get("affiliateNetwork"));
								 if (jAds != null && jAds.size() > 0 && voluumAdverIdMap.containsKey(jAds.get("id"))) {
									 resources.setAdsId(voluumAdverIdMap.get(jAds.get("id")).getId());
								 }
								 resources.setTargetLink(jo.getString("url"));
								 resources.setIsSupportParam(1);
								 resources.setStatus(1);
								 JSONObject jPay = JSONObject.fromObject(jo.get("payout"));
								 if (jPay != null && jPay.size() > 0 && jPay.get("value") != null)
									 resources.setPrice(Float.valueOf(jPay.get("value").toString()));
								 else
									 resources.setPrice(0.0F);
								 resources.setModifyDate(DateUtils.jsonDateToDate(jo.getString("updatedTime")));
								 resourcesMapper.updateByPrimaryKeySelective(resources);
							 }
						 }
					 }
						 log.info("Voluum  offer complete！--------------------");
				 }
			 }
		 }
		 }catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
