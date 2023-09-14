package com.example.smilekarina.check.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCheckPoint is a Querydsl query type for CheckPoint
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCheckPoint extends EntityPathBase<CheckPoint> {

    private static final long serialVersionUID = -1760889931L;

    public static final QCheckPoint checkPoint = new QCheckPoint("checkPoint");

    public final DatePath<java.time.LocalDate> checkDate = createDate("checkDate", java.time.LocalDate.class);

    public final NumberPath<Integer> cntDate = createNumber("cntDate", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QCheckPoint(String variable) {
        super(CheckPoint.class, forVariable(variable));
    }

    public QCheckPoint(Path<? extends CheckPoint> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCheckPoint(PathMetadata metadata) {
        super(CheckPoint.class, metadata);
    }

}

