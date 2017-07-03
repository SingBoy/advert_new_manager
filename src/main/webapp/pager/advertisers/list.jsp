<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="page" uri="http://www.ibingo.net.cn/tags/pagination"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- Page Breadcrumb -->
	<div class="page-breadcrumbs">
		<ul class="breadcrumb">
			<li><i class="fa fa-home"></i>广告资源管理</li>
			<li class="active"><a href="${pageContext.request.contextPath}/advertisers/list">广告主管理</a></li>
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
			</a> <a class="refresh" id="refresh-toggler" href="${pageContext.request.contextPath}/advertisers/list"> <i class="glyphicon glyphicon-refresh"></i>
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
						<form action="${pageContext.request.contextPath}/advertisers/list" method="post" class="form-inline" role="form">
							<div class="row">
								<div class="col-lg-12 col-sm-12 col-xs-12">
									<div class="form-group">
										<input type="text" class="form-control" name="keyword" id="keyword" value="${queryBean.keyword }" placeholder="广告主名称/代码">
									</div>
									<button type="submit" class="btn btn-primary">
										<i class="fa fa-search"></i> 查询
									</button>
									<%--<a type="button" class="btn btn-primary" href="${pageContext.request.contextPath}/advertisers/doSave?keyword=${queryBean.keyword}&currentPage=${queryBean.currentPage}&pageSize=${queryBean.pageSize}"><i class="fa fa-plus"></i> 新增</a>--%>
									<a type="button" class="btn btn-info" href="${pageContext.request.contextPath}/advertisers/syncVoluumAffil"><i class="fa fa-asterisk"></i> 同步</a>
								</div>
							</div>
						</form>
						<br />
						<div class="flip-scroll">
							<table class="table table-hover table-bordered table-striped table-condensed flip-content">
								<thead class="flip-content bordered-palegreen">
									<tr>
										<th class="text-center" style="width:350px;">广告主名称</th>
										<%--<th class="text-center" style="width:200px;">代码</th>--%>
										<th class="text-center" style="width:250px;">更新时间</th>
										<th class="text-center" style="width:300px;">描述</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${pageDataList.pageRecords}" var="item">
										<tr>
											<td class="text-center" style="word-break:break-all;">${item.name }</td>
											<td class="text-center"><fmt:formatDate value="${item.modifyDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<%--<td class="text-center" style="word-break:break-all;">${item.code }</td>--%>
											<c:choose>
												<c:when test="${fn:length(item.description) ne 0}">
													<td class="text-center" style="word-break:break-all;">${item.description}</td>
												</c:when>
												<c:otherwise>
													<td class="text-center">-</td>
												</c:otherwise>
											</c:choose>

											<td class="text-center">
												<a href="${pageContext.request.contextPath}/advertisers/doUpdate/${item.id}?keyword=${queryBean.keyword}&currentPage=${queryBean.currentPage}&pageSize=${queryBean.pageSize}" class="btn btn-azure btn-xs shiny"><i class="fa fa-edit"></i> 编辑</a> 
												<a onclick="confirmImg(${item.id})" class="btn btn-darkorange btn-xs shiny"><i class="fa fa-trash-o"></i> 删除</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<hr class="wide" />
						<div class="footer">
							<!-- 分页  -->
							<c:url var="pageUrl" value="/advertisers/list">
								<c:param name="keyword" value="${queryBean.keyword}" />
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
		
		function confirmImg(id) {
			if (confirm("你确认要删除吗？")){
				window.location = "${pageContext.request.contextPath}/advertisers/delete/"+id +
				"?keyword="+$("#keyword").val()+"&currentPage="+${queryBean.currentPage}+"&pageSize="+${queryBean.pageSize};
			}
		}

	</script>
</body>
</html>