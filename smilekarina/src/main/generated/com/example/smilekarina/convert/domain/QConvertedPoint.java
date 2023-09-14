package com.example.smilekarina.convert.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QConvertedPoint is a Querydsl query type for ConvertedPoint
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QConvertedPoint extends EntityPathBase<ConvertedPoint> {

    private static final long serialVersionUID = 1269069590L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QConvertedPoint convertedPoint = new QConvertedPoint("convertedPoint");

    public final QConvertedPartner convertedPartner;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> pointId = createNumber("pointId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QConvertedPoint(String variable) {
        this(ConvertedPoint.class, forVariable(variable), INITS);
    }

    public QConvertedPoint(Path<? extends ConvertedPoint> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QConvertedPoint(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QConvertedPoint(PathMetadata metadata, PathInits inits) {
        this(ConvertedPoint.class, metadata, inits);
    }

    public QConvertedPoint(Class<? extends ConvertedPoint> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.convertedPartner = inits.isInitialized("convertedPartner") ? new QConvertedPartner(forProperty("convertedPartner")) : null;
    }

}

