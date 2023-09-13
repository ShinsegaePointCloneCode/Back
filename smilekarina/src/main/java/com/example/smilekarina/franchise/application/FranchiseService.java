package com.example.smilekarina.franchise.application;

import com.example.smilekarina.franchise.dto.FranchiseDto;
import com.example.smilekarina.franchise.dto.MyStoreDto;
import com.example.smilekarina.franchise.vo.FranchiseOut;
import com.example.smilekarina.franchise.vo.MyStoreOut;
import com.example.smilekarina.franchise.vo.RegionOut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FranchiseService {
    Page<RegionOut> findStore(FranchiseDto franchiseDto, Pageable pageable);

    List<FranchiseOut> findStoreList();

    void createMyStore(String token, MyStoreDto myStoreDto);

    Page<MyStoreOut> myStoreOuts(String token, Pageable pageable);

    void clearMyStore(String token, MyStoreDto myStoreDto);
}
