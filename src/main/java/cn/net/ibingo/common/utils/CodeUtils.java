package cn.net.ibingo.common.utils;

import org.apache.http.HttpRequest;

public class CodeUtils {

    public static String getCode(String code) {
        Integer num = Integer.valueOf(code);
        num = num + 1;

        if (String.valueOf(num).length() == 1) {
            return "000" + num;
        } else if (String.valueOf(num).length() == 2) {
            return "00" + num;
        } else if (String.valueOf(num).length() == 3) {
            return "0" + num;
        }
        return String.valueOf(num);
    }
}
    /*public static String GetPhoneNumber(HttpRequest request)

    {
        String phone = "" NameValueCollection coll = null try {
        coll = request.ServerVariables
    } catch {
    }
        try {
            if (phone == ""){
                phone = coll["HTTP_X_UP_CALLING_LINE_ID"].ToString()}
        } catch {
    }
        try {
            if (phone == ""){
                phone = coll["MISC_MSISDN"].ToString()}
        } catch {
    }
        try {
            if (phone == ""){
                phone = coll["x-up-calling-line-id"].ToString()}
        } catch {
    }
        try {
            if (phone == ""){
                phone = coll["HTTP_X_NOKIA_MSISDN"].ToString()}
        } catch {
    }
        try {
            if (phone.Length > 11){
                phone = phone.SubString(2)}
        } catch(Exception ex){
        phone = ex.Message
    } return phone

    }*/