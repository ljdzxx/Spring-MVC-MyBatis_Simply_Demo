package com.baobaotao.mybatis.mapping;

import java.util.List;
import com.baobaotao.domain.User;

public interface UserMapper {
	/**
	 * 新增用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int insertUser(User user) throws Exception;
	/**
	 * 更改用户
	 * @param user
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int updateUser(User user) throws Exception;
	/**
	 * 删除用户
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int deleteUser(int userId) throws Exception;
	/**
	 * 根据id查询用户
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public User selectUserById(int userId) throws Exception;
	
	/**
	 * 根据userName查询用户信息
	 * @param userName
	 * @throws Exception
	 */
	public User selectUserByName(String userName) throws Exception;
	
	/**
	 * 查询所有用户信息
	 * @return
	 * @throws Exception
	 */
	public List<User> selectAllUser() throws Exception;
	/**
	 * 根据用户名模糊查询
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public List<User> selectUserLikeName(String userName) throws Exception;
	/**
	 * 登录验证
	 * @param userName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public User login(String userName, String password) throws Exception;
}
