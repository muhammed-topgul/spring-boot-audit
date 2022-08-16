package com.muhammedtopgul.springbootaudit.controller;

import com.muhammedtopgul.springbootaudit.dto.DepartmentDto;
import com.muhammedtopgul.springbootaudit.mapper.DepartmentMapper;
import com.muhammedtopgul.springbootaudit.mapper.EmployeeMapper;
import com.muhammedtopgul.springbootaudit.model.Department;
import com.muhammedtopgul.springbootaudit.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author muhammed-topgul
 * @since 16/08/2022 11:30
 */
@RestController
@RequestMapping(value = "/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @GetMapping
    public List<DepartmentDto> findAll() {
        return departmentMapper.toDtoList((List<Department>) departmentRepository.findAll());
    }

    @PostMapping
    public DepartmentDto save(@RequestBody DepartmentDto departmentDto) {
        return departmentMapper.toDto(departmentRepository.save(departmentMapper.toEntity(departmentDto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        departmentRepository.deleteById(id);
    }

    @PutMapping
    public DepartmentDto update(@RequestBody DepartmentDto departmentDto) {
        Department department = departmentRepository.findById(departmentDto.getId()).
                orElseThrow(NullPointerException::new);
        department.setName(departmentDto.getName());
        department.setCapacity(departmentDto.getCapacity());
        return departmentMapper.toDto(departmentMapper.toEntity(departmentDto));
    }
}
