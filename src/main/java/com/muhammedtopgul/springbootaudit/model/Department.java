package com.muhammedtopgul.springbootaudit.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author muhammed-topgul
 * @since 16/08/2022 11:44
 */
@Entity
@Data
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer capacity;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees = new ArrayList<>();
}
