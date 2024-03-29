package com.gascharge.taemin.domain.repository.charge;

import com.gascharge.taemin.domain.entity.charge.Charge;
import com.gascharge.taemin.domain.enums.charge.ChargePlaceMembership;
import com.gascharge.taemin.domain.repository.charge.dto.ChargeSearchStatus;
import com.querydsl.core.QueryResults;
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
import java.util.Objects;
import java.util.Optional;

import static com.gascharge.taemin.common.util.DtoFieldNotNullChecker.checkAnyFieldNotNull;
import static com.gascharge.taemin.domain.entity.charge.QCharge.charge;
import static com.gascharge.taemin.jpa.util.querydsl.QueryDslUtil.getOrderSpecifiers;

@RequiredArgsConstructor
public class ChargeRepositoryImpl implements ChargeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Charge> findChargeWithSearchStatus(ChargeSearchStatus chargeSearchStatus, Pageable pageable) {
        Optional any = checkAnyFieldNotNull(chargeSearchStatus);

        if (any.isEmpty()) return new PageImpl<>(new ArrayList<>(), Pageable.unpaged(), 0);

        List<OrderSpecifier> orderSpecifiers = getOrderSpecifiers(pageable, Charge.class, "charge");

        QueryResults<Charge> results = queryFactory
                .selectFrom(charge)
                .where(
                        chargeNameContains(chargeSearchStatus.getName()),
                        chargeIsMembershipEq(chargeSearchStatus.getChargePlaceMembership())
                )
                .orderBy(orderSpecifiers.stream().toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();


        List<Charge> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression chargeNameContains(String name) {
        return StringUtils.isNullOrEmpty(name) ? null : charge.name.contains(name);
    }

    private BooleanExpression chargeIsMembershipEq(ChargePlaceMembership chargePlaceMembership) {
        return Objects.isNull(chargePlaceMembership) ? null : charge.membership.eq(chargePlaceMembership);
    }

}
