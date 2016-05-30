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
<form id="addFoodForm" name="addFoodForm" action="${ctx}/food/saveNewFood" method="post" enctype="multipart/form-data">
<%--/////////数据框//////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<div class="content_box clearfix">
	<table class="tab_col3" >
		<col/>
		<col/>
		<col/>
		<col/>
		<tbody>
		<tr align="center">
			<td>菜品名称：
				<input class="input02" value="${bean.foodName}"   name="foodName" id="foodName"/>
			</td>
		</tr>
		<tr align="center">
			<td>菜品类型：
				<select class="input02" value="${bean.foodType}"   name="foodType" id="foodType">
					<option value="">请选择</option>
					<option value="1">冷盘</option>
					<option value="2">热荤</option>
					<option value="3">素菜</option>
					<option value="4">汤菜</option>
					<option value="5">主食</option>
					<option value="6">饮料</option>
				</select>
			</td>
		</tr>
		<tr align="center">
			<td>菜品价格：
				<input class="input02" value="${bean.foodPrice}"   name="foodPrice" id="foodPrice"/>
			</td>
			<td>示例图片：
				<input type="file" class="input02"  name="foodImg" id="foodImg"/>
			</td>
		</tr>
		</tbody>
	</table>
	<div class="tc mt10 mb10 pb10 box1094">
			<a class="btn btn16 mr10" title="保存" href="javascript:save();">
				<span class="btn_r btn"><span class="icon14 img"></span>保 存</span>
			</a>
			<a class="btn btn16 mr10" title="取消" href="${ctx}/food/foodList"><span class="btn_r btn"><span class="icon15 img"></span>取 消</span></a>
	</div>
</div>
</form>
</div>
	<script>
		function save(){
			$("#addFoodForm").submit();
		}
	</script>
<%--/////////内容结束//////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
</div>
</body>

</html>