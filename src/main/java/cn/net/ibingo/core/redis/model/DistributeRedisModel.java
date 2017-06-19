package cn.net.ibingo.core.redis.model;

import cn.net.ibingo.common.utils.ConstantConfig;

import java.util.Date;

public class DistributeRedisModel {
	
	private Date date;
	
	private Integer count;
	
	private String offerId;

	private String trafficId;
	
	public String getKey() {
		return ConstantConfig.PROJECT_NAME +"_distribute_"+ offerId+"_"+trafficId;
	}
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public String getTrafficId() {
		return trafficId;
	}

	public void setTrafficId(String trafficId) {
		this.trafficId = trafficId;
	}
}
