package com.vkiprono.springbootrestserver.respositories;

import com.vkiprono.springbootrestserver.models.Department;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vkiprono
 * @created 6/9/23
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
