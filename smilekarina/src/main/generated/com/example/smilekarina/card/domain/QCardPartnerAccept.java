package com.example.smilekarina.card.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCardPartnerAccept is a Querydsl query type for CardPartnerAccept
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCardPartnerAccept extends EntityPathBase<CardPartnerAccept> {

    private static final long serialVersionUID = -1048353667L;

    public static final QCardPartnerAccept cardPartnerAccept = new QCardPartnerAccept("cardPartnerAccept");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> pointId = createNumber("pointId", Long.class);

    public QCardPartnerAccept(String variable) {
        super(CardPartnerAccept.class, forVariable(variable));
    }

    public QCardPartnerAccept(Path<? extends CardPartnerAccept> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCardPartnerAccept(PathMetadata metadata) {
        super(CardPartnerAccept.class, metadata);
    }

}

