package com.github.allanccruz.EmployeeCRUD.api.repository;

import com.github.allanccruz.EmployeeCRUD.api.entities.Employee;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
}
