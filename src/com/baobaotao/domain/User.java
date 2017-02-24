package com.baobaotao.domain;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1935117144676101491L;
	
	private int userId;
	private String userName;
	private String password;
	private int credits;
	private String lastIp;
	private Date lastVisit;
	
	public String toString(){
		return "[userId:"+ userId +" ,userName:"+userName+" ,password:"+password+" ,credits:"+credits+" ,lastIp:"+lastIp+" ,lastVisit:"+lastVisit+"]";
	}
	
	/**
	   * 必须提供一个无参数的构造函数
	   */
	public User(){
		super();
	}
	
	public User(String userName, String password){
		super();
		this.userName=userName;
		this.password=password;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public String getLastIp() {
		return lastIp;
	}
	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}
	public Date getLastVisit() {
		return lastVisit;
	}
	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
	}
}
