/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.37
 * Generated at: 2017-07-07 11:05:38 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
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

      out.write('\n');

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
      out.write("<!--Head-->\n");
      out.write("<head>\n");
      out.write("<base href=\"");
      out.print(basePath);
      out.write("\" />\n");
      out.write("<meta charset=\"utf-8\" />\n");
      out.write("<title>广告资源管理平台</title>\n");
      out.write("<meta name=\"description\" content=\"login page\" />\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n");
      out.write("\n");
      out.write("\n");
      out.write("<!--Basic Styles-->\n");
      out.write("\t<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/plugins/beyond/css/bootstrap.min.css\" rel=\"stylesheet\" />\n");
      out.write("\t<link id=\"bootstrap-rtl-link\" href=\"\" rel=\"stylesheet\" />\n");
      out.write("\t<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/plugins/beyond/css/font-awesome.min.css\" rel=\"stylesheet\" />\n");
      out.write("\n");
      out.write("\t<!--Fonts-->\n");
      out.write("\t<!--     <link href=\"http://fonts.useso.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,600,700,300\" rel=\"stylesheet\" type=\"text/css\"> -->\n");
      out.write("\n");
      out.write("\t<!--Beyond styles-->\n");
      out.write("\t<link id=\"beyond-link\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/plugins/beyond/css/beyond.min.css\" rel=\"stylesheet\" />\n");
      out.write("\t<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/plugins/beyond/css/demo.min.css\" rel=\"stylesheet\" />\n");
      out.write("\t<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/plugins/beyond/css/animate.min.css\" rel=\"stylesheet\" />\n");
      out.write("\t<link id=\"skin-link\" href=\"\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("\n");
      out.write("\t<!--Skin Script: Place this script in head to load scripts for skins and rtl support-->\n");
      out.write("\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/plugins/beyond/js/skins.min.js\"></script>\n");
      out.write("<style type=\"text/css\">\n");
      out.write("\t.error {color:red;}\n");
      out.write("</style>\n");
      out.write("</head>\n");
      out.write("<!--Head Ends-->\n");
      out.write("<!--Body-->\n");
      out.write("<body>\n");
      out.write("\t<form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/login\" method=\"post\">\n");
      out.write("\t\t<div class=\"login-container animated fadeInDown\">\n");
      out.write("\t\t\t<div class=\"loginbox bg-white\">\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t\t<div class=\"loginbox-signup\"> </div>\n");
      out.write("\t\t\t\t<div class=\"loginbox-signup\"> </div>\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t<div class=\"loginbox-title\">运营服务平台</div>\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t<div class=\"loginbox-signup\"> </div>\n");
      out.write("\t            <div class=\"loginbox-signup\"> </div>\n");
      out.write("\t            \n");
      out.write("\t\t\t\t<div class=\"loginbox-or\">\n");
      out.write("\t\t\t\t\t<div class=\"or-line\"></div>\n");
      out.write("\t\t\t\t\t<div class=\"or\">登录</div>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t<div class=\"loginbox-signup\"> </div>\n");
      out.write("\t            <div class=\"loginbox-signup\"> </div>\n");
      out.write("\t            <div class=\"loginbox-signup error\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${errorMessages}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</div>\n");
      out.write("\t            <div class=\"loginbox-signup\"> </div>\n");
      out.write("\t            <div class=\"loginbox-signup\"> </div>\n");
      out.write("\t            \n");
      out.write("\t\t\t\t<div class=\"loginbox-textbox\">\n");
      out.write("\t\t\t\t\t<input type=\"text\" name=\"username\" class=\"form-control\" placeholder=\"账户\" />\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t<div class=\"loginbox-signup\"> </div>\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t<div class=\"loginbox-textbox\">\n");
      out.write("\t\t\t\t\t<input type=\"password\" name=\"password\" class=\"form-control\" placeholder=\"密码\" />\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t<div class=\"loginbox-signup\"> </div>\n");
      out.write("\t\t\t\t<div class=\"loginbox-signup\"> </div>\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t<div class=\"loginbox-submit\">\n");
      out.write("\t\t\t\t\t<input type=\"submit\" class=\"btn btn-primary btn-block\" value=\"登录\">\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t</form>\n");
      out.write("\t<!--Basic Scripts-->\n");
      out.write("\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/plugins/beyond/js/jquery-2.0.3.min.js\"></script>\n");
      out.write("\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/plugins/beyond/js/bootstrap.min.js\"></script>\n");
      out.write("</body>\n");
      out.write("<!--Body Ends-->\n");
      out.write("</html>\n");
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
}
