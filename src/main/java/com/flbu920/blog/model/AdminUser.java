package com.flbu920.blog.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminUser {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer adminUserId;
    @NotNull
    private String loginUserName;
    @NotNull
    private String loginPassword;

    private String NickName;

}
