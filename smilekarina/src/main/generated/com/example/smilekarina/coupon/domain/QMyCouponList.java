package com.example.smilekarina.coupon.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMyCouponList is a Querydsl query type for MyCouponList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMyCouponList extends EntityPathBase<MyCouponList> {

    private static final long serialVersionUID = -102399703L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMyCouponList myCouponList = new QMyCouponList("myCouponList");

    public final QCoupon coupon;

    public final StringPath couponNumber = createString("couponNumber");

    public final DateTimePath<java.time.LocalDateTime> downloadDate = createDateTime("downloadDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final BooleanPath useStatus = createBoolean("useStatus");

    public QMyCouponList(String variable) {
        this(MyCouponList.class, forVariable(variable), INITS);
    }

    public QMyCouponList(Path<? extends MyCouponList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMyCouponList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMyCouponList(PathMetadata metadata, PathInits inits) {
        this(MyCouponList.class, metadata, inits);
    }

    public QMyCouponList(Class<? extends MyCouponList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.coupon = inits.isInitialized("coupon") ? new QCoupon(forProperty("coupon"), inits.get("coupon")) : null;
    }

}

