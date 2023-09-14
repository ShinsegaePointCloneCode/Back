package com.example.smilekarina.card.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMileageCard is a Querydsl query type for MileageCard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMileageCard extends EntityPathBase<MileageCard> {

    private static final long serialVersionUID = 1594333815L;

    public static final QMileageCard mileageCard = new QMileageCard("mileageCard");

    public final com.example.smilekarina.global.domain.QBaseEntity _super = new com.example.smilekarina.global.domain.QBaseEntity(this);

    public final StringPath cardNumber = createString("cardNumber");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath issuePlace = createString("issuePlace");

    public final StringPath lastName = createString("lastName");

    public final BooleanPath mainSelect = createBoolean("mainSelect");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QMileageCard(String variable) {
        super(MileageCard.class, forVariable(variable));
    }

    public QMileageCard(Path<? extends MileageCard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMileageCard(PathMetadata metadata) {
        super(MileageCard.class, metadata);
    }

}

