package cn.net.ibingo.common.utils;

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