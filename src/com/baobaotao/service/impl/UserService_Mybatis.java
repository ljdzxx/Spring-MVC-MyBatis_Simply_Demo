package com.baobaotao.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baobaotao.domain.LoginLog;
import com.baobaotao.domain.User;
import com.baobaotao.mybatis.mapping.LoginLogMapper;
import com.baobaotao.mybatis.mapping.UserMapper;
import com.baobaotao.service.IUserService_Mybatis;

@Service
public class UserService_Mybatis implements IUserService_Mybatis {
	@Resource
	private UserMapper um;
	@Resource
	private LoginLogMapper llm;

	@Override
	public boolean login(String userName, String password) throws Exception {
		// TODO Auto-generated method stub
		
		User user=um.login(userName, password);
		if(null!=user){
			
			return true;
		}
		
		return false;
	}

	@Override
	public User findUserByUserId(int userId) throws Exception {
		return um.selectUserById(userId);
	}
	
	@Override
	public User findUserByUserName(String userName) throws Exception {
		return um.selectUserByName(userName);
	}

	@Override
	public boolean updateOnloginSuccess(User user) {
		boolean ret=false;
		try{
			user.setCredits(5+user.getCredits());
			//user.setLastVisit(new Date());
			//user.setLastIp("unknow");
			
			LoginLog loginLog=new LoginLog();
			loginLog.setUserId(user.getUserId());
			loginLog.setIp(user.getLastIp());
			loginLog.setLoginDate(user.getLastVisit());
			
			//userDao.updateLoginInfo(user);
			//loginLogDao.insertLoginLog(loginLog);
			um.updateUser(user);
			llm.insertLoginLog(loginLog);
			//lm.insertLoginLog(loginLog);
			ret=true;
		}
		catch(Exception ex){
			
		}
		return ret;
	}

}
