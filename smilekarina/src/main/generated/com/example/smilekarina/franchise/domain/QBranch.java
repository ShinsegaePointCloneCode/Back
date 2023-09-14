package com.example.smilekarina.franchise.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBranch is a Querydsl query type for Branch
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBranch extends EntityPathBase<Branch> {

    private static final long serialVersionUID = -2028041276L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBranch branch = new QBranch("branch");

    public final StringPath branchAddress = createString("branchAddress");

    public final NumberPath<Double> branchLatitude = createNumber("branchLatitude", Double.class);

    public final NumberPath<Double> branchLontitude = createNumber("branchLontitude", Double.class);

    public final StringPath branchName = createString("branchName");

    public final QFranchise franchise;

    public final StringPath gugunName = createString("gugunName");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath sidoName = createString("sidoName");

    public QBranch(String variable) {
        this(Branch.class, forVariable(variable), INITS);
    }

    public QBranch(Path<? extends Branch> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBranch(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBranch(PathMetadata metadata, PathInits inits) {
        this(Branch.class, metadata, inits);
    }

    public QBranch(Class<? extends Branch> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.franchise = inits.isInitialized("franchise") ? new QFranchise(forProperty("franchise")) : null;
    }

}

