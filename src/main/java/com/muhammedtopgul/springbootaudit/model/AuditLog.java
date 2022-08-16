package com.muhammedtopgul.springbootaudit.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author muhammed-topgul
 * @since 16/08/2022 10:50
 */
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateCreated;
    private LocalDate lastUpdated;
    private String className;
    private String persistedObjectId;
    private String eventName;
    private String propertyName;
    private String oldValue;
    private String newValue;

    @PrePersist
    void beforeInsert() {
        this.dateCreated = LocalDate.now();
        this.lastUpdated = LocalDate.now();
    }
}
