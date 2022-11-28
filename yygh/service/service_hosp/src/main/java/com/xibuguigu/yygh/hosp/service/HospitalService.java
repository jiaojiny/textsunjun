package com.xibuguigu.yygh.hosp.service;

import com.xibuguigu.yygh.model.hosp.Hospital;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface HospitalService {
    void save(Map<String, Object> map);

    List<Hospital> selectList(String keywords, String levelId, String areaId);
    Page<Hospital> selectPage(Integer page, Integer limit, String hosname);
}
