package com.minis.beans;

/**
 * @author wjgful
 * @version 2023/5/29 16:23
 */
public interface SingletonBeanRegister {
    /**
     * 注册单列bean
     * @param beanName
     * @param obj
     * @throws BeansException
     */
    void registerSingleton(String beanName,Object obj) ;

    Object getSingleton(String beanName);

    boolean containsSingleton(String beanName);
    String[] getSingletonNames();
}
