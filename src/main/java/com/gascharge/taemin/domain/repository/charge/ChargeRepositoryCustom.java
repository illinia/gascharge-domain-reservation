package com.gascharge.taemin.domain.repository.charge;

import com.gascharge.taemin.domain.entity.charge.Charge;
import com.gascharge.taemin.domain.repository.charge.dto.ChargeSearchStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChargeRepositoryCustom {
    Page<Charge> findChargeWithSearchStatus(ChargeSearchStatus chargeSearchStatus, Pageable pageable);
}
