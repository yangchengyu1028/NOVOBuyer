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
<!-- 所有的连接前面加上以下代码 -->
<base href="<%=basePath%>">
    <meta charset="utf-8" />
    <title>比价系统</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
     <!-- BEGIN GLOBAL MANDATORY STYLES -->
   <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css" />
    <link href="/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link href="/css/style-metro.css" rel="stylesheet" type="text/css" />
    <link href="/css/style.css" rel="stylesheet" type="text/css" />
    <link href="/css/style-responsive.css" rel="stylesheet" type="text/css" />
    <link href="/css/default.css" rel="stylesheet" type="text/css" id="style_color" />
    <link href="/css/uniform.default.css" rel="stylesheet" type="text/css" />
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link href="/css/jquery.gritter.css" rel="stylesheet" type="text/css" />
    <link href="/css/daterangepicker.css" rel="stylesheet" type="text/css" />
    <link href="/css/fullcalendar.css" rel="stylesheet" type="text/css" />
    <link href="/css/jqvmap.css" rel="stylesheet" type="text/css" media="screen" />
    <link href="/css/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" media="screen" />
    <link href="/css/pageStyle.css" rel="stylesheet" type="text/css" media="screen" />
    <!-- END PAGE LEVEL STYLES -->
    <link rel="shortcut icon" href="/image/favicon.ico" />
	<style>
	.active{
		background-color: gray;
	}
	.alarm{
		color:red;
	}
	.div{
		width: 80px;
		height: 10px;
	}
	.ycy{
	width: 70px;
	height: 30px;
	line-height: 80px;
	display: inline-block;
	text-align: center;
}
	.table th, .table td { 
text-align: center;
vertical-align: middle!important;
}
	</style>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed">
<%
       String id = request.getParameter("id");
%>
<!-- BEGIN HEADER -->
<div class="header navbar navbar-inverse navbar-fixed-top">
    <!-- BEGIN TOP NAVIGATION BAR -->
    <div class="navbar-inner">
        <div class="container-fluid">
            <!-- BEGIN LOGO -->
            <a class="brand" href="index.html">
               
            </a>
            <a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
                <img src="/image/menu-toggler.png" alt="" />
            </a>
            <!-- END RESPONSIVE MENU TOGGLER -->
            <!-- BEGIN TOP NAVIGATION MENU -->
            <ul class="nav pull-right">
                <li class="dropdown user">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span class="username">${sessionScope.user.realName}</span>
                        <i class="icon-angle-down"></i>
                    </a>
                    <ul class="dropdown-menu">
                       <!--  <li>
                            <a href="#"><i class="icon-user"></i> 我的资料</a>
                        </li> -->
                        <li>
                            <a href="outlogin.novo"><i class="icon-key"></i> 退出登陆</a>
                        </li>
                    </ul>
                </li>
                <!-- END USER LOGIN DROPDOWN -->
            </ul>
            <!-- END TOP NAVIGATION MENU -->
        </div>
    </div>
    <!-- END TOP NAVIGATION BAR -->
