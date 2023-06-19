package com.potato.util;

import java.security.MessageDigest;

/**
 * @Author potato
 * @PackageName:com.potato.util
 * @Description: 自定义MD5 加密工具类
 */

public class MD5Util {


    /**
     * 加密
     * @param origin
     * @return
     */
    public static String getMd5(String origin){

        // 自定义数组,相当于盐
        char [] hexArray ={
                '5','a','4','b','9',
                '6','8','f','e','2',
                '2','7','c','d','a',
                '5'
        };

        try {
            byte[] originBytes = origin.getBytes();
            //md5 加密对象
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(originBytes);
            // 获取加密后的数组
            byte [] digest = md5.digest();
            char[] str = new char[digest.length*2];
            int k = 0 ;
            // 对加密后的数组加盐
            // 首先判断加密后的数组长度,遍历数组,对每个元素进行移位运算(二进制的位运算)
            for (int i = 0; i < digest.length; i++) {
                byte b = digest[i];
                str[k++] =  hexArray[b>>>4 & 0xf ];
                str[k++] = hexArray[b & 0xf];
            }
            return new String(str);
        }catch (Exception e){
            e.printStackTrace();
            return "123456";
        }

    }

    public static void main(String[] args) {
        System.out.println(getMd5("123456"));
    }
}
