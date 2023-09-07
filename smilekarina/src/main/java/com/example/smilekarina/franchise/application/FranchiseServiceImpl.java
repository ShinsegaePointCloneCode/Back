package com.example.smilekarina.franchise.application;
import com.example.smilekarina.franchise.dto.FranchiseDto;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;

import com.example.smilekarina.franchise.domain.Branch;
import com.example.smilekarina.franchise.domain.QBranch;
import com.example.smilekarina.franchise.domain.QFranchise;
import com.example.smilekarina.franchise.infrastructure.BranchRepository;
import com.example.smilekarina.franchise.infrastructure.FranchiseRepository;
import com.example.smilekarina.franchise.vo.FranchiseOut;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FranchiseServiceImpl implements FranchiseService{
    private final BranchRepository branchRepository;
    private final FranchiseRepository franchiseRepository;
    private final JPAQueryFactory query;

    @Override
    public List<FranchiseOut> findStore() {
        List<Branch> getAllMarkers=branchRepository.findAll();
        return null;
    }

    @Override
    public Page<FranchiseOut> findstorelist(FranchiseDto franchiseDto, Pageable pageable) {
        QBranch branch =QBranch.branch;
        QFranchise franchise =QFranchise.franchise;

        List<FranchiseOut> FranchiseOut = query
                .select(Projections.constructor(FranchiseOut.class,
                        branch,
                        franchise.franchiseImage, franchise.franchiseName))
                .from(branch)
                .leftJoin(branch.franchise, franchise)
                .where(branch.franchise.eq(franchise),
                        franchise.franchiseName.eq(franchiseDto.getFranchise_name()),
                        branch.sidoName.eq(franchiseDto.getSidoNm()),
                        branch.gugunName.eq(franchiseDto.getGugunName()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = query
                .select(franchise.count())
                .from(franchise)
                .fetchOne();
        if (count == null) count = 0L;
        return new PageImpl<>(FranchiseOut,pageable,count);
    }
}
