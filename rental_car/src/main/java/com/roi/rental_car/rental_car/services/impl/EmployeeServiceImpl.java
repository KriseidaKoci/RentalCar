package com.roi.rental_car.rental_car.services.impl;

import com.roi.rental_car.rental_car.dto.EmployeeDTO;
import com.roi.rental_car.rental_car.entities.Employee;
import com.roi.rental_car.rental_car.mappers.EmployeeMapper;
import com.roi.rental_car.rental_car.repositories.EmployeeeRepo;
import com.roi.rental_car.rental_car.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeeRepo employeeeRepo;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public EmployeeDTO getById(Long id) {
        Employee employee = employeeeRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Employee with this id " + id + "does not exists"));
        return employeeMapper.toDto(employee);
    }

    @Override
    public List<EmployeeDTO> getAll() {
        return employeeMapper.toDtoList(employeeeRepo.findAll());
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
    if(employeeDTO.getEmployeeId()!= null) throw new RuntimeException("Id must not be null");
    Employee employee=employeeMapper.toEntity(employeeDTO);
    employeeeRepo.save(employee);
    return employeeMapper.toDto(employee);

    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
      if (employeeDTO.getEmployeeId()==null) throw new RuntimeException("Id must not be null") ;
      Employee employee= employeeeRepo.findById(employeeDTO.getEmployeeId()).orElseThrow(
              ()->new RuntimeException("Employee with this id" + employeeDTO.getEmployeeId()+ "  does not exists"));
      employee.setName(employeeDTO.getName());
      employee.setPosittion(employeeDTO.getPosittion());
        employeeeRepo.save(employee);
        return employeeMapper.toDto(employee);
    }

    @Override
    public String deleteEmploye(Long id) {
        try {
            if (employeeeRepo.findById(id).isEmpty())
                throw new RuntimeException("Employee with this id" + id + " does not exists");
            employeeeRepo.deleteById(id);
            return "employee with the id " + id + "has been deleted";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
