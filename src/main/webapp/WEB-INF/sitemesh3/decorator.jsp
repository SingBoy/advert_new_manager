<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<!-- Head -->
<head>
<base href="<%=basePath%>" />
<meta charset="utf-8" />
<title>广告资源管理平台</title>

<meta name="description" content="Dashboard" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%-- <link rel="shortcut icon" href="${pageContext.request.contextPath}/plugins/img/favicon.png" type="image/x-icon"> --%>


<!--Basic Styles-->
<link href="${pageContext.request.contextPath}/plugins/simpletree/SimpleTree.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/plugins/beyond/css/bootstrap.min.css" rel="stylesheet" />

<link id="bootstrap-rtl-link" href="" rel="stylesheet" />

<link href="${pageContext.request.contextPath}/plugins/beyond/css/font-awesome.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/plugins/beyond/css/weather-icons.min.css" rel="stylesheet" />

<!--Fonts-->
<!-- <link href="http://fonts.useso.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,600,700,300" rel="stylesheet" type="text/css"> -->

<!--Beyond styles-->
<link id="beyond-link" href="${pageContext.request.contextPath}/plugins/beyond/css/beyond.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/plugins/beyond/css/demo.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/plugins/beyond/css/typicons.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/plugins/beyond/css/animate.min.css" rel="stylesheet" />
<link id="skin-link" href="" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/simpletree/SimpleTree.css"/>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"/>


<!-- <script src="plugins/jquery/jquery-1.11.1.min.js"></script> -->
<script src="${pageContext.request.contextPath}/plugins/jquery/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/simpletree/SimpleTree.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/jquery-validate/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/jquery-validate/additional-methods.js"></script>
<!-- <script src="plugins/beyond/js/jquery-2.0.3.min.js"></script> -->

