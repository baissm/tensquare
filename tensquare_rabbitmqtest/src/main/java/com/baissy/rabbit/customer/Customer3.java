package com.baissy.rabbit.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author chenlin
 * @create 2020/6/2/16:26
 */
@Component
@RabbitListener(queues = "aaa1")
public class Customer2 {
    @RabbitHandler
    public void getMsg1(String msg){
        System.out.println("直接模式消费消息:"+msg);
    }
}
