package cn.net.ibingo.common.utils;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.web.multipart.MultipartFile;
import sun.rmi.runtime.Log;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;
import java.util.logging.Logger;

/**
 *  http工具类
 * Created by yuxiangjie on 2017/5/24.
 */
public class HttpUtil {

    private static Logger log = Logger.getLogger(String.valueOf(HttpUtil.class));

	private static String defaultContentEncoding;     
    
    public HttpUtil() {     
      this.defaultContentEncoding = Charset.defaultCharset().name();     
    }


    /**
     * 发送GET请求
     *
     * @param urlString
     *            URL地址
     * @return 响应对象
     * @throws IOException
     */
    public static HttpRespons sendGet(String urlString) throws IOException {
        return sendG(urlString,null, null);
    }

    /**
     * 发送GET请求
     *
     * @param urlString
     *            URL地址
     * @param params
     *            参数集合
     * @return 响应对象
     * @throws IOException
     */
    public HttpRespons sendGet(String urlString, Map<String, Object> params,Map<String, Object> propertys)
            throws IOException {
        return this.sendG(urlString, params, propertys);
    }
    /**   
     * 发送POST请求   
     *    
     * @param urlString   
     *            URL地址   
     * @param params   
     *            参数集合   
     * @return 响应对象   
     * @throws IOException   
     */    
    public HttpRespons sendPost(String urlString, Map<String, Object> params,Map<String, Object> propertys)
            throws IOException {     
        return this.send(urlString,  params,"POST", propertys);     
    }     
    
    /**   
     * 发送PUT请求   
     *    
     * @param urlString   
     *            URL地址   
     * @param params   
     *            参数集合   
     * @return 响应对象   
     * @throws IOException   
     */    
    public HttpRespons sendPut(String urlString, Map<String, Object> params,Map<String, Object> propertys)
            throws IOException {     
        return this.send(urlString,  params,"PUT", propertys);     
    }  
    
    /**   
     * 发送DELETE请求   
     *    
     * @param urlString   
     *            URL地址   
     * @param params   
     *            参数集合   
     * @return 响应对象   
     * @throws IOException   
     */ 
    public HttpRespons sendDelete(String urlString, Map<String, Object> params,
			Map<String, Object> propertys)  throws IOException {
    	return this.send(urlString,  params,"DELETE", propertys);
	}

