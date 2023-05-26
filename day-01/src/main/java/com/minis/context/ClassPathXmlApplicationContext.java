package com.minis.context;

import com.minis.beans.BeanDefinition;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wjgful
 * @version 2023/5/26 15:02
 * 解析某个路径下的 XML 来构建应用上下文
 * <p>按照一定的规则将 XML 文件的内容解析出来，获取 Bean 的配置信息</p>
 */
public class ClassPathXmlApplicationContext {
    private final List<BeanDefinition> beanDefinitions = new ArrayList<>();
    private final Map<String, Object> singletons = new HashMap<>();

    public ClassPathXmlApplicationContext(String fileName) {
        readXml(fileName);
        instanceBeans();
    }

    /**
     * 读取bean xml 信息 将解析结果放入 list集合中
     *
     * @param fileName
     */
    private void readXml(String fileName) {
        try {
            SAXReader saxReader = new SAXReader();
            URL xmlPath = this.getClass().getClassLoader().getResource(fileName);

            Document document = saxReader.read(xmlPath);

            Element rootElement = document.getRootElement();
            //遍历处理bean中的所有配置文件
            for (Element element : rootElement.elements()) {
                //bean基本信息
                String beanId = element.attributeValue("id");
                String beanClassName = element.attributeValue("class");
                BeanDefinition beanDefinition = new BeanDefinition(beanId, beanClassName);

                //将xml中定义的bean 放入 beanDefinitions 集合中
                beanDefinitions.add(beanDefinition);
            }
        } catch (DocumentException e) {
            System.out.println("读取配置bean 配置xml 异常");
        }
    }

    /**
     * 创建bean 对象 （通过反射的方式）
     */
    private void instanceBeans() {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            try {
                Object obj = Class.forName(beanDefinition.getClassName()).newInstance();
                singletons.put(beanDefinition.getId(), obj);
            } catch (Exception e) {
                System.out.println("创建xml 方式配置的bean 对象异常");
            }
        }
    }

    /**
     * 外部从容器中获取bean实列
     *
     * @param beanName
     * @return
     */
    public Object getBean(String beanName) {
        return singletons.get(beanName);
    }

}
