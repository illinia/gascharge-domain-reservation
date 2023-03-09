package com.gascharge.taemin.domain.repository.user;

import com.gascharge.taemin.common.oauth.AuthProvider;
import com.gascharge.taemin.domain.entity.user.User;
import com.gascharge.taemin.domain.entity.user.search.UserSearchStatus;
import com.gascharge.taemin.domain.enums.user.UserAuthority;
import com.gascharge.taemin.domain.enums.user.UserEmailVerified;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;

import static com.gascharge.taemin.domain.entity.charge.QCharge.charge;
import static com.gascharge.taemin.domain.entity.reservation.QReservation.reservation;
import static com.gascharge.taemin.domain.entity.user.QUser.user;
import static com.gascharge.taemin.util.querydsl.QueryDslUtil.getOrderSpecifiers;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<User> findUserWithSearchStatus(UserSearchStatus userSearchStatus, Pageable pageable) {
        List<OrderSpecifier> orderSpecifiers = getOrderSpecifiers(pageable, User.class, "user");

        List<User> fetch = queryFactory
                .selectFrom(user)
                .where(
                        userEmailContains(userSearchStatus.getEmail()),
                        userEmailVerifiedEq(userSearchStatus.getEmailVerified()),
                        userNameContains(userSearchStatus.getName()),
                        userAuthProviderEq(userSearchStatus.getAuthProvider()),
                        userAuthorityEq(userSearchStatus.getUserAuthority())
                )
                .leftJoin(user.reservations, reservation)
                .fetchJoin()
                .leftJoin(reservation.charge, charge)
                .fetchJoin()
                .orderBy(orderSpecifiers.stream().toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .distinct()
                .fetch();

        int size = fetch.size();

        return new PageImpl<>(fetch, pageable, size);
    }

    private BooleanExpression userEmailContains(String email) {
        return StringUtils.isNullOrEmpty(email) ? null : user.email.contains(email);
    }

    private BooleanExpression userEmailVerifiedEq(UserEmailVerified userEmailVerified) {
        return Objects.isNull(userEmailVerified) ? null : user.emailVerified.eq(userEmailVerified);
    }

    private BooleanExpression userNameContains(String name) {
        return StringUtils.isNullOrEmpty(name) ? null : user.name.contains(name);
    }

    private BooleanExpression userAuthProviderEq(AuthProvider authProvider) {
        return Objects.isNull(authProvider) ? null : user.provider.eq(authProvider);
    }

    private BooleanExpression userAuthorityEq(UserAuthority userAuthority) {
        return Objects.isNull(userAuthority) ? null : user.userAuthority.eq(userAuthority);
    }
}