package com.example.smilekarina.franchise.infrastructure;

import com.example.smilekarina.franchise.domain.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FranchiseRepository extends JpaRepository<Franchise,Integer> {
}
