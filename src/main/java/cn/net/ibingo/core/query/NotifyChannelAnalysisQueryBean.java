package cn.net.ibingo.core.query;

import cn.net.ibingo.common.utils.QueryBean;

public class NotifyChannelAnalysisQueryBean extends QueryBean{
	
	public String startDate;
	
	public String endDate;
	
	public String fristCode;
	
	public String twoCode;
	
	private String resourcesName;
	
	private String country;
	
	private String oneTree = "1";
	
	private String twoTree = "1";

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getFristCode() {
		return fristCode;
	}

	public void setFristCode(String fristCode) {
		this.fristCode = fristCode;
	}

	public String getTwoCode() {
		return twoCode;
	}

	public void setTwoCode(String twoCode) {
		this.twoCode = twoCode;
	}

	public String getOneTree() {
		return oneTree;
	}

	public void setOneTree(String oneTree) {
		this.oneTree = oneTree;
	}

	public String getTwoTree() {
		return twoTree;
	}

	public void setTwoTree(String twoTree) {
		this.twoTree = twoTree;
	}

	public String getResourcesName() {
		return resourcesName;
	}

	public void setResourcesName(String resourcesName) {
		this.resourcesName = resourcesName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
}
