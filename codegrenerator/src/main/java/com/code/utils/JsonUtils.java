package com.code.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author yangkaihu@yeah.net
 * @date 2024/5/9 20:04
 * @descirption: 把 obj 转换为json 格式
 */
public class JsonUtils {
    public static  String convertObj2Json(Object obj){
        if (null==obj)
        {
            return null;
        }
        return JSON.toJSONString(obj, SerializerFeature.DisableCircularReferenceDetect);
    }
}
