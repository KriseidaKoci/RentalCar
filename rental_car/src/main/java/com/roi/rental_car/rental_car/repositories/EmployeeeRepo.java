package com.roi.rental_car.rental_car.repositories;

import com.roi.rental_car.rental_car.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.StyledEditorKit;

@Repository

public interface EmployeeeRepo extends JpaRepository<Employee, Long> {
    Employee getEmployeeByEmployeeId (Long id);
    Boolean existsEmployeeByNameIgnoreCase(String name);
    Employee getEmployeeByName(String name);
}
