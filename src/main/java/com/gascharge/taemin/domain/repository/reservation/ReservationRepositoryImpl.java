package com.gascharge.taemin.domain.repository.reservation;

import com.gascharge.taemin.domain.entity.charge.QCharge;
import com.gascharge.taemin.domain.entity.reservation.Reservation;
import com.gascharge.taemin.domain.entity.reservation.search.ReservationSearchStatus;
import com.gascharge.taemin.domain.entity.user.QUser;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.gascharge.taemin.common.util.DtoFieldNotNullChecker.checkAnyFieldNotNull;
import static com.gascharge.taemin.domain.entity.charge.QCharge.charge;
import static com.gascharge.taemin.domain.entity.reservation.QReservation.reservation;
import static com.gascharge.taemin.domain.entity.user.QUser.user;
import static com.gascharge.taemin.jpa.util.querydsl.QueryDslUtil.getOrderSpecifiers;

@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Reservation> findReservationWithSearchStatus(ReservationSearchStatus reservationSearchStatus, Pageable pageable) {
        Optional any = checkAnyFieldNotNull(reservationSearchStatus);

        if (any.isEmpty()) return new PageImpl<>(new ArrayList<>(), Pageable.unpaged(), 0);

        List<OrderSpecifier> orderSpecifiers = getOrderSpecifiers(pageable, Reservation.class, "reservation");

        List<Reservation> fetch = queryFactory
                .selectFrom(reservation)
                .where(
                        reservationEmailEq(reservationSearchStatus.getEmail()),
                        reservationChargePlaceIdEq(reservationSearchStatus.getChargePlaceId())
                )
                .leftJoin(reservation.user, user)
                .fetchJoin()
                .leftJoin(reservation.charge, charge)
                .fetchJoin()
                .orderBy(orderSpecifiers.stream().toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        int size = fetch.size();

        return new PageImpl<>(fetch, pageable, size);
    }

    private BooleanExpression reservationEmailEq(String email) {
        return StringUtils.isNullOrEmpty(email) ? null : reservation.user.email.eq(email);
    }

    private BooleanExpression reservationChargePlaceIdEq(String id) {
        return StringUtils.isNullOrEmpty(id) ? null : reservation.charge.chargePlaceId.eq(id);
    }
}