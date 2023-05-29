package com.minis.context;

import com.minis.beans.*;
import com.minis.core.ClassPathXmlResource;
import com.minis.core.Resource;

/**
 * @author wjgful
 * @version 2023/5/26 15:02
 * 解析某个路径下的 XML 来构建应用上下文
 * <p>按照一定的规则将 XML 文件的内容解析出来，获取 Bean 的配置信息</p>
 */
public class ClassPathXmlApplicationContext implements BeanFactory {
    private final SimpleBeanFactory simpleBeanFactory;

    public ClassPathXmlApplicationContext(String fileName) {
        //读取外部配置信息
        Resource resource = new ClassPathXmlResource(fileName);
        //解析bean 创建beanFactory
        SimpleBeanFactory beanFactory = new SimpleBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);
        this.simpleBeanFactory = beanFactory;
    }


    /**
     * 外部从容器中获取bean实列
     *
     * @param beanName
     * @return
     */
    @Override
    public Object getBean(String beanName) throws BeansException {
        return this.simpleBeanFactory.getBean(beanName);
    }

    @Override
    public void registerBean(String beanName, Object obj) {
        this.simpleBeanFactory.registerBean(beanName,obj);
    }

    @Override
    public Boolean containsBean(String name) {
        return this.simpleBeanFactory.containsBean(name);
    }


}
