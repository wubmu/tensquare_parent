package com.tensquare.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 吴亚斌
 * create : 2019-01-17 19:30
 * description
 */
@SpringBootApplication

public class BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class,args);


    }
    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
