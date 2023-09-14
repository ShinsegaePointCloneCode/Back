package com.example.smilekarina.franchise.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMyStore is a Querydsl query type for MyStore
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMyStore extends EntityPathBase<MyStore> {

    private static final long serialVersionUID = -1379466061L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMyStore myStore = new QMyStore("myStore");

    public final QBranch branch;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.example.smilekarina.user.domain.QUser user;

    public QMyStore(String variable) {
        this(MyStore.class, forVariable(variable), INITS);
    }

    public QMyStore(Path<? extends MyStore> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMyStore(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMyStore(PathMetadata metadata, PathInits inits) {
        this(MyStore.class, metadata, inits);
    }

    public QMyStore(Class<? extends MyStore> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.branch = inits.isInitialized("branch") ? new QBranch(forProperty("branch"), inits.get("branch")) : null;
        this.user = inits.isInitialized("user") ? new com.example.smilekarina.user.domain.QUser(forProperty("user")) : null;
    }

}

