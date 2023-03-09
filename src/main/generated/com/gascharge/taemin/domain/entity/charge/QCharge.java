package com.gascharge.taemin.domain.entity.charge;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCharge is a Querydsl query type for Charge
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCharge extends EntityPathBase<Charge> {

    private static final long serialVersionUID = 1328248135L;

    public static final QCharge charge = new QCharge("charge");

    public final com.gascharge.taemin.entity.QBaseTimeEntity _super = new com.gascharge.taemin.entity.QBaseTimeEntity(this);

    public final StringPath chargePlaceId = createString("chargePlaceId");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> currentCount = createNumber("currentCount", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.gascharge.taemin.domain.enums.charge.ChargePlaceMembership> membership = createEnum("membership", com.gascharge.taemin.domain.enums.charge.ChargePlaceMembership.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath name = createString("name");

    public final ListPath<com.gascharge.taemin.domain.entity.reservation.Reservation, com.gascharge.taemin.domain.entity.reservation.QReservation> reservations = this.<com.gascharge.taemin.domain.entity.reservation.Reservation, com.gascharge.taemin.domain.entity.reservation.QReservation>createList("reservations", com.gascharge.taemin.domain.entity.reservation.Reservation.class, com.gascharge.taemin.domain.entity.reservation.QReservation.class, PathInits.DIRECT2);

    public final NumberPath<Long> totalCount = createNumber("totalCount", Long.class);

    public QCharge(String variable) {
        super(Charge.class, forVariable(variable));
    }

    public QCharge(Path<? extends Charge> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCharge(PathMetadata metadata) {
        super(Charge.class, metadata);
    }

}

