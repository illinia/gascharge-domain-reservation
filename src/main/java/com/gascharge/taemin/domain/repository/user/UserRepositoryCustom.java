package com.gascharge.taemin.domain.repository.user;

import com.gascharge.taemin.domain.entity.user.User;
import com.gascharge.taemin.domain.entity.user.search.UserSearchStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepositoryCustom {
    Page<User> findUserWithSearchStatus(UserSearchStatus userSearchStatus, Pageable pageable);
}
