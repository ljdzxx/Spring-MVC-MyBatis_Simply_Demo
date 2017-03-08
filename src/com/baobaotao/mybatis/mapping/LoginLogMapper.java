package com.baobaotao.mybatis.mapping;

import java.util.List;

import com.baobaotao.domain.LoginLog;

public interface LoginLogMapper {
	public int insertLoginLog(LoginLog loginLog) throws Exception;
	
	public List<LoginLog> listLoginLog() throws Exception; 
}
