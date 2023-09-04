package com.example.smilekarina.agree.infrastructure;

import com.example.smilekarina.agree.domain.AgreeAdvertise;
import com.example.smilekarina.agree.domain.AgreeServiceManage;
import com.example.smilekarina.agree.domain.AgreeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AgreeServiceManageRepository extends JpaRepository<AgreeServiceManage, Long> {
    Optional<AgreeServiceManage> findByUserIdAndAgreeType(Long userId, AgreeType agreeType);
    List<AgreeServiceManage> findByUserId(Long userId);
}
