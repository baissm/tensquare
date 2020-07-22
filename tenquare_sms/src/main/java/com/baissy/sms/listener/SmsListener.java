package com.baissy.sms.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author chenlin
 * @create 2020/6/3/16:50
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener {
    @RabbitHandler
    public void executeSms(Map<String,String> map){
        String mobile = map.get("mobile");
        String checkcode = map.get("checkcode");

        System.out.println("手机号："+mobile);
        System.out.println("验证码："+checkcode);

    }
}
