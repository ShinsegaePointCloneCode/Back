package com.example.smilekarina.coupon.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCouponPartner is a Querydsl query type for CouponPartner
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCouponPartner extends EntityPathBase<CouponPartner> {

    private static final long serialVersionUID = -396390295L;

    public static final QCouponPartner couponPartner = new QCouponPartner("couponPartner");

    public final StringPath couponMerchandise = createString("couponMerchandise");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final StringPath imageUrlCircle = createString("imageUrlCircle");

    public final StringPath partnerName = createString("partnerName");

    public QCouponPartner(String variable) {
        super(CouponPartner.class, forVariable(variable));
    }

    public QCouponPartner(Path<? extends CouponPartner> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCouponPartner(PathMetadata metadata) {
        super(CouponPartner.class, metadata);
    }

}

