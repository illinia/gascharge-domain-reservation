package com.gascharge.taemin.domain.repository.reservation;

import com.gascharge.taemin.domain.entity.reservation.Reservation;
import com.gascharge.taemin.domain.entity.reservation.search.ReservationSearchStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReservationRepositoryCustom {
    Page<Reservation> findReservationWithSearchStatus(ReservationSearchStatus reservationSearchStatus, Pageable pageable);
}
