package com.vkiprono.springbootrestserver.respositories;

import com.vkiprono.springbootrestserver.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vkiprono
 * @created 6/9/23
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
     Employee findEmployeeBypNo(String pNo);
}
