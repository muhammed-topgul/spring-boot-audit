package com.muhammedtopgul.springbootaudit.controller;

import com.muhammedtopgul.springbootaudit.dto.EmployeeDto;
import com.muhammedtopgul.springbootaudit.mapper.EmployeeMapper;
import com.muhammedtopgul.springbootaudit.model.Employee;
import com.muhammedtopgul.springbootaudit.repository.DepartmentRepository;
import com.muhammedtopgul.springbootaudit.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author muhammed-topgul
 * @since 16/08/2022 11:30
 */
@RestController
@RequestMapping(value = "/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeMapper employeeMapper;

    @GetMapping
    public List<EmployeeDto> findAll() {
        return employeeMapper.toDtoList((List<Employee>) employeeRepository.findAll());
    }

    @PostMapping
    public EmployeeDto save(@RequestBody EmployeeDto employeeDto) {
        return employeeMapper.toDto(employeeRepository.save(employeeMapper.toEntity(employeeDto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }

    @PutMapping
    public EmployeeDto update(@RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(employeeDto.getId()).
                orElseThrow(NullPointerException::new);
        employee.setFullName(employeeDto.getFullName());
        employee.setDepartment(departmentRepository.findById(employeeDto.getDepartmentId()).orElse(null));
        employee.setEmail(employeeDto.getEmail());
        return employeeMapper.toDto(employeeRepository.save(employee));
    }
}
