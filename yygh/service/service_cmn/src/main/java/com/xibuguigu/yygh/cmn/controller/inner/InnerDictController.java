package com.xibuguigu.yygh.cmn.controller.inner;

import com.xibuguigu.yygh.cmn.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "数据字典接口")
@RestController
@RequestMapping("/inner/cmn/dict")
public class InnerDictController {

    @Autowired
    private DictService dictService;

    @ApiOperation(value = "获取数据字典名称")
    @GetMapping(value = "getName")
    public String getName(
            String parentDictCode,
            String value) {

        return dictService.getNameByParentDictCodeAndValue(parentDictCode, value);
    }

}