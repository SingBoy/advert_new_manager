package cn.net.ibingo.core.model;

public class TimezoneCountry {
    private Integer id;

    private Integer timezone;

    private String countryZhCn;

    private String countryIso;

    private String keyword;
    
    private Integer currentPage;
    
    private Integer pageSize;
    
    private Integer countryId;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public String getCountryZhCn() {
        return countryZhCn;
    }

    public void setCountryZhCn(String countryZhCn) {
        this.countryZhCn = countryZhCn == null ? null : countryZhCn.trim();
    }

    public String getCountryIso() {
        return countryIso;
    }

    public void setCountryIso(String countryIso) {
        this.countryIso = countryIso == null ? null : countryIso.trim();
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

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	
}