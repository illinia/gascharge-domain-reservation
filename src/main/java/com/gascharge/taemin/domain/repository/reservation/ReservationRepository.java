package com.gascharge.taemin.domain.repository.reservation;

import com.gascharge.taemin.domain.entity.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long>, ReservationRepositoryCustom {
    @Query("select r from Reservation r where r.reservationValidationId = :reservationValidationId")
    Optional<Reservation> findByReservationValidationId(@Param("reservationValidationId") String id);
}
