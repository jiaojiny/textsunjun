package com.xibuguigu.yygh.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xibuguigu.yygh.model.user.Patient;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 就诊人表 Mapper 接口
 * </p>
 *
 * @author xibuguigu
 * @since 2022-11-24
 */
@Mapper
public interface PatientMapper extends BaseMapper<Patient> {

}
