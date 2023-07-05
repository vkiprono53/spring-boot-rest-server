package com.vkiprono.springbootrestserver.services.impl;

import com.vkiprono.springbootrestserver.models.Department;
import com.vkiprono.springbootrestserver.respositories.DepartmentRepository;
import com.vkiprono.springbootrestserver.services.DepartmentServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author vkiprono
 * @created 6/12/23
 */
@Service
public class DepartmentServiceImpl implements DepartmentServiceI {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department save(Department department) {
        LOGGER.info(":::::START DepartmentServiceImpl.save():::::");

        if (department != null){
            departmentRepository.save(department);
        }
        LOGGER.info(":::::EXIT DepartmentServiceImpl.save():::::");

        return null;
    }

    @Override
    public Optional<Department> findById(Long departmentId) {
        LOGGER.info(":::::START EmployeeServiceImpl.findEmployeeById():::::");

        if (departmentId != null){
            return departmentRepository.findById(departmentId);
        }
        LOGGER.info(":::::EXIT EmployeeServiceImpl.findEmployeeById():::::");

        return Optional.empty();
    }

    @Override
    public Boolean update(Department department) {
        LOGGER.info(":::::START DepartmentServiceImpl.update():::::");

        boolean isUpdated = false;
        if (department != null){
            department.setDepartmentId(department.getDepartmentId());
            departmentRepository.save(department);
            isUpdated = true;
        }
        LOGGER.info(":::::EXIT DepartmentServiceImpl.update():::::");

        return isUpdated;
    }

    @Override
    public Boolean deleteById(Long departmentId) {
        LOGGER.info(":::::START DepartmentServiceImpl.deleteById():::::");

        boolean isDeleted = false;

        if (departmentId != null){
            departmentRepository.deleteById(departmentId);
            isDeleted = true;
        }
        LOGGER.info(":::::EXIT DepartmentServiceImpl.deleteById():::::");

        return isDeleted;
    }

    @Override
    public List<Department> findAll() {
        LOGGER.info(":::::START DepartmentServiceImpl.findAll():::::");

        List<Department> employees;
        employees = departmentRepository.findAll();
        LOGGER.info(":::::EXIT DepartmentServiceImpl.findAll():::::");

        return employees;
    }
}
