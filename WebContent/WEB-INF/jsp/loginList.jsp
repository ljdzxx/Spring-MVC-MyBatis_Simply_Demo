<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>登录日志列表</title>
  </head>
  
  <body>
    <c:if test="${!empty list}">
    	
    	<table style="width:100%;">
    	
    		<tr>
	            <td style="width:25%;">
	                	编号
	            </td>
	            <td style="width:25%;">
						用户Id
	            </td>
	            
	            <td style="width:25%;">
	                	IP
	            </td>
	            
	            <td style="width:25%;">
	                	登录时间
	            </td>
            </tr>
    	

     
    		<c:forEach items="${list}" var="li"  varStatus="id">
    		
            <tr>
	            <td style="width:25%;">
	                ${id.index}&nbsp;->${li.loginLogId}
	            </td>
	            <td style="width:25%;">
					${li.getUserId()}
	            </td>
	            
	            <td style="width:25%;">
	                ${li.getIp()}
	            </td>
	            
	            <td style="width:25%;">
	                ${li.getLoginDate()}
	            </td>
            </tr>
            
            </c:forEach>
            
        </table>
    	
    </c:if>
  </body>
</html>