    /**
     * 以GET的方式访问HTTP地址并返回响应内容
     *
     * @param httpPath
     * @return
     */
    public  String sendHttpClientGet(String httpPath, Map<String, Object> params,Map<String, Object> propertys) throws IOException {

        try {
            StringBuffer uri = new StringBuffer();
            uri.append(httpPath + "?1=1");

            paramMap2Sbf(params, uri);

            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(uri.toString());
            if(propertys!=null && propertys.get("CWAUTH-TOKEN") != null)
                get.setHeader("cwauth-token",propertys.get("CWAUTH-TOKEN").toString());
            HttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instreams = entity.getContent();
                String str = convertStreamToString(instreams);
                get.abort();
                return str;
            }
        } catch (IOException e) {
            log.info("调用HTTP REST接口出现异常: " + e.getMessage());
        }
        return null;
    }

    /**
     * 以POST的方式访问HTTP地址并返回响应内容
     *
     * @param httpPath
     * @param parameters
     * @return
     */
    @SuppressWarnings({ "resource", "deprecation" })
    public  String sendHttpClientPost(String httpPath, Map<String, Object> parameters,Map<String, Object> propertys)throws IOException  {

        log.info("httpPath: " + httpPath);

        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(httpPath);
            if(propertys!=null && propertys.get("CWAUTH-TOKEN") != null)
                post.setHeader("cwauth-token",propertys.get("CWAUTH-TOKEN").toString());
            // 设置POST参数
            if (parameters != null) {
                JSONObject jsonObject = JSONObject.fromObject(parameters);
                StringEntity se = new StringEntity(jsonObject.toString(), "application/json", "UTF-8");
                post.setEntity(se);
            }
            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instreams = entity.getContent();
                String str = convertStreamToString(instreams);
                post.abort();
                return str;
            }
        } catch (IOException e) {
            log.info("exception: " + e.getMessage());
        }
        return null;
    }
    /**
     * 以POST的方式访问HTTP地址并返回响应内容
     *
     * @param httpPath
     * @param parameters
     * @return
     */
    @SuppressWarnings({ "resource", "deprecation" })
    public String sendHttpClientPut(String httpPath, Map<String, Object> parameters,Map<String, Object> propertys)throws IOException  {

        log.info("httpPath: " + httpPath);

        try {
            HttpClient client = new DefaultHttpClient();
            HttpPut put = new HttpPut(httpPath);
            if(propertys!=null && propertys.get("CWAUTH-TOKEN") != null)
                put.setHeader("cwauth-token",propertys.get("CWAUTH-TOKEN").toString());
            // 设置POST参数
            if (parameters != null) {
                JSONObject jsonObject = JSONObject.fromObject(parameters);
                StringEntity se = new StringEntity(jsonObject.toString(), "application/json", "UTF-8");
                put.setEntity(se);
            }
            HttpResponse response = client.execute(put);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instreams = entity.getContent();
                String str = convertStreamToString(instreams);
                put.abort();
                return str;
            }
        } catch (IOException e) {
            log.info("exception: " + e.getMessage());
        }
        return null;
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param parameters
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static HttpRespons sendG(String url, Map<String,Object> parameters,Map<String, Object> propertys)throws IOException {
        String result = "";
        BufferedReader in = null;
        try {
        	StringBuffer param = new StringBuffer();
        	 if (parameters != null) {   
	            int i = 0;     
	            for (String key : parameters.keySet()) {     
	                if (i == 0)     
	                    param.append("?");     
	                else    
	                    param.append("&");     
	                param.append(key).append("=").append(parameters.get(key));     
	                i++; 
	            }     
        	 }
               
            String urlNameString = url + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            if(propertys!=null && propertys.get("CWAUTH-TOKEN") != null)
            	connection.setRequestProperty("cwauth-token",propertys.get("CWAUTH-TOKEN").toString());
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            // 建立实际的连接
            connection.connect();

            return makeContent(url,connection);
        } catch (Exception e) {
            System.out.println("GET  request exception！！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        return null;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param parameters
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public HttpRespons send(String url, Map<String, Object> parameters,String methodType,Map<String, Object> propertys)  throws IOException {
        String param = "";
        try {
        	 if (parameters != null) {
        		 JSONObject jsonObject = JSONObject.fromObject(parameters);
        		 param = jsonObject.toString();
             }     
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            if(propertys!=null && propertys.get("CWAUTH-TOKEN") != null)
	            conn.addRequestProperty("cwauth-token",propertys.get("CWAUTH-TOKEN").toString());
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Accept-Charset","utf-8");
            /*conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");*/
            conn.setRequestMethod(methodType);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.getOutputStream().write(param.getBytes());     
            conn.getOutputStream().flush();     
            conn.getOutputStream().close();     
            return makeContent(url,conn);
        } catch (Exception e) {
            System.out.println(methodType+" request exception！"+e);
            e.printStackTrace();
        }
		return null;
        
    }

    /**   
     * 得到响应对象   
     *    
     * @param urlConnection   
     * @return 响应对象   
     * @throws IOException   
     */    
    private static HttpRespons makeContent(String urlString,
            HttpURLConnection urlConnection) throws IOException {     
        HttpRespons httpResponser = new HttpRespons();     
        try {
            InputStream in = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            httpResponser.contentCollection = new Vector<String>();
            StringBuffer temp = new StringBuffer();     
            String line = bufferedReader.readLine();     
            while (line != null) {     
                temp.append(line).append("\r\n");
                line = bufferedReader.readLine();     
            }
            bufferedReader.close();
      
            String ecod = urlConnection.getContentEncoding();     
            if (ecod == null)     
                ecod = defaultContentEncoding;     
      
            httpResponser.urlString = urlString;     
      
            httpResponser.defaultPort = urlConnection.getURL().getDefaultPort();     
            httpResponser.file = urlConnection.getURL().getFile();     
            httpResponser.host = urlConnection.getURL().getHost();     
            httpResponser.path = urlConnection.getURL().getPath();     
            httpResponser.port = urlConnection.getURL().getPort();     
            httpResponser.protocol = urlConnection.getURL().getProtocol();     
            httpResponser.query = urlConnection.getURL().getQuery();     
            httpResponser.ref = urlConnection.getURL().getRef();     
            httpResponser.userInfo = urlConnection.getURL().getUserInfo();     
            httpResponser.content = new String(temp.toString().getBytes(), ecod);
            httpResponser.contentEncoding = ecod;
            httpResponser.code = urlConnection.getResponseCode();     
            httpResponser.message = urlConnection.getResponseMessage();     
            httpResponser.contentType = urlConnection.getContentType();     
            httpResponser.method = urlConnection.getRequestMethod();     
            httpResponser.connectTimeout = urlConnection.getConnectTimeout();     
            httpResponser.readTimeout = urlConnection.getReadTimeout();     
      
            return httpResponser;     
        } catch (IOException e) {     
            throw e;     
        } finally {     
            if (urlConnection != null)     
                urlConnection.disconnect();     
        }     
    }

    /**
     * 参数拼接
     *
     * @param params
     * @param url
     * @return
     */
    public StringBuffer paramMap2Sbf(Map<String, Object> params, StringBuffer url) {
        Set<String> keys = params.keySet();
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String next = it.next();
            url.append("&").append(next).append("=").append(params.get(next));
        }
        return url;
    }
    /**
     * 将响应的数据流转换成字符串
     *
     * @param is
     * @return
     */
    public String convertStreamToString(InputStream is) {
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


    public static String uploadFile(MultipartFile file, String RequestURL) {
        String result = null;
        String BOUNDARY = UUID.randomUUID().toString(); // 边界标识 随机生成
        String PREFIX = "--",LINE_END = "\r\n",CONTENT_TYPE = "multipart/form-data";
        try {
            URL url = new URL(RequestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(1000 * 1000);
            conn.setConnectTimeout(1000 * 1000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Charset", "utf-8");
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);
            if (file != null) {
                DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                StringBuffer sb = new StringBuffer();
                sb.append(PREFIX);sb.append(BOUNDARY);
                sb.append(LINE_END);
                sb.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getOriginalFilename() + "\"" + LINE_END);
                sb.append("Content-Type: application/octet-stream; charset=" + "utf-8" + LINE_END);
                sb.append(LINE_END);
                dos.write(sb.toString().getBytes());
                InputStream is = file.getInputStream();//new FileInputStream(file);
                byte[] bytes = new byte[1024];
                int len;
                while ((len = is.read(bytes)) != -1) {
                    dos.write(bytes, 0, len);
                }

                is.close();
                dos.write(LINE_END.getBytes());
                byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END).getBytes();
                dos.write(end_data);
                dos.flush();

                InputStream input = conn.getInputStream();
                StringBuffer sb1 = new StringBuffer();
                int ss;
                while ((ss = input.read()) != -1) {
                    sb1.append((char) ss);
                }
                result = sb1.toString();
                input.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
