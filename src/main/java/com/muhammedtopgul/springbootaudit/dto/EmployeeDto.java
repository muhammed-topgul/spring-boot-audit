package com.muhammedtopgul.springbootaudit.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author muhammed-topgul
 * @since 16/08/2022 11:55
 */
@Getter
@Setter
public class EmployeeDto {
    private Long id;
    private String fullName;
    private String email;
    private Long departmentId;
}
