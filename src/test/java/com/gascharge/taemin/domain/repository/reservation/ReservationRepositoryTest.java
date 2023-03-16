package com.gascharge.taemin.domain.repository.reservation;

import com.gascharge.taemin.domain.entity.charge.Charge;
import com.gascharge.taemin.domain.entity.charge.ChargeTestData;
import com.gascharge.taemin.domain.entity.reservation.Reservation;
import com.gascharge.taemin.domain.entity.reservation.search.ReservationSearchStatus;
import com.gascharge.taemin.domain.entity.user.User;
import com.gascharge.taemin.domain.entity.user.UserTestData;
import com.gascharge.taemin.domain.repository.charge.ChargeRepository;
import com.gascharge.taemin.domain.repository.reservation.ReservationRepository;
import com.gascharge.taemin.domain.repository.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ReservationRepositoryTest {

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ChargeRepository chargeRepository;

    @Test
    void findReservationWithSearchStatus() {
        // given
        User cloneUser = userRepository.save(UserTestData.getCloneUser());
        User cloneAdmin = userRepository.save(UserTestData.getCloneAdmin());
        Charge testCharge = chargeRepository.save(ChargeTestData.getTestCharge());
        Charge testCharge1 = chargeRepository.save(ChargeTestData.getTestCharge1());

        Reservation reservation = reservationRepository.save(Reservation.builder()
                .reservationValidationId(UUID.randomUUID().toString())
                .user(cloneUser)
                .charge(testCharge)
                .reservationTime(LocalDateTime.now().plusHours(1))
                .build());

        Reservation reservation1 = reservationRepository.save(Reservation.builder()
                .reservationValidationId(UUID.randomUUID().toString())
                .user(cloneAdmin)
                .charge(testCharge1)
                .reservationTime(LocalDateTime.now().plusDays(1))
                .build());

        ReservationSearchStatus reservationSearchStatus = new ReservationSearchStatus();
        int page = 0;
        int size = 10;
        Sort sort = Sort.by("reservationTime").descending();
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        // when
        Page<Reservation> result = reservationRepository.findReservationWithSearchStatus(reservationSearchStatus, pageRequest);

        // then
        assertThat(result.getPageable().getPageNumber()).isEqualTo(page);
        assertThat(result.getPageable().getPageSize()).isEqualTo(size);
        assertThat(result.getPageable().getSort()).isEqualTo(sort);
        assertThat(result.getContent()).hasSize(2);
        assertThat(result.getContent().get(0).toString()).isEqualTo(reservation1.toString());
        assertThat(result.getContent().get(1).toString()).isEqualTo(reservation.toString());

        // given
        reservationSearchStatus.setEmail(reservation.getUser().getEmail());

        // when
        Page<Reservation> result1 = reservationRepository.findReservationWithSearchStatus(reservationSearchStatus, pageRequest);

        // then
        assertThat(result1.getContent()).hasSize(1);
        assertThat(result1.getContent().get(0).toString()).isEqualTo(reservation.toString());

        // given
        reservationSearchStatus.setEmail(null);
        reservationSearchStatus.setChargePlaceId(testCharge.getChargePlaceId());

        // when
        Page<Reservation> result2 = reservationRepository.findReservationWithSearchStatus(reservationSearchStatus, pageRequest);

        // then
        assertThat(result2.getContent()).hasSize(1);
        assertThat(result2.getContent().get(0).toString()).isEqualTo(reservation.toString());
    }
}