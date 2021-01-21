package com.tang.di.api;

import com.tang.di.service.OrderService;
import com.tang.di.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * spring autowired 源码实现逻辑
 * autowired 注解是通过AutowiredAnnotationBeanPostProcessor#postProcessProperties处理注入
 * 最终处理会调用DefaultListableBeanFactory#doResolveDependency
 * findAutowireCandidates通过类型查找所有满足的bean
 * 如果结果大于1
 * determineAutowireCandidate确定具体的注入bean，首先判断是否存在@Primary注解的bean、@Primary及是否设置优先级的bean，有则注入
 * 否则 抛出无法匹配异常
 *
 */
@Controller
public class OrderController {

    /**
     * 此种在存在OrderService一个以上的实现类上 肯定是注入失败
     */
    //@Autowired
    //private OrderService orderService;

    /**
     * 此种是可以成功的  这种方式也并不证明autowired是先byName 后byType 因为可能先byType 没有匹配 并不先报错 而是再通过byName匹配
     */
    @Autowired
    private OrderService orderServiceImpl;

    /**
     * 此种是可以成功的  这种方式也并不证明autowired是先byType 后byName 因为可能先byName 没有匹配 并不先报错 而是再通过byType匹配
     */
    //Autowired
    //rivate OrderServiceImpl orderService;
}
