package com.muhammedtopgul.springbootaudit.model;

import com.muhammedtopgul.springbootaudit.audit.AuditAware;
import com.muhammedtopgul.springbootaudit.audit.AuditLogListener;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author muhammed-topgul
 * @since 16/08/2022 10:45
 */
@Entity
@Data
@NoArgsConstructor
@EntityListeners(AuditLogListener.class)
public class Employee implements Serializable, AuditAware {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_department")
    private Department department;
}
