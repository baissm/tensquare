package com.baissy.test;

import com.baissy.rabbit.RabbitApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chenlin
 * @create 2020/6/2/16:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitApplication.class)
public class ProductTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    ///

    @Test
    public void sendMsg1(){
        rabbitTemplate.convertAndSend("itcast","测试直接模式");
    }
    @Test
    public void sendMsg2(){
        rabbitTemplate.convertAndSend("testExchanges","","分裂模式");
    }
//    @Test
//    public void sendMsg3(){
//        rabbitTemplate.convertAndSend("aaa2","测试直接模式");
//    }
}