<!--Skin Script: Place this script in head to load scripts for skins and rtl support-->
<script src="${pageContext.request.contextPath}/plugins/beyond/js/skins.min.js"></script>
<sitemesh:write property="head" />
</head>
<!-- /Head -->
<!-- Body -->
<body>
	<!-- Loading Container -->
	<div class="loading-container">
		<div class="loading-progress">
			<div class="rotator">
				<div class="rotator">
					<div class="rotator colored">
						<div class="rotator">
							<div class="rotator colored">
								<div class="rotator colored"></div>
								<div class="rotator"></div>
							</div>
							<div class="rotator colored"></div>
						</div>
						<div class="rotator"></div>
					</div>
					<div class="rotator"></div>
				</div>
				<div class="rotator"></div>
			</div>
			<div class="rotator"></div>
		</div>
	</div>
	<!--  /Loading Container -->
	<!-- Navbar -->
	<div class="navbar">
		<div class="navbar-inner">
			<div class="navbar-container">
				<!-- Navbar Barnd -->
				<div class="navbar-header pull-left">
					<a href="javascript:void(0);" class="navbar-brand"> 
						<small> 
							广告资源管理平台
						</small>
					</a>
				</div>
				<!-- /Navbar Barnd -->

				<!-- Sidebar Collapse -->
				<div class="sidebar-collapse" id="sidebar-collapse">
					<i class="collapse-icon fa fa-bars"></i>
				</div>
				<!-- /Sidebar Collapse -->
				<!-- Account Area and Settings --->
				<div class="navbar-header pull-right">
					<div class="navbar-account">
						<ul class="account-area">
								
							<li><a class="login-area dropdown-toggle" data-toggle="dropdown">
									<div class="avatar" title="View your public profile">
										 <img src="plugins/beyond/img/avatars/adam-jansen.jpg"> 
									</div>
									<section>
										<h2>
											<span class="profile"><span>&nbsp;欢迎您，${sessionScope.current_user.nickname }&nbsp;</span></span>
										</h2>
									</section>
							</a> <!--Login Area Dropdown-->
								<ul class="pull-right dropdown-menu dropdown-arrow dropdown-login-area">
									<li class="username"><a>${sessionScope.current_user.nickname }</a></li>
									<li class="email"><a>${sessionScope.current_user.email }</a></li>
									<li class="theme-area">
										<ul class="colorpicker" id="skin-changer">
											<li><a class="colorpick-btn" href="javascript:void(0);" style="background-color: #5DB2FF;" rel="plugins/beyond/css/skins/blue.min.css"></a></li>
											<li><a class="colorpick-btn" href="javascript:void(0);" style="background-color: #2dc3e8;" rel="plugins/beyond/css/skins/azure.min.css"></a></li>
											<li><a class="colorpick-btn" href="javascript:void(0);" style="background-color: #03B3B2;" rel="plugins/beyond/css/skins/teal.min.css"></a></li>
											<li><a class="colorpick-btn" href="javascript:void(0);" style="background-color: #53a93f;" rel="plugins/beyond/css/skins/green.min.css"></a></li>
											<li><a class="colorpick-btn" href="javascript:void(0);" style="background-color: #FF8F32;" rel="plugins/beyond/css/skins/orange.min.css"></a></li>
											<li><a class="colorpick-btn" href="javascript:void(0);" style="background-color: #cc324b;" rel="plugins/beyond/css/skins/pink.min.css"></a></li>
											<li><a class="colorpick-btn" href="javascript:void(0);" style="background-color: #AC193D;" rel="plugins/beyond/css/skins/darkred.min.css"></a></li>
											<li><a class="colorpick-btn" href="javascript:void(0);" style="background-color: #8C0095;" rel="plugins/beyond/css/skins/purple.min.css"></a></li>
											<li><a class="colorpick-btn" href="javascript:void(0);" style="background-color: #0072C6;" rel="plugins/beyond/css/skins/darkblue.min.css"></a></li>
											<li><a class="colorpick-btn" href="javascript:void(0);" style="background-color: #585858;" rel="plugins/beyond/css/skins/gray.min.css"></a></li>
											<li><a class="colorpick-btn" href="javascript:void(0);" style="background-color: #474544;" rel="plugins/beyond/css/skins/black.min.css"></a></li>
											<li><a class="colorpick-btn" href="javascript:void(0);" style="background-color: #001940;" rel="plugins/beyond/css/skins/deepblue.min.css"></a></li>
										</ul>
									</li>
									<!--/Theme Selector Area-->
									<li class="dropdown-footer"><a href="${pageContext.request.contextPath}/logout"> 退出 </a></li>
								</ul> <!--/Login Area Dropdown--></li>
							<!-- /Account Area -->
							<!--Note: notice that setting div must start right after account area list.
                            no space must be between these elements-->
							<!-- Settings -->
						</ul>
						<div class="setting">
							<a id="btn-setting" title="Setting" href="javascript:void(0);"> <i class="icon glyphicon glyphicon-cog"></i>
							</a>
						</div>
						<div class="setting-container">
							<label> <input type="checkbox" id="checkbox_fixednavbar"> <span class="text">固定导航栏</span>
							</label> <label> <input type="checkbox" id="checkbox_fixedsidebar"> <span class="text">固定侧边栏</span>
							</label> <label> <input type="checkbox" id="checkbox_fixedbreadcrumbs"> <span class="text">固定面包屑</span>
							</label> <label> <input type="checkbox" id="checkbox_fixedheader"> <span class="text">固定页头</span>
							</label>
						</div>
						<!-- Settings -->
					</div>
				</div>
				<!-- /Account Area and Settings -->
			</div>
		</div>
	</div>
	<!-- /Navbar -->
	<!-- Main Container -->
	<div class="main-container container-fluid">
		<!-- Page Container -->
		<div class="page-container">
			<!-- Page Sidebar -->
			<div class="page-sidebar" id="sidebar">
				<!-- Page Sidebar Header-->
