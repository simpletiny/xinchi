package com.xinchi.tools;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;

/** 
 * @author wjx 
 * @date 2015年1月13日 上午9:29:30 
 * @note 反射工具类
 */
public class ReflexUtil {
    static Logger logger = Logger.getLogger(ReflexUtil.class);
 
    //getMethod
    static public Object invokeMethod(String propertiesName, Object object) {
        try {
            if(object==null) return null;
            if (!propertiesName.contains(".")) {
                String methodName = "get"+getMethodName(propertiesName);
                Method method = object.getClass().getMethod(methodName);
                return method.invoke(object);
            }
            String methodName = "get"+getMethodName(propertiesName.substring(0,propertiesName.indexOf(".")));
            Method method = object.getClass().getMethod(methodName);
            return invokeMethod(propertiesName.substring(propertiesName.indexOf(".")+1), method.invoke(object));
 
        } catch (Exception e) {
            logger.error(e.toString(), e);
            return null;
        }
    }
 
    private static String getMethodName(String fildeName) {
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }
 
}
