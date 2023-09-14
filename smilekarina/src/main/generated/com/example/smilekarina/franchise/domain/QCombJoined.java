package com.example.smilekarina.franchise.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCombJoined is a Querydsl query type for CombJoined
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCombJoined extends EntityPathBase<CombJoined> {

    private static final long serialVersionUID = -355114292L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCombJoined combJoined = new QCombJoined("combJoined");

    public final QFranchise franchise;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.example.smilekarina.user.domain.QUser user;

    public QCombJoined(String variable) {
        this(CombJoined.class, forVariable(variable), INITS);
    }

    public QCombJoined(Path<? extends CombJoined> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCombJoined(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCombJoined(PathMetadata metadata, PathInits inits) {
        this(CombJoined.class, metadata, inits);
    }

    public QCombJoined(Class<? extends CombJoined> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.franchise = inits.isInitialized("franchise") ? new QFranchise(forProperty("franchise")) : null;
        this.user = inits.isInitialized("user") ? new com.example.smilekarina.user.domain.QUser(forProperty("user")) : null;
    }

}

