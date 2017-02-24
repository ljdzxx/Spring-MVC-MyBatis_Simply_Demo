package com.baobaotao.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter{
	private static final String LOGIN_PAGE = "LOGIN_PAGE";    //登录页面
	private static final String LOGIN_ACTION_PAGE = "LOGIN_ACTION_PAGE";        //操作处理页面
	private static final String USER_SESSION_KEY = "USER_SESSION_KEY";      //登录用户在session中的属性key
	private String login_page;  
    private String login_action_page;  
    private String user_session_key;
    
	/**
	 * 初始化过滤器配置信息
	 */
	public void init(FilterConfig config) throws ServletException {
		login_page = config.getInitParameter(LOGIN_PAGE);  
        login_action_page = config.getInitParameter(LOGIN_ACTION_PAGE);  
        user_session_key = config.getInitParameter(USER_SESSION_KEY);  
        if(login_page==null || login_page.equals("") || login_page.equals("null") ){  
            throw new ServletException("登录页面配置出错...");  
        }  
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {  
        HttpServletRequest  httpReq  = (HttpServletRequest) request;  
        HttpServletResponse httpResp = (HttpServletResponse) response;  
        httpResp.setContentType("text/html");  
        String servletPath = httpReq.getServletPath();  
        System.out.println("servletPath---------------->"+servletPath);
        
        boolean isLogining = false;  //是否正在进行登录操作
        if(servletPath.equals(login_page) || servletPath.equals(login_action_page)){  
            chain.doFilter(request, response);  //下一条过滤链
            isLogining = true;  
            return;  
        }  
        else  
        {   //如果不是在进行登录操作，则需先判断用户是否已登录
            Object session_user = httpReq.getSession().getAttribute(user_session_key);  
            if(session_user != null){//如果不为空,则表明已登录  
                chain.doFilter(request, response);  //下一条过滤链
            }else{//否则进行登录跳转
                if ( httpReq.getQueryString() != null )  
                {  
                    servletPath += "?"+httpReq.getQueryString();  
                    //System.out.println("servletPath---------------->"+servletPath);
                }  
                httpReq.getSession().setAttribute("returnUri", servletPath);
                if ( isLogining == false )  
                {    
                   httpReq.getRequestDispatcher(login_page).forward(httpReq,httpResp);  
                }   
            }  
        }  
   }

	/**
	 * 进行相应的过滤处理
	 */
	/*public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse res = (HttpServletResponse) arg1;
		//从SESSION获取对象
		Object obj = req.getSession().getAttribute("user");
		String servletPath = req.getServletPath();
		System.out.println("req.getServletPath()---------------->"+req.getServletPath());
		if(obj != null){//已登录
			arg2.doFilter(arg0, arg1);//请求：我自己定义请求，例外就是页面JS,JSP,HTML
		}else{
			String path = req.getContextPath();
			System.out.println("req.getContextPath()---------------->"+path);
			String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path+"/";
			System.out.println("basePath---------------->"+basePath);
			
			if(servletPath.contains(".")){
				int index = servletPath.lastIndexOf(".");
				String suffix = servletPath.substring(index);
				if(".js.htm.css.jpg.png.jsp".contains(suffix)){
					arg2.doFilter(arg0, arg1);
				}else{//重定向
					res.sendRedirect(basePath);
				}
			}else{//这里是处理我们自己定义请求
				if(servletPath.contains("login")||servletPath.contains("regist")){
					arg2.doFilter(arg0, arg1);
				}else{//重定向
					res.sendRedirect(basePath);
				}
			}
		}
	}*/

	
	/**
	 * 在取消执行之前进行过滤资源释放
	 */
	public void destroy() {
		
	}
	
}
