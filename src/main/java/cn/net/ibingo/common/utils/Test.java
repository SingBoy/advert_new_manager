package cn.net.ibingo.common.utils;

import cn.net.ibingo.core.controller.VoluumNotifyController;
import cn.net.ibingo.core.query.FristChannelQueryBean;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.logging.Logger;

//import java.util.HashMap;
//import java.util.Map;

public class Test {
	private static Logger log = Logger.getLogger(String.valueOf(Test.class));
	public static void main(String[] args) {
		//VoluumNotifyController voluumNotifyController = new VoluumNotifyController();
		//voluumNotifyController.delete();
		//params();
			/*final String str = "38262c42-8e99-4fd8-b752-98c51728a604";//"8efbfa54-0f2e-4bd4-8369-fb1aa2532b69";
			for(int i = 1;i < 2;i++){
				final int count = i;
				new Thread(){
					public void run(){
						requestVoluum(count,str);
					}
				}.start();
			}*/
		Timer();
	}

	public static void params(){
		try {
			String [] strArray = {"efadf425-0425-4276-933e-91539abd32d7","8efbfa54-0f2e-4bd4-8369-fb1aa2532b69","ee3a6d4e-834c-4f45-babe-6655cf96bcd3"};
			String str = "";
			for(int i = 1;i < 5000;i++){
				if(i % 3 == 0){
					str = strArray[2];
				}else if(i % 2 == 0){
					str = strArray[1];
				}else{
					str = strArray[0];
				}
				final int count = i;
				final String str1 = str;
				new Thread(){
					public void run(){
						requestVoluum(0,str1);
					}
				}.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void requestVoluum(int i,String src){
		log.info(""+i+"-------line request thread start-------"+new Date());
		HttpUtil httpUtil = new HttpUtil();
		Map<String, Object> paramsToken = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			String url = "http://t.nicegame.me/"+src;
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
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(locationUrl.toString());
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instreams = entity.getContent();
				String stra = httpUtil.convertStreamToString(instreams);
				get.abort();
			}
			log.info(""+i+"line request thread end-------"+new Date());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void requestVoluumOne(int i, Integer src)
	{
		HttpUtil httpUtil = new HttpUtil();
		Map<String, Object> paramsToken = new HashMap();
		Map<String, Object> params = new HashMap();
		try
		{
			String url = "http://u.nicegame.me/api/subscribe?c1=9999&c2=9999&type=9999&resourcesId=" + src;
			URL serverUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection)serverUrl.openConnection();
			conn.setRequestMethod("GET");
			conn.setInstanceFollowRedirects(false);
			conn.addRequestProperty("Accept-Charset", "UTF-8;");
			conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Firefox/3.6.8");
			conn.addRequestProperty("Referer", "http://zuidaima.com/");
			conn.connect();
			String location = conn.getHeaderField("Location");


			URL serverUrl2 = new URL(location);
			HttpURLConnection conn2 = (HttpURLConnection)serverUrl2.openConnection();
			conn2.setRequestMethod("GET");
			conn2.setInstanceFollowRedirects(false);
			conn2.addRequestProperty("Accept-Charset", "UTF-8;");
			conn2.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Firefox/3.6.8");
			conn2.addRequestProperty("Referer", "http://zuidaima.com/");
			conn2.connect();
			String location2 = conn2.getHeaderField("Location");
			int count = location2.lastIndexOf("?");
			String str = location2.substring(count + 1, location2.length());
			Map<String, String> map = new HashMap();
			String[] paramArray = str.split("&");
			if ((paramArray != null) && (paramArray.length > 0)) {
				for (String param : paramArray)
				{
					String[] paramA = param.split("=");
					if ((paramA != null) && (paramA.length > 0)) {
						map.put(paramA[0], paramA[1]);
					}
				}
			}
			String locationUrl = "http://t.nicegame.me/postback?cid=" + (String)map.get("cid");
			Object client = new DefaultHttpClient();
			HttpGet get = new HttpGet(locationUrl.toString());
			HttpResponse response = ((HttpClient)client).execute(get);
			HttpEntity entity = response.getEntity();
			if (entity != null)
			{
				InputStream instreams = entity.getContent();
				String stra = httpUtil.convertStreamToString(instreams);
				get.abort();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void Timer(){
		Timer timer=new Timer();
		timer.schedule(new TimerTask(){

			@Override
			public void run() {
				System.out.println(new Date());

			}

		}, 0,1000);
	}


}
