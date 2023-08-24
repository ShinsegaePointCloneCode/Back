package com.example.smilekarina.event.infrastructure;

import com.example.smilekarina.event.domain.EventPartner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventPartnerRepository extends JpaRepository<EventPartner,Long> {

}
