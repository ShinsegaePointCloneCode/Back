package com.example.smilekarina.receipt.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReceiptAccept is a Querydsl query type for ReceiptAccept
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReceiptAccept extends EntityPathBase<ReceiptAccept> {

    private static final long serialVersionUID = -175023357L;

    public static final QReceiptAccept receiptAccept = new QReceiptAccept("receiptAccept");

    public final StringPath branchName = createString("branchName");

    public final NumberPath<Integer> franchiseId = createNumber("franchiseId", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> pointId = createNumber("pointId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QReceiptAccept(String variable) {
        super(ReceiptAccept.class, forVariable(variable));
    }

    public QReceiptAccept(Path<? extends ReceiptAccept> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReceiptAccept(PathMetadata metadata) {
        super(ReceiptAccept.class, metadata);
    }

}

