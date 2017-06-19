package cn.net.ibingo.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Mcc {
    private Integer id;

    private String country;

    private String code;

    private Date createDate;

    private Date modifyDate;
    
    private String mncGroup;
    
    private String countryCode;
    
    private String operator;
    
    private String operatorCode;
    
    private List<Mnc> listMnc = new ArrayList<Mnc>();
    
    private String keyword;
    
    private Integer currentPage;
    
    private Integer pageSize;
    
    private String description;
    
    private String iso;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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

	public String getMncGroup() {
		return mncGroup;
	}

	public void setMncGroup(String mncGroup) {
		this.mncGroup = mncGroup;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public List<Mnc> getListMnc() {
		return listMnc;
	}

	public void setListMnc(List<Mnc> listMnc) {
		this.listMnc = listMnc;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIso() {
		return iso;
	}

	public void setIso(String iso) {
		this.iso = iso;
	}
    
}