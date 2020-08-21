package com.tang.config;

import com.tang.controller.UserController;
import com.tang.entity.BeanConfig;
import com.tang.service.OrderService;
import com.tang.utils.XmlResolver;
import org.dom4j.Attribute;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;

/**
 * bean 工厂类
 */
public class BeanFactory {

    private Map<String, BeanConfig> container = new HashMap<String, BeanConfig>();

    private String configPath;

    public BeanFactory(String configPath) throws Exception {
        this.configPath = configPath;

        init();
    }

    private void init() throws Exception {
        String filePrefix = BeanFactory.class.getClassLoader().getResource("").getFile();
        Element root = XmlResolver.resolver(filePrefix + configPath);

        Iterator<Element> iterator = root.elementIterator();
        while (iterator.hasNext()) {
            Element element = iterator.next();
            Attribute idAtt = element.attribute("id");
            Attribute clazzAtt = element.attribute("class");
            String clazzStr = clazzAtt.getValue();
            String idStr = idAtt.getValue();

            Class<?> clazz = Class.forName(clazzStr);

            Iterator<Element> constructionIterator = element.elementIterator("construction");

            if(constructionIterator.hasNext()) {
                continue;
            }

            BeanConfig beanConfig = new BeanConfig();
            beanConfig.setId(idStr);
            beanConfig.setClazz(clazz);
            Object obj = clazz.newInstance();
            beanConfig.setObject(obj);

            container.put(idStr, beanConfig);
        }
    }

    public Object getBean(String name) throws Exception {
        String filePrefix = BeanFactory.class.getClassLoader().getResource("").getFile();
        Element root = XmlResolver.resolver(filePrefix + configPath);

        Attribute inject = root.attribute("inject");
        boolean byType = true;
        if (inject !=null && "byName".equals(inject.getValue())) {
            byType = false;
        }

        Iterator<Element> iterator = root.elementIterator();

        while (iterator.hasNext()) {
            Element element = iterator.next();
            Attribute idAtt = element.attribute("id");
            Attribute clazzAtt = element.attribute("class");
            String clazzStr = clazzAtt.getValue();
            String idStr = idAtt.getValue();

            Class<?> clazz = Class.forName(clazzStr);

            Iterator<Element> constructionIterator = element.elementIterator("construction");

            Iterator<Element> propertyIterator = element.elementIterator("property");

            Object object = null;
            Object filedObj = null;
            boolean constFlag = false;

            List<Class> filedClass = new ArrayList<Class>();
            List<Object> filedConfig = new ArrayList<Object>();
            while (constructionIterator.hasNext()) {
                constFlag = true;
                Element construction = constructionIterator.next();
                Attribute argsAtt = construction.attribute("args");
                Attribute refAtt = construction.attribute("ref");
                String filedAttStr = argsAtt.getValue();
                String refAttStr = refAtt.getValue();

                Field field = clazz.getDeclaredField(filedAttStr);
                Class<?> fieldDeclaringClass = field.getType();

                int count = 0;
                if (byType) {
                    Collection<BeanConfig> values = container.values();
                    for (BeanConfig beanConfig : values) {
                        // TODO 判断是否未接口
                        if (beanConfig.getClazz() != null && beanConfig.getClazz().getInterfaces()[0].getName().equals(fieldDeclaringClass.getName())) {
                            filedClass.add(beanConfig.getClazz().getInterfaces()[0]);
                            filedConfig.add(beanConfig.getObject());
                            count++;
                        }
                    }

                    if (count > 1) {
                        throw new Exception("存在多个匹配类型");
                    }
                } else {
                    filedClass.add(container.get(refAttStr).getClazz().getInterfaces()[0]);
                    filedConfig.add(container.get(refAttStr).getObject());
                }
            }

            if (constFlag) {
                Class[] classes = filedClass.toArray(new Class[filedClass.size()]);
                Constructor<?> constructor = clazz.getConstructor(classes);
                object = constructor.newInstance(filedConfig.toArray());

                BeanConfig beanConfigConst = new BeanConfig();
                beanConfigConst.setId(idStr);
                beanConfigConst.setClazz(clazz);
                beanConfigConst.setObject(object);
                container.put(idStr, beanConfigConst);
            }

            BeanConfig beanConfig = container.get(idStr);

            while (propertyIterator.hasNext()) {
                Element property = propertyIterator.next();

                Attribute filedAtt = property.attribute("name");
                Attribute refAtt = property.attribute("ref");
                String filedAttStr = filedAtt.getValue();
                String refAttStr = refAtt.getValue();

                Field field = clazz.getDeclaredField(filedAttStr);
                Class<?> fieldDeclaringClass = field.getDeclaringClass();

                filedObj = container.get(refAttStr).getObject();
                // TODO 顺序问题
                if (filedObj == null || filedObj.getClass().getName().equals(fieldDeclaringClass.getName())) {
                    throw new Exception("属性类型不存在或不匹配");
                }

                field.setAccessible(true);
                field.set(beanConfig.getObject(), filedObj);
            }

        }

        return container.get(name).getObject();
    }

    public static void main(String[] args) throws Exception {
        //BeanFactory beanFactory = new BeanFactory("application.xml");
        //UserController userController = (UserController) beanFactory.getBean("userController");
        //userController.insert();
        Class<?>[] interfaces = OrderService.class.getInterfaces();
        System.out.println(OrderService.class.isInterface());
    }
}
