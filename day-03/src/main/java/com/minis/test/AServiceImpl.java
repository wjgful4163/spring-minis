package com.minis.test;

/**
 * @author wjgful
 * @version 2023/5/26 16:38
 */
public class AServiceImpl implements AService{

    private String property1;

    public void setProperty1(String property1) {
        this.property1 = property1;
    }

    @Override
    public String eCho() {
        System.out.println("AServiceImpl....");
        return "AServiceImpl";
    }
}
