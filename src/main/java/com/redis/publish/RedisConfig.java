package com.redis.publish;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisConfig {
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory redisConnectionFactory,
                                                   MessageListenerAdapter listenerAdapter){
        RedisMessageListenerContainer container=new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        //订阅chat通道
        container.addMessageListener(listenerAdapter,new PatternTopic("chat"));
        //可以订阅多个通道
        return container;
    }
    @Bean
    public MessageListenerAdapter listenerAdapter(MessageResiver resiver){
            //给MessageListenerAdapter传入消息处理器
            return new MessageListenerAdapter(resiver,"message");
    }
}
