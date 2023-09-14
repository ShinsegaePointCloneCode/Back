package com.example.smilekarina.point.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QExtinctionPoint is a Querydsl query type for ExtinctionPoint
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExtinctionPoint extends EntityPathBase<ExtinctionPoint> {

    private static final long serialVersionUID = 1765044650L;

    public static final QExtinctionPoint extinctionPoint = new QExtinctionPoint("extinctionPoint");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> nextExtictionPoint = createNumber("nextExtictionPoint", Long.class);

    public final NumberPath<Long> thisExtinctionPoint = createNumber("thisExtinctionPoint", Long.class);

    public final DatePath<java.time.LocalDate> updateDate = createDate("updateDate", java.time.LocalDate.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QExtinctionPoint(String variable) {
        super(ExtinctionPoint.class, forVariable(variable));
    }

    public QExtinctionPoint(Path<? extends ExtinctionPoint> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExtinctionPoint(PathMetadata metadata) {
        super(ExtinctionPoint.class, metadata);
    }

}

