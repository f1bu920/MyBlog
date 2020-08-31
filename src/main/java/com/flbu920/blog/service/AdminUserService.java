package com.flbu920.blog.service;

import com.flbu920.blog.model.AdminUser;

/**
 *
 */
public interface AdminUserService {
    AdminUser login(String name,String password);

    AdminUser getAdminUserById(Integer id);

    Boolean updateAdminUser(AdminUser adminUser);
}
