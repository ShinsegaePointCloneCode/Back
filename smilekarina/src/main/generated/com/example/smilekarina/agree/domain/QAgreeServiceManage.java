package com.example.smilekarina.agree.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAgreeServiceManage is a Querydsl query type for AgreeServiceManage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAgreeServiceManage extends EntityPathBase<AgreeServiceManage> {

    private static final long serialVersionUID = -1005837537L;

    public static final QAgreeServiceManage agreeServiceManage = new QAgreeServiceManage("agreeServiceManage");

    public final com.example.smilekarina.global.domain.QBaseEntity _super = new com.example.smilekarina.global.domain.QBaseEntity(this);

    public final BooleanPath agreeCheck = createBoolean("agreeCheck");

    public final EnumPath<AgreeType> agreeType = createEnum("agreeType", AgreeType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QAgreeServiceManage(String variable) {
        super(AgreeServiceManage.class, forVariable(variable));
    }

    public QAgreeServiceManage(Path<? extends AgreeServiceManage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAgreeServiceManage(PathMetadata metadata) {
        super(AgreeServiceManage.class, metadata);
    }

}

