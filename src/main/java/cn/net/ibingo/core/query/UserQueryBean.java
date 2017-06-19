package cn.net.ibingo.core.query;

import java.util.List;

import cn.net.ibingo.common.utils.QueryBean;

public class UserQueryBean extends QueryBean {

	private String keyword;
	
	private List<Integer> userRoleIds;
	
	private String oneTree = "4";
	
	private String twoTree = "1";
	
	private int UserRole;
	
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

	public List<Integer> getUserRoleIds() {
		return userRoleIds;
	}

	public void setUserRoleIds(List<Integer> userRoleIds) {
		this.userRoleIds = userRoleIds;
	}

	public int getUserRole() {
		return UserRole;
	}

	public void setUserRole(int userRole) {
		UserRole = userRole;
	}
}
