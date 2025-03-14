package cn.wenxuan.controller;

import cn.wenxuan.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    @Autowired
    private RestTemplate template;

    /*浏览器-->orderController-->userController*/
    @GetMapping("/order/{id}")
    public User getById(@PathVariable("id") Long id) {
        ResponseEntity<User> forEntity = template.getForEntity("http://user-service/user/" + id, User.class);
        User user = forEntity.getBody();
        return user;
    }

    @GetMapping("/getProduct/{id}")
    public String getProduct(@PathVariable("id") Long id) {
        ResponseEntity<String> forEntity =
                template.getForEntity("http://user-service/userProduct/" + id, String.class);
        return forEntity.getBody();
    }
}
