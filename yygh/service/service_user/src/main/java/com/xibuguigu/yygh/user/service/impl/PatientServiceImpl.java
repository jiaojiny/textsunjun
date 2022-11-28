package com.xibuguigu.yygh.user.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xibuguigu.yygh.model.user.Patient;
import com.xibuguigu.yygh.user.mapper.PatientMapper;
import com.xibuguigu.yygh.user.service.PatientService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 就诊人表 服务实现类
 * </p>
 *
 * @author xibuguigu
 * @since 2022-11-24
 */
@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient> implements PatientService {

}
