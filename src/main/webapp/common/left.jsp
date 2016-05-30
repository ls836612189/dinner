<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="common.jsp" %>
<%@ taglib prefix="menu" uri="/WEB-INF/menu.tld"%>
<script type="text/javascript">
 $(function(){
     $(".chd_on").parent().parent().addClass('on');
	/*主导航*/
	$( ".nav_icon > li").hover(
		function() {
				$(this).prev().removeClass('nav_line');
				$(this).prev().addClass('no_bg');
	}, function() {
	
			$(this).prev().removeClass('no_bg');
			$(this).prev().addClass('nav_line');
	}
	);
	$( ".nav_icon > li").click(function(){
	   if($(this).find('ul')){
	       $( ".nav_icon > li").removeClass('on');
	       $(this).addClass('on');
	       $( ".nav_icon > li > ul").not($(this).find('ul')).hide();
	       $(this).find('ul').show();
	   }
	});
});
</script>
<!-- left_menu-->
<div class="left_content">
    <!--user_info-->
    <div class="user_info">
		<a class="pl30"><img id="userTitleImg" src="${ctx}/images/user.jpg" alt="" width="160" height="160" /></a>
        <p class="tc user_name">${sessionScope.emp_session.userName}</p>
        <p class="tc"></p>
    </div>
    <!-- nav-->
       <div class="nav">
               <menu:menu menuMap="${menu}" fid="${fid}"/>
       </div>
</div>
