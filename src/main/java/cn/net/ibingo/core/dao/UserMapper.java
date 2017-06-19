package cn.net.ibingo.core.dao;

import java.util.List;

import cn.net.ibingo.core.model.User;
import cn.net.ibingo.core.query.UserQueryBean;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
	public Integer selectCountByQueryBean(UserQueryBean queryBean);

	public List<User> selectByQueryBean(UserQueryBean queryBean);

	public User selectUserByUsername(String username);
	
	public List<User> selectByUsername(String username);
}