package com.xibuguigu.yygh.hosp.controller.api;

import com.xibuguigu.yygh.hosp.service.HospitalService;
import com.xibuguigu.yygh.hosp.utils.HttpRequestHelper;
import com.xibuguigu.yygh.hosp.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Api("对医院方开放管理接口")
@RestController
@RequestMapping("/api/hosp")

public class HospitalController {
    @Autowired
    private HospitalService hospitalService;



    //http://localhost/api/hosp/saveHospital
    @ApiOperation("上传医院基本信息")
    @PostMapping("/saveHospital")
    public Result save(
            HttpServletRequest request
            ){
        Map<String, Object> map = HttpRequestHelper.switchMap(request.getParameterMap());
        hospitalService.save(map);
        return Result.ok();

    }

}
