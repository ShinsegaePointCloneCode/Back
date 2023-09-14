package com.example.smilekarina.receipt.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSmartReceipt is a Querydsl query type for SmartReceipt
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSmartReceipt extends EntityPathBase<SmartReceipt> {

    private static final long serialVersionUID = 1482227244L;

    public static final QSmartReceipt smartReceipt = new QSmartReceipt("smartReceipt");

    public final StringPath branchName = createString("branchName");

    public final NumberPath<Integer> franchiseId = createNumber("franchiseId", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> pointId = createNumber("pointId", Long.class);

    public final NumberPath<Long> receiptId = createNumber("receiptId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QSmartReceipt(String variable) {
        super(SmartReceipt.class, forVariable(variable));
    }

    public QSmartReceipt(Path<? extends SmartReceipt> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSmartReceipt(PathMetadata metadata) {
        super(SmartReceipt.class, metadata);
    }

}

