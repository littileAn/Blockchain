package org.littlean.block;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 01 on 2018/3/10.
 */
public class Util {
    public static String getNextHash(int newIndex, String hash, long newTimeStamp, String blockData) {
        MessageDigest messageDigest;
        StringBuilder builder = new StringBuilder();
        String encdeStr = "";
        builder.append(newIndex).append(hash).append(newTimeStamp).append(blockData);
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] newHash = messageDigest.digest(builder.toString().getBytes("UTF-8"));
            encdeStr = byte2Hex(newHash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encdeStr;
    }

    private static String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<bytes.length;i++){
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length()==1){
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
}
