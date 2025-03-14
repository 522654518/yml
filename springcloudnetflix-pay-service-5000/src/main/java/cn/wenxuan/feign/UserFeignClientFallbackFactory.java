package cn.wenxuan.feign;

import cn.wenxuan.domain.User;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class UserFeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {

    @Override
    public UserFeignClient create(Throwable throwable) {
        return new UserFeignClient() {
            @Override
            public User getById(Long id) {
                throwable.printStackTrace();
                //真正托底方法 ， 这里的数据是托底数据
                return new User(-1L, "无此用户", "用户服务不可用");
            }
        };
    }
}
