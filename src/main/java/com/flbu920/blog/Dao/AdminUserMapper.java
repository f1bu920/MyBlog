package com.flbu920.blog.Dao;

import com.flbu920.blog.model.AdminUser;

public interface adminUserDAO {
    AdminUser getAdminUserById(Integer id);

    AdminUser getAdminUserByNameAndPassword(String loginName, String loginPassword);

    Boolean updateAdminUser(AdminUser adminUser);
}
