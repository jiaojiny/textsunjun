package com.xibuguigu.yygh.user.controller.front;

import com.xibuguigu.yygh.common.result.R;
import com.xibuguigu.yygh.user.service.UserInfoService;
import com.xibuguigu.yygh.vo.user.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(tags = "用户接口")
@RestController
@RequestMapping("/front/user/userInfo")
public class FrontUserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation("会员登录")
    @PostMapping("login")
    public R login(@RequestBody LoginVo loginVo) {

        Map<String, Object> info = userInfoService.login(loginVo);
        return R.ok().data(info);
    }

}