<!-- 				<div class="sidebar-header-wrapper"> -->
<!-- 					<input type="text" class="searchinput" /> <i class="searchicon fa fa-search"></i> -->
<!-- 					<div class="searchhelper">Search Reports, Charts, Emails or Notifications</div> -->
<!-- 				</div> -->
				<!-- /Page Sidebar Header -->
				<!-- Sidebar Menu -->
					<ul class="nav sidebar-menu">
						<%--<li id="li0">
							<a href="${pageContext.request.contextPath}/welcome/index" class="menu-dropdown"> 
								<i class="menu-icon typcn typcn-home"></i> 
								<span class="menu-text">首页</span>
							</a>
						</li>--%>

							<li id="li0">
								<a href="javascript:void(0);" class="menu-dropdown">
									<i class="menu-icon fa fa-briefcase"></i>
									<span class="menu-text">原始数据查询</span>
								</a>
								<ul class="submenu">
									<li>
										<a href="${pageContext.request.contextPath}/voluum/list">
											<i class="menu-icon typcn typcn-clipboard"></i>
											<span class="menu-text">数据查询</span>
										</a>
									</li>
								</ul>
							</li>
							<li id="li1">
								<a href="javascript:void(0);" class="menu-dropdown">
									<i class="menu-icon fa fa-briefcase"></i>
									<span class="menu-text">统计数据查询</span>
								</a>
								<ul class="submenu">
									<c:if test="${sessionScope.current_user.userRole == 1}">
										<li>
											<a href="${pageContext.request.contextPath}/offerStatistics/list">
												<i class="menu-icon typcn typcn-clipboard"></i>
												<span class="menu-text">按广告查询</span>
											</a>
										</li>
										<li>
											<a href="${pageContext.request.contextPath}/trafficSourceStatistics/list">
												<i class="menu-icon typcn typcn-document-text"></i>
												<span class="menu-text">按渠道查询</span>
											</a>
										</li>
										<li>
											<a href="${pageContext.request.contextPath}/trafficSourceStatistics/rateList">
												<i class="menu-icon typcn typcn-clipboard"></i>
												<span class="menu-text">以渠道身份查询</span>
											</a>
										</li>
									</c:if>
									<c:if test="${sessionScope.current_user.userRole == 2 }">
										<li>
											<a href="${pageContext.request.contextPath}/advertisersStatistics/list">
												<i class="menu-icon typcn typcn-document-text"></i>
												<span class="menu-text">按广告主查询</span>
											</a>
										</li>
									</c:if>
									<c:if test="${sessionScope.current_user.userRole == 3 }">
										<li>
											<a href="${pageContext.request.contextPath}/trafficSourceStatistics/rateList">
												<i class="menu-icon typcn typcn-document-text"></i>
												<span class="menu-text">按渠道查询</span>
											</a>
										</li>
									</c:if>
								</ul>
							</li>
						<c:if test="${sessionScope.current_user.userRole == 1 }">
						<li id="li2">
							<a href="javascript:void(0);" class="menu-dropdown">
								<i class="menu-icon fa fa-desktop"></i>
								<span class="menu-text">广告资源管理</span>
							</a>
							<ul class="submenu">
								<li>
									<a href="${pageContext.request.contextPath}/resources/list">
										<i class="menu-icon typcn typcn-contacts"></i> 
										<span class="menu-text">广告管理</span>
									</a>
								</li>
								<li>
									<a href="${pageContext.request.contextPath}/advertisers/list">
										<i class="menu-icon typcn typcn-document"></i> 
										<span class="menu-text">广告主管理</span>
									</a>
								</li>
								<%--<li>
									<a href="${pageContext.request.contextPath}/mnc/list"> 
										<i class="menu-icon typcn typcn-mortar-board"></i> 
										<span class="menu-text">国家运营商</span>
									</a>
								</li>
								<li>
									<a href="${pageContext.request.contextPath}/ipArea/list"> 
										<i class="menu-icon typcn typcn-chart-area-outline"></i> 
										<span class="menu-text">IP段管理</span>
									</a>
								</li>
								<li>
									<a href="${pageContext.request.contextPath}/default/list"> 
										<i class="menu-icon typcn typcn-puzzle-outline"></i> 
										<span class="menu-text">默认地址配置</span>
									</a>
								</li>
								<li>
									<a href="${pageContext.request.contextPath}/timezoneCountry/list"> 
										<i class="menu-icon typcn typcn-world-outline"></i> 
										<span class="menu-text">国家时区配置</span>
									</a>
								</li>--%>
							</ul>
						</li>
						</c:if>
						<c:if test="${sessionScope.current_user.userRole != 2}">
						<li id="li3">
							<a href="javascript:void(0);" class="menu-dropdown">
								<i class="menu-icon fa fa-folder-open"></i> 
								<span class="menu-text">渠道管理</span>
							</a>
							<ul class="submenu">
								<c:if test="${sessionScope.current_user.userRole != 2 && sessionScope.current_user.userRole != 4}">
								<li>
									<a href="${pageContext.request.contextPath}/fristChannel/list"> 
										<i class="menu-icon typcn typcn-tag"></i> 
										<span class="menu-text">一级渠道</span>
									</a>
								</li>
								</c:if>
								<%--<li>
									<a href="${pageContext.request.contextPath}/twoChannel/list"> 
										<i class="menu-icon typcn typcn-tags"></i> 
										<span class="menu-text">二级渠道</span>
									</a>
								</li>--%>
							</ul>
						</li>
						</c:if>
						<c:if test="${sessionScope.current_user.userRole == 1 || sessionScope.current_user.userRole == 3}">
						<li id="li4">
							<a href="javascript:void(0);" class="menu-dropdown"> 
								<i class="menu-icon fa fa-user"></i> 
								<span class="menu-text">账户管理</span>
							</a>
							<ul class="submenu">
								<li>
									<a href="${pageContext.request.contextPath}/user/list"> 
										<i class="menu-icon typcn typcn-user"></i> 
										<span class="menu-text">账户管理</span>
									</a>
								</li>
							</ul>
						</li>
						</c:if>
					</ul>
				<!-- /Sidebar Menu -->
			</div>
			<!-- /Page Sidebar -->
			<!-- Page Content -->
			<div class="page-content">
				<sitemesh:write property="body" />
			</div>
			<!-- /Page Content -->
		</div>
		<!-- /Page Container -->
		<!-- Main Container -->

	</div>

	<!--Basic Scripts-->
	
