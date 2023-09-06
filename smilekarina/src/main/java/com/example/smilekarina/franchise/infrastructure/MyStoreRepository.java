package com.example.smilekarina.franchise.infrastructure;

import com.example.smilekarina.franchise.domain.MyStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyStoreRepository extends JpaRepository<MyStore,Long> {
}
