package com.muhammedtopgul.springbootaudit.mapper;

import com.muhammedtopgul.springbootaudit.dto.DepartmentDto;
import com.muhammedtopgul.springbootaudit.model.Department;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author muhammed-topgul
 * @since 16/08/2022 12:05
 */
@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentDto toDto(Department entity);

    Department toEntity(DepartmentDto dto);

    List<DepartmentDto> toDtoList(List<Department> entityList);

    List<Department> toEntityList(List<DepartmentDto> dtoList);
}
