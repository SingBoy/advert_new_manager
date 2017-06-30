package cn.net.ibingo.core.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import cn.net.ibingo.common.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cn.net.ibingo.core.model.FristChannel;
import cn.net.ibingo.core.service.VoluumTrafficSourceService;

import com.alibaba.fastjson.JSONObject;

@Service
public class VoluumTrafficSourceServiceImpl implements	VoluumTrafficSourceService {

	private static Logger log = Logger.getLogger(String.valueOf(VoluumTrafficSourceServiceImpl.class));

	private  HttpUtil httpUtil = new HttpUtil();
	
	private Map<String, Object> paramsToken = new HashMap<String, Object>();
	
	/**
	 * 根据账号和密码获取会话token
	 */
	@Override
	public  Map<String, Object> createSessionByAccount() {
		if(paramsToken.get("expirationTimestamp") != null && !StringUtils.isEmpty(paramsToken.get("expirationTimestamp").toString())){
			Date a = (Date) paramsToken.get("expirationTimestamp");
			Map<String, Long> longMap = DateUtils.getDistanceTimes(a, new Date());
			if(longMap != null){
				long day = longMap.get("day");
				long hour = longMap.get("hour");
				//会话session保留四个小时
				if(day>0 || hour > 2){
					getAccunt();
				}
			}
		}else{
			getAccunt();
		}
		 return paramsToken;
	}
	public void getAccunt(){
		Map<String, Object> params = new HashMap<String, Object>();
		 params.put("email", ConstantConfig.VOLUUM_USERNAEM);
		 params.put("password",ConstantConfig.VOLUUM_PASSWORD);
		 String url  = ConstantConfig.VOLUUM_URL+"auth/session";
		 
		 try {
			 HttpRespons sr  = httpUtil.sendPost(url,params,null);
			 if(sr != null && ConstantConfig.HTTP_CODE_200==sr.getCode() ){
				 JSONObject jsStr = JSONObject.parseObject(sr.getContent());
				 if(jsStr != null ){
					 paramsToken.put("CWAUTH-TOKEN", jsStr.getString("token"));
					 paramsToken.put("expirationTimestamp", new Date());//jsStr.getString("expirationTimestamp")
					 paramsToken.put("inaugural", jsStr.getString("inaugural"));
				 }
				 log.info("Voluum session success！--------------------");
			 }else{
				 log.info("Voluum session failure！--------------------");
			 }
		 } catch (IOException e) {
				e.printStackTrace();
			}
	}

