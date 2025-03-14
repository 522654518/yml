package cn.wenxuan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer/*声明这是Eureka服务端*/
public class EurekaServerStarter {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerStarter.class, args);
    }
}
