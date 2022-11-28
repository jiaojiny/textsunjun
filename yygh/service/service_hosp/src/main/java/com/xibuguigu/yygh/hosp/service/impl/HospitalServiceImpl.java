package com.xibuguigu.yygh.hosp.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.xibuguigu.yygh.cmn.client.DictFeignClient;
import com.xibuguigu.yygh.enums.DictEnum;
import com.xibuguigu.yygh.hosp.repository.HospitalRepository;
import com.xibuguigu.yygh.hosp.service.HospitalService;
import com.xibuguigu.yygh.model.hosp.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private DictFeignClient dictFeignClient;
    @Override
    public void save(Map<String, Object> map) {
        //把 map 转成json字符串 再把json字符串转成 对象
        String s = JSONObject.toJSONString(map);
        Hospital hospital = JSONObject.parseObject(s, Hospital.class);
        //去后台查是否已经添加，
        Hospital existHospital = hospitalRepository.findByHoscode(hospital.getHoscode());
        //如果已经添加就修改，
        if (existHospital != null) {
            hospital.setId(existHospital.getId());
            hospital.setCreateTime(existHospital.getCreateTime());
            hospital.setUpdateTime(new Date());
            hospitalRepository.save(hospital);
        }else {
            //如果没有添加就新增。
            hospital.setCreateTime(new Date());
            hospital.setUpdateTime(new Date());
            hospitalRepository.save(hospital);
        }
    }

    @Override
    public List<Hospital> selectList(String keywords, String levelId, String areaId) {
        return null;
    }

    @Override
    public Page<Hospital> selectPage(Integer page, Integer limit, String hosname) {

        //设置排序规则
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");

        //设置分页参数
        Pageable pageable = PageRequest.of(page-1, limit, sort);

        //创建条件匹配器
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //模糊查询
                .withIgnoreCase(true); //忽略大小写

        //创建查询对象
        Hospital hospital = new Hospital();
        hospital.setHosname(hosname);
        Example<Hospital> example = Example.of(hospital, matcher);

        //执行查询
        Page<Hospital>  pageModel =  hospitalRepository.findAll(example, pageable);

        pageModel.forEach(item->packHospital(item));
        return pageModel;






    }

    private void packHospital(Hospital hospital) {
            //远程调用cmn  给它一个类型一个值，查出我们想要的结果
        String hostype = dictFeignClient.getName(DictEnum.HOSTYPE.getDictCode(), hospital.getHostype());
            //把我们查出来的值封装到hostype
        hospital.getParam().put("hostypeString",hostype);
       String provinceCode = dictFeignClient.getName(null,hospital.getProvinceCode());
        String cityCode = dictFeignClient.getName(null, hospital.getCityCode());
        String districtCode = dictFeignClient.getName(null, hospital.getDistrictCode());
        hospital.getParam().put("fullAddress",provinceCode+cityCode+districtCode+hospital.getAddress());

    }



}
