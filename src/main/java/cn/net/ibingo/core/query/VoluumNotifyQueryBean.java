package cn.net.ibingo.core.query;

import cn.net.ibingo.common.utils.QueryBean;

public class VoluumNotifyQueryBean extends QueryBean {
	
	public String startDate;

	public String endDate;

	public String keyword;
	
	public String affilicateId;

	public String trafficId;

	public int dataType;

	private String oneTree = "2";
	
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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public String getAffilicateId() {
		return affilicateId;
	}

	public void setAffilicateId(String affilicateId) {
		this.affilicateId = affilicateId;
	}

	public String getTrafficId() {
		return trafficId;
	}

	public void setTrafficId(String trafficId) {
		this.trafficId = trafficId;
	}
}
