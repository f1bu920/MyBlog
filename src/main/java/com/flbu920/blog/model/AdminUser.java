package com.flbu920.blog.Model;

import lombok.*;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AdminUser {
    @Getter
    @Setter
    private Integer adminUserId;
    @Getter
    @Setter
    private String loginUserName;
    @Setter
    @Getter
    private String loginPassword;
    @Getter
    @Setter
    private String NickName;

}
