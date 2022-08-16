package com.muhammedtopgul.springbootaudit.mapper;

import com.muhammedtopgul.springbootaudit.dto.EmployeeDto;
import com.muhammedtopgul.springbootaudit.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author muhammed-topgul
 * @since 16/08/2022 12:01
 */
@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    @Mapping(source = "department.id", target = "departmentId")
    EmployeeDto toDto(Employee entity);

    @Mapping(source = "departmentId", target = "department.id")
    Employee toEntity(EmployeeDto dto);

    List<EmployeeDto> toDtoList(List<Employee> entityList);

    List<Employee> toEntityList(List<EmployeeDto> dtoList);
}
