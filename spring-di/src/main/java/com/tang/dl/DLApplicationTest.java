package com.tang.dl;

import com.tang.dl.annotation.SpecialCar;
import com.tang.dl.pojo.Car;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;


/**
 * @Description 依赖查找相关示例
 * 依赖查找-依赖主动获取，依赖容器API
 * @Author tang
 * @Date 2020/6/25 10:27
 * @Version 1.0
 **/
public class DLApplicationTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-context.xml");

        // DL
        lookupByName(applicationContext);

        lookupInLazy(applicationContext);

        lookupByType(applicationContext);

        lookupCollection(applicationContext);

        lookupAnnotation(applicationContext);
    }

    /**
     * 依赖查找-实时查找-byName
     * @param applicationContext
     */
    private static void lookupByName(ApplicationContext applicationContext) {
        Car car = (Car) applicationContext.getBean("car");
        System.out.println(car.toString());
    }

    /**
     * {@link ObjectFactory} 依赖查找-延时查找 通过ObjectFactory实现，只有objectFactory.getObject()才实例化 正确的targetName 对应的bean需要设置懒加载
     * @param applicationContext
     */
    private static void lookupInLazy(ApplicationContext applicationContext) {
        ObjectFactory<Car> objectFactory = (ObjectFactory<Car>) applicationContext.getBean("objectFactory");
        Car car = objectFactory.getObject();
        System.out.println(car.toString());
    }

    /**
     * 依赖查找-实时查找-byType
     * @param applicationContext
     */
    private static void lookupByType(ApplicationContext applicationContext) {
        Car car = applicationContext.getBean(Car.class);
        System.out.println(car.toString());
    }


    /**
     * 依赖查找-查找同注解所有对象
     * @param applicationContext
     */
    private static void lookupAnnotation(ApplicationContext applicationContext) {
        if (applicationContext instanceof ListableBeanFactory) {
            Map<String, Object> carMap = applicationContext.getBeansWithAnnotation(SpecialCar.class);
            System.out.println(carMap.toString());
        }
    }

    /**
     * 依赖查找-查找同类型所有对象
     * @param applicationContext
     */
    private static void lookupCollection(ApplicationContext applicationContext) {
        if (applicationContext instanceof ListableBeanFactory) {
            Map<String, Car> carMap = applicationContext.getBeansOfType(Car.class);
            System.out.println(carMap.toString());
        }
    }






}
