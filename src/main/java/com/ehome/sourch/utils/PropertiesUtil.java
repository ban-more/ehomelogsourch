package com.ehome.sourch.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * 读取配置文件工具类
 */
public class PropertiesUtil {
    static Properties property = new Properties();
    public static boolean loadFile(String fileName){
        try {
            property.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static String getPropertyValue(String key){
        return property.getProperty(key);
    }
}
