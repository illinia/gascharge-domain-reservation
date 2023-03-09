package com.gascharge.taemin.domain.entity.reservation.search;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReservationSearchStatus {

    private String email;
    private String chargePlaceId;
}