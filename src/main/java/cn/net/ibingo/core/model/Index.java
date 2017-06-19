package cn.net.ibingo.core.model;

public class Index {
	
	private int resourcesCount; //现有广告
	
	private int advertisersCount; //合作广告主
	
	private int mccCount; //覆盖国家
	
	private int mncCount; //运营商
	
	private int channelCount; //推广渠道
	
	private int yesterdayPv; //昨日访问次数
	
	private int yesterdayUv; //昨日访问用户数
	
	private int yesterdaySs;  //昨日订阅成功数
	
	private double yesterdayPrice; //昨日预计收入
	
	private int pv; //累计访问次数
	
	private int uv; //累计访问用户数
	
	private int ss; //累计订阅成功数

	public int getResourcesCount() {
		return resourcesCount;
	}

	public void setResourcesCount(int resourcesCount) {
		this.resourcesCount = resourcesCount;
	}

	public int getAdvertisersCount() {
		return advertisersCount;
	}

	public void setAdvertisersCount(int advertisersCount) {
		this.advertisersCount = advertisersCount;
	}

	public int getMccCount() {
		return mccCount;
	}

	public void setMccCount(int mccCount) {
		this.mccCount = mccCount;
	}

	public int getMncCount() {
		return mncCount;
	}

	public void setMncCount(int mncCount) {
		this.mncCount = mncCount;
	}

	public int getChannelCount() {
		return channelCount;
	}

	public void setChannelCount(int channelCount) {
		this.channelCount = channelCount;
	}

	public int getYesterdayPv() {
		return yesterdayPv;
	}

	public void setYesterdayPv(int yesterdayPv) {
		this.yesterdayPv = yesterdayPv;
	}

	public int getYesterdayUv() {
		return yesterdayUv;
	}

	public void setYesterdayUv(int yesterdayUv) {
		this.yesterdayUv = yesterdayUv;
	}

	public int getYesterdaySs() {
		return yesterdaySs;
	}

	public void setYesterdaySs(int yesterdaySs) {
		this.yesterdaySs = yesterdaySs;
	}

	public int getPv() {
		return pv;
	}

	public void setPv(int pv) {
		this.pv = pv;
	}

	public int getUv() {
		return uv;
	}

	public void setUv(int uv) {
		this.uv = uv;
	}

	public int getSs() {
		return ss;
	}

	public void setSs(int ss) {
		this.ss = ss;
	}

	public double getYesterdayPrice() {
		return yesterdayPrice;
	}

	public void setYesterdayPrice(double yesterdayPrice) {
		this.yesterdayPrice = yesterdayPrice;
	}
	
}
