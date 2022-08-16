package com.muhammedtopgul.springbootaudit.model;

import com.muhammedtopgul.springbootaudit.audit.AuditAware;
import com.muhammedtopgul.springbootaudit.audit.AuditLogListener;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author muhammed-topgul
 * @since 16/08/2022 10:45
 */
@Entity
@Data
@NoArgsConstructor
@AuditAware
@EntityListeners(AuditLogListener.class)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, max = 20)
    private String fullName;
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_department")
    private Department department;
}
