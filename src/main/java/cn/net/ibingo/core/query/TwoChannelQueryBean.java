package cn.net.ibingo.core.query;

import cn.net.ibingo.common.utils.QueryBean;

public class TwoChannelQueryBean extends QueryBean{
	
	private String keyword;
	
	private Integer pid;
	
	private String oneTree = "3";
	
	private String twoTree = "2";

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

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
}
