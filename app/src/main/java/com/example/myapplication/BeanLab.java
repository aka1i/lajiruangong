package com.example.myapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yjn
 * @create 2019/11/2 - 10:35
 */
public class BeanLab {
    private static BeanLab sBeanLab;
    private static List<ShopBean> mShopBeans = new ArrayList<>();
    private static Map<String,List<ShopBean>> map = new HashMap<>();
    public static BeanLab get(){
        if (sBeanLab == null){
            sBeanLab = new BeanLab();
        }
        return sBeanLab;
    }
    public List<ShopBean> getShopBeans() {
        return mShopBeans;
    }
    public Map<String,List<ShopBean>> getMap() {
        return map;
    }
}
