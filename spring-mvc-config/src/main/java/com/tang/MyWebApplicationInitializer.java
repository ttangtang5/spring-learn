package com.tang;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * 用此类代替web.xml的配置 这是servlet 3.0之后的特性
 * https://docs.spring.io/spring-framework/docs/current/reference/html/web.html
 * servlet 底层使用spi
 */
public class MyWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // 配置spring 容器加载
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(AppConfig.class);
        //applicationContext.setConfigLocations("classpath:xxx.xml");
        applicationContext.refresh();

        // 配置DispatcherServlet  这里需要传入mvc.xml 配置
        XmlWebApplicationContext webContext = new XmlWebApplicationContext();
        //webContext.setConfigLocation("/WEB-INF/spring/dispatcher-config.xml");
        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
        ServletRegistration.Dynamic registration = servletContext.addServlet("mvc", dispatcherServlet);
        registration.addMapping("/");
        registration.setLoadOnStartup(1);
    }
}
