<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>后台管理系统</title>
    <script src="${ctx}/script/jquery/jquery-1.8.1.min.js"></script>
</head>

<body>
<script type="text/javascript">
	function getSelectState(selectId, optionValue) {
		var sel = document.getElementById(selectId);
		for (var i = 0; i < sel.length; i++) {
			if (sel.options[i].value == optionValue) {
				sel.selectedIndex = i;
				break;
			}
		}
	}
	$(document).ready(function () {
		getSelectState("foodTT",$("#hiddenFootType").val());
	});
</script>
<div class="main client" id="client_control" role="main">
<%--//////////标题/////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<div class="title clearfix">
   <h1>标数据统计</h1>
</div>
<%--/////////内容//////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<div class="main_content">
<form id="foodListForm" name="foodListForm" action="${ctx}/food/foodList"  method="post">
<%--/////////搜索//////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<div class="search04">
	<ul class="search_ul clearfix">
		<li class="fl mr45"><span class="pr5">菜名:</span>
	    	<input class="input02" name="foodName" id="foodName" value="${queryBean.foodName}"/>
	    </li>
		<li class="fl mr45"><span class="pr5">类型:</span>
			<input type="hidden" id="hiddenFootType" value="${queryBean.foodType}"/>
			<select id="foodTT" name="foodType" style="height: 33px;width:230px;" >
				<option value="">请选择</option>
				<c:forEach var="type" items="${foodType}" >
					<option value="${type.id}">${type.name}</option>
				</c:forEach>
			</select>
		</li>
		<li class="fl">
			<a id="search" name="search" title="搜索" class="btn01 btn" href="javascript:search();"><span class="btn">搜&nbsp;&nbsp;索<span class="icon04 icon"></span></span></a>
		</li>
	</ul>
</div>
<%--/////////搜索结束//////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<%--/////////数据框//////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<div class="content_box clearfix">
	<%--操作按钮--%>
	<div class="user_operate mb10 clearfix">
		<a class="fl btn btn11 mr10" href="${ctx}/food/addNewFood" id="addNewFood" >
			<span class="btn_r btn"><span class="icon05 icon"></span>添加新菜品</span>
		</a>
	</div>
	<%--按钮结束--%>
	<%--table数据--%>
	<div class="box1100">
	<table id="borrow-rep-table1" class="table04" style="min-width:1100px;" >
		<thead>
			<tr >
				<th>序号</th>
				<th>名称</th>
				<th>类型</th>
				<th>价格</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:if test="${pageData!=null}">
		<c:forEach items="${pageData.list}" var="temp" varStatus="status">
			<tr>
				<td>${status.index+1}</td>
				<td>${temp.foodName}</td>
				<td>${temp.foodTypeName}</td>
				<td>${temp.foodPrice}</td>
				<td>
					<a id = "check" href="javascript:void(0);" onclick="showImg('${ctx}${temp.imgUrl}')">图片</a>
					<a id = "edit" href="${ctx}/food/editFood?id=${temp.id}">编辑</a>
					<a id = "delete" href="javascript:void(0);" onclick="deleteFood(${temp.id})">删除</a>
				</td>
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
		<input type="hidden" name="pageNum" id="pageNum" />
		<%@include file="/common/pageUtil.jsp"%>
	</c:if>
</div>
</form>
</div>
<%--/////////内容结束//////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
	<script type="text/javascript">
		function search(){
			$("#foodListForm").submit();
		}
		function deleteFood(id){
			if(confirm("确定需要删除此菜品")){
				$.post("${ctx}/food/foodDelete",{"id":id},function(result){
					if(result.code == "00000"){
						$("#foodListForm").submit();
					}else{
						alert(result.message);
					}
				},"json");
			}
		}

		function showImg(img){
			if (img == null || img ==''){
				alert("没有该图片");
				return ;
			}
			window.showModalDialog(img,null,"dialogHeight:500px;dialogWidth:600px;resizable:yes;");
		}
	</script>
</div>
</body>

</html>
