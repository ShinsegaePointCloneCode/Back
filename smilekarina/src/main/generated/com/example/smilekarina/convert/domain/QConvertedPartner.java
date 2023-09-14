package com.example.smilekarina.convert.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QConvertedPartner is a Querydsl query type for ConvertedPartner
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QConvertedPartner extends EntityPathBase<ConvertedPartner> {

    private static final long serialVersionUID = -587156274L;

    public static final QConvertedPartner convertedPartner = new QConvertedPartner("convertedPartner");

    public final StringPath convertedPartnerName = createString("convertedPartnerName");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QConvertedPartner(String variable) {
        super(ConvertedPartner.class, forVariable(variable));
    }

    public QConvertedPartner(Path<? extends ConvertedPartner> path) {
        super(path.getType(), path.getMetadata());
    }

    public QConvertedPartner(PathMetadata metadata) {
        super(ConvertedPartner.class, metadata);
    }

}

