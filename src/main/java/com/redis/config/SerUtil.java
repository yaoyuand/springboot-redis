package com.redis.config;

import java.io.*;

public class SerUtil {
    /**
     * 系列化将对象转换为byte数组
     * */
    public static byte[] serizable(Object obj){
        try {
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            ObjectOutputStream oos=new ObjectOutputStream(bos);
            oos.writeObject(obj);
            byte[] bytes=bos.toByteArray();
            return bytes;
        } catch (Exception e) {
            System.out.println("序列化对象报错:"+e.getMessage());
        }
        return null;
    }
    /**
     * 反序列化将byte数据转换为object对象
     * */
    public static Object unserizable(byte[] by){
        try {
            ByteArrayInputStream bis=new ByteArrayInputStream(by);
            ObjectInputStream ois=new ObjectInputStream(bis);
            Object obj=ois.readObject();
            return obj;
        } catch (Exception e) {
            System.out.println("反序列化报错:"+e.getMessage());
        }
        return null;
    }
}
