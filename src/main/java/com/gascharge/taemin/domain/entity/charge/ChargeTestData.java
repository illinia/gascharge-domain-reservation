package com.gascharge.taemin.domain.entity.charge;

import com.gascharge.taemin.domain.enums.charge.ChargePlaceMembership;

public class ChargeTestData {
    public static final String CHARGE_TEST_ID = "testId";
    public static final String CHARGE_TEST_NAME = "test";
    public static final Long CHARGE_TEST_TOTAL_COUNT = 5L;
    public static final Long CHARGE_TEST_CURRENT_COUNT = 2L;
    public static final ChargePlaceMembership CHARGE_TEST_MEMBERSHIP = ChargePlaceMembership.MEMBERSHIP;

    private static final Charge CHARGE_TEST = Charge.builder()
            .chargePlaceId(CHARGE_TEST_ID)
            .name(CHARGE_TEST_NAME)
            .totalCount(CHARGE_TEST_TOTAL_COUNT)
            .currentCount(CHARGE_TEST_CURRENT_COUNT)
            .membership(CHARGE_TEST_MEMBERSHIP)
            .build();
    public static Charge getTestCharge() {
        return CHARGE_TEST.clone();
    }
    public static final String CHARGE_TEST_ID1 = "testId1";
    public static final String CHARGE_TEST_NAME1 = "test1";
    public static final Long CHARGE_TEST_TOTAL_COUNT1 = 10L;
    public static final Long CHARGE_TEST_CURRENT_COUNT1 = 4L;
    public static final ChargePlaceMembership CHARGE_TEST_MEMBERSHIP1 = ChargePlaceMembership.NOT_MEMBERSHIP;

    private static final Charge CHARGE_TEST1 = Charge.builder()
            .chargePlaceId(CHARGE_TEST_ID1)
            .name(CHARGE_TEST_NAME1)
            .totalCount(CHARGE_TEST_TOTAL_COUNT1)
            .currentCount(CHARGE_TEST_CURRENT_COUNT1)
            .membership(CHARGE_TEST_MEMBERSHIP1)
            .build();

    public static Charge getTestCharge1() {
        return CHARGE_TEST1.clone();
    }
}
