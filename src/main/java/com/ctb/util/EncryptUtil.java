package com.ctb.util;

import java.util.Date;

/**
 * Created by jayway on 2017/6/26.
 */
public class EncryptUtil {
    public static  final String aeskey="cheyixing";
    public static String encrypt(String paramJson) throws Exception {
        String rangeNum=DateUtils.formatDate(new Date(),"yyyyMMddHHmmssff");
        //16位随机数+请求参数json字符串
        String md5Str=MD5Util.MD5(rangeNum+paramJson).toUpperCase();//md5转换大写
        String aesStr=rangeNum+paramJson+md5Str;
        byte[] reStr=AESUtils.encrypt(aesStr.getBytes(),aeskey.getBytes());
        String returnStr=new String(reStr,"UTF-8");
        return returnStr;
    }
    public static String aesDecrypt(String content) throws Exception {
        byte[] str=AESUtils.decrypt(content.getBytes(),aeskey.getBytes());
        String returnStr=new String(str,"UTF-8");
        return returnStr;
    }
}
