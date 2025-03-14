package cn.wenxuan.controller;

import cn.wenxuan.domain.User;
import cn.wenxuan.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PayController {


    @Autowired
    private UserFeignClient userFeignClient;

    /*浏览器-->orderController-->userController*/
    @GetMapping("/pay/{id}")
    public User getById(@PathVariable("id") Long id) {
        return userFeignClient.getById(id);
    }


}
