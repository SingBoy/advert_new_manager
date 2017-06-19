package cn.net.ibingo.core.model;

import java.util.Date;

public class Resources {
	
    private Integer id;

    private String name;

    private Integer adsId;

    private Float price;

    private Date activityStart;

    private Date activityEnd;

    private Float activityPrice;

    private String targetLink;

    private Integer isSupportParam;

    private Integer status;

    private String imageUrl;

    private String downloadUrl;

    private Integer dailyLimit;

    private String description;

    private Date createDate;

    private Date modifyDate;
    
    private String country;
    
    private String operator;
    
    private Integer mccId;
    
    private Integer mncId;

    private String adsName;
    
    private String mccGroup;
    
    private String mncGroup;
    
    private String mccMnc;
    
    private String mccIds;
    
	private String keyword;
	
	private String countryBean;
	
	private String operatorBean;
	
	private Integer adsIdBean;
	
	private Integer statusBean;
	
    private Integer currentPage;
    
    private Integer pageSize;
    
    private String c1Key;
    
    private String c2Key;
    
    private String typeKey;

    private String c3Key;
    
    private Integer callbackStatus;
    
    private String voluumOfferId;

	private Float subscriptionRate;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAdsId() {
        return adsId;
    }

    public void setAdsId(Integer adsId) {
        this.adsId = adsId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getActivityStart() {
        return activityStart;
    }

    public void setActivityStart(Date activityStart) {
        this.activityStart = activityStart;
    }

    public Date getActivityEnd() {
        return activityEnd;
    }

    public void setActivityEnd(Date activityEnd) {
        this.activityEnd = activityEnd;
    }

    public Float getActivityPrice() {
        return activityPrice;
    }

    public void setActivityPrice(Float activityPrice) {
        this.activityPrice = activityPrice;
    }

    public String getTargetLink() {
        return targetLink;
    }

    public void setTargetLink(String targetLink) {
        this.targetLink = targetLink == null ? null : targetLink.trim();
    }

    public Integer getIsSupportParam() {
        return isSupportParam;
    }

    public void setIsSupportParam(Integer isSupportParam) {
        this.isSupportParam = isSupportParam;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl == null ? null : downloadUrl.trim();
    }

    public Integer getDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(Integer dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Integer getMccId() {
		return mccId;
	}

	public void setMccId(Integer mccId) {
		this.mccId = mccId;
	}

	public Integer getMncId() {
		return mncId;
	}

	public void setMncId(Integer mncId) {
		this.mncId = mncId;
	}

	public String getAdsName() {
		return adsName;
	}

	public void setAdsName(String adsName) {
		this.adsName = adsName;
	}

	public String getMccGroup() {
		return mccGroup;
	}

	public void setMccGroup(String mccGroup) {
		this.mccGroup = mccGroup;
	}

	public String getMncGroup() {
		return mncGroup;
	}

	public void setMncGroup(String mncGroup) {
		this.mncGroup = mncGroup;
	}

	public String getMccMnc() {
		return mccMnc;
	}

	public void setMccMnc(String mccMnc) {
		this.mccMnc = mccMnc;
	}

	public String getMccIds() {
		return mccIds;
	}

	public void setMccIds(String mccIds) {
		this.mccIds = mccIds;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getCountryBean() {
		return countryBean;
	}

	public void setCountryBean(String countryBean) {
		this.countryBean = countryBean;
	}

	public String getOperatorBean() {
		return operatorBean;
	}

	public void setOperatorBean(String operatorBean) {
		this.operatorBean = operatorBean;
	}

	public Integer getAdsIdBean() {
		return adsIdBean;
	}

	public void setAdsIdBean(Integer adsIdBean) {
		this.adsIdBean = adsIdBean;
	}

	public Integer getStatusBean() {
		return statusBean;
	}

	public void setStatusBean(Integer statusBean) {
		this.statusBean = statusBean;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getC1Key() {
		return c1Key;
	}

	public void setC1Key(String c1Key) {
		this.c1Key = c1Key;
	}

	public String getC2Key() {
		return c2Key;
	}

	public void setC2Key(String c2Key) {
		this.c2Key = c2Key;
	}

	public String getTypeKey() {
		return typeKey;
	}

	public void setTypeKey(String typeKey) {
		this.typeKey = typeKey;
	}

	public String getC3Key() {
		return c3Key;
	}

	public void setC3Key(String c3Key) {
		this.c3Key = c3Key;
	}

	public Integer getCallbackStatus() {
		return callbackStatus;
	}

	public void setCallbackStatus(Integer callbackStatus) {
		this.callbackStatus = callbackStatus;
	}

	public String getVoluumOfferId() {
		return voluumOfferId;
	}

	public void setVoluumOfferId(String voluumOfferId) {
		this.voluumOfferId = voluumOfferId;
	}

	public Float getSubscriptionRate() {
		return subscriptionRate;
	}

	public void setSubscriptionRate(Float subscriptionRate) {
		this.subscriptionRate = subscriptionRate;
	}
}