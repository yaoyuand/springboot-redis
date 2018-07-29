package com.redis.publish;

import org.springframework.stereotype.Component;

@Component
public class MessageResiver {
    /**
     * 接收消息
     * */
    public void message(String message){
        System.out.println("接收消息为:"+message);
    }
}
