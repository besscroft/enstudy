package dev.heming.enstudy.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description Sa-Token 注解鉴权注册拦截器配置
 * @Author Bess Croft
 * @Date 2023/8/25 12:36
 */
@Slf4j
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，打开注解式鉴权功能
        registry.addInterceptor(new SaInterceptor(handle -> {
                    // 登录校验，登录后才允许放行
                    StpUtil.checkLogin();
                })).addPathPatterns("/**")
                .excludePathPatterns(
                        "/v3/**",
                        "/api-docs/**",
                        "/swagger-ui.html/**",
                        "/swagger-ui/**",
                        "/doc.html/**",
                        "/error",
                        "/favicon.ico",
                        "/actuator/**",
                        "/api-docs.yaml"
                );
    }

}
