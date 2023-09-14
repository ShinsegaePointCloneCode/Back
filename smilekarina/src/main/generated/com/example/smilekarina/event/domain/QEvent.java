package com.example.smilekarina.event.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEvent is a Querydsl query type for Event
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEvent extends EntityPathBase<Event> {

    private static final long serialVersionUID = 1857157083L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEvent event = new QEvent("event");

    public final com.example.smilekarina.global.domain.QBaseEntity _super = new com.example.smilekarina.global.domain.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath eventBenefit = createString("eventBenefit");

    public final DateTimePath<java.time.LocalDateTime> eventEnd = createDateTime("eventEnd", java.time.LocalDateTime.class);

    public final StringPath eventHead = createString("eventHead");

    public final QEventPartner eventPartner;

    public final DateTimePath<java.time.LocalDateTime> eventResultDate = createDateTime("eventResultDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> eventStart = createDateTime("eventStart", java.time.LocalDateTime.class);

    public final StringPath eventThumbnail = createString("eventThumbnail");

    public final EnumPath<EventType> eventType = createEnum("eventType", EventType.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath linkedUrl = createString("linkedUrl");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QEvent(String variable) {
        this(Event.class, forVariable(variable), INITS);
    }

    public QEvent(Path<? extends Event> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEvent(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEvent(PathMetadata metadata, PathInits inits) {
        this(Event.class, metadata, inits);
    }

    public QEvent(Class<? extends Event> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.eventPartner = inits.isInitialized("eventPartner") ? new QEventPartner(forProperty("eventPartner")) : null;
    }

}

