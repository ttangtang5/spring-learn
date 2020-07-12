package com.tang.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Description
 * @Author tang
 * @Date 2020/7/4 10:57
 * @Version 1.0
 **/
@Configuration
@ComponentScan(basePackages = {"com.tang"})
@EnableAspectJAutoProxy(proxyTargetClass = false)
public class SpringContext {
}
