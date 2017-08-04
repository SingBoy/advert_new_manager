/**
 * 
 */
package cn.net.ibingo.common.utils;
 
import java.io.File;
import java.io.IOException;
 
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
 
/**
 * @作者 Goofy
 * @邮件 252878950@qq.com
 * @日期 2014-4-2上午10:54:53
 * @描述 
 */
public class ParseDocument {
     
    /**
     * 将String转换成Document
     * @return org.jsoup.nodes.Document
     */
    public static Document parseHtmlFromString(){
        String html = "<html><head><title>标题</title></head>"
                + "<body><p>段落</p></body></html>";
        Document doc = Jsoup.parse(html);
        return doc;
    }
     
    /**
     * 注意：这是一个不安全的方法
     * 将String转换成Html片段,注意防止跨站脚本攻击
     * @return Element
     */
    public static Element parseHtmlFragmentFromStringNotSafe(){
        String html = "<div><p>Lorem ipsum.</p>";
        Document doc = Jsoup.parseBodyFragment(html);
        Element body = doc.body();
        return body;
    }
     
    /**
     * 这是一个安全的方法
     * 将String转换成Html片段,注意防止跨站脚本攻击
     * @return Element
     */
    public static Element parseHtmlFragmentFromStringSafe(){
        String html = "<div><p>Lorem ipsum.</p>";
        //白名单列表定义了哪些元素和属性可以通过清洁器，其他的元素和属性一律移除
        Whitelist wl=new Whitelist();
        //比较松散的过滤，包括
        //"a", "b", "blockquote", "br", "caption", "cite", "code", "col",
        //"colgroup", "dd", "div", "dl", "dt", "em", "h1", "h2", "h3", "h4", "h5", "h6",
        //"i", "img", "li", "ol", "p", "pre", "q", "small", "strike", "strong",
        //"sub", "sup", "table", "tbody", "td", "tfoot", "th", "thead", "tr", "u",
        //"ul"
        Whitelist.relaxed();
        //没有任何标签，只有文本
        Whitelist.none();
        //常规的过滤器
        //"a", "b", "blockquote", "br", "cite", "code", "dd", "dl", "dt", "em",
        //"i", "li", "ol", "p", "pre", "q", "small", "strike", "strong", "sub",
        //"sup", "u", "ul"
        Whitelist.basic();
        //常规的过滤器，多了一个img标签
        Whitelist.basicWithImages();
        //文本类型的标签
        //"b", "em", "i", "strong", "u"
        Whitelist.simpleText();
        //另外还可以自定义过滤规则,例如
        wl.addTags("a");
        //执行过滤
        Jsoup.clean(html, wl);
        Document doc = Jsoup.parseBodyFragment(html);
        Element body = doc.body();
        return body;
    }
     
    /**
     * 从URL加载
     * @return Document
     */
    public static Document parseDocumentFromAndroidUrl(String url){
        Document doc = null;
        try {
            //post或者get方法
            doc = Jsoup.connect(url)
                      .data("query", "Java")
                      .userAgent("Mozilla/5.0 (Linux; U; Android 2.2; en-us; Nexus One Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.2")
                      .cookie("auth", "token")
                      .timeout(3000)
                      .post();
             
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    /**
     * 从URL加载
     * @return Document
     */
    public static Document parseDocumentFromUrl(String url){
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }
    /**
     * 从文件加载
     * @return Document
     */
    public static Document parseDocumentFromFile(String path){
        File input = new File("/tmp/input.html");
        Document doc=null;
        try {
            //从文件加载Document文档
            doc = Jsoup.parse(input, "UTF-8");
            System.out.println(doc.title());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }
     
     
 
}