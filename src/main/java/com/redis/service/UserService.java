package com.redis.service;

import com.redis.config.SerUtil;
import com.redis.dao.UserDao;
import com.redis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisService redisService;
    @Autowired
    private ShardedJedisPool shardedJedisPool;
    /**
     * 唯一查询
     * */
    public User getUser(Integer id){
        String key="user_"+id;
        Object obj=redisService.getObj(key);
        if(obj!=null) {
            System.out.println("调用redis缓存");
            return (User) obj;
        }else {
            User user = userDao.getUser(id);
            System.out.println("通过数据库查询");
            redisService.setObj(key,user);
            return user;
        }
    }
    /**
     * 使用jedis方式缓存
     * */
    public User getUserById(Integer id){
        try {
            ShardedJedis jedis=shardedJedisPool.getResource();
            String key="user_"+id;
            byte[] bytes=jedis.get(key.getBytes());
            if(bytes!=null){
                Object obj= SerUtil.unserizable(bytes);
                return (User)obj;
            }else{
                User user=userDao.getUser(id);
                jedis.set(key.getBytes(),SerUtil.serizable(user));
                return user;
            }
        } catch (Exception e) {
            System.out.println("调用报错为:"+e.getMessage());
            return null;
        }
    }
    /**
     * 新增
     * */
    public boolean insertUser(User user){
        try {
            userDao.insertUser(user);
            return true;
        } catch (Exception e) {
            System.out.println("新增用户报错:"+e.getMessage());
            return false;
        }
    }
    /**
     * 删除
     * */
    public boolean deleteUser(Integer id){
        try {
            userDao.deleteUser(id);
            boolean flg=redisService.delKey("user_"+id);
            System.out.println("删除缓存用户结果为:"+flg);
            return true;
        } catch (Exception e) {
            System.out.println("删除用户信息报错:"+e.getMessage());
            return false;
        }
    }
    /**
     * 修改
     * */
    public boolean updateUser(User  user){
        try {
            userDao.updateUser(user);
            boolean flg=redisService.delKey("user_"+user.getId());
            System.out.println("删除修改用户信息结果为:"+flg);
            return true;
        } catch (Exception e) {
            System.out.println("修改用户信息报错:"+e.getMessage());
            return false;
        }
    }
}
