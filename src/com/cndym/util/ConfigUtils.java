package com.cndym.util;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.cndym.util.export.Utils;


public class ConfigUtils extends BaseUtils {
    private static Map<String, String> map = new HashMap<String, String>();
    public static final String CONFIG_FILE = "properties/config.properties";
    
    static {
        forInstance();
    }

    public static void forInstance() {
        try {
            Properties properties = new Properties();
            InputStream inputStream = ConfigUtils.class.getResourceAsStream("/config.properties");
            inputStream = new FileInputStream(Utils.getClassPath() + CONFIG_FILE);

            properties.load(inputStream);
            Enumeration all = properties.keys();
            while (all.hasMoreElements()) {
                String name = (String) all.nextElement();
                String value = (String) properties.get(name);
                map.put(name, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static String getValue(String key) {
        if (map.containsKey(key)) {
            return map.get(key).trim();
        }
        throw new IllegalArgumentException("属性值（" + key + "）不存在");
    }

    public static void update(String key, String value) {
        if (map.containsKey(key)) {
            map.put(key, value);
        }
    }

}