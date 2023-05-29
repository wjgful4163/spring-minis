package com.minis.beans;

import com.minis.core.Resource;
import org.dom4j.Element;


/**
 * @author wjgful
 * @version 2023/5/29 10:14
 */
public class XmlBeanDefinitionReader {

    SimpleBeanFactory simpleBeanFactory;

    public XmlBeanDefinitionReader(SimpleBeanFactory simpleBeanFactory) {
        this.simpleBeanFactory = simpleBeanFactory;
    }

    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            //bean基本信息
            String beanId = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(beanId, beanClassName);

            this.simpleBeanFactory.registerBeanDefinition(beanDefinition);
        }

    }
}
