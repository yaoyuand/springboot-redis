package com.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.util.ShardInfo;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class RedisService {
    @Autowired
    private  RedisTemplate redisTemplate;

    /**
     * 设置缓存
     * */
    public  boolean setObj(String key,Object value){
        try {
            ValueOperations<Object,Object> ops=redisTemplate.opsForValue();
            /**
             * value可以直接放入对象，底层对对象已经进行序列化操作
             * */
            ops.set(key,value);
            return true;
        } catch (Exception e) {
            System.out.println("插入缓存出错。。。。");
            return false;
        }
    }
    /**
     * 获取缓存中数据
     * */
    public  Object getObj(String key){
        ValueOperations<Object,Object> ops=redisTemplate.opsForValue();
        //获取键值
        Object obj=ops.get(key);
        return obj;
    }
    /**
     * 删除指定key
     * */
    public  boolean delKey(String key){
        //删除键
        boolean flg=redisTemplate.delete(key);
        return flg;
    }

    public void test(){
        //string类型操作
        ValueOperations<Object,Object> vos=redisTemplate.opsForValue();
        //map类型操作
        HashOperations hash=redisTemplate.opsForHash();
        //list类型操作
        ListOperations list=redisTemplate.opsForList();
        //set类型操作
        SetOperations set=redisTemplate.opsForSet();
        //sortSet类型操作
        ZSetOperations zset=redisTemplate.opsForZSet();
    }
    //redis操作hash类型
    public void map(){
        HashOperations<Object,Object,Object> hash=redisTemplate.opsForHash();
//        插入key为hash1的hash类型，其中hash的key为hashkey1，value为hashvalue1
        hash.put("hash1","hashkey1","hashvalue1");
//        获取key为hash1的hash类型，hash的key为hashkey1
        Object obj=hash.get("hash1","hashkey1");
        System.out.println("获取存储值为:"+obj);
        Map<String,String> map=new HashMap<>();
        map.put("key1","value1");
        map.put("key2","value2");
        //插入key为hahs2的hash类型
        hash.putAll("hahs2",map);
        Map<Object,Object> maps=hash.entries("hahs2");
        Set<Object> set=maps.keySet();
        for(Object s:set){
            System.out.println("获取key值为:"+s);
            System.out.println("获取value值为:"+map.get(s));
        }
        //删除key值
        Object[] objs={"key1","key2"};
        Long num=hash.delete("hahs2",objs);
        System.out.println("获取函数值为:"+num);

    }
    public void hyper(){
        HyperLogLogOperations<Object,Object> hyper=redisTemplate.opsForHyperLogLog();
        Object[] obj={"1","2","3","4","1","2","3","4"};
        hyper.add("key",obj);
        Long size=hyper.size(new Object[]{"key"});
        System.out.println("size值为:"+size);
    }
    /**
     * 创建订阅消息
     * */
    public void publish(){
        redisTemplate.convertAndSend("chat","今天是个好天气");
    }
    public static void main(String[] args) {
        Jedis jedis=new Jedis("47.98.189.123",6379);
        jedis.lpush("key",new String[]{"key1","key2","key3","key4"});
    }
}
