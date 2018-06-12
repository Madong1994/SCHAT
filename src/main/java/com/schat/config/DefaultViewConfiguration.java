package com.schat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by 马东 on 2018/3/27.
 *
 * @Author:madong
 * @Description:
 * @Date:Create in 21:15 2018/3/27
 * 关关雎鸠，在河之洲，
 * 窈窕淑女，君子好逑。
 */
@Configuration
public class DefaultViewConfiguration extends WebMvcConfigurerAdapter {
    /**
     * 视图控制器配置
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
        registry.addViewController("/").setViewName("/index");
    }
}
