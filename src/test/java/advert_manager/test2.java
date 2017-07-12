package advert_manager;

import org.apache.http.util.ByteArrayBuffer;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lqj on 2017-07-10.
 */
public class test2 {
    public static void main(String[] arg){
        try {
           postLogin("http://localhost:9090/user_manager/account/userRegistered","{\"callerType\":\"gameCenter\",\"deviceNo\":\"860988035381917\",\"imei\":\"860988035381917\",\"imsi\":\"898600\",\"passWord\":\"123456\",\"softName\":\"\",\"userName\":\"15632569823\"}");
            //postLogin("http://localhost:9090/user_manager/account/commonUserLogin","{\"passWord\":\"123456\",\"userName\":\"i_xiangj@163.com\"}");
            //postLogin("http://localhost:9090/user_manager/account/downloadImage","{\"userName\":\"i_xiangj@163.com\"}");
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



}
