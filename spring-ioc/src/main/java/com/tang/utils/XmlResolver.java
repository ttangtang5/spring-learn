package com.tang.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 读取xml文件
 */
public class XmlResolver {

    public static Element resolver(String path) throws IOException, ParserConfigurationException, SAXException, DocumentException {
        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException("文件资源未找到");
        }

        SAXReader reader = new SAXReader();
        //加载document对象
        Document document = reader.read(file);

        Element rootElement = document.getRootElement();

        return rootElement;
    }
}
