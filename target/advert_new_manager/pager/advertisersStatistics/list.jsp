<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="page" uri="http://www.ibingo.net.cn/tags/pagination"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/My97DatePicker/My97DatePicker/WdatePicker.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- Page Breadcrumb -->
	<div class="page-breadcrumbs">
		<ul class="breadcrumb">
			<li><i class="fa fa-home"></i>统计数据查询</li>
			<li class="active"><a href="${pageContext.request.contextPath}/advertisersStatistics/list">按广告主查询</a></li>
		</ul>
	</div>
	<!-- Page Header -->
	<div class="page-header position-relative">
<!-- 		<div class="header-title"> -->
<!-- 			<h1> -->
<!-- 				用户管理 <small> <i class="fa fa-angle-right"></i> 用户列表 -->
<!-- 				</small> -->
<!-- 			</h1> -->
<!-- 		</div> -->
		<!--Header Buttons-->
		<div class="header-buttons">
			<a class="sidebar-toggler" href="javascript:void(0);"> <i class="fa fa-arrows-h"></i>
			</a> <a class="refresh" id="refresh-toggler" href="${pageContext.request.contextPath}/notifyAnalysis/list"> <i class="glyphicon glyphicon-refresh"></i>
			</a> <a class="fullscreen" id="fullscreen-toggler" href="javascript:void(0);"> <i class="glyphicon glyphicon-fullscreen"></i>
			</a>
		</div>
		<!--Header Buttons End-->
	</div>
	<!-- /Page Header -->
	<!-- Page Body -->
	<div class="page-body">
		<div class="row">
			<div class="col-xs-12 col-md-12">
				<div class="widget">
					<div class="widget-header  with-footer">
						<span class="widget-caption"><i class="fa  fa-table">页面列表</i></span>
						<div class="widget-buttons">
							<a href="javascript:void(0);" data-toggle="maximize"> <i class="fa fa-expand"></i></a> <a href="javascript:void(0);" data-toggle="collapse"> <i class="fa fa-minus"></i></a>
						</div>
					</div>
					<div class="widget-body">
						<form id="advertisersStatistics_form" action="${pageContext.request.contextPath}/advertisersStatistics/list" method="post" class="form-inline" role="form">
							<div class="row">
								<div class="col-lg-12 col-sm-12 col-xs-12">
									<div class="form-group">
										<input type="text" name="startDate" id="startDate" value="${queryBean.startDate }" style="height: 32px;" class="Wdate" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'})" placeholder="开始时间">
									</div>
									<div class="form-group">
										<input type="text" name="endDate" id="endDate" value="${queryBean.endDate }" style="height: 32px;" class="Wdate" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'})" placeholder="结束时间">
									</div>
									<%--<c:if test="${sessionScope.current_user.userRole != 2}">
									<div class="form-group">
										<select id="advertisersName" name="advertisersName" class="multiselect" style="width: 210px;">
											<option value="">广告主</option>
											<c:forEach items="${listAdvertisers}" var="item">
												<option value="${item.name }" <c:if test="${queryBean.advertisersName== item.name}">selected="selected"</c:if> >
													<c:if test="${item.name != null}">
														${item.name }
													</c:if>
												</option>
											</c:forEach>
										</select>
									</div>
									</c:if>--%>
									<div class="form-group">
										<select id="offerId" name="offerId" class="multiselect" style="width: 210px;">
											<option value="">资源名称</option>
											<c:forEach items="${listResources}" var="item">
												<option value="${item.voluumOfferId }" <c:if test="${queryBean.offerId== item.voluumOfferId}">selected="selected"</c:if> >
													<c:if test="${item.name != null}">
														${item.name } 
													</c:if>
												</option>
											</c:forEach> 
										</select>
									</div>
									<div class="form-group">
										<select id="country" name="country" class="multiselect" style="width: 210px;">
											<option value="">国家</option>
											<c:forEach items="${listMcc}" var="item">
												<option value="${item.iso }" <c:if test="${queryBean.country== item.iso}">selected="selected"</c:if> >
													<c:if test="${item.country != null}">
														${item.country }
													</c:if>
												</option>
											</c:forEach>
										</select>
									</div>
									<%--<div class="form-group">
										<select id="trafficSourceId" name="trafficSourceId" class="multiselect" style="width: 210px;">
											<option value="">渠道</option>
											<c:forEach items="${listFristChannel}" var="item">
												<option value="${item.voluumTrafficSourceId }" <c:if test="${queryBean.trafficSourceId== item.voluumTrafficSourceId}">selected="selected"</c:if> >
													<c:if test="${item.name != null}">
														${item.name }
													</c:if>
												</option>
											</c:forEach>
										</select>
									</div>--%>
									<button type="submit" class="btn btn-primary">
										<i class="fa fa-search"></i> 查询
									</button>
								</div>
							</div>
						</form>
						<br />
						<div class="flip-scroll">
							<table class="table table-hover table-bordered table-striped table-condensed flip-content">
								<thead class="flip-content bordered-palegreen">
									<tr>
										<th class="text-center" style="width:100px;">日期</th>
										<th class="text-center" style="width:100px;">国家</th>
										<th class="text-center" style="width:300px;">资源名称</th>
										<th class="text-center" style="width:250px;">资源别名</th>
										<th class="text-center" style="width:250px;">广告主名称</th>
										<th class="text-center" style="width:80px;">访问数量</th>
										<th class="text-center" style="width:80px;">转化数量</th>
										<th class="text-center" style="width:80px;">转化率</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${pageDataList.pageRecords}" var="item">
										<tr>
											<td class="text-center" style="word-break:break-all;"><fmt:formatDate value="${item.date}" pattern="yyyy-MM-dd" /></td>
											<td class="text-center" style="word-break:break-all;">${item.country }</td>
											<td class="text-center" style="word-break:break-all;">${item.offerName }</td>
											<td class="text-center" style="word-break:break-all;">${item.offerNameAlias }</td>
											<td class="text-center" style="word-break:break-all;">${item.affiliateNetworkName }</td>
											<td class="text-center" style="word-break:break-all;">${item.visitsNum}</td>
											<td class="text-center" style="word-break:break-all;">${item.conversNum}</td>
											<td class="text-center" style="word-break:break-all;">
												<c:choose>
													<c:when test="${item.conversNum > 0}">
														<fmt:formatNumber type="number" value="${item.visitsNum/item.conversNum * 100}" pattern="0.00" maxFractionDigits="2"/>
													</c:when>
													<c:otherwise>
														0.00
													</c:otherwise>
												</c:choose>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<hr class="wide" />
						<div class="footer">
							<!-- 分页  -->
							<c:url var="pageUrl" value="/advertisersStatistics/list">
								<c:param name="startDate" value="${queryBean.startDate}" />
								<c:param name="endDate" value="${queryBean.endDate}" />
								<c:param name="offerId" value="${queryBean.offerId}" />
								<c:param name="offerNameAlias" value="${queryBean.offerNameAlias}" />
							</c:url>
							<page:pagerNav modelRef="pageDataList" url="${pageUrl}" type="beyond" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /Page Body -->
	<script type="text/javascript">
		$(document).ready(function() {
			$(".multiselect").select2();            
		});
		
