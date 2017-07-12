<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>广告资源管理平台</title>
</head>
<body>
	
<!-- 	<br/>
	<center>
		<h2>欢迎使用运营服务平台！</h2>
	</center> -->
<!-- 	<div class="widget-body" style="height:400px;"> -->
		<br/>
		<%--<div class="form-group">
			<label for="name" class="col-sm-1 control-label no-padding-right"></label>
			<div class="col-sm-10">
				<input type="hidden" class="form-control" name="id" id="id" value="">
				<label style="font-weight:bold;font-size:16px;">首页</label>
			</div>
		</div>
		<br/>
		<br/>
		<div class="form-group">
			<label for="name" class="col-sm-1 control-label no-padding-right"></label>
			<div class="col-sm-10">
				<input type="hidden" class="form-control" name="id" id="id" value="">
				<label style="font-weight:bold;font-size:16px;">昨日广告数据概况：</label>
			</div>
		</div>	
		<br/>
		<div class="form-group">
			<label for="name" class="col-sm-1 control-label no-padding-right"></label>
			<div class="col-sm-10">
				<input type="hidden" class="form-control" name="id" id="id" value="">
				<label style="font-weight:bold;font-size:14px;">访问次数：<nobr style="color:red;">${index.yesterdayUv}</nobr>个</label>
			</div>
		</div>
		<br/>
		<div class="form-group">
			<label for="name" class="col-sm-1 control-label no-padding-right"></label>
			<div class="col-sm-10">
				<input type="hidden" class="form-control" name="id" id="id" value="">
				<label style="font-weight:bold;font-size:14px;">订阅成功数：<nobr style="color:red;">${index.yesterdaySs}</nobr>个</label>
			</div>
		</div>
		<br/>
		<c:if test="${sessionScope.current_user.userRole == 1}">
		<div class="form-group">
			<label for="name" class="col-sm-1 control-label no-padding-right"></label>
			<div class="col-sm-10">
				<input type="hidden" class="form-control" name="id" id="id" value="">
				<label style="font-weight:bold;font-size:14px;">预计收入：
					$<nobr style="color:red;">
						<fmt:formatNumber type="number" value="${index.yesterdayPrice * index.yesterdaySs}" pattern="0.00" maxFractionDigits="2"/>
					</nobr>USD
				</label>
			</div>
		</div>
		<br/>
		</c:if>
		<c:if test="${sessionScope.current_user.userRole == 1}">
		<div class="form-group">
			<label class="col-sm-1 control-label no-padding-right"></label>
			<div class="col-sm-10">
				<input type="hidden" class="form-control" name="id" id="id" value="">
				<label style="font-weight:bold;font-size:14px;">
					累计访问次数：<nobr style="color:red;">${index.uv}</nobr>个&nbsp;&nbsp;
					累计订阅成功数：<nobr style="color:red;">${index.ss}</nobr>个
				</label>
			</div>
		</div>
		</c:if>
		<br/>
		<br/>
		<c:if test="${sessionScope.current_user.userRole == 1 || sessionScope.current_user.userRole == 2}">
		<div class="form-group">
			<label for="name" class="col-sm-1 control-label no-padding-right"></label>
			<div class="col-sm-10">
				<input type="hidden" class="form-control" name="id" id="id" value="">
				<label style="font-weight:bold;font-size:14px;">
					现有广告资源：<nobr style="color:red;">${index.resourcesCount}</nobr>个，
					合作广告主：<nobr style="color:red;">${index.advertisersCount}</nobr>个
				</label>
			</div>
		</div>
		<br/>
		</c:if>
		<c:if test="${sessionScope.current_user.userRole == 1 || sessionScope.current_user.userRole == 2}">
		<div class="form-group">
			<label for="name" class="col-sm-1 control-label no-padding-right"></label>
			<div class="col-sm-10">
				<input type="hidden" class="form-control" name="id" id="id" value="">
				<label style="font-weight:bold;font-size:14px;">
					覆盖国家：<nobr style="color:red;">${index.mccCount}</nobr>个，
					运营商<nobr style="color:red;">${index.mncCount}</nobr>个
				</label>
			</div>
		</div>
		<br/>
		</c:if>
		<c:if test="${sessionScope.current_user.userRole == 1}">
		<div class="form-group">
			<label for="name" class="col-sm-1 control-label no-padding-right"></label>
			<div class="col-sm-10">
				<input type="hidden" class="form-control" name="id" id="id" value="">
				<label style="font-weight:bold;font-size:14px;">现有推广渠道： 
					<nobr style="color:red;">${index.channelCount}</nobr>个
				</label>
			</div>
		</div>
		</c:if>--%>
<!-- 	</div> -->
</body>
</html>