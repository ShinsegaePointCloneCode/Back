package com.example.smilekarina.franchise.application;

import com.example.smilekarina.franchise.vo.FranchiseOut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FranchiseService {
    List<FranchiseOut> findStore();

    Page<FranchiseOut> findstorelist(Pageable pageable);
}
