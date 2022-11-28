package com.xibuguigu.yygh.hosp.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xibuguigu.yygh.model.hosp.Hospital;
import com.xibuguigu.yygh.model.hosp.HospitalSet;
import com.xibuguigu.yygh.vo.hosp.HospitalSetQueryVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 医院设置表 服务类
 * </p>
 *
 * @author atxibuguigu
 * @since 2022-11-19
 */
public interface HospitalSetService extends IService<HospitalSet> {

    List<HospitalSet> findAll();
    Integer deleteByHoscode(String hoscode);
    Page<HospitalSet> selectPage(Long page, Long limit, HospitalSetQueryVo searchObj);

    void save(String hoscode);

    HospitalSet findById(String hoscode);

}
