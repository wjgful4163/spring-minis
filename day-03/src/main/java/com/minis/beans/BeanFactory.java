package com.minis.beans;

/**
 * @author wjgful
 * @version 2023/5/29 9:49
 */
public interface BeanFactory {
    /**
     * 获取Bean 实列
     *
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object getBean(String beanName) throws BeansException;

    /**
     * 注册BeanDefinition
     *
     * @param beanName
     * @param obj
     */
    void registerBean(String beanName, Object obj);

    /**
     * 判断是否存在
     *
     * @param name
     * @return
     */
    Boolean containsBean(String name);
}
