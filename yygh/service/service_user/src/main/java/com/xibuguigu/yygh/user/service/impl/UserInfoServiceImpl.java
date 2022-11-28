package com.xibuguigu.yygh.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xibuguigu.yygh.common.exception.YyghException;
import com.xibuguigu.yygh.common.utils.JwtHelper;
import com.xibuguigu.yygh.model.user.UserInfo;
import com.xibuguigu.yygh.user.mapper.UserInfoMapper;
import com.xibuguigu.yygh.user.service.UserInfoService;
import com.xibuguigu.yygh.vo.user.LoginVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author xibuguigu
 * @since 2022-11-24
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService{

    @Override
    public Map<String, Object> login(LoginVo loginVo) {
        String phone = loginVo.getPhone();
        String code = loginVo.getCode();

        //校验参数
        if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(code)) {
            throw new YyghException(20010, "参数不能为空");
        }

        //TODO 校验验证码

        //根据手机号查找会员
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        UserInfo userInfo = baseMapper.selectOne(queryWrapper);

        //校验是否被禁用
        if(userInfo != null && userInfo.getStatus() == 0) {
            throw new YyghException(20001,"用户已经禁用");
        }

        //用户注册
        if(userInfo == null) {
            userInfo = new UserInfo();
            userInfo.setPhone(phone);
            userInfo.setName(phone);
            userInfo.setNickName(phone);
            userInfo.setStatus(1); //正常
            baseMapper.insert(userInfo);
        }

        //返回页面显示名称
        Map<String, Object> map = new HashMap<>();
        String name = userInfo.getName();

        String token = JwtHelper.createToken(userInfo.getId(),name);
        map.put("token", token); //TODO 访问令牌
        return map;
    }

}
