package com.xibuguigu.yygh.hosp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xibuguigu.yygh.hosp.mapper.HospitalSetMapper;
import com.xibuguigu.yygh.hosp.service.HospitalSetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xibuguigu.yygh.model.hosp.HospitalSet;
import com.xibuguigu.yygh.vo.hosp.HospitalSetQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 医院设置表 服务实现类
 * </p>
 *
 * @author atxibuguigu
 * @since 2022-11-19
 */
@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalSetService {


    @Override
    public HospitalSet findById(String hoscode) {
        return baseMapper.selectById(hoscode);
    }

    @Override
    public List<HospitalSet> findAll() {
        QueryWrapper<HospitalSet> queryWrapper = new QueryWrapper<>();

        return baseMapper.selectList(queryWrapper);
    }



    @Override
    public Page<HospitalSet> selectPage(Long page, Long limit, HospitalSetQueryVo searchObj) {
        Page<HospitalSet> result = new Page<>(page, limit);

        QueryWrapper<HospitalSet> queryWrapper = new QueryWrapper<>();

        baseMapper.selectPage(result, queryWrapper);

        if (searchObj == null) {
            //判断 这个查询条件为空的情况下 执行这个查询
            baseMapper.selectPage(result, null);

        } else {

            //判断这个查询条件不能为空
            String hoscode = searchObj.getHoscode();
            String hosname = searchObj.getHosname();
            //正则，列名，数据
            queryWrapper.eq(!StringUtils.isEmpty(hoscode), "hoscode", hoscode);
            queryWrapper.like(!StringUtils.isEmpty(hosname), "hosname", hosname);
            //有查询条件的话，执行这个查询
            baseMapper.selectPage(result, queryWrapper);
        }
        return result;
    }

    @Override
    public void save(String hoscode) {

        HospitalSet hospitalSet = baseMapper.selectById(hoscode);

    }

    @Override
    public Integer deleteByHoscode(String hoscode) {
        QueryWrapper<HospitalSet> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("hoscode", hoscode);
        return baseMapper.delete(queryWrapper);
    }




}

