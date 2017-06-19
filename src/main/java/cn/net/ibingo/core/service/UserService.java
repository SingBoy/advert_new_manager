package cn.net.ibingo.core.service;

import cn.net.ibingo.common.pagination.model.PaginationList;
import cn.net.ibingo.core.model.User;
import cn.net.ibingo.core.query.UserQueryBean;

public interface UserService {
	
	public User login(String username, String password);

	public PaginationList<User> list(UserQueryBean queryBean);
	
	public User get(Integer id);

	public boolean saveOrUpdate(User user);

	public boolean delete(Integer id);

	public boolean enable(Integer id);
	
	public Boolean selectByUsername(User user);
	
}
