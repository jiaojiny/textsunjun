package com.xibuguigu.yygh.cmn.controller.admin;


import com.xibuguigu.yygh.cmn.service.DictService;
import com.xibuguigu.yygh.common.result.R;
import com.xibuguigu.yygh.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 组织架构表 前端控制器
 * </p>
 *
 * @author atxibuguigu
 * @since 2022-11-21
 */
@Api("数据字典")
@RestController
@RequestMapping("/cmn/dict")
public class DictController {

    @Autowired
    private DictService dictService;

    @ApiOperation("字典树")
    @GetMapping("{hoscode}")
    public R findTrees(
            @PathVariable Long parentId
                ){
              List<Dict> list =  dictService.findTreeByParentId(parentId);

        return R.ok().data("list",list);
    }


}

