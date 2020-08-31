package com.flbu920.blog.service.Impl;

import com.flbu920.blog.Dao.AdminUserMapper;
import com.flbu920.blog.model.AdminUser;
import com.flbu920.blog.service.AdminUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUser login(String name, String password) {
        if (name == null || password == null) {
            return null;
        }
        return adminUserMapper.getAdminUserByNameAndPassword(name, password);
    }

    @Override
    public AdminUser getAdminUserById(Integer id) {
        if (id == null) {
            return null;
        }
        return adminUserMapper.getAdminUserById(id);
    }

    @Override
    public Boolean updateAdminUser(AdminUser adminUser) {
        if (adminUser == null) {
            return false;
        }
        return updateAdminUser(adminUser);
    }
}
