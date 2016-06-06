<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>后台管理系统</title>
    <script src="${ctx}/script/jquery/jquery-1.8.1.min.js"></script>
    <script src="${ctx}/script/jquery/jquery.form.js"></script>
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
        getSelectState("foodTT",$("#hiddenType").val());
    });
</script>
<div class="main client" id="client_control" role="main">
    <%--//////////标题/////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
    <div class="title clearfix">
        <h1>编辑菜品</h1>
    </div>
    <%--/////////内容//////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
    <div class="main_content">
        <form id="addFoodForm" name="addFoodForm" action="${ctx}/food/saveOrUpdateFood" method="post" enctype="multipart/form-data">
            <%--/////////数据框//////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
            <div class="content_box clearfix">
                <table class="tab_col3" >
                    <col/>
                    <col/>
                    <col/>
                    <col/>
                    <tbody>
                    <tr align="center">
                        <td align="right">菜品名称：
                        </td>
                        <td align="left">
                            <input type="hidden" id = "id"  name ="id" value="${bean.id}"/>
                            <input class="input02" name="foodName" id="foodName" value="${bean.foodName}"/>
                        </td>
                    </tr>
                    <tr align="center">
                        <td align="right">菜品类型：</td>
                        <td align="left">
                            <input type="hidden" id="hiddenType" value="${bean.foodType}"/>
                            <select id="foodTT" name="foodType" style="height: 33px;width:230px;"  >
                                <option value="">请选择</option>
                                <c:forEach var="type" items="${foodType}" >
                                    <option value="${type.id}">${type.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr align="center">
                        <td align="right">菜品价格：</td>
                        <td align="left">
                            <input class="input02"  name="foodPrice" id="foodPrice" value="${bean.foodPrice}"/>
                        </td>
                    </tr>
                    <tr align="center">
                        <td align="right">菜品功效：</td>
                        <td align="left">
                            <textarea class="textarea01"id="effect" name="effect" class="myEditor" style="width:230px;height: 80px; overflow-x:visible;overflow-y:visible;" maxlength="1000">${bean.effect}</textarea>
                        </td>
                    </tr>
                    <tr align="center">
                        <td align="right">菜品描述：</td>
                        <td align="left">
                            <textarea class="textarea01"id="detail" name="detail" class="myEditor" style="width:230px;height: 80px; overflow-x:visible;overflow-y:visible;" maxlength="1000">${bean.detail}</textarea>
                        </td>
                    </tr>
                    <tr align="center">
                        <td align="right">示例图片：</td>
                        <td align="left">
                            <input type="file" class="input02"  name="foodImg" id="foodImg"/>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div style="margin-left: auto;margin-top: 50px;">
                    <a class="btn btn16 mr10" title="保存" href="javascript:save();">
                        <span class="btn_r btn"><span class="icon14 img"></span>保 存</span>
                    </a>
                    <a class="btn btn16 mr10" title="取消" href="${ctx}/food/foodList"><span class="btn_r btn"><span class="icon15 img"></span>取 消</span></a>
                </div>
            </div>
        </form>
    </div>
    <script type="text/javascript">


        function save(){
            $("#addFoodForm").ajaxSubmit(function (data){
                if(data.code == "00000"){
                    window.location.href="${ctx}/food/foodList";
                }else{
                    alert(data.message);
                }
            });
        }
    </script>
    <%--/////////内容结束//////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
</div>
</body>

</html>
