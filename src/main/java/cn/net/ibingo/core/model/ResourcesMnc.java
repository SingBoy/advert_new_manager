package cn.net.ibingo.core.model;

public class ResourcesMnc {
    private Integer id;

    private Integer pid;

    private Integer mncId;

    private String operator;
    
    private String code;
    
    private Integer mccId;
    
    private String country;
    
    private Integer adsId;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getMncId() {
        return mncId;
    }

    public void setMncId(Integer mncId) {
        this.mncId = mncId;
    }

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getMccId() {
		return mccId;
	}

	public void setMccId(Integer mccId) {
		this.mccId = mccId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getAdsId() {
		return adsId;
	}

	public void setAdsId(Integer adsId) {
		this.adsId = adsId;
	}
	
}