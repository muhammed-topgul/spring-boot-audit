package com.muhammedtopgul.springbootaudit.repository;

import com.muhammedtopgul.springbootaudit.model.AuditLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author muhammed-topgul
 * @since 16/08/2022 10:53
 */
@Repository
public interface AuditLogRepository extends CrudRepository<AuditLog, Long> {
    List<AuditLog> findAuditLogByPersistedObjectId(String persistedObjectId);
}
