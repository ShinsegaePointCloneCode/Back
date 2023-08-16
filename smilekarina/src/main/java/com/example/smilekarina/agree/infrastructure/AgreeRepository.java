package com.example.smilekarina.agree.infrastructure;

import com.example.smilekarina.agree.domain.Agree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgreeRepository  extends JpaRepository<Agree, Long> {
}
