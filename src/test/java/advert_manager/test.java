package advert_manager;


import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Logger;

import cn.net.ibingo.common.utils.ConstantConfig;
import cn.net.ibingo.common.utils.HttpRequester;
import cn.net.ibingo.common.utils.HttpRespons;
import cn.net.ibingo.common.utils.HttpUtil;

import cn.net.ibingo.core.controller.VoluumNotifyController;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.cache.jcache.interceptor.JCacheOperationSourcePointcut;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class test {
	private static Logger log = Logger.getLogger(String.valueOf(test.class));
	private static String token = "";
	
	public static void main(String[] main){
		//getAccount();
		//createSessionByAccount();
		//getTrafficSource();
		//addTrafficSource();
		//updateTrafficSource();
		//deleteTracfficSource();
		try {
			//uploadFile();
			downloadFile("","","");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void uploadFile() throws UnsupportedEncodingException {

			Map<String, Object> params = new HashMap<String, Object>();
			//params.put("content", URLEncoder.encode("中文的撒大大是的大大是的", "UTF-8"));
			params.put("fileName", "ph");
		   HttpRequester httpUtil = new HttpRequester();
			//HttpUtil httpUtil = new HttpUtil();
			try {
				//HttpRespons sr = httpUtil.sendPost("http://u.nicegame.me/api/uploadMesFile", params, null);
				//HttpRespons sr = httpUtil.sendPost("http://u.nicegame.me/api/downloadImage", params, null);
				//HttpRespons sr = httpUtil.sendPost("http://localhost:9090/downloadImageProject", params, null);
				HttpRespons sr = httpUtil.sendPost("http://yxj.ngrok.cc/downloadImage", params, null);

				if (sr != null && "200".equals(sr.getCode())) {
					System.out.println("渠道上传成功");
				}


			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	public static void downloadFile(String link,String p,String name) {

		String urlStr="http://u.nicegame.me/api/downloadImage";//http://yxj.ngrok.cc/downloadImage";
		String path= p;
		String fileName=name;
		FileOutputStream output = null;
		InputStream input = null;
		HttpURLConnection conn = null;
		System.out.println("downloadFile1 = ");
		try {
            /*
             * 通过URL取得HttpURLConnection
             * 要网络连接成功，需在AndroidMainfest.xml中进行权限配置
             * <uses-permission android:name="android.permission.INTERNET" />
             */
			URL url=new URL(urlStr);
			conn=(HttpURLConnection)url.openConnection();
			// conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);
			conn.setDoOutput(true);
			conn.setDoInput(true);

			StringBuffer param = new StringBuffer();
			param.append("&");
			param.append("fileName").append("=").append("ph");

			conn.getOutputStream().write(param.toString().getBytes());
			conn.getOutputStream().flush();
			conn.getOutputStream().close();

			input = conn.getInputStream();


			//String SDCard=Environment.getExternalStorageDirectory()+"";
			//String pathName=SDCard+"/"+path+"/"+fileName;//文件存储路径

			File file=new File("");



			System.out.println("downloadFile1 = ");
			if(file.exists()){
				System.out.println("exits");
				return;
			}else{
				int len;
				String dir="D:\\test/b.jpg";//SDCard+"/"+path;
				file = new File(dir);//新建文件夹
				file.createNewFile();//新建文件

				//读取大文件
				try {
					output=new FileOutputStream(file);
				} catch(FileNotFoundException e) {

				}

				byte[] bs = new byte[1024];
				// 读取到的数据长度
				while((len = input.read(bs))!=-1){
					output.write(bs,0,len);
				}

				// output.write(Buffer,0,1);
				output.flush();


				//handler.sendEmptyMessage(0);

			}

			output.close();
			input.close();
			conn.disconnect();



		} catch (Exception e) {

			System.out.println("downloadFile e = " + e.getClass().getName());
		}

	}


	public void params(){
			String [] strArray = {"efadf425-0425-4276-933e-91539abd32d7","8efbfa54-0f2e-4bd4-8369-fb1aa2532b69","ee3a6d4e-834c-4f45-babe-6655cf96bcd3"};
			String str = "";
			for(int i = 1;i < 100;i++){
				if(i % 3 == 0){
					str = strArray[2];
				}else if(i % 2 == 0){
					str = strArray[1];
				}else{
					str = strArray[0];
				}
				System.out.println("---:" + str);
				final int count = i;
				final String str1 = str;
				new Thread(){
					public void run(){
						//requestVoluum(0,str1);
					}
				}.start();
			}
		}

	public InputStream postMethodHanya(String param, String urlStr,
									   String contentType) {

		InputStream is = null;
		try {
			byte[] xmlData = param.getBytes("UTF-8");
			URL url = new URL(urlStr);
			URLConnection urlCon = url.openConnection();
			urlCon.setDoOutput(false);
			urlCon.setDoInput(true);
			urlCon.setConnectTimeout(40000);
			urlCon.setReadTimeout(40000);
			urlCon.setUseCaches(false);
			//          urlCon.setRequestProperty("Content-Type", contentType);
			//          urlCon.setRequestProperty("Content-length",
			//                  String.valueOf(xmlData.length));
			//          // urlCon.setFixedLengthStreamingMode(xmlData.length);
			//          DataOutputStream printout = new DataOutputStream(
			//                  urlCon.getOutputStream());
			//          printout.write(xmlData);
			//          printout.flush();
			//          printout.close();
			is = urlCon.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;
	}



	public  void requestVoluum(int i,String src){
		log.info("第"+i+"条线程请求开始"+new Date());
		HttpUtil httpUtil = new HttpUtil();
		Map<String, Object> paramsToken = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			String url = "http://t.nicegame.me/"+src;
			System.out.println("访问地址:" + url);
			URL serverUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) serverUrl
					.openConnection();
			conn.setRequestMethod("GET");
			// 必须设置false，否则会自动redirect到Location的地址
			conn.setInstanceFollowRedirects(false);

			conn.addRequestProperty("Accept-Charset", "UTF-8;");
			conn.addRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Firefox/3.6.8");
			conn.addRequestProperty("Referer", "http://zuidaima.com/");
			conn.connect();
			String location = conn.getHeaderField("Location");
			System.out.println("跳转地址:" + location);
			int count = location.lastIndexOf("?");
			String str = location.substring(count+1,location.length());
			Map<String,String> map = new HashMap<>();
			String [] paramArray = str.split("&");
			if(paramArray != null && paramArray.length >0){
				for(String param : paramArray){
					String [] paramA = param.split("=");
					if(paramA != null && paramA.length >0){
						map.put(paramA[0],paramA[1]);
					}
				}
			}
			String locationUrl = "http://t.nicegame.me/postback?cid="+map.get("cid");
			/*serverUrl = new URL(location);
			conn = (HttpURLConnection) serverUrl.openConnection();
			conn.setRequestMethod("GET");

			conn.addRequestProperty("Accept-Charset", "UTF-8;");
			conn.addRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Firefox/3.6.8");
			conn.addRequestProperty("Referer", "http://zuidaima.com/");
			conn.connect();*/
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(locationUrl.toString());
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instreams = entity.getContent();
				String stra = httpUtil.convertStreamToString(instreams);
				get.abort();
			}
			log.info("第"+i+"条线程请求结束"+new Date());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void getTrafficSource(){
		 Map<String, Object> params = new HashMap<String, Object>();
		 params.put("includeDeleted", false+"");
		Map<String, Object> paramsToken = new HashMap<String, Object>();
		 paramsToken.put("CWAUTH-TOKEN", token);
		 HttpUtil httpUtil = new HttpUtil();
		 try {
			HttpRespons sr  = httpUtil.sendGet(ConstantConfig.VOLUUM_URL+"traffic-source",params,paramsToken);
			System.out.println(sr.getContent());
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 当新增渠道时，将对应的渠道信息上传到Voluum平台
	 */
	public static void addTrafficSource() {
		 Map<String, Object> params = new HashMap<String, Object>();
			 params.put("name", "ceshi2");
			 params.put("createdTime", "2017-05-19 03:24:42");
			 params.put("updatedTime", "2017-05-19 03:24:42");
			 params.put("deleted", false);
			params.put("postbackUrl",  ConstantConfig.ADVERT_MANAGER_URL);
			params.put("type", ConstantConfig.TRAFFICSOURCE_TYPE_CUS);//CUSTOM', 'PREDEFINED
			//params.put("predefinedType", ConstantConfig.TRAFFICSOURCE_FINEDTYPE_ZER);//ZERO_PARK', 'DSP
			List<Map<String,Object>> listMap = new ArrayList<>();

			Map<String,Object> map1 = new HashMap<String,Object>();
			map1.put("index", 1);
			map1.put("name", "c1");
			map1.put("parameter", "c1");
			map1.put("placeholder", "{c1}");
			map1.put("trackedInReports", true);
			listMap.add(map1);
		    params.put("customVariables", listMap);
		    params.put("impressionSpecific", true);
		    HttpUtil httpUtil = new HttpUtil();
			 Map<String, Object> paramsToken = new HashMap<String, Object>();
			 paramsToken.put("CWAUTH-TOKEN", token);
			 try {
			HttpRespons sr  = httpUtil.sendPost(ConstantConfig.VOLUUM_URL+"traffic-source",params,paramsToken);
			if("200".equals(sr.getCode())){
				System.out.println("渠道上传成功");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void updateTrafficSource() {
		 Map<String, Object> params = new HashMap<String, Object>();
		 params.put("name", "Zeropar于祥杰测试");
		 params.put("createdTime", "2017-05-19 03:24:42");
		 params.put("updatedTime", "2017-05-19 03:24:42");
		 params.put("deleted", false);
		 params.put("type", ConstantConfig.TRAFFICSOURCE_TYPE_CUS);//CUSTOM', 'PREDEFINED
		 params.put("predefinedType", ConstantConfig.TRAFFICSOURCE_FINEDTYPE_ZER);
		 List<Map<String,Object>> listMap = new ArrayList<>(); 
		 Map<String,Object> map1 = new HashMap<String,Object>();
		 map1.put("index", 1);
		 map1.put("name", "target");
		 map1.put("parameter", "target");
		 map1.put("placeholder", "{target}");
		 map1.put("trackedInReports", true);
		 listMap.add(map1);
		 Map<String,Object> map2 = new HashMap<String,Object>();
		 map2.put("index", 2);
		 map2.put("name", "keyword");
		 map2.put("parameter", "keyword");
		 map2.put("placeholder", "{keyword}");
		 map2.put("trackedInReports", true);
		 listMap.add(map2);
		 params.put("customVariables", listMap);
		 params.put("impressionSpecific", true);
		 String trafficSourceId = "81a06ce8-25e2-4d88-b28d-e895417cf195";
		 HttpUtil httpUtil = new HttpUtil();
		Map<String, Object> paramsToken = new HashMap<String, Object>();
		 paramsToken.put("CWAUTH-TOKEN", token);
		 try {
			HttpRespons sr  = httpUtil.sendPut(ConstantConfig.VOLUUM_URL+"traffic-source/"+trafficSourceId,params,paramsToken);
			if("200".equals(sr.getCode())){
				System.out.println("渠道更新成功");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static  void deleteTracfficSource() {
		 Map<String, Object> params = new HashMap<String, Object>();
		 List<String> list = new ArrayList<String>();
		 list.add("81a06ce8-25e2-4d88-b28d-e895417cf195");
		 params.put("ids", list);
		 HttpUtil httpUtil = new HttpUtil();
		Map<String, Object> paramsToken = new HashMap<String, Object>();
		 paramsToken.put("CWAUTH-TOKEN", token);
		try {
			HttpRespons sr  = httpUtil.sendDelete(ConstantConfig.VOLUUM_URL+"traffic-source",params,paramsToken);
			if("200".equals(sr.getCode())){
				System.out.println("渠道删除成功");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void createSessionByAccount() {
		 Map<String, Object> params = new HashMap<String, Object>();
		 params.put("email", ConstantConfig.VOLUUM_USERNAEM);
		 params.put("password",ConstantConfig.VOLUUM_PASSWORD);
		 String url  = ConstantConfig.VOLUUM_URL+"auth/session";
		 HttpUtil httpUtil = new HttpUtil();
		 try {
			 HttpRespons sr  = httpUtil.sendPost(url,params,null);
			 JSONObject jsStr = JSONObject.parseObject(sr.getContent());
			 token = jsStr.getString("token");
			 System.out.println(token);
		 } catch (IOException e) {
				e.printStackTrace();
			}
	}
	

	
	
	

}
