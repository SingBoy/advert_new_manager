package advert_manager;

import cn.net.ibingo.common.utils.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lqj on 2017-07-10.
 */
public class test2 {
    public static void main(String[] arg){
        try {

            postLogin("http://localhost:9090/user_manager/account/commonUserLogin","{\"passWord\":\"123456\",\"userName\":\"15250960714\"}");

            //requestVoluum();

            //GetWebContent("http://t.nicegame.me/801d0290-6ca6-4dd9-8e37-9086d58f5139","UTF-8",10000);
            //GetWebContent("http://m.asiael3ab.asiacell.com/asiacell/servlet/adletangZombieReaper3?cid=wGDKKT8BV1TVSP77HR5T78PE&channel=567d1e21-f671-4a1a-8fa6-bb4b2963d594wGDKKT8BV1TVSP77HR5T78PE&proj=1004107&p=86110891290217&c=l00181.10029&o&u&needCnfm=0&a=00000452&viaSubLink&traceid=erL188sr&sign=a2b3842fb629805eda5cf6d29d38158a4debcb19","UTF-8",10000);
            //ssssssss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String postLogin(String urlString, String contents) throws Exception {
        String line = "";
        HttpURLConnection connection = null;
        DataOutputStream out = null;
        BufferedInputStream bis = null;
        ByteArrayBuffer baf = null;

        try {
            byte[] encrypted = contents.getBytes("utf-8");
            URL postUrl = new URL(urlString);
            connection = (HttpURLConnection) postUrl.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(30000);
            connection.setReadTimeout(30000);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("contentType", "utf-8");
            connection.setRequestProperty("Content-Type","application/json");
            connection.setRequestProperty("Content-Length", ""+ encrypted.length);
            out = new DataOutputStream(connection.getOutputStream());
            out.write(encrypted);
            out.flush();
            out.close();
            bis = new BufferedInputStream(connection.getInputStream());
            baf = new ByteArrayBuffer(1024);

            int current = 0;
            while ((current = bis.read()) != -1) {
                baf.append((byte) current);
            }
            if (baf.length() > 0) {
                line = new String(baf.toByteArray());
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (connection != null)
                connection.disconnect();
            if (bis != null)
                bis.close();
            if (baf != null)
                baf.clear();
        }
        return line.trim();
    }


    public static void requestVoluum(){
        HttpUtil httpUtil = new HttpUtil();
        Map<String, Object> paramsToken = new HashMap<String, Object>();
        Map<String, Object> params = new HashMap<String, Object>();
        try {
            String url = "http://t.nicegame.me/801d0290-6ca6-4dd9-8e37-9086d58f5139";//"http://t.nicegame.me/9c419064-b80e-4ab1-a88d-b5476ffeda83?postback={postback}";//"http://t.nicegame.me/"+src;
            System.out.println("访问地址:" + url);
            URL serverUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) serverUrl
                    .openConnection();
            conn.setRequestMethod("GET");
            // 必须设置false，否则会自动redirect到Location的地址
            conn.setInstanceFollowRedirects(false);

            conn.addRequestProperty("Accept-Charset", "UTF-8;");
            conn.addRequestProperty("User-Agent",
                    "User-Agent: Mozilla/5.0 (Linux; X11)");
                    //"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Firefox/3.6.8");
                    //"Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Mobile Safari/537.36");
                  // "Mozilla/5.0 (Linux; U; Android 2.1-update1; zh-cn; XT800 Build/TITA_M2_16.22.7) AppleWebKit/530.17 (KHTML like Gecko) Version/4.0  Mobile Safari/530.17");
            conn.addRequestProperty("Referer", "http://zuidaima.com/");
            conn.connect();
            String location = conn.getHeaderField("Location");
            System.out.println("跳转地址:" + location);
           URL url1 =  conn.getURL();
           // getHuodongHTML(conn.getURL().getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getHuodongHTML(String wsurl){
        StringBuffer str = new StringBuffer();
        try {
            URL url = new URL(wsurl);
            URLConnection connection = url.openConnection();
            InputStream is = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String tempStr = null;
            while ((tempStr = reader.readLine()) != null) {
                str.append(tempStr);
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return str.toString();
    }

    /**
     *网页抓取方法
     * @param urlString      要抓取的url地址
     * @param charset        网页编码方式
     * @param timeout        超时时间
     * @return               抓取的网页内容\
     *      //http://blog.csdn.net/yjflinchong
     * @throws IOException   抓取异常
     */
    public static String GetWebContent(String urlString, final String charset, int timeout) throws IOException {
        if (urlString == null || urlString.length() == 0) {
            return null;
        }
        urlString = (urlString.startsWith("http://") || urlString.startsWith("https://")) ? urlString : ("http://" + urlString).intern();
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestProperty("Pragma", "no-cache");
        conn.setRequestProperty("Cache-Control", "no-cache");
        //http://blog.csdn.net/yjflinchong
        int temp = Integer.parseInt(Math.round(Math.random()*7)+"");
       // conn.setRequestProperty(UserAgent[temp]);  // 模拟手机系统
        conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");//只接受text/html类型，当然也可以接受图片,pdf,*/*任意，就是tomcat/conf/web里面定义那些
        conn.setConnectTimeout(timeout);
        try {
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }
        } catch (Exception e) {
            try {
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return null;
        }
        InputStream input = conn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input,
                charset));
        String line = null;
        StringBuffer sb = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\r\n");
        }
        //http://blog.csdn.net/yjflinchong
        if (reader != null) {
            reader.close();
        }
        if (conn != null) {
            conn.disconnect();
        }
        return sb.toString();
    }

    public static String[] UserAgent = {
            "Mozilla/5.0 (Linux; U; Android 2.2; en-us; Nexus One Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.2",
            "Mozilla/5.0 (iPad; U; CPU OS 3_2_2 like Mac OS X; en-us) AppleWebKit/531.21.10 (KHTML, like Gecko) Version/4.0.4 Mobile/7B500 Safari/531.21.11",
            "Mozilla/5.0 (SymbianOS/9.4; Series60/5.0 NokiaN97-1/20.0.019; Profile/MIDP-2.1 Configuration/CLDC-1.1) AppleWebKit/525 (KHTML, like Gecko) BrowserNG/7.1.18121",
            //http://blog.csdn.net/yjflinchong
            "Nokia5700AP23.01/SymbianOS/9.1 Series60/3.0",
            "UCWEB7.0.2.37/28/998",
            "NOKIA5700/UCWEB7.0.2.37/28/977",
            "Openwave/UCWEB7.0.2.37/28/978",
            "Mozilla/4.0 (compatible; MSIE 6.0; ) Opera/UCWEB7.0.2.37/28/989"
    };
    public static void ssssssss(){
        Document doc =  ParseDocument.parseDocumentFromUrl("http://t.nicegame.me/801d0290-6ca6-4dd9-8e37-9086d58f5139");

    }
}
