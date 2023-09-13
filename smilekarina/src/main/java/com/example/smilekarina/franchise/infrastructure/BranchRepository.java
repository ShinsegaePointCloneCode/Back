package com.example.smilekarina.franchise.infrastructure;

import com.example.smilekarina.franchise.domain.Branch;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BranchRepository extends JpaRepository<Branch,Integer> {
}
