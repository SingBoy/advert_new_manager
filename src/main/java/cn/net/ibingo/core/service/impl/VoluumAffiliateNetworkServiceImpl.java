package cn.net.ibingo.core.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import cn.net.ibingo.common.utils.*;
import com.alibaba.druid.util.StringUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.net.ibingo.core.dao.AdvertisersMapper;
import cn.net.ibingo.core.model.Advertisers;
import cn.net.ibingo.core.service.VoluumAffiliateNetworkService;
import cn.net.ibingo.core.service.VoluumTrafficSourceService;

@Service
public class VoluumAffiliateNetworkServiceImpl implements VoluumAffiliateNetworkService {

	private static Logger log = Logger.getLogger(String.valueOf(VoluumAffiliateNetworkServiceImpl.class));

	@Autowired
	private VoluumTrafficSourceService voluumTrafficSourceServiceImpl;
	
	@Autowired
	private AdvertisersMapper advertisersMapper;
	
	private  HttpUtil httpUtil = new HttpUtil();

	/**
	 * 从Voluum平台获取广告主数据，并保存早广告主表中
	 */
	@Override
	public void getAffiliateNetworksAndInsert() {
		 Map<String,Advertisers> voluumAdverIdMap = new HashMap<String,Advertisers>();
		//获取本系统中的所有广告主
		 List<Advertisers> list = advertisersMapper.selectBean();
		 if(list != null && list.size() > 0){
			 for(Advertisers advertisers : list){
				//在所有广告主的以<voluumAdverid,adver>的结构存入map中
				 voluumAdverIdMap.put(advertisers.getVoluumAffiliateNetworkId(), advertisers);
			 }
		 }
		 //获取会话session
		 Map<String, Object> paramsToken = voluumTrafficSourceServiceImpl.createSessionByAccount();
		 Map<String, Object> params = new HashMap<String, Object>();
		 params.put("includeDeleted", false);
		 try {
			//根据条件查询Voluum上所有的AffiliateNetworks数据
			 //HttpRespons sr  = httpUtil.sendGet(ConstantConfig.VOLUUM_URL+"affiliate-network",params,paramsToken);
			 //if(sr != null && ConstantConfig.HTTP_CODE_200==sr.getCode()){
			//	JSONObject jsStr = JSONObject.fromObject(sr.getContent());
			 String	sr = httpUtil.sendHttpClientGet(ConstantConfig.VOLUUM_URL+"affiliate-network",params,paramsToken);
			 if(!StringUtils.isEmpty(sr)) {
				JSONObject jsStr = JSONObject.fromObject(sr);
				if (jsStr != null) {
					JSONArray jsonArray = JSONArray.fromObject(jsStr.get("affiliateNetworks"));
					if (jsonArray != null && jsonArray.size() > 0) {
						JSONObject jo = null;
						Advertisers advertisers = null;
						for (int i = 0; i < jsonArray.size(); i++) {
							jo = jsonArray.getJSONObject(i);
							//根据voluum平台的id判断该数据是否存在于本系统中
							//存在则更新，否则插入
							if (jo != null && jo.size() > 0 && !voluumAdverIdMap.containsKey(jo.getString("id"))) {
								advertisers = new Advertisers();
								advertisers.setVoluumAffiliateNetworkId(jo.getString("id"));
								advertisers.setName(jo.getString("name"));
								String maxCode = advertisersMapper.selectMaxCode();
								if (maxCode == null) {
									advertisers.setCode("0001");
								} else {
									advertisers.setCode(CodeUtils.getCode(maxCode));
								}
								advertisers.setCreateDate(DateUtils.jsonDateToDate(jo.getString("createdTime")));
								advertisers.setModifyDate(DateUtils.jsonDateToDate(jo.getString("updatedTime")));
								advertisersMapper.insertSelective(advertisers);
							} else {
								advertisers = voluumAdverIdMap.get(jo.getString("id"));
								advertisers.setName(jo.getString("name"));
								advertisers.setModifyDate(DateUtils.jsonDateToDate(jo.getString("updatedTime")));
								advertisersMapper.updateByPrimaryKeySelective(advertisers);
							}
						}
						log.info("Voluum affiliate-network complete！--------------------");
					}
				}
			}
			} catch (UnsupportedEncodingException e1) {
			 e1.printStackTrace();
		 } catch (IOException e1) {
			 e1.printStackTrace();
		 }

	}
	

	
}
