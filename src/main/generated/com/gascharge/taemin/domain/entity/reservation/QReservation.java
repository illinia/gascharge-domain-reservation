package com.gascharge.taemin.domain.entity.reservation;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReservation is a Querydsl query type for Reservation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservation extends EntityPathBase<Reservation> {

    private static final long serialVersionUID = -107559101L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReservation reservation = new QReservation("reservation");

    public final com.gascharge.taemin.entity.QBaseTimeEntity _super = new com.gascharge.taemin.entity.QBaseTimeEntity(this);

    public final com.gascharge.taemin.domain.entity.charge.QCharge charge;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final DateTimePath<java.time.LocalDateTime> reservationTime = createDateTime("reservationTime", java.time.LocalDateTime.class);

    public final StringPath reservationValidationId = createString("reservationValidationId");

    public final EnumPath<com.gascharge.taemin.domain.enums.reservation.ReservationStatus> status = createEnum("status", com.gascharge.taemin.domain.enums.reservation.ReservationStatus.class);

    public final com.gascharge.taemin.domain.entity.user.QUser user;

    public QReservation(String variable) {
        this(Reservation.class, forVariable(variable), INITS);
    }

    public QReservation(Path<? extends Reservation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReservation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReservation(PathMetadata metadata, PathInits inits) {
        this(Reservation.class, metadata, inits);
    }

    public QReservation(Class<? extends Reservation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.charge = inits.isInitialized("charge") ? new com.gascharge.taemin.domain.entity.charge.QCharge(forProperty("charge")) : null;
        this.user = inits.isInitialized("user") ? new com.gascharge.taemin.domain.entity.user.QUser(forProperty("user")) : null;
    }

}

