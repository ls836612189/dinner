<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<%@ include file="common.jsp" %>
	<title><decorator:title/></title>
	<%--<link href="${cxt}/images/favicon.ico" type="image/x-icon" rel="icon" />--%>
	<link href="${cxt}/css/base.css" rel="stylesheet" />
	<link href="${cxt}/css/common.css" rel="stylesheet" />
	<link href="${cxt}/css/layout.css" rel="stylesheet" />
	<script src="${cxt}/script/jquery/jquery-1.8.1.min.js" type="text/javascript"></script>
	<decorator:head/>
   </head>
  <body>
  <div class='top'>
	<div class="fl white f12 pl80 mt10">${sessionScope.emp_session.userName} 您好，欢迎您登录后台管理系统！</div>
	<div class="fr">
		<div class="fr mt5"><img src="${cxt}/images/photo_mini.png" width='32' height='32'  /></div>
	</div>
  </div>
    <%@include file="left.jsp"%>
   	<decorator:body/>
    <%@include file="footer.jsp"%>
  </body>
</html>
