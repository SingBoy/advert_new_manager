/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.37
 * Generated at: 2017-07-03 11:06:41 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.pager.fristChannel;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class form_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("<title>Insert title here</title>\n");
      out.write("<style type=\"text/css\">\n");
      out.write("\t.error {\n");
      out.write("/* \t\tmargin-left: 10px; */\n");
      out.write("/* \t\twidth: auto; */\n");
      out.write("/* \t\tdisplay: inline; */\n");
      out.write("\t\tcolor:red;\n");
      out.write("\t}\n");
      out.write("</style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\t<!-- Page Breadcrumb -->\n");
      out.write("\t<div class=\"page-breadcrumbs\">\n");
      out.write("\t\t<ul class=\"breadcrumb\">\n");
      out.write("\t\t\t<li><i class=\"fa fa-home\"></i>渠道管理</li>\n");
      out.write("\t\t\t<li><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/fristChannel/list\">一级渠道</a></li>\n");
      out.write("\t\t\t<li class=\"active\"> ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fristChannel eq null ? \"新增\" : \"编辑\" }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("一级渠道</li>\n");
      out.write("\t\t</ul>\n");
      out.write("\t</div>\n");
      out.write("\t<!-- Page Header -->\n");
      out.write("\t<div class=\"page-header position-relative\">\n");
      out.write("\t\t<!-- \t\t<div class=\"header-title\"> -->\n");
      out.write("\t\t<!-- \t\t\t<h1> -->\n");
      out.write("\t\t<!-- \t\t\t\t用户管理 <small> <i class=\"fa fa-angle-right\"></i> ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user eq null ? \"新增\" : \"编辑\" }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("用户 -->\n");
      out.write("\t\t<!-- \t\t\t\t</small> -->\n");
      out.write("\t\t<!-- \t\t\t</h1> -->\n");
      out.write("\t\t<!-- \t\t</div> -->\n");
      out.write("\t\t<!--Header Buttons-->\n");
      out.write("\t\t<div class=\"header-buttons\">\n");
      out.write("\t\t\t<a class=\"sidebar-toggler\" href=\"javascript:void(0);\"> <i class=\"fa fa-arrows-h\"></i>\n");
      out.write("\t\t\t</a> <a class=\"refresh\" id=\"refresh-toggler\" href=\"javascript:void(0);\"> <i class=\"glyphicon glyphicon-refresh\"></i>\n");
      out.write("\t\t\t</a> <a class=\"fullscreen\" id=\"fullscreen-toggler\" href=\"javascript:void(0);\"> <i class=\"glyphicon glyphicon-fullscreen\"></i>\n");
      out.write("\t\t\t</a>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<!--Header Buttons End-->\n");
      out.write("\t</div>\n");
      out.write("\t<!-- /Page Header -->\n");
      out.write("\n");
      out.write("\t<!-- Page Body -->\n");
      out.write("\t<div class=\"page-body\">\n");
      out.write("\t\t<div class=\"row\">\n");
      out.write("\t\t\t<div class=\"col-lg-12 col-xs-12 col-md-12\">\n");
      out.write("\t\t\t\t<div class=\"widget\">\n");
      out.write("\t\t\t\t\t<div class=\"widget-header bordered-bottom bordered-palegreen\">\n");
      out.write("\t\t\t\t\t\t<span class=\"widget-caption\"><i class=\"fa  fa-table\"> ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fristChannel eq null ? \"新增\" : \"编辑\" }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("一级渠道</i></span>\n");
      out.write("\t\t\t\t\t\t<div class=\"widget-buttons\">\n");
      out.write("\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" data-toggle=\"maximize\"> <i class=\"fa fa-expand\"></i></a> <a href=\"javascript:void(0);\" data-toggle=\"collapse\"> <i class=\"fa fa-minus\"></i></a>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t<div class=\"widget-body\">\n");
      out.write("\t\t\t\t\t\t<form id=\"fristChannel_form\" class=\"form-horizontal\" action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/fristChannel/");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fristChannel eq null ? 'save' : 'update' }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" method=\"post\" role=\"form\">\n");
      out.write("\t\t\t\t\t\t\t<input type=\"hidden\" name=\"id\" id=\"id\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fristChannel.id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">\n");
      out.write("\t\t\t\t\t\t\t<input type=\"hidden\" name=\"voluumTrafficSourceId\" id=\"voluumTrafficSourceId\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fristChannel.voluumTrafficSourceId}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">\n");
      out.write("\t\t\t\t\t\t\t<input type=\"hidden\" name=\"keyword\" id=\"keyword\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${queryBean.keyword}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">\n");
      out.write("\t\t\t\t\t\t\t<input type=\"hidden\" name=\"currentPage\" id=\"currentPage\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${queryBean.currentPage}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">\n");
      out.write("\t\t\t\t\t\t\t<input type=\"hidden\" name=\"pageSize\" id=\"pageSize\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${queryBean.pageSize}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">\n");
      out.write("\t\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t\t<label for=\"name\" class=\"col-sm-4 control-label no-padding-right\">渠道名称</label>\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"col-sm-4\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"name\" name=\"name\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fristChannel.name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" placeholder=\"渠道名称\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<label class=\"error\" for=\"name\" style=\"display:none;\">请填写渠道名称</label>\n");
      out.write("\t\t\t\t\t\t\t\t\t<label class=\"error\" for=\"name\" style=\"display:none;\">渠道名称已存在</label>\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t\t<label for=\"name\" class=\"col-sm-4 control-label no-padding-right\">分成比例</label>\n");
      out.write("\t\t\t\t\t\t\t\t<div style = \"display:inline;\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<div style=\"float:left\" class=\"col-sm-3\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"dividedRate\" name=\"dividedRate\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fristChannel.dividedRate}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" placeholder=\"分成比例\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<label class=\"error\" for=\"dividedRate\" style=\"display:none;\">请填写分成比例</label>\n");
      out.write("\t\t\t\t\t\t\t\t\t<label class=\"error\" for=\"dividedRate\" style=\"display:none;\">不能大于1</label>\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t\t\t<div style=\"float:left;padding-top:5px;\" class=\"col-sm-2\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<nobr style=\"font-size:14px;color:red;\">(0-1)</nobr>\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t\t<label for=\"name\" class=\"col-sm-4 control-label no-padding-right\">订阅量分配比例</label>\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t\t\t");
      out.write("\n");
      out.write("\t\t\t\t\t\t\t\t<div style=\"float:left\" class=\"col-sm-3\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<a type=\"button\" id=\"lookOver\" class=\"btn btn-primary\" onclick=\"ModalDialog()\"><i class=\"fa fa-plus\"></i> 设置分配比例</a>\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t\t<label for=\"name\" class=\"col-sm-4 control-label no-padding-right\">回调地址(<nobr style=\"font-size:14px;color:red;\">http://打头</nobr>)</label>\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"col-sm-4\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"callbackUrl\" name=\"callbackUrl\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fristChannel.callbackUrl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" placeholder=\"回调地址\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<label class=\"error\" for=\"callbackUrl\" style=\"display:none;\">请填写回调地址</label>\n");
      out.write("\t\t\t\t\t\t\t\t\t<label class=\"error\" for=\"callbackUrl\" style=\"display:none;\">回调地址格式不对</label>\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t\t<label for=\"name\" class=\"col-sm-4 control-label no-padding-right\">参数1名称</label>\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"col-sm-3\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"p1\" name=\"p1\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fristChannel.p1}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" placeholder=\"参数1名称\">\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t\t<label for=\"name\" class=\"col-sm-4 control-label no-padding-right\">参数2名称</label>\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"col-sm-3\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"p2\" name=\"p2\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fristChannel.p2}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" placeholder=\"参数2名称\">\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t\t<label for=\"name\" class=\"col-sm-4 control-label no-padding-right\">参数3名称</label>\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"col-sm-3\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"p3\" name=\"p3\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fristChannel.p3}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" placeholder=\"参数3名称\">\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t\t<label for=\"name\" class=\"col-sm-4 control-label no-padding-right\">参数4名称</label>\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"col-sm-3\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"p4\" name=\"p4\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fristChannel.p4}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" placeholder=\"参数4名称\">\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t\t<label for=\"name\" class=\"col-sm-4 control-label no-padding-right\">参数5名称</label>\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"col-sm-3\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"p5\" name=\"p5\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fristChannel.p5}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" placeholder=\"参数5名称\">\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t\t<label for=\"name\" class=\"col-sm-4 control-label no-padding-right\">负责人</label>\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"col-sm-4\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"leader\" name=\"leader\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fristChannel.leader}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" placeholder=\"负责人\">\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t\t<label for=\"name\" class=\"col-sm-4 control-label no-padding-right\">描述</label>\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"col-sm-4\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<textarea name=\"description\" id=\"description\" class=\"form-control\" rows=\"5\" placeholder=\"（限制150字以内）\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fristChannel.description}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</textarea>\n");
      out.write("\t\t\t\t\t\t\t\t\t<label class=\"error\" for=\"description\" style=\"display:none;\">限制150字以内</label>\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t<hr class=\"wide\" />\n");
      out.write("\t\t\t\t\t\t\t<div class=\"buttons-preview text-align-center\">\n");
      out.write("\t\t\t\t\t\t\t\t<a type=\"\" class=\"btn btn-primary\" href=\"javascript:history.back();\"> <i class=\"fa fa-mail-reply\"></i> 返回\n");
      out.write("\t\t\t\t\t\t\t\t</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n");
      out.write("\t\t\t\t\t\t\t\t<button type=\"button\" class=\"btn btn-primary\" id=\"submitInput\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<i class=\"fa fa-save\"></i> 保存\n");
      out.write("\t\t\t\t\t\t\t\t</button>\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"hidden\" name=\"distribution\" id=\"distribution\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fristChannel.distribution}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write('"');
      out.write('>');
      out.write("\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t</form>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\t<!--广告弹框 -->\n");
      out.write("\t<div class=\"modal fade\" id=\"myModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLabel\">\n");
      out.write("\t\t<div class=\"modal-dialog\" style=\"width:50%;\" role=\"document\">\n");
      out.write("\t\t\t<div class=\"modal-content\">\n");
      out.write("\t\t\t\t<div class=\"modal-header\">\n");
      out.write("\t\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\"><span\n");
      out.write("\t\t\t\t\t\t\taria-hidden=\"true\">×</span></button>\n");
      out.write("\t\t\t\t\t<h4 class=\"modal-title\" id=\"exampleModalLabel\">设置分成比例</h4>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t<div class=\"modal-body\">\n");
      out.write("\t\t\t\t\t<div class=\"row\">\n");
      out.write("\t\t\t\t\t\t<div class=\"col-lg-7  col-sm-5\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<label><span class='text'>广告名称</span></label>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t<div class=\"row\">\n");
      out.write("\t\t\t\t\t\t\t\t<label><span class='text'>分配比例</span></label>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t<div class=\"modal-body\">\n");
      out.write("\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t<div class=\"modal-body\">\n");
      out.write("\t\t\t\t\t<div class=\"row\">\n");
      out.write("\t\t\t\t\t\t<div class=\"col-lg-7 col-sm-5\">\n");
      out.write("\t\t\t\t\t\t\t<span class=\"text\" style=\"\">全局比例</span>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t<div class=\"row\">\n");
      out.write("\t\t\t\t\t\t\t<input type=\"text\" class=\"colored-success\" id=\"allRate\" name=\"allRate\" value=\"\"/>\n");
      out.write("\t\t\t\t\t\t\t<label class=\"error\" for=\"\" style=\"display:none;\">请输入正确的比例</label>\n");
      out.write("\t\t\t\t\t\t\t<nobr style=\"font-size:14px;color:red;\">(0-1)</nobr>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t<div class=\"col-lg-7 col-sm-5\">\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t<div class=\"row\">\n");
      out.write("\t\t\t\t\t\t\t<label><input type=\"checkbox\" id=\"allRateBox\" name=\"allRateBox\" title=\"\"><span class=\"text\">是否全局设置</span></label>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t<hr>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\n");
      out.write("\t\t\t\t<div class=\"modal-footer\">\n");
      out.write("\t\t\t\t\t<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">取消</button>\n");
      out.write("\t\t\t\t\t<button type=\"button\" class=\"btn btn-primary\" onclick=\"saveOperator()\">确定</button>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("\n");
      out.write("\t<script type=\"text/javascript\">\n");
      out.write("\t\t/*$(\"#callbackUrl\").change(function(){\n");
      out.write("\t\t\tvar callbackUrl = $.trim($(\"#callbackUrl\").val());\n");
      out.write("\t\t\t\n");
      out.write("\t\t\tif(callbackUrl != ''){\n");
      out.write("\t\t\t\t$(\"#c1Div\").show();\n");
      out.write("\t\t\t\t$(\"#c2Div\").show();\n");
      out.write("\t\t\t\t$(\"#typeDiv\").show();\n");
      out.write("\t\t\t\t$(\"#c3Div\").show();\n");
      out.write("\t\t\t}else{\n");
      out.write("\t\t\t\t$(\"#c1Div\").hide();\n");
      out.write("\t\t\t\t$(\"#c2Div\").hide();\n");
      out.write("\t\t\t\t$(\"#typeDiv\").hide();\n");
      out.write("\t\t\t\t$(\"#c3Div\").hide();\n");
      out.write("\t\t\t}\n");
      out.write("\t\t});*/\n");
      out.write("\t\n");
      out.write("\t\n");
      out.write("\t\t$(\"#submitInput\").click(function(){\n");
      out.write("\t\t\tvar name = $.trim($(\"#name\").val());\n");
      out.write("\t\t\tvar dividedRate = $.trim($(\"#dividedRate\").val());\n");
      out.write("\t\t\tvar callbackUrl = $.trim($(\"#callbackUrl\").val());\n");
      out.write("\t\t\tvar description = $.trim($(\"#description\").val());\n");
      out.write("\t\t\tvar distribution= $.trim($(\"#distribution\").val());\n");
      out.write("\t\t\t\n");
      out.write("\t\t\tvar c1 = $.trim($(\"#c1\").val());\n");
      out.write("\t\t\tvar c2 = $.trim($(\"#c2\").val());\n");
      out.write("\t\t\tvar type = $.trim($(\"#type\").val());\n");
      out.write("\t\t\t//var c3 = $.trim($(\"#c3\").val());\n");
      out.write("\t\t\t\n");
      out.write("\t\t\tvar reg = /^[0-9]+.?[0-9]*$/;\n");
      out.write("\t\t\tvar num = 0;\n");
      out.write("\t\t\t\n");
      out.write("\t\t\tif(name == \"\"){\n");
      out.write("\t\t\t\t$(\"#name\").next().show();\n");
      out.write("\t\t\t\t$(\"#name\").next().next().hide();\n");
      out.write("\t\t\t}else{\n");
      out.write("\t\t\t\t$(\"#name\").next().hide();\n");
      out.write("\t\t\t\t$.ajax({\n");
      out.write("\t\t\t\t\turl: \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/fristChannel/doBean\",\n");
      out.write("\t\t            type :\"GET\",\n");
      out.write("\t\t            data:{id:$(\"#id\").val(),name:$(\"#name\").val()},\n");
      out.write("\t\t            async: false,\n");
      out.write("\t\t            dataType : \"text\",\n");
      out.write("\t\t\t\t\tsuccess: function(date){\n");
      out.write("\t\t\t\t\t\tif(date == \"true\"){\n");
      out.write("\t\t\t\t\t\t\t$(\"#name\").next().next().hide();\n");
      out.write("\t\t\t\t\t\t\tnum = num + 1;\n");
      out.write("\t\t\t\t\t\t}else{\n");
      out.write("\t\t\t\t\t\t\t$(\"#name\").next().next().show();\n");
      out.write("\t\t\t\t\t\t}\n");
      out.write("\t\t\t      \t}\n");
      out.write("\t\t    \t});\n");
      out.write("\t\t\t}\n");
      out.write("\t\t\t\n");
      out.write("\t\t\tif(dividedRate == \"\"){\n");
      out.write("\t\t\t\t$(\"#dividedRate\").next().show();\n");
      out.write("\t\t\t}else{\n");
      out.write("\t\t\t\t$(\"#dividedRate\").next().hide();\n");
      out.write("\t\t\t\tif(!reg.test(dividedRate) || dividedRate > 1){\n");
      out.write("\t\t\t\t\t$(\"#dividedRate\").next().next().show();\n");
      out.write("\t\t\t\t}else{\n");
      out.write("\t\t\t\t\tnum = num + 1;\n");
      out.write("\t\t\t\t\t$(\"#dividedRate\").next().next().hide();\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t}\n");
      out.write("\n");
      out.write(" \t\t\tvar strReg=/^http:\\/\\/[A-Za-z0-9]+\\.[A-Za-z0-9]+[\\/=\\?%\\-&_~`@[\\]\\':+!]*([^<>\\\"\\\"])*$/;\n");
      out.write(" \t\t\tif(callbackUrl == \"\"){\n");
      out.write(" \t\t\t\tnum = num + 1;\t\n");
      out.write(" \t\t\t\t$(\"#callbackUrl\").next().next().hide();\n");
      out.write("\t\t\t}else{\n");
      out.write("                if(!strReg.test(callbackUrl)) {\n");
      out.write("                    $(\"#callbackUrl\").next().next().show();\n");
      out.write("                }else{\n");
      out.write("                    num = num + 1;\n");
      out.write("                    $(\"#callbackUrl\").next().next().hide();\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("\t\t\t\n");
      out.write(" \t\t\tif(description.length > 150){\n");
      out.write("\t\t\t\t$(\"#description\").next().show();\n");
      out.write("\t\t\t}else{\n");
      out.write("\t\t\t\tnum = num + 1;\n");
      out.write("\t\t\t\t$(\"#description\").next().hide();\n");
      out.write("\t\t\t} \n");
      out.write("\t\t\tif(num == 4){\n");
      out.write("\t\t\t\t$(\"#submitInput\").attr(\"disabled\",\"true\");\n");
      out.write("\t\t\t\t$(\"#fristChannel_form\").submit();\n");
      out.write("\t\t\t}\n");
      out.write("\t\t});\n");
      out.write("        function ModalDialog(){\n");
      out.write("            $('#myModal').modal('show').css({\n");
      out.write("                width: '100%',\n");
      out.write("            });\n");
      out.write("            $('.modal').css({'overflow-x':'scroll'});\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        function saveOperator(){\n");
      out.write("\t\t\tvar str = \"\";\n");
      out.write("            var flg = true;\n");
      out.write("            var reg = /^[0-9]+.?[0-9]*$/;\n");
      out.write("            var flgBox = $(\"#allRateBox\").is(\":checked\");\n");
      out.write("            if(!flgBox){\n");
      out.write("\t\t\t\t$('input[name=\"voluumIds\"]').each(function(){\n");
      out.write("\t\t\t\t\tvar offerId = $(this).val();\n");
      out.write("\t\t\t\t\tvar subscriptionRate = $.trim($(\"#rate_\"+offerId).val());\n");
      out.write("\t\t\t\t\tif(offerId != null && offerId != \"\" ){\n");
      out.write("\t\t\t\t\t    if(subscriptionRate != null && subscriptionRate!=\"\" ){\n");
      out.write("                            if(!reg.test(subscriptionRate) || subscriptionRate > 1){\n");
      out.write("                                $(\"#rate_\"+offerId).next().show();\n");
      out.write("                                flg = false;\n");
      out.write("                            }else{\n");
      out.write("                                $(\"#rate_\"+offerId).next().hide();\n");
      out.write("                                str += \"{\\\"voluumOfferId\\\":\\\"\" + offerId + \"\\\",\\\"subscriptionRate\\\":\\\"\" + subscriptionRate + \"\\\"}&\";\n");
      out.write("                            }\n");
      out.write("\t\t\t\t\t\t}else{\n");
      out.write("                            $(\"#rate_\"+offerId).next().hide();\n");
      out.write("                            str += \"{\\\"voluumOfferId\\\":\\\"\" + offerId + \"\\\",\\\"subscriptionRate\\\":\\\"\" + 1 + \"\\\"}&\";\n");
      out.write("\t\t\t\t\t\t}\n");
      out.write("\t\t\t\t\t}\n");
      out.write("\t\t\t\t});\n");
      out.write("            }else{\n");
      out.write("\t\t\t\tvar subscriptionRate = $.trim($(\"#allRate\").val());\n");
      out.write("\t\t\t\tif (subscriptionRate != null && subscriptionRate != \"\") {\n");
      out.write("\t\t\t\t\tif (confirm(\"当前渠道所有广告的分成比例将被修改，你确定修改吗？\")) {\n");
      out.write("\t\t\t\t\t\tif (!reg.test(subscriptionRate) || subscriptionRate > 1) {\n");
      out.write("\t\t\t\t\t\t\t$(\"#allRate\").next().show();\n");
      out.write("\t\t\t\t\t\t\tflg = false;\n");
      out.write("\t\t\t\t\t\t}\n");
      out.write("\t\t\t\t\t\t$('input[name=\"voluumIds\"]').each(function () {\n");
      out.write("\t\t\t\t\t\t\tvar offerId = $(this).val();\n");
      out.write("\t\t\t\t\t\t\t$(\"#allRate\").next().hide();\n");
      out.write("\t\t\t\t\t\t\tstr += \"{\\\"voluumOfferId\\\":\\\"\" + offerId + \"\\\",\\\"subscriptionRate\\\":\\\"\" + subscriptionRate + \"\\\"}&\";\n");
      out.write("\t\t\t\t\t\t});\n");
      out.write("\t\t\t\t\t}else{\n");
      out.write("                        flg = false;\n");
      out.write("\t\t\t\t\t}\n");
      out.write("\t\t\t\t} else {\n");
      out.write("\t\t\t\t\t$(\"#allRate\").next().show();\n");
      out.write("\t\t\t\t\tflg = false;\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t}\n");
      out.write("            if(flg){\n");
      out.write("                $(\"#distribution\").val(str);\n");
      out.write("                $('#myModal').modal('hide');\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("\t</script>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fforEach_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent(null);
    // /pager/fristChannel/form.jsp(213,6) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/pager/fristChannel/form.jsp(213,6) '${listResources}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${listResources}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    // /pager/fristChannel/form.jsp(213,6) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("resource");
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("\t\t\t\t\t\t\t<div class=\"row\">\n");
          out.write("\t\t\t\t\t\t\t\t<div class=\"col-lg-7 col-sm-5\">\n");
          out.write("\t\t\t\t\t\t\t\t\t<span class=\"text\" style=\"\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${resource.name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</span>\n");
          out.write("\t\t\t\t\t\t\t\t</div>\n");
          out.write("\t\t\t\t\t\t\t\t<div class=\"row\">\n");
          out.write("\t\t\t\t\t\t\t\t\t<input type=\"text\" class=\"colored-success\" id=\"rate_");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${resource.voluumOfferId}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("\" name=\"rateName\" value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${resource.subscriptionRate}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("\"/>\n");
          out.write("\t\t\t\t\t\t\t\t\t<label class=\"error\" for=\"rate_");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${resource.voluumOfferId}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("\" style=\"display:none;\">请输入正确的比例</label>\n");
          out.write("\t\t\t\t\t\t\t\t\t<input type=\"hidden\" class=\"colored-success\" name=\"voluumIds\" value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${resource.voluumOfferId}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("\"/>\n");
          out.write("\t\t\t\t\t\t\t\t\t<nobr style=\"font-size:14px;color:red;\">(0-1)</nobr>\n");
          out.write("\t\t\t\t\t\t\t\t</div>\n");
          out.write("\n");
          out.write("\t\t\t\t\t\t\t</div>\n");
          out.write("\t\t\t\t\t\t\t<hr>\n");
          out.write("\t\t\t\t\t\t");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f0.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }
}
