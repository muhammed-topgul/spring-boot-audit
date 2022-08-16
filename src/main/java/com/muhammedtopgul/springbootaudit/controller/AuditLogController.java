package com.muhammedtopgul.springbootaudit.controller;

import com.muhammedtopgul.springbootaudit.model.AuditLog;
import com.muhammedtopgul.springbootaudit.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author muhammed-topgul
 * @since 16/08/2022 15:24
 */
@RestController
@RequestMapping(value = "/audit-logs")
@RequiredArgsConstructor
public class AuditLogController {
    private final AuditLogRepository auditLogRepository;

    @GetMapping
    public List<AuditLog> findAll() {
        return (List<AuditLog>) auditLogRepository.findAll();
    }

    @GetMapping("/{persistedObjectId}")
    public List<AuditLog> findAll(@PathVariable Long persistedObjectId) {
        return auditLogRepository.findAuditLogByPersistedObjectId(persistedObjectId.toString());
    }
}
