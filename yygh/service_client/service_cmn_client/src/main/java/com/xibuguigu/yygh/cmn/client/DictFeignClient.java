package com.xibuguigu.yygh.cmn.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("service-cmn")
public interface DictFeignClient {
//get请求没有请求体
    //requestParam
    @GetMapping(value = "/inner/cmn/dict/getName")
    String getName(
            @RequestParam("parentDictCode") String parentDictCode,
            @RequestParam("value") String value);



}
