package cn.net.ibingo.core.query;

import cn.net.ibingo.common.utils.QueryBean;

public class FristChannelQueryBean extends QueryBean{
	
	private String keyword;
	
	private String oneTree = "3";
	
	private String twoTree = "1";

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
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
