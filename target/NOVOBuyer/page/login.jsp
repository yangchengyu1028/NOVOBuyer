<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 <title>拜欧-比价系统</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <!-- 开始必须的CSS样式 -->
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css" />
    <link href="/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link href="/css/style-metro.css" rel="stylesheet" type="text/css" />
    <link href="/css/style.css" rel="stylesheet" type="text/css" />
    <link href="/css/style-responsive.css" rel="stylesheet" type="text/css" />
    <link href="/css/default.css" rel="stylesheet" type="text/css" id="style_color" />
    <link href="/css/uniform.default.css" rel="stylesheet" type="text/css" />
    <!-- 结束必须的CSS样式 -->
    <!-- 开始页面样式 -->
    <link href="/css/login-soft.css" rel="stylesheet" type="text/css" />
    <!-- 结束页面样式 -->
    <link rel="shortcut icon" href="/image/favicon.ico" />
</head>

<body class="login">
<!-- 开始LOGO -->
<div class="logo">
   
</div>
<!-- 结束 LOGO -->
<!-- 开始 LOGIN -->
<div class="content">
    <!-- 开始 LOGIN FORM -->
    <form name="myform" id="userLogin" class="form-vertical login-form" onkeydown="if(event.keyCode==13){return false;}">
        <h3 class="form-title">诺耳平台比价系统</h3>
        <span style="color: red;">${login_error}</span>
        <div class="alert alert-error hide">
            <button class="close" data-dismiss="alert"></button>
            <span>请输入用户名和密码.</span>  
        </div>
        <div class="control-group">
            <label class="control-label visible-ie8 visible-ie9">Username</label>
            <div class="controls">
                <div class="input-icon left">
                    <i class="icon-user"></i>
                    <input class="m-wrap placeholder-no-fix" type="text" placeholder="请输入账号" id="username" name="username"/>
                	<span id="nameSpan" style="background-color: red;"></span>
                </div>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label visible-ie8 visible-ie9">Password</label>
            <div class="controls">
                <div class="input-icon left">
                    <i class="icon-lock"></i>
                    <input class="m-wrap placeholder-no-fix" type="password" placeholder="请输入密码" id="password" name="password"/>
                </div>
            </div>
        </div>
        
        <div class="form-actions">
            <!-- <label class="checkbox">
                <input id="remember_password" type="checkbox"/> 记住密码
            </label> -->
            <input type="button" onclick="login()" id="btnsubmit" class="btn blue pull-right" value="登陆"/>
                 <!-- <i class="m-icon-swapright m-icon-white"></i> -->
        </div>
    </form>

</div>
<!-- 结束 LOGIN -->
<!-- 开始 COPYRIGHT -->
<div class="copyright">
    2018 &copy; 诺耳 - 比价系统.
</div>
<!-- 结束 COPYRIGHT -->
<!-- 开始Javascript（放在底部节约加载时间） -->
<!-- 开始核心插件 -->
<script src="/js/jquery-1.10.1.min.js" type="text/javascript"></script>
<script src="/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<!-- 重要! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
<script src="/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/js/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="/js/jquery.blockui.min.js" type="text/javascript"></script>
<script src="/js/jquery.cookie.min.js" type="text/javascript"></script>
<script src="/js/jquery.uniform.min.js" type="text/javascript"></script>
<!-- 结束核心插件 -->
<!-- 开始页面插件 -->
<script src="/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="/js/jquery.backstretch.min.js" type="text/javascript"></script>
<!-- 结束页面插件 -->
<!-- 开始界面脚本-->
<script src="/js/app.js" type="text/javascript"></script>
<script src="/js/login-soft.js" type="text/javascript"></script>
<!-- 结束页面脚本 -->

<script>
    jQuery(document).ready(function() {
        App.init();
        Login.init();
    });
    

     function login(){
    	var username = $("#username").val();
        var password = $("#password").val();
        $.ajax({
			method : "post",
			url : "userLogin.novo",
			data : {
				"username": username,
				"password": password
			},
			async : true,
			success : function(msg) {
				if(msg=="seccess"){
					 window.location.href = "getDataOfIndex.novo";
				}else{
					alert(msg);
				}
				
			}
		});
       
    }
    //登陆用户名正则表达式验证
    $("#username").focusout(function(){
    	var str = /^[\u4e00-\u9fa5A-Za-z0-9-_]*$/;
    	if(!str.test($("#username").val())){
    		 $("#nameSpan").html("用户名4-11位以内（包含字母）");
    	}
    	else{
    		$("#nameSpan").html("");
    	}
    });
    
/*     jQuery(function(){
    	 //获取cookie的值
    	 var username = $.cookie('username');
    	 var password = $.cookie('password');
    	 //将获取的值填充入输入框中
    	 $('#username').val(username);
    	 $('#password').val(password); 
    	 if(username != null && username != '' && password != null && password != ''){//选中保存秘密的复选框
    	  $("#remember_password").attr('checked',true);
    	 }
    	}); */
    
/*     window.onload = function(){
        var oForm = document.getElementById('userLogin');
        var oUser = document.getElementById('username');
        var oPswd = document.getElementById('password');
        var oRemember = document.getElementById('remember');
        //页面初始化时，如果帐号密码cookie存在则填充
        if(getCookie('user') && getCookie('pswd')){
          oUser.value = getCookie('user');
          oPswd.value = getCookie('pswd');
          oRemember.checked = true;
        }
        //复选框勾选状态发生改变时，如果未勾选则清除cookie
        oRemember.onchange = function(){
          if(oRemember.checked == false){
            delCookie('user');
            delCookie('pswd');
          }
        };
        //表单提交事件触发时，如果复选框是勾选状态则保存cookie
       oForm.onclick = function(){
          if(oRemember.checked == true){ 
            setCookie('user',oUser.value,7); //保存帐号到cookie，有效期7天
            setCookie('pswd',oPswd.value,7); //保存密码到cookie，有效期7天
          }
        };
      };
      //设置cookie
      function setCookie(name,value,day){
        var date = new Date();
        date.setDate(date.getDate() + day);
        document.cookie = name + '=' + value + ';expires='+ date;
      };
      //获取cookie
      function getCookie(name){
        var reg = RegExp(name+'=([^;]+)');
        var arr = document.cookie.match(reg);
        if(arr){
          return arr[1];
        }else{
          return '';
        }
      };
      //删除cookie
      function delCookie(name){
        setCookie(name,null,-1);
      }; */
   
</script>
<!-- 结束JavaScript代码 -->
</body>

</html>