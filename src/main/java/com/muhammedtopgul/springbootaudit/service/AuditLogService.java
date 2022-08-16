package com.muhammedtopgul.springbootaudit.service;

import com.muhammedtopgul.springbootaudit.model.AuditLog;
import com.muhammedtopgul.springbootaudit.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author muhammed-topgul
 * @since 16/08/2022 10:52
 */
@Service
@RequiredArgsConstructor
public class AuditLogService {
    private final AuditLogRepository auditLogRepository;

    public void save(AuditLog auditLog) {
        auditLogRepository.save(auditLog);
    }
}
