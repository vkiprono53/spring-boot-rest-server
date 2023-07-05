package com.vkiprono.springbootrestserver.services;

import com.vkiprono.springbootrestserver.models.Department;

import java.util.List;
import java.util.Optional;

/**
 * @author vkiprono
 * @created 6/12/23
 */

public interface DepartmentServiceI {
    Department save(Department address);
    Optional<Department> findById(Long departmentId);
    Boolean update(Department department);
    Boolean deleteById(Long departmentId);
    List<Department> findAll();
}
