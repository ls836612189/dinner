<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="common.jsp" %>
 <SCRIPT type=text/JavaScript>   
     
    $(function(){
    	selectedPageSizeInit();

     	$("#pageSize").change(function(){
    	 	document.forms[0].submit();
     	});
    })
 	

	 function gotoPage(pageno){
		$("#pageNum").val(pageno);

	 	document.forms[0].submit();
		$(".pager > a").click(function(){
			 $( ".pager > a").removeClass('selected');
			 $this.addClass("selected");
		});
	 }

	    function selectedPageSizeInit() {

	        getSelectStatus("pageSize", $("#hiddenpageSize").val());
	    }
	    
	    function getSelectStatus(selectId, optionValue) {
	        var sel = document.getElementById(selectId);
	        for (var i = 0; i < sel.length; i++) {
	            if (sel.options[i].value == optionValue) {
	                sel.selectedIndex = i;
	                break;
	            }
	        }
	    }
	 </SCRIPT>
  
<div class="pager fr">
	<div style="float: left">
                       <input type="hidden" id="hiddenpageSize" value="${pageData.pageSize}"/>
                         <select id="pageSize" class="selectbox" style="width:110px" name="pageSize">
                            <option value="10">每页 10件</option>
                            <option value="20">每页 20件</option>
                            <option value="50">每页 50件</option>
                            <option value="100">每页 100件</option>
                        </select>
     </div>
	<a href="#" onclick="gotoPage(1)">首 页</a>
	<c:if test="${pageData.pageNum!=1}">
		<a href="#" onclick="gotoPage(${pageData.pageNum-1})">上一页</a>
	</c:if>
	<c:if test="${pageData.pageNum==1}">
	<a href="#" onclick="gotoPage(1)">上一页</a>
	</c:if>
	<c:if test="${pageData.pageNum < pageData.pages}">
	<a href="#" onclick="gotoPage(${pageData.pageNum+1})">下一页</a>
	</c:if>
	<c:if test="${pageData.pageNum==pageData.pages}">
	<a href="#" onclick="gotoPage(${pageData.pageNum})">下一页</a>
	</c:if>
	<a href="#" onclick="gotoPage(${pageData.pages})">末页</a>
	
	<select class="selectbox" onchange="gotoPage(this.value);">
		<c:forEach begin="1" end="${pageData.pages}" step="1" varStatus="status">
			<option value="${status.index }" <c:if test="${status.index==pageData.pageNum }">selected</c:if>>${status.index }</option>
		</c:forEach>
	</select>
	<span class="f14">当前页[<c:out value="${pageData.pageNum}"/>]&nbsp;
&nbsp;共&nbsp;<c:out value="${pageData.pages}"/>&nbsp;页&nbsp;
&nbsp;总记录数[<c:out value="${pageData.total}"/>]</span>
</div>

      
