package com.zssq.news.enums;

/**
 * Created by admin on 2017-06-20.
 */
public enum UnitUtil {


    UnitB(1,"b"), UnitK(2,"kb"), UnitM(3,"mb"), UnitG(4,"g");


    private int index ;
    private String name ;

    private UnitUtil(int index ,String name ){
        this.index = index ;
        this.name = name ;
    }
    // 普通方法
    public static String getName(int index) {
        for (UnitUtil c : UnitUtil.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }
    // get set 方法
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }

}
