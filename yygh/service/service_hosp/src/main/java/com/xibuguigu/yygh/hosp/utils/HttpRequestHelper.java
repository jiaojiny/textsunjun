package com.xibuguigu.yygh.hosp.utils;

import com.xibuguigu.yygh.common.exception.YyghException;
import com.xibuguigu.yygh.common.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
public class HttpRequestHelper {

    public static Map<String, Object> switchMap(Map<String, String[]> paramMap) {
        Map<String, Object> resultMap = new HashMap<>();
        for (Map.Entry<String, String[]> param : paramMap.entrySet()) {
            resultMap.put(param.getKey(), param.getValue()[0]);
        }
        return resultMap;
    }

    /**
     * 请求数据获取签名
     * @param paramMap
     * @return·
     */
    public static String getSign(Map<String, Object> paramMap, String signKey) {
        //去掉sign参数
        if(paramMap.containsKey("sign")) {
            paramMap.remove("sign");
        }

        //有序
        TreeMap<String, Object> sorted = new TreeMap<>(paramMap);
        StringBuilder str = new StringBuilder();
        for (Map.Entry<String, Object> param : sorted.entrySet()) {
            //获取键值对中的值
            str.append(param.getValue()).append("|");
        }
        //最后连接signKey
        str.append(signKey);
        log.info("加密前：" + str.toString());
        String md5Str = MD5.encrypt(str.toString());
        log.info("加密后：" + md5Str);
        return md5Str;
    }

    /**
     * 验签方法
     * @param parameterMap
     */
    public static void checkSign(Map<String, Object> parameterMap, String signKey){
        //从参数中获取签名（signKey）
        String signRemote = (String)parameterMap.get("sign");

        String signLocal = getSign(parameterMap, signKey);
        if(StringUtils.isEmpty(signRemote)){
            log.error("没有签名");
            throw new YyghException("没有签名", ResultCode.ERROR);
        }

        if(!signRemote.equals(signLocal)){
            log.error("验签失败");
            throw new YyghException("签名校验失败", ResultCode.ERROR);
        }
    }
}
