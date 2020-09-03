package com.flbu920.blog.controller.admin;

import com.flbu920.blog.model.AdminUser;
import com.flbu920.blog.service.AdminUserService;
import com.flbu920.blog.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
@RequestMapping("/admin")
public class adminLoginController {
    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private TokenUtil tokenUtil;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login2(@RequestParam("username") String loginName,
                         @RequestParam("password") String loginPassword, HttpServletResponse response) {
        log.info("开始登陆");
        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(loginPassword)) {
            return "/admin/login";
        }
        AdminUser login = adminUserService.login(loginName, loginPassword);
        if (login == null) {
            log.info("没有此用户");
            return "login";
        }
        log.info("登陆成功");
        response.addHeader("token", tokenUtil.sign(login.getAdminUserId(), System.currentTimeMillis()));
        return "redirect:/admin/index";
    }
}
