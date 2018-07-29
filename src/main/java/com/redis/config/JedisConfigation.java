package com.redis.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableCaching
public class JedisConfigation extends CachingConfigurerSupport{
    //分布式操作redis
    @Bean
    public ShardedJedisPool createJedisConnectionFactory(){
        String host="47.98.189.123";
        Integer port=6379;
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(1000);
        jedisPoolConfig.setMaxIdle(20);
        jedisPoolConfig.setTestOnBorrow(true);
        List<JedisShardInfo> list=new ArrayList<>();
        JedisShardInfo jedisShardInfo=new JedisShardInfo(host,port);
        list.add(jedisShardInfo);
        ShardedJedisPool jedisPool=new ShardedJedisPool(jedisPoolConfig,list);
        return jedisPool;
    }

    public static void main(String[] args) {
        String host="";
        Integer port=null;
        Jedis jedis=new Jedis(host,port);
    }

}
