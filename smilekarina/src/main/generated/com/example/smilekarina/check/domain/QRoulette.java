package com.example.smilekarina.check.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRoulette is a Querydsl query type for Roulette
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRoulette extends EntityPathBase<Roulette> {

    private static final long serialVersionUID = -1688141695L;

    public static final QRoulette roulette = new QRoulette("roulette");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.time.LocalDate> rouletteDate = createDate("rouletteDate", java.time.LocalDate.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QRoulette(String variable) {
        super(Roulette.class, forVariable(variable));
    }

    public QRoulette(Path<? extends Roulette> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRoulette(PathMetadata metadata) {
        super(Roulette.class, metadata);
    }

}

