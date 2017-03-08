package com.baobaotao.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.baobaotao.domain.LoginLog;
import com.baobaotao.domain.User;
import com.baobaotao.service.impl.UserService_Mybatis;

@Controller
public class MainController {
	@Autowired
	private UserService_Mybatis userService;
	
	//负责处理login.html的请求
	@RequestMapping(value="/login.html")
	public String loginPage(){
		return "login";
	}
	
	//负责处理main.html的请求
	@RequestMapping(value="/main.html")
	public String mainPage(){
		return "main";
	}
	
	//登录日志显示
	@RequestMapping(value="/listLoginLog.html")
	public ModelAndView listLoginLog() throws Exception{
		List<LoginLog> list=userService.listLoginLog();
		
		return new ModelAndView("loginList","list",list);
	}
	
	
	
	//负责处理/loginCheck.html的请求
	@RequestMapping(value="/loginCheck.html")
	public ModelAndView loginCheck(HttpServletRequest request, User user) throws Exception{
		//boolean isValidUser=userService.hasMatchUser(loginCommand.getUserName(), loginCommand.getPassword());
		boolean isValidUser=userService.login(user.getUserName(),user.getPassword());
		if(!isValidUser){
			return new ModelAndView("login","error","用户名或密码错误。");
		}else{
			User u=userService.findUserByUserName(user.getUserName());
			//System.out.println("1.user:[credits:"+u.getCredits()+"]");
			if(null!=u){
				u.setLastIp(request.getLocalAddr());
				u.setLastVisit(new Date());
				userService.updateOnloginSuccess(u);
				request.getSession().setAttribute("user", u);
				//System.out.println("2.user:["+request.getSession().getAttribute("user").toString()+"]");
				if(request.getSession().getAttribute("returnUri")==null || request.getSession().getAttribute("returnUri").equals("")){//登录前没有访问别的页面
					return new ModelAndView("main");
				}else{//返回登录前访问的页面
					return new ModelAndView("redirect");
				}
			}else{
				return new ModelAndView("login","error","数据库中没有找到该用户["+user.getUserName()+"]。");
			}
			
		}
	}
}
