package com.gascharge.taemin.domain.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -694235481L;

    public static final QUser user = new QUser("user");

    public final StringPath email = createString("email");

    public final EnumPath<com.gascharge.taemin.domain.enums.user.UserEmailVerified> emailVerified = createEnum("emailVerified", com.gascharge.taemin.domain.enums.user.UserEmailVerified.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final EnumPath<com.gascharge.taemin.common.oauth.AuthProvider> provider = createEnum("provider", com.gascharge.taemin.common.oauth.AuthProvider.class);

    public final StringPath providerId = createString("providerId");

    public final ListPath<com.gascharge.taemin.domain.entity.reservation.Reservation, com.gascharge.taemin.domain.entity.reservation.QReservation> reservations = this.<com.gascharge.taemin.domain.entity.reservation.Reservation, com.gascharge.taemin.domain.entity.reservation.QReservation>createList("reservations", com.gascharge.taemin.domain.entity.reservation.Reservation.class, com.gascharge.taemin.domain.entity.reservation.QReservation.class, PathInits.DIRECT2);

    public final EnumPath<com.gascharge.taemin.domain.enums.user.UserAuthority> userAuthority = createEnum("userAuthority", com.gascharge.taemin.domain.enums.user.UserAuthority.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

