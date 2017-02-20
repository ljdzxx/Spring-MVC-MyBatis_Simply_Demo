package com.baobaotao.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baobaotao.dao.*;
import com.baobaotao.domain.*;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private LoginLogDao loginLogDao;
	
	//是否找到匹配的用户
	public boolean hasMatchUser(String userName, String password){
		int matchCount=userDao.getMatchCount(userName, password);
		return matchCount>0;
	}
	
	
	//根据userName查询数据库取得User Bean
	public User findUserByUserName(String userName){
		return userDao.findUserByUserName(userName);
	}
	
	//登录成功后进行的操作
	public boolean loginSuccess(User user){
		boolean ret=false;
		try{
			user.setCredits(5+user.getCredits());
			//user.setLastVisit(new Date());
			//user.setLastIp("unknow");
			
			LoginLog loginLog=new LoginLog();
			loginLog.setUserId(user.getUserId());
			loginLog.setIp(user.getLastIp());
			loginLog.setLoginDate(user.getLastVisit());
			userDao.updateLoginInfo(user);
			loginLogDao.insertLoginLog(loginLog);
			ret=true;
		}
		catch(Exception ex){
			
		}
		return ret;
	}
	
}
