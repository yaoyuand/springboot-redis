package com.redis.dao;

import com.redis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 用户接口
 * */
@Component
@Mapper
public interface UserDao {
    /**
     * 获取User对象
     * */
    public User getUser(Integer id);
    /**
     * 新增User对象
     * */
    public void insertUser(User user);
    /**
     * 删除对象
     * */
    public void deleteUser(Integer id);
    /**
     * 修改对象
     * */
    public void updateUser(User user);
}
