package cn.net.ibingo.core.model;

import java.util.Date;

public class NotifyChannelAnalysis {
	
    private String firstchannelid;

    private String secchannelid;

    private String advertisersname;

    private String resourcesname;

    private String ipoprator;

    private String type;

    private String status;

    private String price;

    private String country;
	
    private Long index;

    private Long count;

    private Date date;

    private Long subscribeCount;
    
    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

	public String getFirstchannelid() {
		return firstchannelid;
	}

	public void setFirstchannelid(String firstchannelid) {
		this.firstchannelid = firstchannelid;
	}

	public String getSecchannelid() {
		return secchannelid;
	}

	public void setSecchannelid(String secchannelid) {
		this.secchannelid = secchannelid;
	}

	public String getAdvertisersname() {
		return advertisersname;
	}

	public void setAdvertisersname(String advertisersname) {
		this.advertisersname = advertisersname;
	}

	public String getResourcesname() {
		return resourcesname;
	}

	public void setResourcesname(String resourcesname) {
		this.resourcesname = resourcesname;
	}

	public String getIpoprator() {
		return ipoprator;
	}

	public void setIpoprator(String ipoprator) {
		this.ipoprator = ipoprator;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Long getSubscribeCount() {
		return subscribeCount;
	}

	public void setSubscribeCount(Long subscribeCount) {
		this.subscribeCount = subscribeCount;
	}
	
}