	/**
	 * 当新增渠道时，将对应的渠道信息上传到Voluum平台
	 */
	@Override
	public String addTrafficSource(FristChannel fristChannel) {
		  Map<String, Object> paramsToken = createSessionByAccount();
		  Map<String, Object> params = new HashMap<String, Object>();
			 params.put("name", fristChannel.getName());
			 params.put("createdTime", DateUtils.formatDateTimeAll(fristChannel.getCreateDate()));
			 params.put("updatedTime", DateUtils.formatDateTimeAll(fristChannel.getCreateDate()));
			 params.put("deleted", false);
			 params.put("type", ConstantConfig.TRAFFICSOURCE_TYPE_CUS);//CUSTOM', 'PREDEFINED
			 params.put("predefinedType", ConstantConfig.TRAFFICSOURCE_FINEDTYPE_ZER);//ZERO_PARK', 'DSP
			 spliceParam(params,fristChannel);
			 params.put("impressionSpecific", false);//标有此标志的流量来源可用于跟踪展示次数
			 try {
			String  sr  = httpUtil.sendHttpClientPost(ConstantConfig.VOLUUM_URL+"traffic-source",params,paramsToken);
			if(!StringUtils.isEmpty(sr)){
				JSONObject jsStr = JSONObject.parseObject(sr);
				 String voluumTrafficSourceId = jsStr.getString("id");
				 log.info("Voluum success upload traffic-source!-----------------------");
				 return voluumTrafficSourceId;
			}else{
				log.info("Voluum failure upload traffic-source!-----------------------");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 当更新渠道时，将对应的渠道信息更新到Voluum平台
	 */
	@Override
	public void updateTrafficSource(FristChannel fristChannel) {
		if(StringUtils.isEmpty(fristChannel.getVoluumTrafficSourceId())){
			addTrafficSource(fristChannel);
		}
		 Map<String, Object> paramsToken = createSessionByAccount();
		 Map<String, Object> params = new HashMap<String, Object>();
		 params.put("name", fristChannel.getName());
		 params.put("createdTime", DateUtils.formatDateTimeAll(fristChannel.getCreateDate()));
		 params.put("updatedTime", DateUtils.formatDateTimeAll(fristChannel.getModifyDate()));
		 params.put("deleted", false);
		 params.put("type", ConstantConfig.TRAFFICSOURCE_TYPE_CUS);//CUSTOM', 'PREDEFINED
		 params.put("predefinedType", ConstantConfig.TRAFFICSOURCE_FINEDTYPE_ZER);//ZERO_PARK', 'DSP
		 spliceParam(params,fristChannel);
		 params.put("impressionSpecific", false);//标有此标志的流量来源可用于跟踪展示次数
		 try {
			 String sr  = httpUtil.sendHttpClientPut(ConstantConfig.VOLUUM_URL+"traffic-source/"+fristChannel.getVoluumTrafficSourceId(),params,paramsToken);
			if(!StringUtils.isEmpty(sr)){
				log.info("Voluum success update traffic-source-----------------------");
			}else{
				log.info("Voluum failure update traffic-source-----------------------");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * 当渠道信息删除时，将对应的Voluum平台上的信息删除
	 */
	@Override
	public void deleteTracfficSource(String id) {
		Map<String, Object> paramsToken = createSessionByAccount();
		Map<String, Object> params = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		list.add(id);
		params.put("ids", list);
		try {
			HttpRespons sr  = httpUtil.sendDelete(ConstantConfig.VOLUUM_URL+"traffic-source",params,paramsToken);
			if(sr != null && ConstantConfig.HTTP_CODE_200==sr.getCode()){
				log.info("Voluum success delete traffic-source--------------------------");
			}else{
				log.info("Voluum failure delete traffic-source--------------------------");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public  void spliceParam(Map<String, Object> params,FristChannel fristChannel){
		List<Map<String,Object>> listMap = new ArrayList<>();
		Map<String,Object> map = null;
		String param = "";
		StringBuffer stringBuffer = new StringBuffer();
		for(int i = 0;i < 5; i++){
			if(i == 0)	param = fristChannel.getP1();
			if(i == 1)	param = fristChannel.getP2();
			if(i == 2)	param = fristChannel.getP3();
			if(i == 3)	param = fristChannel.getP4();
			if(i == 4)	param = fristChannel.getP5();
			if(!org.apache.commons.lang.StringUtils.isEmpty(param)){
				map = new HashMap<String,Object>();
				map.put("index", i+1);
				map.put("name", ""+param+"");
				map.put("parameter", ""+param+"");
				map.put("placeholder", "{"+param+"}");
				map.put("trackedInReports", true);
				listMap.add(map);
				if(i == 0)	stringBuffer.append("&p1={var1}");
				if(i == 1)	stringBuffer.append("&p2={var2}");
				if(i == 2)	stringBuffer.append("&p3={var3}");
				if(i == 3)	stringBuffer.append("&p4={var4}");
				if(i == 4)	stringBuffer.append("&p5={var5}");
			}
		}
		/*if(listMap.size() == 0){
			map = new HashMap<String,Object>();
			map.put("index", 1);
			map.put("name", "target");
			map.put("parameter", "target");
			map.put("placeholder", "{target}");
			map.put("trackedInReports", true);
			listMap.add(map);
		}*/
		params.put("postbackUrl", ConstantConfig.ADVERT_MANAGER_URL+stringBuffer);
		params.put("customVariables", listMap);
	}
		
}
