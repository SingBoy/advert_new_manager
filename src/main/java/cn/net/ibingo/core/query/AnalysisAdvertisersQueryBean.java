package cn.net.ibingo.core.query;

import cn.net.ibingo.common.utils.QueryBean;

public class AnalysisAdvertisersQueryBean extends QueryBean{
	
	public String startDate;
	
	public String endDate;
	
	public Integer adsId;
	
	public Integer resId;
	
	private String oneTree = "1";
	
	private String twoTree = "2";

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

	public Integer getAdsId() {
		return adsId;
	}

	public void setAdsId(Integer adsId) {
		this.adsId = adsId;
	}

	public Integer getResId() {
		return resId;
	}

	public void setResId(Integer resId) {
		this.resId = resId;
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
	
}
