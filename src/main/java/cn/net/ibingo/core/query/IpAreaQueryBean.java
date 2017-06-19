package cn.net.ibingo.core.query;

import cn.net.ibingo.common.utils.QueryBean;

public class IpAreaQueryBean extends QueryBean{
	
	private String country;
	
	private String operator;
	
	private String oneTree = "2";
	
	private String twoTree = "4";

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
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
