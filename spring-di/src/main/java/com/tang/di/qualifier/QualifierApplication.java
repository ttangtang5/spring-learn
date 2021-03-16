package com.tang.di.qualifier;

import com.tang.di.qualifier.annotation.CarGroup;
import com.tang.pojo.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

/**
 * {@link Qualifier} 通过扩展此注解 实现bean分组。 spring cloud 中@LoadBalanced就是用了此特性
 */
public class QualifierApplication {

    @Autowired
    private Collection<Car> cars;

    @Autowired
    @CarGroup
    private Collection<Car> carsGroup;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(QualifierApplication.class);

        QualifierApplication qualifierApplication = applicationContext.getBean(QualifierApplication.class);

        System.out.println(qualifierApplication.cars);
        System.out.println(qualifierApplication.carsGroup);
    }

    @Bean
    public Car car1() {
        return new Car(1L, "car1");
    }

    @Bean
    public Car car2() {
        return new Car(2L, "car2");
    }

    @Bean
    @CarGroup
    public Car car3() {
        return new Car(3L, "car3");
    }

    @Bean
    @CarGroup
    public Car car4() {
        return new Car(4L, "car4");
    }


}