/* 		$("#adsId").change(function(){
			var str = "<option value=\"\">请选择</option>";
			$.ajax({
				url: "${pageContext.request.contextPath}/resources/byAdsId",
	            type :"GET",
	            data:{adsId:$("#adsId").val()},
	            dataType : "json",
				success: function(resources){
					$.each(resources, function(index, resource){
						str += "<option value=\""+resource['id']+"\">"+resource['name']+"</option>";
					});
					//str += "</select>";
					$("#twoCode").html(str);
		      	}
	    	});
		}); */
		
		$("#submitInput").click(function(){
			var startDate=$("#startDate").val();
			var endDate=$("#endDate").val();

			if(startDate != "" && endDate !=""){
			    var start = new Date(startDate.replace("-", "/").replace("-", "/"));
			    var end = new Date(endDate.replace("-", "/").replace("-", "/"));
			    var d = new Date();
			    
			    if(start > d || end > d){
					bootbox.dialog({
				    	message: "日期不得大于当前日期！！！",
				        title: "警告",
				        className: "",
				    });
			    }else{
				    if(end<start){
						bootbox.dialog({
					    	message: "开始日期不得大于结束日期！！！",
					        title: "警告",
					        className: "",
					    });
				    }else{
				    	$("#advertisersStatistics_form").submit();
				    }
			    }
			}
		});
	</script>
</body>
</html>