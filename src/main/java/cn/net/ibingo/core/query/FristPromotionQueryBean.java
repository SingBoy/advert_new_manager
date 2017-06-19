package cn.net.ibingo.core.query;

import cn.net.ibingo.common.utils.QueryBean;

public class FristPromotionQueryBean extends QueryBean{
	
	private Integer pid;
	
	private String oneTree = "3";
	
	private String twoTree = "1";

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
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