</div>
<!-- END HEADER -->
<!-- BEGIN CONTAINER -->
<div class="page-container">
    <!-- BEGIN SIDEBAR -->
    <div class="page-sidebar nav-collapse collapse">
        <!-- BEGIN SIDEBAR MENU -->
        <ul class="page-sidebar-menu">
            <li>
                <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                <div class="sidebar-toggler hidden-phone"></div>
                <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
            </li>
            <li>
                <form class="sidebar-search">
                    <!-- <div class="input-box">
                        <a href="javascript:;" class="remove"></a>
                        <input type="text" placeholder="搜索功能" />
                        <input type="button" class="submit" value=" " />
                    </div> -->
                </form>
            </li>
            <li class="" id="li1">
                <a href="javascript:gomain();">
                    <i class="icon-home"></i>
                    <span class="title">首页</span>
                   <!--  <span class="arrow "></span> -->
                </a>
            </li>
            <li class="" id="li2">
                <a href="javascript:goplan();">
                    <i class="icon-book"></i>
                    <span class="title">采购计划</span>
                </a>
            </li>
            <li class="" id="li3">
                <a href="javascript:goshop();">
                    <i class="icon-cogs"></i>
                    <span class="title">商品目录管理</span>
                </a>
            </li>
            <li class=""id="li4">
                <a href="javascript:goshops();">
                    <i class="icon-list"></i>
                    <span class="title">供应商管理</span>
                </a>
            </li>
             <li class="start active"id="li5">
                <a href="javascript:goproject();">
                    <i class="icon-th"></i>
                    <span class="title">方案管理</span>
                </a>
            </li>
            
        </ul>
        <!-- END SIDEBAR MENU -->
    </div>
    <!-- END SIDEBAR -->
    <!-- BEGIN PAGE -->
    <div class="page-content" id="miandiv">
				<div class="page-container" style="height: 100%">
        <div class="container-fluid">
            <!-- BEGIN PAGE HEADER-->
            <div class="row-fluid">
                <div class="span12">

                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->

                    <h3 class="page-title">
                      	  方案详情 
                    </h3>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>
            <!-- END PAGE HEADER-->
            <div class="row-fluid">
                <div class="span12">
                    <!-- BEGIN ALERTS PORTLET-->
                    <!-- <div class="portlet">
                        <div class="portlet-title">
                            <div class="caption"><i class="icon-cogs"></i>查询</div>
                            <div class="tools">
                                <a href="javascript:;" class="collapse"></a>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="">
                                <span class="ycy">通用名</span>
                                <input type="text" class="span2 m-wrap" id="comName" value=""/>
                                  
                                <button type="submit" class="btn blue" id="findlikeBtn">查询</button>
                            </div>
                         </div>
                    </div> -->
                    
                    <!-- END ALERTS PORTLET-->
                </div>
            </div>
                <!-- END DASHBOARD STATS -->
                <div class="clearfix"></div>
                <div class="row-fluid">
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12" style="padding-left: 2%;padding-right:2%">

                    <!-- BEGIN PORTLET-->
                    <div class="portlet paddingless">
                       <!--  <div class="portlet-title line">
                            <div class="caption"><i class="icon-bell"></i>计划采购商品</div>
                            <div class="tools">
                                <a href="" class="collapse"></a>
                                <a href="#portlet-config" data-toggle="modal" class="config"></a>
                            </div>
                        </div> -->
                        <div class="portlet-body">
                            <!--BEGIN TABS-->
                            <div class="tabbable tabbable-custom">
                                <ul class="nav nav-tabs">
                                    <li class="active">
                                        <a href="" data-toggle="tab" id="order" index="1">采购订单</a>
                                    </li>
                                    <li>
                                        <a href="" data-toggle="tab" id="nobuy" index="0">未采购商品</a>
                                    </li>
                                </ul>
                            </div>
                            
	                            <div class="portlet-body">
	                                <table class="table table-hover" id="table1">
	                                    <thead>
		                                    <tr>
		                                   		<th>供应商</th>
												<th>商品数量</th>
												<th>金额</th>
												<th>操作</th>
	                                    </thead>
	                                    <tbody id="tbody1">
	                                    
	                                    </tbody>
	                                </table>
	                            <div class="table-responsive table2excel" data-tableName="Test Table 1">
	                                 <table class="table table-hover" id="table2">
	                                    <thead>
		                                     <tr>
		                                   		<th>ERP编号</th>
												<th>通用名</th>
												<th>规格</th>
												<th>批准文号</th>
												<th>厂家</th>
												<th>计划采购数量</th>
												<th>未采购数量</th>
		                                    </tr>
	                                    </thead>
	                                    <tbody id="tbody2">
	                                    
	                                    </tbody>
	                                </table>
	                            </div>
	                        </div>
                        </div>
                        <br><br>
                        <div class="row-fluid">
                        	 <div class="span6">
                        	 
                                 <button class="btn"  data-toggle="modal" data-target="#myModa1" id="exportBtn">
                                   导出Excel
                                </button>
	 							<button class="btn"  data-toggle="modal" id="exportBtn2">
                                   导出Excel
                                </button>
          
                                </div>
                        
                            <div class="span6" style="padding-left: 0%;padding-right:0%" id="divPage">
	                            <button class="btn" id="firstpage_btn">首页</button>
								<button class="btn" id="pre_btn">上一页</button>
								<input type="text"
									style="margin-top: 1%; width: 30px; margin-left: 1%" id="pageNo"
									value="1" />/ <span id="totalPage">5</span>
								<button class="btn" id="jumppage_btn">GO</button>
								<button class="btn" id="nextpage_btn">下一页</button>
								<button class="btn" id="endpage_btn">尾页</button>
                            </div>
							
                           
 
                            
                        </div></div></div></div></div>
    
		<input type="hidden" value="1" id="hidden1">
		<input type="hidden" value="<%=id%>" id="hidden2" />		
    </div>
    
						

    <!-- END FOOTER -->
                                    <!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
                                    <!-- BEGIN CORE PLUGINS -->  
                                    <script src="/js/jquery-1.10.1.min.js" type="text/javascript"></script>                        
                                    <script src="/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
                                    <!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
                                    <script src="/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
                                    <script src="/js/bootstrap.min.js" type="text/javascript"></script>
                                    <script src="/js/jquery.slimscroll.min.js" type="text/javascript"></script>
                                    <script src="/js/jquery.blockui.min.js" type="text/javascript"></script>
                                    <script src="/js/jquery.cookie.min.js" type="text/javascript"></script>
                                    <script src="/js/jquery.uniform.min.js" type="text/javascript"></script>
                                        <!-- END CORE PLUGINS -->
                                    <!-- BEGIN PAGE LEVEL SCRIPTS -->
                                    <script src="/js/app.js" type="text/javascript"></script>
                                    <script src="/js/index.js" type="text/javascript"></script>
                                    <script src="/js/echarts.common.min.js" type="text/javascript"></script>
                                    <!-- END PAGE LEVEL SCRIPTS -->
									<script src="/js/jquery.table2excel.js" type="text/javascript"></script>
                                    <script>
                                    	
                                    	
                                      /*   bkLib.onDomLoaded(function() { nicEditors.allTextAreas() }); */
                                        jQuery(document).ready(function() {

                                            App.init(); // initlayout and core plugins

                                            Index.init();

                                            Index.initJQVMAP(); // init index page's custom scripts

                                            Index.initCalendar(); // init index page's custom scripts

                                            Index.initCharts(); // init index page's custom scripts

                                            Index.initChat();

                                            Index.initMiniCharts();

                                            Index.initDashboardDaterange();

                                            Index.initIntro();

                                        });
                                        function gomain(){
    										window.location.href="getDataOfIndex.novo";
    									}		
    									function goplan(){
    										window.location.href="buyerPlan.novo";		
    									}
    									function goshops(){
    										window.location.href="supplierManage.novo";
    									}
    									function goshop(){
    										window.location.href="localGoods.novo";
    									}
    									function goproject(){
    										window.location.href="projectManage.novo";
    									}
						            	var pageNo = $("#pageNo").val();
						            	var id = $("#hidden2").val();
									$("#order").click(function () {                                                                         
                                        $("#hidden1").val("1");
                                        $("#pageNo").val("1");
                                        $("#exportBtn").hide();
                                        $("#table1").show();
                                        $("#table2").hide();
										$("#divPage").show();
										$("#exportBtn2").show();
                                        show();
                                    })
                                    $("#nobuy").click(function () {
                                    	 $("#hidden1").val("0");
                                    	 $("#pageNo").val("1");
                                    	 $("#exportBtn").show();
                                    	 $("#table1").hide();
                                    	 $("#table2").show();
                                    	 $("#divPage").hide();
                                    	 $("#exportBtn2").hide();
                                    	 showNo();
                                    }) 
                                    show();
									$("#exportBtn").hide();
									$("#table2").hide();
					            	function getVal() {
						            	comName = $("#comName").val();
						            	pageNo = $("#pageNo").val();
						            	id = $("#hidden2").val();
									}
                                    function show() {
                                    	getVal();
                                    	$.ajax({
											method : "post",
											url : "schemeDetails.novo",
											data : {
												"pageNo" : pageNo,
												"pageSize" : "10",
												"id" : id
											},
											async : true,
											success : function(pageBean) {
												console.log(pageBean)
												var str = "";
													for (var i = 0; i < pageBean.list.length; i++) {
														str += "<tr><td>"
	                                						+ pageBean.list[i].supp.name
	            											+ "</td><td>"
	            											+ pageBean.list[i].buyNum
	            											+ "</td><td>"
	            											+ pageBean.list[i].price
	            											+ "</td><td><input type='button' onclick='queryDetails("
	            											+ pageBean.list[i].id
	            											+ ")' class='btn' value='查看详情' /></td></tr>";
													}
												
												$("#tbody1").html(str);
												$("#pageNo").val(pageBean.pageNo);
												$("#pre_btn").html(
														"<a href='javascript:left("
																+ pageBean.pageNo + ")'>上一页</a>")
												$("#nextpage_btn")
														.html(
																"<a href='javascript:right("
																		+ pageBean.pageNo + ","
																		+ pageBean.totalPage
																		+ ")'>下一页</a>");
												$("#totalPage").text(pageBean.totalPage);
											}
										});
									}
                                    $("#findlikeBtn").click(function () {
                            			
                            			pageNo = 1;
                            			$("#pageNo").val(pageNo);
                            			show();
                            		})
                                    $("#firstpage_btn").click(function() {
									
										pageNo = 1;
										$("#pageNo").val(pageNo);
										show();
									});
									function left(page) {
										
										if (page > 1) {
											page -= 1;
										} else {
											page = 1;
										}
										$("#pageNo").val(page);
										show();
									};

									function right(page, total) {
										
										if (page < total) {
											page += 1;
										} else {
											page = total;
										}
										$("#pageNo").val(page);
										show();
									};
									$("#jumppage_btn").click(function() {
										pageNo = $("#pageNo").val();
										totalPage = $("#totalPage").text();
										var reg = /^([1-9][0-9]*)$/;
										if (!reg.test(pageNo)) {
											alert("请输入正确的页码");
											return;
										}
										if (pageNo > parseInt(totalPage)) {
											pageNo = totalPage;
										}
										if (pageNo < 1) {
											pageNo = 1;
										}
										$("#pageNo").val(pageNo);
										show();
									});
									$("#endpage_btn").click(function() {
										getVal();
										$("#pageNo").val($("#totalPage").text());
										show();
									})
						            
						            /*查看子方案详情*/
						            function queryDetails(id) {
										window.location.href="jumpChildSchemeDetails.novo?id="+id;
									}
									/*展示未采购商品*/
									function showNo() {
										var id = $("#hidden2").val();
										$.ajax({
											method : "post",
											url : "showNoBuy.novo",
											data : {
												"id": id
											},
											async : true,
											success : function(dto) {
												var list1 = dto.list1
												var list2 = dto.list2
												var str = "";
												for(var i=0;i<list1.length;i++){
													str += "<tr><td>"
														+ list1[i].erpNo
														+ "</td><td>"
														+ list1[i].comName
														+ "</td><td>"
														+ list1[i].spec
														+ "</td><td>"
														+ list1[i].licenseNo
														+ "</td><td>"
														+ list1[i].produceFact
														+ "</td><td>"
														+ list1[i].buyNum
														+ "</td><td>"
														+ list2[i]
														+ "</td></tr>"
												}
												$("#tbody2").html(str);
											}
										});
									}
									/* 导出未采购商品 */
									$("#exportBtn").click(function () {
										$(".table2excel").table2excel({
											exclude: ".noExl",
											name: "Apptoapi Excel",
											filename: "domain",
											exclude_img: true,
											exclude_links: true,
											exclude_inputs: true
											});

									})
									/* 导出已采购商品 */
									$("#exportBtn2").click(function () {
										var id = $("#hidden2").val();
										$.ajax({
											method : "post",
											url : "downloadBuyScheme.novo",
											data : {
												"id" : id
											},
											async : true,
											success : function(msg) {
												if(msg=="success"){
													alert("导出成功，请在D:/download/scheme下查看");
												}else{
													alert("导出失败");
												}
												show();
											}
										});
									})	
									
									</script> 

</body>
</html>


</html>