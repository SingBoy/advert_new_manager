package cn.net.ibingo.core.model;

import java.util.Date;

public class Advertisers {
    private Integer id;

    private String name;

    private String code;

    private String description;

    private Date createDate;

    private Date modifyDate;
    
    private String keyword;
    
    private Integer currentPage;
    
    private Integer pageSize;
    
    private String voluumAffiliateNetworkId;

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
        this.code = code;
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

	public String getVoluumAffiliateNetworkId() {
		return voluumAffiliateNetworkId;
	}

	public void setVoluumAffiliateNetworkId(String voluumAffiliateNetworkId) {
		this.voluumAffiliateNetworkId = voluumAffiliateNetworkId;
	}
	
	
}