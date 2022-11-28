package com.xibuguigu.yygh.hosp.controller.front;

import com.xibuguigu.yygh.common.result.R;
import com.xibuguigu.yygh.hosp.service.HospitalService;
import com.xibuguigu.yygh.model.hosp.Hospital;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "医院接口")
@RestController
@RequestMapping("/front/hosp/hospital")
public class FrontHospitalController {

    @Autowired
    private HospitalService hospitalService;

    @ApiOperation(value = "根据医院名称、级别和区域查询医院列表")
    @GetMapping("list")
    public R list(String keywords, String levelId, String areaId) {

        List<Hospital> list = hospitalService.selectList(keywords, levelId, areaId);
        return R.ok().data("list",list);
    }
}