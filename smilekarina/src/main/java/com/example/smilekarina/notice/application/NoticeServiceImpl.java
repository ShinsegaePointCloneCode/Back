package com.example.smilekarina.notice.application;

import com.example.smilekarina.notice.infrastructure.FAQRepository;
import com.example.smilekarina.notice.infrastructure.NoticeRepository;
import com.example.smilekarina.notice.infrastructure.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeServiceImpl {
    private final ReportRepository reportRepository;
    private final NoticeRepository noticeRepository;
    private final FAQRepository faqRepository;

}
