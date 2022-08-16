package com.muhammedtopgul.springbootaudit.model;

import com.muhammedtopgul.springbootaudit.converter.MapToStringConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * @author muhammed-topgul
 * @since 16/08/2022 10:50
 */
@Entity
//@Builder
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

    @Column(name = "changeLog")
    @Convert(converter = MapToStringConverter.class)
    private Map<String, Map<String, String>> changeLog = new HashMap<>();

    @PrePersist
    void beforeInsert() {
        this.dateCreated = LocalDate.now();
        this.lastUpdated = LocalDate.now();
    }
}
