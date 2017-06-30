package cn.net.ibingo.core.query;

import cn.net.ibingo.common.utils.QueryBean;

public class OfferStatisticsQueryBean extends QueryBean{
	
	private String keyword;

	public String startDate;

	public String endDate;

	public String offerId;

	public String offerNameAlias;

	public String country;

	public String trafficSourceId;
	
	private String oneTree = "1";
	
	private String twoTree = "1";

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

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

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public String getOfferNameAlias() {
		return offerNameAlias;
	}

	public void setOfferNameAlias(String offerNameAlias) {
		this.offerNameAlias = offerNameAlias;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTrafficSourceId() {
		return trafficSourceId;
	}

	public void setTrafficSourceId(String trafficSourceId) {
		this.trafficSourceId = trafficSourceId;
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
