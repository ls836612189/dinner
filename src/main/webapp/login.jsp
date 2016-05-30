<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link href="${ctx}/css/base.css" rel="stylesheet"/>
    <link href="${ctx}/css/common.css" rel="stylesheet"/>
    <script src="${ctx}/script/jquery/jquery-1.8.1.min.js" type="text/javascript"></script>
    <title>冠群驰骋线上P2P后台管理系统</title>
    <style type="text/css">
        html {
            background: #e4e4e4;
        }
    </style>
</head>
<script type="text/javascript">
    $(function () {
        $("#login").click(function () {
            var loginid = $("#loginId").val();
            var loginpwd = $("#loginPwd").val();
            var valid = $("#validCode").val();
            $.post("${ctx}/login",
            {"loginName":loginid,"loginPwd":loginpwd,"verifyCode":valid},
            function(data){
                if(data.code == "10000"){
                    window.location.href = "${ctx}/index.jsp";
                    return;
                }else{
                    alert(data.message);
                }
            },"json");
        });

        $('#changeCode').click(function () {
            $('#imgVerify').attr('src', '${ctx}/servlet/VerifyingCodeGen?id=' + Math.random());
        });
    });

</script>
<body>
<div class="loginbg">
    <div class="loginbox">
        <ul class="user_login mb20">
            <li><input type="text" class="input_login" id="loginId" name="loginid" maxlength="20" placeholder="用户名"><span class="user_pic bg"></span></li>
            <li><input type="password" class="input_login" id="loginPwd" name="loginpwd"  maxlength="20"  placeholder="密&nbsp;&nbsp;码"><span class="user_pw bg"></span></li>
            <li>
                <input type="text" class="input_login fl" style="width:198px;" value="" maxlength="6" name="verifyCode" placeholder="验证码" id="validCode" autocomplete="off"/>
                <span class="user_vi bg"></span>
                <span class="fr mt10" style="height: 28px;width:84px;display: inline-block;">
                    <a id="changeCode" href="javascript:void(0);">
                        <img height="28" width="84" id="imgVerify" src="${ctx}/servlet/VerifyingCodeGen"/>
                    </a>
                </span>
            </li>
        </ul>
        <input id="login" class="mt10 btn_login" type="button" value=""/>
    </div>
</div>

<script>
    $(function(){
    	
        var _position = function () {
            var _height = $(window).height()/2 - 186;
            var _left = $(window).width()/2 - 203;
            $('.loginbg').css({
                'margin-top':_height+'px',
                'margin-left':_left+'px'
            });
        }
        window.onresize = _position;
        $(function () {
            _position();
        });
        document.onkeydown = function(e){  
            var ev = document.all ? window.event : e;
            //回车事件
            if(ev.keyCode==13) {
                //要处理的事件
                var loginid = $("#loginId").val();
                var loginpwd = $("#loginPwd").val();
                var valid = $("#validCode").val();
                $.post("${ctx}/login",
                {"loginName":loginid,"loginPwd":loginpwd,"verifyCode":valid},
                 function(data){
                        if(data.code == "10000"){
                            window.location.href = "${ctx}/index.jsp";
                            return;
                        }else{
                            alert(data.message);
                 }},"json");
            }
        }
    });
</script>
</body>
</html>
