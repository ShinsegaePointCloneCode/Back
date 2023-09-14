package com.example.smilekarina.point.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMonthPoint is a Querydsl query type for MonthPoint
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMonthPoint extends EntityPathBase<MonthPoint> {

    private static final long serialVersionUID = 677429189L;

    public static final QMonthPoint monthPoint1 = new QMonthPoint("monthPoint1");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> monthPoint = createNumber("monthPoint", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final DatePath<java.time.LocalDate> yearMonthDate = createDate("yearMonthDate", java.time.LocalDate.class);

    public QMonthPoint(String variable) {
        super(MonthPoint.class, forVariable(variable));
    }

    public QMonthPoint(Path<? extends MonthPoint> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMonthPoint(PathMetadata metadata) {
        super(MonthPoint.class, metadata);
    }

}

