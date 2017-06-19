package cn.net.ibingo.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IpArea {
    private Integer id;

    private String start;

    private String end;

    private String country;

    private String operator;

    private Date createDate;

    private Date modifyDate;
    
    private String iso;
    
    private String newCountry;

    private String newOperator;
    
    private List<IpArea> list = new ArrayList<IpArea>();
    
    private String countryBean;
    
    private String operatorBean;
    
    private Integer currentPage;
    
    private Integer pageSize;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start == null ? null : start.trim();
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end == null ? null : end.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
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

	public String getNewCountry() {
		return newCountry;
	}

	public void setNewCountry(String newCountry) {
		this.newCountry = newCountry;
	}

	public String getNewOperator() {
		return newOperator;
	}

	public void setNewOperator(String newOperator) {
		this.newOperator = newOperator;
	}

	public List<IpArea> getList() {
		return list;
	}

	public void setList(List<IpArea> list) {
		this.list = list;
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

	public String getIso() {
		return iso;
	}

	public void setIso(String iso) {
		this.iso = iso;
	}
  
}