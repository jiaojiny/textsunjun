package com.xibuguigu.yygh.hosp.controller.admin;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xibuguigu.yygh.common.result.R;
import com.xibuguigu.yygh.hosp.service.HospitalSetService;
import com.xibuguigu.yygh.hosp.utils.MD5;
import com.xibuguigu.yygh.model.hosp.HospitalSet;
import com.xibuguigu.yygh.vo.hosp.HospitalSetQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Random;

/**
 * <p>
 * 医院设置表 前端控制器
 * </p>
 *
 * @author atxibuguigu
 * @since 2022-11-19
 */
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
@Api("医院设置")
public class HospitalSetController {
    @Autowired
    private HospitalSetService hospitalSetService;


    @GetMapping("find/{hoscode}")
    @ApiOperation("通过id查询")
    public R findById(@PathVariable String hoscode) {
        HospitalSet hospset = hospitalSetService.findById(hoscode);
        return R.ok().data("item", hospset);
    }

    @GetMapping("findAll")
    @ApiOperation("查询所有")
    public R findAll() {
        List<HospitalSet> list = hospitalSetService.findAll();

        return R.ok().data("list", list);
    }

    @GetMapping("{page}/{limit}")
    @ApiOperation("分页")
    public R pageList(@PathVariable Long page,
                      @PathVariable Long limit,
                      HospitalSetQueryVo searchObj) {

        Page<HospitalSet> pageParams = hospitalSetService.selectPage(page, limit, searchObj);
        return R.ok().data("total", pageParams.getTotal()).data("rows", pageParams.getRecords());
    }



    @PostMapping("insert")
    @ApiOperation("新增")
    public R insert(HospitalSet hospitalSet) {
        hospitalSet.setStatus(0);
        Random random = new Random();
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis() + "" + random.nextInt(100)));


        if (hospitalSetService.save(hospitalSet)) {
            return R.ok().message("新增成功");
        } else {
            return R.ok().message("新增失败");
        }

    }


    @DeleteMapping("delete/{hoscode}")
    @ApiOperation("删除")
    public R delete(@PathVariable String hoscode) {

        if (hospitalSetService.deleteByHoscode(hoscode) > 0) {
            return R.ok().message("删除成功");
        } else {
            return R.ok().message("删除失败");
        }
    }


    //更新普遍都是根据id进行更新
    @PutMapping("update/{id}")
    @ApiOperation("更新")
    public void updateById(String hoscode) {
        hospitalSetService.save(hoscode);

    }


}

