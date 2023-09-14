package com.example.smilekarina.franchise.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFranchise is a Querydsl query type for Franchise
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFranchise extends EntityPathBase<Franchise> {

    private static final long serialVersionUID = 1287520827L;

    public static final QFranchise franchise = new QFranchise("franchise");

    public final BooleanPath bCombine = createBoolean("bCombine");

    public final BooleanPath bGettedPoint = createBoolean("bGettedPoint");

    public final BooleanPath bSmartReceipt = createBoolean("bSmartReceipt");

    public final StringPath franchiseImage = createString("franchiseImage");

    public final StringPath franchiseName = createString("franchiseName");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QFranchise(String variable) {
        super(Franchise.class, forVariable(variable));
    }

    public QFranchise(Path<? extends Franchise> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFranchise(PathMetadata metadata) {
        super(Franchise.class, metadata);
    }

}

