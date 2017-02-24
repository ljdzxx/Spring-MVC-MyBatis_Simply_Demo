package com.baobaotao.service;

import com.baobaotao.domain.User;

public interface IUserService_Mybatis {
	//是否找到匹配的用户
		public boolean login(String userName, String password) throws Exception;
		
		//根据userName模糊查询数据库取得User Bean
		public User findUserByUserId(int userId) throws Exception;
		
		public User findUserByUserName(String userName) throws Exception;
		
		//登录成功后进行的操作
		public boolean updateOnloginSuccess(User user) throws Exception;
}
