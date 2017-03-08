<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>宝宝淘论坛</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
    ${sessionScope.user.userName},欢迎进入宝宝淘论坛，您当前积分为${sessionScope.user.credits}，密码是：${sessionScope.user.password}，登录IP：${sessionScope.user.lastIp}，最后一次登录时间：${sessionScope.user.lastVisit}；
    <p />
    JSTL标签获取Session：${sessionScope.user}
    
    <p />
    Jsp中获取Session：<%= session.getAttribute("user").toString() %>
    
  </body>
</html>
