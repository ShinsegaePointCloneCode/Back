package com.example.smilekarina.franchise.application;

import com.example.smilekarina.franchise.domain.Branch;
import com.example.smilekarina.franchise.infrastructure.BranchRepository;
import com.example.smilekarina.franchise.vo.FranchiseOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FranchiseServiceImpl implements FranchiseService{
    private final BranchRepository branchRepository;

    @Override
    public List<FranchiseOut> findStore() {
        List<Branch> getAllMarkers=branchRepository.findAll();
        return null;
    }
}
