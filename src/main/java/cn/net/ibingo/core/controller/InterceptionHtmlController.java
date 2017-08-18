package cn.net.ibingo.core.controller;

import cn.net.ibingo.common.utils.ParseDocument;
import cn.net.ibingo.core.model.HtmlLog;
import cn.net.ibingo.core.service.HtmlLogService;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by Singe on 2017-08-07.
 */
@Controller
@RequestMapping("/interception")
public class InterceptionHtmlController {
    @Autowired
    private HtmlLogService htmlLogService;

    @RequestMapping("/html")
    public String html(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("+++++++++++++++++++"+getIp2(request));
        return "interceptionHtml";
    }


    @RequestMapping("/htmlOne")
    public void htmlOne(HttpServletRequest request, HttpServletResponse response) {
        printHtml(request, response, "http://t.nicegame.me/801d0290-6ca6-4dd9-8e37-9086d58f5139", "htmlOne");
    }

    @RequestMapping("/htmlTwo")
    public void htmlTwo(HttpServletRequest request, HttpServletResponse response) {
        printHtml(request, response, "http://t.nicegame.me/1c4255f7-e656-498c-ab5b-a3cdeb6135a8", "htmlTwo");
    }

    @RequestMapping("/testhtml")
    public void testhtml(HttpServletRequest request, HttpServletResponse response) {
        printHtml(request, response, "http://localhost:9091/advert_new_manager/interception/html", "testhtml");
    }


    public void printHtml(HttpServletRequest request, HttpServletResponse response, String url, String accessType) {
        try {
            System.getProperties().setProperty("http.proxyHost",getIp2(request));
            System.out.println(getIp2(request));
            Connection con2 = Jsoup.connect(url);
            con2.header("User-Agent", "Mozilla/5.0 (Linux; U; Android 2.2; en-us; Nexus One Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.2");
            //设置cookie和post上面的map数据
            Connection.Response login = con2.ignoreContentType(true).method(Connection.Method.POST).execute();
            //打印，登陆成功后的信息
            //System.out.println(login.body());
            PrintWriter out = response.getWriter();
            out.print(login);
            out.flush();
            /*Document doc = ParseDocument.parseDocumentFromAndroidUrl(url);
            HtmlLog htmllog = new HtmlLog();
            htmllog.setHtml(doc.outerHtml());

            Element body = doc.body();
            String sid = doc.select("input[name=snid]").val();
            String jumpUrl = doc.location();
            String scbUrl = jumpUrl.substring(jumpUrl.indexOf("?")+1,jumpUrl.length());
            body.append("<form id=\"form1\" name=\"form1\" action=\"http://m.asiael3ab.asiacell.com:80/asiacell/servlet/handle?"+scbUrl+"\" method=\"post\">\n" +
                    "<input type=\"hidden\" name=\"flag\" value=\"Submit\">\n" +
                    "<input type=\"hidden\" name=\"sid\" value="+sid+">\n" +
                    "</form>");
            body.append("<script type=\"text/javascript\">" +
                    "var oForm2 = document.getElementById('form1');\n" +
                    "_edata.push(['setEvent','subscribe','subscribe',[['C1','subscribe_page'],['C2','adletangZombieReaper3']]]);\n" +
                    "oForm2.submit();\n" +
                    "</script>");

            htmllog.setEditHtml(doc.outerHtml());
            htmllog.setSid(sid);
            htmllog.setAccessType(accessType);
            htmlLogService.saveOrUpdate(htmllog);

            PrintWriter out = response.getWriter();
            out.print(doc);
            out.flush();*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }








    /*Map<String, String> datas = new HashMap<>();
            datas.put("flag","Submit");
			datas.put("sid","2FAC852F6F9A253240A068AEFE1D986F");
			log.info("sid-----------------------------------------------------"+sid);
			String url = doc.location();
			Connection con2 = Jsoup.connect("http://m.asiael3ab.asiacell.com:80/asiacell/servlet/handle"+"?"+url.substring(url.indexOf("?")+1,url.length()));
			con2.header("User-Agent", "Mozilla/5.0 (Linux; U; Android 2.2; en-us; Nexus One Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.2");
			//设置cookie和post上面的map数据
			Connection.Response login = con2.ignoreContentType(true).method(Connection.Method.POST).data(datas).execute();
			//打印，登陆成功后的信息
			System.out.println(login.body());
			//登陆成功后的cookie信息，可以保存到本地，以后登陆时，只需一次登陆即可
			Map<String, String> map = login.cookies();
			for (String s : map.keySet()) {
				System.out.println(s + "      " + map.get(s));
			}*/
    //response.sendRedirect(doc.location());

    public static String getIp2(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }
}
