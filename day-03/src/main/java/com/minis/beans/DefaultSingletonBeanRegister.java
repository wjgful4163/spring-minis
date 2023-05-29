package com.minis.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wjgful
 * @version 2023/5/29 19:18
 */
public class DefaultSingletonBeanRegister implements SingletonBeanRegister {

    /**
     * IOC容器中存放多有bean 名称集合
     */
    private List<String> beanNames = new ArrayList<>();
    /**
     * IOC容器中 存放所有bean 实列 集合
     */
    private Map<String, Object> singletons = new ConcurrentHashMap<>(256);

    @Override
    public void registerSingleton(String beanName, Object obj)  {
        synchronized (this.singletons) {
            this.singletons.put(beanName, obj);
            beanNames.add(beanName);
        }
    }

    @Override
    public Object getSingleton(String beanName) {
        return this.singletons.get(beanName);
    }

    @Override
    public boolean containsSingleton(String beanName) {
        return this.singletons.containsKey(beanName);
    }

    @Override
    public String[] getSingletonNames() {
        return (String[]) this.beanNames.toArray();
    }

    protected void removeSingleton(String beanName) {
        synchronized (this.singletons) {
            this.singletons.remove(beanName);
            this.beanNames.remove(beanName);
        }
    }
}
