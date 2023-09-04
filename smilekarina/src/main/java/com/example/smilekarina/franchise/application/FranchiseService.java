package com.example.smilekarina.franchise.application;

import com.example.smilekarina.franchise.vo.FranchiseOut;

import java.util.List;

public interface FranchiseService {
    List<FranchiseOut> findStore();
}
