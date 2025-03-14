package cn.wenxuan.controller;

import cn.wenxuan.domain.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@DefaultProperties(defaultFallback ="fallbackMethod")
public class OrderController {

    @Autowired
    private RestTemplate template;

    /*浏览器-->orderController-->userController*/
    @GetMapping("/order/{id}")
    @HystrixCommand
    public User getById(@PathVariable("id") Long id) {

        return new User(id, "wenxuan", "123456");
    }

    public User fallbackMethod() {
    //返回托底数据
        return new User(-1L, "无此用户", "用户服务不可用");
    }

    @GetMapping("/getProduct/{id}")
    public String getProduct(@PathVariable("id") Long id) {
        ResponseEntity<String> forEntity =
                template.getForEntity("http://user-service/userProduct/" + id, String.class);
        return forEntity.getBody();
    }
}
