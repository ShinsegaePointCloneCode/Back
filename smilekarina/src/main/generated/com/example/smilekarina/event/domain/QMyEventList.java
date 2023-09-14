package com.example.smilekarina.event.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMyEventList is a Querydsl query type for MyEventList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMyEventList extends EntityPathBase<MyEventList> {

    private static final long serialVersionUID = 760037005L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMyEventList myEventList = new QMyEventList("myEventList");

    public final NumberPath<Long> eventId = createNumber("eventId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath prizeBool = createBoolean("prizeBool");

    public final com.example.smilekarina.user.domain.QUser user;

    public QMyEventList(String variable) {
        this(MyEventList.class, forVariable(variable), INITS);
    }

    public QMyEventList(Path<? extends MyEventList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMyEventList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMyEventList(PathMetadata metadata, PathInits inits) {
        this(MyEventList.class, metadata, inits);
    }

    public QMyEventList(Class<? extends MyEventList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.example.smilekarina.user.domain.QUser(forProperty("user")) : null;
    }

}

