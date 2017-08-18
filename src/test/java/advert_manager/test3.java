package advert_manager;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import redis.clients.jedis.Jedis;

/**
 * Created by IntelliJ IDEA.
 * User: Singe
 * Date: 2017-08-18
 * Time: 14:26
 * To change this template use File | Settings | File Templates.
 */
public class test3 {
    public static void main(String[] arg){
      /*  List<AppTest> list =   parsePage("http://as.baidu.com/a/asgame?cid=102&s=1");
        System.out.println(list.size());*/

        Jedis jedis = new Jedis("52.221.245.186",6379);
        jedis.set("study", "redis");
        String value = jedis.get("study");
        System.out.println(value);
    }


    public static List<AppTest> parsePage(String url) {
        List<AppTest> appTests=new LinkedList<AppTest>();
        Document doc=null;
        try {
            doc=Jsoup.connect(url).get();
        } catch(IOException e1) {
            System.out.println("连接失败!");
            e1.printStackTrace();
        }
        Elements divs=doc.getElementsByTag("div");
        for(Element e: divs) {
            if(e.attr("class").equals("app-bd show")) {//filter-app-list-wrapper
                Elements divsChildrens=e.children();
                for(Element e2: divsChildrens) {
                    Elements lis=e2.getElementsByTag("li");// <li>
                    for(Element e3: lis) {
                        AppTest at=new AppTest();
                        // img
                        Elements imgs=e3.getElementsByTag("img");
                        Element img=imgs.get(0);
                        String src=img.attr("src");
                        System.out.println("---------------------------");
                        System.out.println("Logo地址:" + src);
                        at.setLogoUrl(src);
                        // div hover-show
                        for(Element div: e3.getElementsByTag("div")) {
                            if(div.attr("class").equals("hover-show")) {
                                for(Element e4: div.children()) {
                                    String name=null;
                                    String about=null;
                                    String downloadUrl=null;
                                    String detailUrl=null;
                                    if(e4.attr("class").equals("hover-link")) {
                                        detailUrl=e4.attr("href");
                                        System.out.println("二级链接地址:" + detailUrl);
                                        parseDetailPage(at, detailUrl);
                                        for(Element e5: e4.children()) {
                                            Elements h4s=e5.getElementsByTag("h4");
                                            for(Element ee: h4s) {
                                                for(Element eee: ee.children()) {
                                                    if(eee.attr("class").equals("tit")) {
                                                        name=eee.childNode(0).outerHtml();
                                                        System.out.println("应用名称:" + name);
                                                        at.setName(name);
                                                    }
                                                }
                                            }
                                            if(e5.attr("class").equals("detail")) {
                                                about=e5.childNode(0).outerHtml();
                                                System.out.println("简介:" + about);
                                                at.setAbout(about);
                                            }
                                        }
                                    }
                                    if(e4.attr("class").equals("s-index-down s-index-icon tjitem")) {
                                        downloadUrl=e4.attr("href");
                                        System.out.println("下载地址:" + downloadUrl);
                                        at.setDownloadUrl(downloadUrl);
                                    }
                                }
                            }
                        }
                        appTests.add(at);
                    }
                }
            }
        }
        return appTests;
    }

    private static void parseDetailPage(AppTest at, String url) {
        Document doc=null;
        String imgsUrl="";
        try {
            doc=Jsoup.connect(url).get();
        } catch(IOException e) {
            System.out.println("二级连接失败!");
            e.printStackTrace();
        }
        Elements uls=doc.getElementsByTag("ul");
        for(Element ul: uls) {
            if(ul.attr("class").equals("screen cls data-screenshots")) {
                Elements lis=ul.getElementsByTag("li");
                for(Element li: lis) {
                    for(Element img: li.children()) {
                        String imgUrl=img.attr("src");
                        System.out.println("截图:" + imgUrl);
                        imgsUrl+=imgUrl + "@";
                    }
                    at.setImgs(imgsUrl);
                }
            }
        }
        Elements divs=doc.getElementsByTag("div");
        for(Element div: divs) {
            if(div.attr("class").equals("brief-des")) {
                StringBuffer sb=new StringBuffer();
                for(Node n: div.childNodes()) {
                    sb.append(n.outerHtml().replace("<br />", "\r\n"));
                }
                System.out.println("详细介绍:" + sb.toString());
                at.setDetail(sb.toString());
            }
        }
        Elements spans=doc.getElementsByTag("span");
        for(Element span: spans) {
            if(span.attr("id").equals("params-catename")) {
                String type=span.childNode(0).outerHtml();
                System.out.println("游戏类别:" + type);
                at.setType(type);
            }
            if(span.attr("id").equals("params-size")) {
                String size=span.childNode(0).outerHtml();
                System.out.println("游戏大小:" + size);
                at.setSize(size);
            }
            if(span.attr("id").equals("params-vname")) {
                String version=span.childNode(0).outerHtml();
                System.out.println("游戏版本:" + version);
                at.setVersion(version);
            }
        }
    }
}
