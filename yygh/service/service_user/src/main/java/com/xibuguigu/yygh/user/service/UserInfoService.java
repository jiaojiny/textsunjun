package com.xibuguigu.yygh.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xibuguigu.yygh.model.user.UserInfo;
import com.xibuguigu.yygh.vo.user.LoginVo;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author xibuguigu
 * @since 2022-11-24
 */
public interface UserInfoService extends IService<UserInfo> {
    Map<String, Object> login(LoginVo loginVo);
}
