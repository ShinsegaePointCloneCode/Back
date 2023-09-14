package com.example.smilekarina.agree.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAgreeAdvertise is a Querydsl query type for AgreeAdvertise
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAgreeAdvertise extends EntityPathBase<AgreeAdvertise> {

    private static final long serialVersionUID = -2068205236L;

    public static final QAgreeAdvertise agreeAdvertise = new QAgreeAdvertise("agreeAdvertise");

    public final BooleanPath agreeEmail = createBoolean("agreeEmail");

    public final BooleanPath DM = createBoolean("DM");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath letter = createBoolean("letter");

    public final BooleanPath optionOne = createBoolean("optionOne");

    public final BooleanPath optionTwo = createBoolean("optionTwo");

    public final BooleanPath TM = createBoolean("TM");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QAgreeAdvertise(String variable) {
        super(AgreeAdvertise.class, forVariable(variable));
    }

    public QAgreeAdvertise(Path<? extends AgreeAdvertise> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAgreeAdvertise(PathMetadata metadata) {
        super(AgreeAdvertise.class, metadata);
    }

}

