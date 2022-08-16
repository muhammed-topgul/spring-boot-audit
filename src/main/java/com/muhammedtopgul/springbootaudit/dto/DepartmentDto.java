package com.muhammedtopgul.springbootaudit.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author muhammed-topgul
 * @since 16/08/2022 11:56
 */
@Getter
@Setter
public class DepartmentDto {
    private Long id;
    private String name;
    private Integer capacity;
    private List<EmployeeDto> employees = new ArrayList<>();
}
