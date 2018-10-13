package com.pm.japi.utils;


public class PathUtils {

    public static String parentName(String name,String flag){
        int i=name.lastIndexOf(flag);
        if(i<=0){
            return null;
        }else{
            return name.substring(0,i);
        }
    }

    public static String getName(String name,String flag){
        int i=name.lastIndexOf(flag);
        if(i<=0){
            return name;
        }else{
            return name.substring(i+1);
        }
    }

}
