package com.minis.beans;

import java.util.*;

/**
 * @author wjgful
 * @version 2023/5/29 10:20
 */
public class SimpleBeanFactory implements BeanFactory {

    private List<BeanDefinition> beanDefinitions = new ArrayList<>();
    private Map<String, Object> singletons = new HashMap<>();

    public SimpleBeanFactory() {
    }

    @Override
    public Object getBean(String beanName) throws BeansException {
        //获取bean 实列
        Object singleton = singletons.get(beanName);
        if (singleton == null) {
            //检查beanDefinition 中是否已经加载了配置信息
            BeanDefinition beanDefinition = beanDefinitions.stream().filter(bean -> Objects.equals(bean.getId(), beanName))
                    .findFirst().orElseThrow(() -> new BeansException());

            try {
                singleton = Class.forName(beanDefinition.getClassName()).newInstance();
                singletons.put(beanDefinition.getId(), singleton);
            } catch (Exception e) {
                System.out.println("反射创建实列异常");
            }

        }
        return singleton;
    }

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanDefinitions.add(beanDefinition);
    }
}
