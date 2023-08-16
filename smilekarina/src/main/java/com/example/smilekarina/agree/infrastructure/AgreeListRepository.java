package com.example.smilekarina.agree.infrastructure;

import com.example.smilekarina.agree.domain.AgreeList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgreeListRepository  extends JpaRepository<AgreeList, Long> {
}
