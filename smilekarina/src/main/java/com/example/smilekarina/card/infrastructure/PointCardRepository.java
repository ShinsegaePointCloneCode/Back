package com.example.smilekarina.card.infrastructure;

import com.example.smilekarina.card.domain.IssueType;
import com.example.smilekarina.card.domain.PointCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointCardRepository  extends JpaRepository<PointCard, Long> {

    List<PointCard> findByUserIdAndIssueType(Long userId, IssueType issueType);

    PointCard findFirstByUserIdAndIssueTypeAndIssuePlaceOrderByCreatedDateDesc
            (Long userId, IssueType issueType, String issuePlace);


}
