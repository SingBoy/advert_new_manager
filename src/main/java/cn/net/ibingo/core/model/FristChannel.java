package cn.net.ibingo.core.model;

import java.util.Date;
import java.util.List;

public class FristChannel {
    private Integer id;

    private String name;

    private String code;

    private Float dividedRate;

    private Float subscriptionRate;

    private String callbackUrl;
    
    private String leader;
    
    private String p1;
    
    private String p2;

    private String p3;

    private String p4;

    private String p5;
    
    private String type;
    
    private String description;

    private Date createDate;

    private Date modifyDate;
    
    private String keyword;
    
    private String voluumTrafficSourceId;//Voluum平台上渠道的id
    
    private Integer currentPage;
    
    private Integer pageSize;

    private String distribution;//设置渠道和广告的分配比例

    public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Float getDividedRate() {
        return dividedRate;
    }

    public void setDividedRate(Float dividedRate) {
        this.dividedRate = dividedRate;
    }

    public Float getSubscriptionRate() {
        return subscriptionRate;
    }

    public void setSubscriptionRate(Float subscriptionRate) {
        this.subscriptionRate = subscriptionRate;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl == null ? null : callbackUrl.trim();
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

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }

    public String getP3() {
        return p3;
    }

    public void setP3(String p3) {
        this.p3 = p3;
    }

    public String getP4() {
        return p4;
    }

    public void setP4(String p4) {
        this.p4 = p4;
    }

    public String getP5() {
        return p5;
    }

    public void setP5(String p5) {
        this.p5 = p5;
    }

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVoluumTrafficSourceId() {
		return voluumTrafficSourceId;
	}

	public void setVoluumTrafficSourceId(String voluumTrafficSourceId) {
		this.voluumTrafficSourceId = voluumTrafficSourceId;
	}

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }
}