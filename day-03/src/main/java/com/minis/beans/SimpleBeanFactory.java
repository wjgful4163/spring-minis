package com.minis.beans;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wjgful
 * @version 2023/5/29 10:20
 */
public class SimpleBeanFactory extends DefaultSingletonBeanRegister implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitions = new ConcurrentHashMap<>(256);

    public SimpleBeanFactory() {
    }

    @Override
    public Object getBean(String beanName) throws BeansException {
        //获取bean 实列
        Object singleton = this.getSingleton(beanName);
        if (singleton == null) {
            //检查beanDefinition 中是否已经加载了配置信息
            BeanDefinition beanDefinition = beanDefinitions.get(beanName);
            if (beanDefinition == null) {
                throw new BeansException("NO bean..");
            }


            try {
                singleton = Class.forName(beanDefinition.getClassName()).newInstance();
                this.registerSingleton(beanName, singleton);
            } catch (Exception e) {
                System.out.println("反射创建实列异常");
            }

        }
        return singleton;
    }

    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanDefinitions.put(beanDefinition.getId(), beanDefinition);
    }

    @Override
    public void registerBean(String beanName, Object obj) {
        this.registerSingleton(beanName, obj);
    }

    @Override
    public Boolean containsBean(String name) {
        return this.containsSingleton(name);
    }


}
