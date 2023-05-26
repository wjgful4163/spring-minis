package com.minis;

import com.minis.context.ClassPathXmlApplicationContext;
import com.minis.test.AService;
import org.junit.jupiter.api.Test;

/**
 * @author wjgful
 * @version 2023/5/26 16:43
 */
public class Test01 {

    @Test
    public void test_Ioc01(){
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
        AService aservice = (AService)context.getBean("aservice");
        String result = aservice.eCho();
        System.out.println("返回结果内容："+result);

    }
}
