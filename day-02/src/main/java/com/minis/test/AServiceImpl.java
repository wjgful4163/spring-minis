package com.minis.test;

/**
 * @author wjgful
 * @version 2023/5/26 16:38
 */
public class AServiceImpl implements AService{
    @Override
    public String eCho() {
        System.out.println("AServiceImpl....");
        return "AServiceImpl";
    }
}
