<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>后台管理系统</title>
    <script src="${cxt}/script/jquery/jquery-1.8.1.min.js"></script>
</head>

<body>
<div class="main client" id="client_control" role="main">
<%--//////////标题/////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<div class="title clearfix">
   <h1>标数据统计</h1>
</div>
<%--/////////内容//////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<div class="main_content">
<form id="#####" name="######" action="${cxt}/" method="post">
<%--/////////搜索//////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<div class="search04">
	<ul class="search_ul clearfix">
		<li class="fl mr45"><span class="pr5">######:</span>
	    	<input class="input02" name="######" id="填写" value="${queryBean.ddddd}"/>
	    </li>
		<li class="fl">
			<a id="search" name="search" title="搜索" class="btn01 btn" href="javascript:void(0);"><span class="btn">搜&nbsp;&nbsp;索<span class="icon04 icon"></span></span></a>
		</li>
	</ul>
</div>
<%--/////////搜索结束//////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<%--/////////数据框//////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<div class="content_box clearfix">
	<%--操作按钮--%>
	<div class="user_operate mb10 clearfix">
		<a class="fl btn btn11 mr10" href="javascript:void(0);" id="#######" >
			<span class="btn_r btn"><span class="icon05 icon"></span>#######</span>
		</a>
	</div>
	<%--按钮结束--%>
	<%--table数据--%>
	<div class="box1100">
	<table id="borrow-rep-table1" class="table04" style="min-width:2500px;" >
		<thead>
			<tr >
				<th>日期</th>
			</tr>
		</thead>
		<tbody>
		<c:if test="${pageData.list!=null}">
		<c:forEach items="${pageData.list}" var="temp" varStatus="status">
			<tr>
				<td></td>
			</tr>
		</c:forEach>
		</c:if>
		</tbody>
	</table>
	</div>
	<%--table数据结束--%>
	<c:if test="${empty  pageData.list}">
	<div class="no_data">
		<div class="no_data_tips f14">对不起，没有相关数据显示！</div>
	</div>
	</c:if>
	<c:if test="${!empty  pageData.list}">
	<input type="hidden" name="pageNum" id="pageNum" value="${pageData.pageNumber}">
	</c:if>
</div>
</form>
</div>
<%--/////////内容结束//////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
</div>
</body>

</html>
