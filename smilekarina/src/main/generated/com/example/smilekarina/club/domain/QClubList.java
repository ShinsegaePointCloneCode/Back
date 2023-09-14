package com.example.smilekarina.club.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClubList is a Querydsl query type for ClubList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClubList extends EntityPathBase<ClubList> {

    private static final long serialVersionUID = -486976067L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClubList clubList = new QClubList("clubList");

    public final QClub club;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.example.smilekarina.user.domain.QUser user;

    public QClubList(String variable) {
        this(ClubList.class, forVariable(variable), INITS);
    }

    public QClubList(Path<? extends ClubList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QClubList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QClubList(PathMetadata metadata, PathInits inits) {
        this(ClubList.class, metadata, inits);
    }

    public QClubList(Class<? extends ClubList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.club = inits.isInitialized("club") ? new QClub(forProperty("club")) : null;
        this.user = inits.isInitialized("user") ? new com.example.smilekarina.user.domain.QUser(forProperty("user")) : null;
    }

}

