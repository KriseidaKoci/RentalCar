package com.roi.rental_car.rental_car.mappers;

import com.roi.rental_car.rental_car.dto.EmployeeDTO;
import com.roi.rental_car.rental_car.entities.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class EmployeeMapper implements BaseMapper <Employee, EmployeeDTO>{
    @Override
    public EmployeeDTO toDto(Employee employee) {

        if (employee==null) return null;
        EmployeeDTO employeeDTO=new EmployeeDTO();
        employeeDTO.setEmployeeId(employee.getEmployeeId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setPosittion(employee.getPosittion());
        return employeeDTO;
    }

    @Override
    public Employee toEntity(EmployeeDTO employeeDTO) {
        if (employeeDTO==null)return null;
        Employee employee= new Employee();
        employee.setEmployeeId(employeeDTO.getEmployeeId());
        employee.setPosittion(employeeDTO.getPosittion());
        employee.setName(employeeDTO.getName());
        return employee;

    }

    @Override
    public List<EmployeeDTO> toDtoList(List<Employee> e) {
        if (e==null)return null;
        List<EmployeeDTO> employeeDTOList= new ArrayList<>();
        for (Employee employee:e) {
            EmployeeDTO employeeDTO= toDto(employee);
            employeeDTOList.add(employeeDTO);
        }
        return  employeeDTOList;
    }

    @Override
    public List<Employee> toEntitity(List<EmployeeDTO> d) {
       if(d==null) return null;
        List<Employee> employeeList= new ArrayList<>();
        for(EmployeeDTO employeeDTO:d){
        Employee employee=toEntity(employeeDTO);
        employeeList.add(employee);
    }
        return employeeList;
}}
