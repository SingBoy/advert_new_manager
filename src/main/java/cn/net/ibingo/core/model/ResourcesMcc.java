package cn.net.ibingo.core.model;

public class ResourcesMcc {
    private Integer id;

    private Integer pid;

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