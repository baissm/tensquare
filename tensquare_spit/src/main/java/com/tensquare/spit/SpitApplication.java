package com.tensquare.spit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @author chenlin
 * @create 2020/5/5/15:51
 */
@SpringBootApplication
public class SpitApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpitApplication.class);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }
}
