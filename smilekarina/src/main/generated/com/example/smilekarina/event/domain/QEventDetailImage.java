package com.example.smilekarina.event.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEventDetailImage is a Querydsl query type for EventDetailImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEventDetailImage extends EntityPathBase<EventDetailImage> {

    private static final long serialVersionUID = 204797103L;

    public static final QEventDetailImage eventDetailImage1 = new QEventDetailImage("eventDetailImage1");

    public final StringPath eventDetailImage = createString("eventDetailImage");

    public final NumberPath<Long> eventId = createNumber("eventId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QEventDetailImage(String variable) {
        super(EventDetailImage.class, forVariable(variable));
    }

    public QEventDetailImage(Path<? extends EventDetailImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEventDetailImage(PathMetadata metadata) {
        super(EventDetailImage.class, metadata);
    }

}

