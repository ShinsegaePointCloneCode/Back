package com.example.smilekarina.franchise.application;
import com.example.smilekarina.franchise.dto.FranchiseDto;
import com.example.smilekarina.franchise.vo.RegionOut;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;


import com.example.smilekarina.franchise.domain.QBranch;
import com.example.smilekarina.franchise.domain.QFranchise;
import com.example.smilekarina.franchise.vo.FranchiseOut;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class FranchiseServiceImpl implements FranchiseService{
    private final JPAQueryFactory query;

@Override
public Page<RegionOut> findStore(FranchiseDto franchiseDto, Pageable pageable) {
    QBranch branch = QBranch.branch;
    QFranchise franchise = QFranchise.franchise;

    BooleanBuilder builder = new BooleanBuilder();

    // 동적 쿼리 조건 추가
    if (StringUtils.hasText(franchiseDto.getFranchiseName()) && !franchiseDto.getFranchiseName().equals("total")) {
        builder.and(franchise.franchiseName.eq(franchiseDto.getFranchiseName()));
    }

    if (StringUtils.hasText(franchiseDto.getSidoNm()) && !franchiseDto.getSidoNm().equals("total")) {
        builder.and(branch.sidoName.eq(franchiseDto.getSidoNm()));
    }

    if (StringUtils.hasText(franchiseDto.getGugunName()) && !franchiseDto.getGugunName().equals("total")) {
        builder.and(branch.gugunName.eq(franchiseDto.getGugunName()));
    }

    // 조건들을 AND 연산자로 결합
    Predicate finalPredicate = builder.getValue();

    List<RegionOut> regionOutList;
    regionOutList = query
                .select(Projections.constructor(RegionOut.class,
                        franchise.franchiseName,
                        branch.sidoName,
                        branch.gugunName,
                        branch.branchName,
                        branch.branchAddress))
                .from(branch)
                .leftJoin(branch.franchise, franchise)
                .where(branch.franchise.eq(franchise), finalPredicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

    Long count = query
            .select(franchise.count())
            .from(franchise)
            .fetchOne();
    if (count == null) count = 0L;
    return new PageImpl<>(regionOutList, pageable, count);
}


    @Override
    public List<FranchiseOut> findStoreList() {
        QBranch branch =QBranch.branch;
        QFranchise franchise =QFranchise.franchise;

        List<FranchiseOut> franchiseOut = query
                .select(Projections.constructor(FranchiseOut.class,
                        franchise.franchiseName,
                        franchise.franchiseImage,
                        branch.branchName,
                        branch.branchAddress,
                        branch.branchLatitude,
                        branch.branchLontitude
                        ))
                .from(branch)
                .leftJoin(branch.franchise, franchise)
                .where(branch.franchise.eq(franchise))

                .fetch();

        return franchiseOut;
    }
}
