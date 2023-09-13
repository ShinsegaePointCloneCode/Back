package com.example.smilekarina.event.infrastructure;

import com.example.smilekarina.event.domain.MyEventList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyEventListRepository extends JpaRepository<MyEventList,Long> {

}
