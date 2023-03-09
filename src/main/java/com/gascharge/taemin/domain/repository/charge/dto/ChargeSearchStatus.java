package com.gascharge.taemin.domain.repository.charge.dto;

import com.gascharge.taemin.domain.enums.charge.ChargePlaceMembership;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChargeSearchStatus {

    private String name;

    private ChargePlaceMembership chargePlaceMembership;

}
