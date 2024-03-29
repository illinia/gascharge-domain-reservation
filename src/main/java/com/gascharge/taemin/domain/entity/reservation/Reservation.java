package com.gascharge.taemin.domain.entity.reservation;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gascharge.taemin.domain.entity.charge.Charge;
import com.gascharge.taemin.domain.entity.user.User;
import com.gascharge.taemin.domain.enums.reservation.ReservationStatus;
import com.gascharge.taemin.jpa.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@NoArgsConstructor
@ToString(exclude = {"user", "charge"})
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"RESERVATION_ID", "reservationValidationId"})
})
@DynamicInsert
@Entity
public class Reservation extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVATION_ID")
    @JsonIgnore
    private Long id;

    @Column(updatable = false, unique = true)
    @NotNull
    private String reservationValidationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "CHARGE_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @NotNull
    private Charge charge;

    @NotNull
    private LocalDateTime reservationTime;

    @ColumnDefault(value = "'BEFORE_CHARGE'")
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @Builder
    public Reservation(User user, Charge charge, LocalDateTime reservationTime, String reservationValidationId) {
        this.reservationValidationId = Objects.isNull(reservationValidationId) ? UUID.randomUUID().toString() : reservationValidationId;
        this.user = user;
        this.charge = charge;
        this.reservationTime = reservationTime;
        this.status = ReservationStatus.BEFORE_CHARGE;
    }

    public void removeUser() {
        if (!Objects.isNull(this.user)) {
            this.user.getReservations().remove(this);
        }
        this.user = null;
    }

    public void changeUser(User user) {
        if (!Objects.isNull(this.user)) {
            this.user.getReservations().remove(this);
        }
        this.user = user;
        user.getReservations().add(this);
    }

    public void removeCharge() {
        if (!Objects.isNull(this.charge)) {
            this.charge.getReservations().remove(this);
        }
        this.charge = null;
    }

    public void changeCharge(Charge charge) {
        if (!Objects.isNull(this.charge)) {
            this.charge.getReservations().remove(this);
        }
        this.charge = charge;
        charge.getReservations().add(this);
    }

    public ReservationStatus updateStatus(ReservationStatus status) {
        this.status = status;
        return this.status;
    }

    public LocalDateTime updateTime(LocalDateTime time) {
        this.reservationTime = time;
        return this.reservationTime;
    }
}
