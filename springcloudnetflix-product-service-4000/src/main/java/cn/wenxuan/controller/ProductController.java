package cn.wenxuan.controller;

import cn.wenxuan.domain.Product;
import cn.wenxuan.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    /*获取配置文件中的端口号*/
    @Value("${server.port}")
    private int port;

    @GetMapping("/product/{id}")
    public Product getById(@PathVariable("id") Long id){
        return new Product(id, "产品1", 200.0);
    }
}
