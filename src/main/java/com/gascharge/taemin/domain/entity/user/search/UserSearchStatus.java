package com.gascharge.taemin.domain.entity.user.search;

import com.gascharge.taemin.common.oauth.AuthProvider;
import com.gascharge.taemin.domain.enums.user.UserAuthority;
import com.gascharge.taemin.domain.enums.user.UserEmailVerified;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserSearchStatus {

    private String email;
    private UserEmailVerified emailVerified;
    private String name;
    private AuthProvider authProvider;
    private UserAuthority userAuthority;

    @Builder
    public UserSearchStatus(String email, UserEmailVerified emailVerified, String name, AuthProvider authProvider, UserAuthority userAuthority) {
        this.email = email;
        this.emailVerified = emailVerified;
        this.name = name;
        this.authProvider = authProvider;
        this.userAuthority = userAuthority;
    }
}