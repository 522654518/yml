package cn.wenxuan.controller;

import cn.wenxuan.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class UserController {
    @Autowired
    private RestTemplate template;



    @GetMapping("/user/{id}")
    public User getById(@PathVariable("id") Long id){
        return new User(id, "孙悟空", "花果山美猴王" );    }

    @GetMapping("/userProduct/{id}")
    public String userProduct(@PathVariable("id") Long id){
        ResponseEntity<String> forEntity = template.getForEntity("http://product-service/product/"+id, String.class);
        return forEntity.getBody()+new User(id, "孙悟空", "花果山美猴王" );
    }
}
