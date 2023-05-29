package com.minis.context;

/**
 * 发布事件
 * @author wjgful
 * @version 2023/5/29 19:39
 */
public interface ApplicationEventPublisher {
    /**
     * 发布事件
     * @param event
     */
    void publishEvent(ApplicationEvent event);
}
