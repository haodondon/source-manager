package cn.app.source;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *  @author: hdd
 *  @Date: 2020/11/21
 *  @Description: 启动器
 */
@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = {"cn.app.source.mapper"})
@EnableAsync
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
