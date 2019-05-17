package com.monster.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Administrator
 *    mvc配置
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//yml文件中配置了静态资源路径为static，添加这项配置是允许访问resource下面的swagger文件
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		
		//registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/META-INF/resources/templates/");
    }
}
