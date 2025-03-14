package cn.wenxuan.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Component
public class LoginCheckFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return -1;
    }

    @Override
    public boolean shouldFilter() {
        // /static/** ，/login , /register 不需要做登录检查，返回false
        //1.获取request对象 ， 获取请求中的url
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        String url = request.getRequestURI();
        //2.判断是否包含在： static/** ，/login , /register
        return !url.contains("pay");
        //要做登录检查的返回true
    }

    @Override
    public Object run() throws ZuulException {
        //1.获取请求对象
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
//响应对象
        HttpServletResponse response = RequestContext.getCurrentContext().getResponse();
//2.获取请求头中的 token
        String token = request.getHeader("token");
//3.如果没有token，登录检查失败 ，
        if (!StringUtils.hasLength(token)) {
//3.1.返回登录检查失败的错误信息 :{ success:false, message:"登录检查失败，请重新登录"}
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("success", false);
            resultMap.put("message", "登录检查失败，请重新登录");
//中文编码
            response.setContentType("application/json;charset=utf-8");
//把map转成json字符串，写到浏览器
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            try {
                response.getWriter().print(resultMap);
            } catch (IOException e) {
                e.printStackTrace();
            }
//3.2.阻止filter继续往后执行
            RequestContext.getCurrentContext().setSendZuulResponse(false);
        }
        return null;
    }
}
