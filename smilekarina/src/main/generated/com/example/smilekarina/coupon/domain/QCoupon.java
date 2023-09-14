package com.example.smilekarina.coupon.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCoupon is a Querydsl query type for Coupon
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCoupon extends EntityPathBase<Coupon> {

    private static final long serialVersionUID = -1815139489L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCoupon coupon = new QCoupon("coupon");

    public final NumberPath<Double> couponDiscount = createNumber("couponDiscount", Double.class);

    public final DateTimePath<java.time.LocalDateTime> couponEnd = createDateTime("couponEnd", java.time.LocalDateTime.class);

    public final StringPath couponImg = createString("couponImg");

    public final StringPath couponName = createString("couponName");

    public final QCouponPartner couponPartner;

    public final StringPath couponPrecaution = createString("couponPrecaution");

    public final DateTimePath<java.time.LocalDateTime> couponStart = createDateTime("couponStart", java.time.LocalDateTime.class);

    public final NumberPath<Integer> couponType = createNumber("couponType", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QCoupon(String variable) {
        this(Coupon.class, forVariable(variable), INITS);
    }

    public QCoupon(Path<? extends Coupon> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCoupon(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCoupon(PathMetadata metadata, PathInits inits) {
        this(Coupon.class, metadata, inits);
    }

    public QCoupon(Class<? extends Coupon> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.couponPartner = inits.isInitialized("couponPartner") ? new QCouponPartner(forProperty("couponPartner")) : null;
    }

}

