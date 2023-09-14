package com.example.smilekarina.gift.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGift is a Querydsl query type for Gift
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGift extends EntityPathBase<Gift> {

    private static final long serialVersionUID = 1187654259L;

    public static final QGift gift = new QGift("gift");

    public final com.example.smilekarina.global.domain.QBaseEntity _super = new com.example.smilekarina.global.domain.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath giftImage = createString("giftImage");

    public final StringPath giftMessage = createString("giftMessage");

    public final NumberPath<Long> giftRecipientId = createNumber("giftRecipientId", Long.class);

    public final NumberPath<Long> giftSenderId = createNumber("giftSenderId", Long.class);

    public final EnumPath<GiftType> giftType = createEnum("giftType", GiftType.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final NumberPath<Long> resultPointId = createNumber("resultPointId", Long.class);

    public final NumberPath<Long> senderPointId = createNumber("senderPointId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QGift(String variable) {
        super(Gift.class, forVariable(variable));
    }

    public QGift(Path<? extends Gift> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGift(PathMetadata metadata) {
        super(Gift.class, metadata);
    }

}

