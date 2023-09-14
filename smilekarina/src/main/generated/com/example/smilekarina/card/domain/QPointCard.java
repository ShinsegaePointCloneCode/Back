package com.example.smilekarina.card.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPointCard is a Querydsl query type for PointCard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPointCard extends EntityPathBase<PointCard> {

    private static final long serialVersionUID = 55605117L;

    public static final QPointCard pointCard = new QPointCard("pointCard");

    public final com.example.smilekarina.global.domain.QBaseEntity _super = new com.example.smilekarina.global.domain.QBaseEntity(this);

    public final StringPath cardNumber = createString("cardNumber");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath issuePlace = createString("issuePlace");

    public final EnumPath<IssueType> issueType = createEnum("issueType", IssueType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QPointCard(String variable) {
        super(PointCard.class, forVariable(variable));
    }

    public QPointCard(Path<? extends PointCard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPointCard(PathMetadata metadata) {
        super(PointCard.class, metadata);
    }

}

