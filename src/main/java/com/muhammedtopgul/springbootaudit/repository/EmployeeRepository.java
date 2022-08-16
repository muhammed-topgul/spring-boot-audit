package com.muhammedtopgul.springbootaudit.repository;

import com.muhammedtopgul.springbootaudit.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author muhammed-topgul
 * @since 16/08/2022 10:53
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
