package cn.net.ibingo.core.redis.model;

import cn.net.ibingo.common.utils.DateUtils;

import java.util.Date;

public class DayLimitRedisModel {
	
	private Date date;
	
	private int count;
	
	private String offerId;

	private String trafficId;

	private String curDate;
	
	
	public String getKey() {
		return "advert-new-daylimit_"+curDate+"_"+offerId+"_"+trafficId;
	}
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
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

	public String getCurDate() {
		return DateUtils.formatDate(new Date());
	}

	public void setCurDate(String curDate) {
		this.curDate = curDate;
	}
}
