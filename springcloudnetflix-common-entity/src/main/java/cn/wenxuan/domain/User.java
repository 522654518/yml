package cn.wenxuan.domain;

import lombok.Data;

@Data
public class User {

    private Long id;
    private String name;
    private String intro;

    public User(Long id, String name, String intro) {
        this.id = id;
        this.name = name;
        this.intro = intro;
    }

}
