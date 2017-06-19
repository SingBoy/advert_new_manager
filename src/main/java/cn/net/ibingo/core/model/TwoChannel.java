package cn.net.ibingo.core.model;

import java.util.Date;

public class TwoChannel {
    private Integer id;

    private Integer pid;

    private String name;

    private String code;

    private String description;

    private Date createDate;

    private Date modifyDate;
    
    private String fristChannelName; //对应的一级渠道名称
    
    private String pidBean;
    
    private String keyword;
    
    private Integer currentPage;
    
    private Integer pageSize;

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

	public String getFristChannelName() {
		return fristChannelName;
	}

	public void setFristChannelName(String fristChannelName) {
		this.fristChannelName = fristChannelName;
	}

	public String getPidBean() {
		return pidBean;
	}

	public void setPidBean(String pidBean) {
		this.pidBean = pidBean;
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
}