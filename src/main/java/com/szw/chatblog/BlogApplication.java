package com.szw.chatblog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author ChatViewer
 */
@Slf4j
@ServletComponentScan
@EnableKafka
@EnableTransactionManagement
@SpringBootApplication
public class BlogApplication {

    //druid超过空闲时间,断开连接修改配置
    static {
        System.setProperty("druid.mysql.usePingMethod","false");
    }

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

}