<!-- 	<script src="plugins/beyond/js/jquery-2.0.3.min.js"></script> -->
	<script src="${pageContext.request.contextPath}/plugins/beyond/js/bootstrap.min.js"></script>

	<!--Beyond Scripts-->
	<script src="${pageContext.request.contextPath}/plugins/beyond/js/beyond.min.js"></script>


	<!--Page Related Scripts-->
	<script src="${pageContext.request.contextPath}/plugins/beyond/js/select2/select2.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/beyond/js/validation/bootstrapValidator.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/beyond/js/bootbox/bootbox.js"></script>
	
	<script src="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"  charset="UTF-8"></script>
	<script src="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"  charset="UTF-8"></script>
<%-- 	<script src="${pageContext.request.contextPath}/plugins/beyond/js/datetime/bootstrap-datepicker.js"></script> --%>
	
	
	
	<!--Sparkline Charts Needed Scripts-->
	<script src="${pageContext.request.contextPath}/plugins/beyond/js/charts/sparkline/jquery.sparkline.js"></script>
	
    

	<!--Easy Pie Charts Needed Scripts-->
	<script src="${pageContext.request.contextPath}/plugins/beyond/js/charts/easypiechart/jquery.easypiechart.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/beyond/js/charts/easypiechart/easypiechart-init.js"></script>

	<!--Flot Charts Needed Scripts-->
	<script src="${pageContext.request.contextPath}/plugins/beyond/js/charts/flot/jquery.flot.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/beyond/js/charts/flot/jquery.flot.resize.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/beyond/js/charts/flot/jquery.flot.pie.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/beyond/js/charts/flot/jquery.flot.tooltip.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/beyond/js/charts/flot/jquery.flot.orderBars.js"></script>
	<script type="text/javascript">
		$(function(){
			var oneTree = ${queryBean.oneTree};
			var twoTree = ${queryBean.twoTree};
			
			//清除一级菜单样式
			$("#li0").removeAttr("class");
			$("#li1").removeAttr("class");
			$("#li2").removeAttr("class");
			$("#li3").removeAttr("class");
			$("#li4").removeAttr("class");
			//清除二级菜单样式
			$("#li1").find('li').removeAttr("class");
			$("#li2").find('li').removeAttr("class");
			$("#li3").find('li').removeAttr("class");
			$("#li4").find('li').removeAttr("class");
            if(oneTree == 0){
                $("#li0").attr("class","active open");
                if(twoTree == 1){
                    $("#li0").children().eq(1).children().eq(0).attr("class","active");
                }
            }else if(oneTree == 1){
				$("#li1").attr("class","active open");
				if(twoTree == 1){
					$("#li1").children().eq(1).children().eq(0).attr("class","active");
				}else if(twoTree == 2){
					$("#li1").children().eq(1).children().eq(1).attr("class","active");
				}else if(twoTree == 3){
                    $("#li1").children().eq(1).children().eq(2).attr("class","active");
                }
			}else if(oneTree == 2){
				$("#li2").attr("class","active open");
				if(twoTree == 1){
					$("#li2").children().eq(1).children().eq(0).attr("class","active");
				}else if(twoTree == 2){
					$("#li2").children().eq(1).children().eq(1).attr("class","active");
				}else if(twoTree == 3){
					$("#li2").children().eq(1).children().eq(2).attr("class","active");
				}else if(twoTree == 4){
					$("#li2").children().eq(1).children().eq(3).attr("class","active");
				}else if(twoTree == 5){
					$("#li2").children().eq(1).children().eq(4).attr("class","active");
				}else if(twoTree == 6){
					$("#li2").children().eq(1).children().eq(5).attr("class","active");
				}
			}else if(oneTree == 3){
				$("#li3").attr("class","active open");
				if(twoTree == 1){
					$("#li3").children().eq(1).children().eq(0).attr("class","active");
				}else if(twoTree == 2){
					$("#li3").children().eq(1).children().eq(1).attr("class","active");
				}
			}else if(oneTree == 4){
				$("#li4").attr("class","active open");
				if(twoTree == 1){
					$("#li4").children().eq(1).children().eq(0).attr("class","active");
				}
			}
		});
	</script>
</body>
<!--  /Body -->
</html>
