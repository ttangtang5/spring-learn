package com.tang.jdkProxy.custom;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class ProxyUtils {

    public static Object newInstance(Class cls, Object target) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String objName = cls.getSimpleName();
        String clsName = cls.getName();
        String pkgName = cls.getPackage().getName();
        Method[] clsMethods = cls.getDeclaredMethods();

        System.out.println(cls.getModifiers());
        String line = "\n";
        String tab = "\t";
        // 1、构造代理对象内容
        String pkgContent = "package com.tang;" + line;
        String importContent = "import " + clsName + ";" + line;
        String attrContent = "private " + objName + " target;" + line;
        String constContent = "public $Proxy(" + objName + " target) {this.target = target;}" + line;
        String methodContent = "";
        for (int i = 0; i < clsMethods.length; i++) {
            Method methodTemp = clsMethods[i];
            String methodStr = "public " + methodTemp.getReturnType().getSimpleName() + " " + methodTemp.getName() +"(){" + line
                    + "System.out.println(\"proxy\"); target." + methodTemp.getName() + "();"+line+"}" + line;
            methodContent += methodStr;
        }

        String clsContent = pkgContent + importContent
                + "public class $Proxy implements " + objName + "{" + line
                + attrContent + constContent + methodContent + line
                + "}";

        // 2、生成Java文件 包名是需要实际的目录支撑
        String fileName = "E:\\com\\tang\\$Proxy.java";
        File file = new File(fileName);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file);
        fw.write(clsContent);
        fw.flush();

        // 3、编译成字节码
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        Iterable iterable = fileManager.getJavaFileObjects(fileName);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, iterable);
        task.call();
        fileManager.close();

        // 4、生成一个实例对象
        // 通过URLClassLoader类加载器 将class加载到内存
        URLClassLoader classLoader = new URLClassLoader(new URL[]{new URL("file:/E:\\")});
        Class<?> loadClass = classLoader.loadClass("com.tang.$Proxy");

        Constructor<?> constructor = loadClass.getConstructor(cls);
        return constructor.newInstance(target);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        UserService userService = (UserService) ProxyUtils.newInstance(UserService.class, new UserServiceImpl());
        userService.insert();
    }
}
