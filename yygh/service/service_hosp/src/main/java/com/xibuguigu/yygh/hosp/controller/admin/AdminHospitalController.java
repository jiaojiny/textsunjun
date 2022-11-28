package com.xibuguigu.yygh.hosp.controller.admin;


import com.xibuguigu.yygh.common.result.R;
import com.xibuguigu.yygh.hosp.service.HospitalService;
import com.xibuguigu.yygh.model.hosp.Hospital;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;                  //查mongodb用这个
import org.springframework.web.bind.annotation.*;

@Api(tags = "医院接口")
@RestController
@RequestMapping("/admin/hosp/hospital")
public class AdminHospitalController {

    //注入service
    @Autowired
    private HospitalService hospitalService;

    @ApiOperation(value = "获取分页列表")
    @GetMapping("{page}/{limit}")
    public R pageList(
            @PathVariable Integer page, //路径
            @PathVariable Integer limit, //路径
            String hosname /*查询字符串*/) {
        //调用方法
        Page<Hospital> pageModel = hospitalService.selectPage(page, limit, hosname);
        return R.ok().data("pageModel", pageModel);
        //封装数据字典相关列
      //  pageModel.getContent().stream().forEach(item->this.packHospital(item));

    }

    private void packHospital(Hospital item) {

    }
}