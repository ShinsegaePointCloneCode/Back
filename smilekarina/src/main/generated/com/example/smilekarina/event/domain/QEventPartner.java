package com.example.smilekarina.event.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEventPartner is a Querydsl query type for EventPartner
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEventPartner extends EntityPathBase<EventPartner> {

    private static final long serialVersionUID = 739311725L;

    public static final QEventPartner eventPartner = new QEventPartner("eventPartner");

    public final StringPath eventPartnerName = createString("eventPartnerName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QEventPartner(String variable) {
        super(EventPartner.class, forVariable(variable));
    }

    public QEventPartner(Path<? extends EventPartner> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEventPartner(PathMetadata metadata) {
        super(EventPartner.class, metadata);
    }

}

