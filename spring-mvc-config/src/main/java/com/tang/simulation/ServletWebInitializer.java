package com.tang.simulation;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * ServletContainerInitializer 为tomcat 提供的接口 通过spi发现实现
 * HandlesTypes 这个会将该中的接口实现类  传到onStartup set参数中
 * 参考spring web: SpringServletContainerInitializer 同理
 *
 * 注意idea 编译没把META-INF复制到编译环境
 */
@HandlesTypes({TestInitializer.class})
public class ServletWebInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        List<Class<?>> classes = new ArrayList<>();
        if (set != null) {
            Iterator<Class<?>> iterator = set.iterator();
            while (iterator.hasNext()) {
                Class<?> waiClass = (Class)iterator.next();
                classes.add(waiClass);
            }
        }

        for (Class<?> aClass : classes) {
            try {
                Object newInstance = aClass.newInstance();
                System.out.println(newInstance.toString());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
