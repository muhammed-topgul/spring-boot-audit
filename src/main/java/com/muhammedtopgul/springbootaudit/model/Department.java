package com.muhammedtopgul.springbootaudit.model;

import com.muhammedtopgul.springbootaudit.audit.AuditRelation;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author muhammed-topgul
 * @since 16/08/2022 11:44
 */
@Entity
@Data
@NoArgsConstructor
public class Department implements AuditRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer capacity;

    @Override
    public String toAuditLog() {
        return String.valueOf(id);
    }
}
