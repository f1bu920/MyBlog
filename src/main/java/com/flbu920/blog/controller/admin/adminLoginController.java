package com.flbu920.blog.controller.admin;

import cn.hutool.core.exceptions.StatefulException;
import com.flbu920.blog.model.AdminUser;
import com.flbu920.blog.service.AdminUserService;
import com.flbu920.blog.util.BlogResult;
import com.flbu920.blog.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
@RequestMapping("/admin")
@Api("管理员登录相关")
public class adminLoginController {
    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private TokenUtil tokenUtil;

//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }

    @PostMapping("/login")
    @ApiOperation(value = "登录",notes = "根据username和password进行登录")
    public BlogResult login2(@RequestParam("username") String loginName,
                             @RequestParam("password") String loginPassword, HttpServletResponse response) {
        log.info("开始登陆");
        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(loginPassword)) {
            return BlogResult.failure("用户名和密码不能为空");
        }
        AdminUser login = adminUserService.login(loginName, loginPassword);
        if (login == null) {
            log.info("没有此用户");
            throw new StatefulException(404,"找不到此用户");
        }
        log.info("登陆成功");
        response.addHeader("token", tokenUtil.sign(login.getAdminUserId(), System.currentTimeMillis()));
        return BlogResult.success();
    }
}
