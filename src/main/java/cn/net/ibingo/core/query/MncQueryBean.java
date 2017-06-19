package cn.net.ibingo.core.query;

import cn.net.ibingo.common.utils.QueryBean;

public class MncQueryBean extends QueryBean{
	
	private String keyword;

	private String oneTree = "2";
	
	private String twoTree = "3";
	
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

}
