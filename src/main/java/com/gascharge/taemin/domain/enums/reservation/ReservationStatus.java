package com.gascharge.taemin.domain.enums.reservation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.gascharge.taemin.common.exception.enums.NotFoundMatchedEnum;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ReservationStatus {
    BEFORE_CHARGE, FINISH_CHARGE, CANCEL, NO_SHOW
    ;

    @JsonCreator
    public static ReservationStatus from(String code) {
        return Arrays.stream(ReservationStatus.values())
                .filter(s -> s.name().equals(code.toUpperCase().replace("-", "_")))
                .findAny()
                .orElseThrow(() -> new NotFoundMatchedEnum(ReservationStatus.class, code));
    }
}