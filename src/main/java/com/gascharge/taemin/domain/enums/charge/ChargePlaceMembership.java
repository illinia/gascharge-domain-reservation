package com.gascharge.taemin.domain.enums.charge;

import com.gascharge.taemin.common.exception.enums.NotFoundMatchedEnum;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ChargePlaceMembership {
    MEMBERSHIP("is-membership"),
    NOT_MEMBERSHIP("not-membership");

    private String membershipString;

    ChargePlaceMembership(String membershipString) {
        this.membershipString = membershipString;
    }

    public static ChargePlaceMembership getChargePlaceMembership(String name) {
        return Arrays.stream(ChargePlaceMembership.values())
                .filter(m -> m.membershipString.equals(name))
                .findAny()
                .orElseThrow(() -> new NotFoundMatchedEnum(ChargePlaceMembership.class, name));
    }
}
