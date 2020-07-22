package com.baissy.rabbit.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author chenlin
 * @create 2020/6/2/16:26
 */
@Component
@RabbitListener(queues = "itcast")
public class Customer1 {
    @RabbitHandler
    public void getMsg1(String msg){
        System.out.println("itcast:"+msg);
    }
}
