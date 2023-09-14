package com.example.smilekarina.receipt.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReceipt is a Querydsl query type for Receipt
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReceipt extends EntityPathBase<Receipt> {

    private static final long serialVersionUID = 1780480539L;

    public static final QReceipt receipt = new QReceipt("receipt");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath receiptNumber = createString("receiptNumber");

    public QReceipt(String variable) {
        super(Receipt.class, forVariable(variable));
    }

    public QReceipt(Path<? extends Receipt> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReceipt(PathMetadata metadata) {
        super(Receipt.class, metadata);
    }

}

