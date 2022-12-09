package com.roi.rental_car.rental_car.services.impl;

import com.roi.rental_car.rental_car.dto.EmployeeDTO;
import com.roi.rental_car.rental_car.entities.Branch;
import com.roi.rental_car.rental_car.entities.Employee;
import com.roi.rental_car.rental_car.mappers.BranchMapper;
import com.roi.rental_car.rental_car.mappers.EmployeeMapper;
import com.roi.rental_car.rental_car.repositories.BranchRepo;
import com.roi.rental_car.rental_car.repositories.EmployeeeRepo;
import com.roi.rental_car.rental_car.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeeRepo employeeeRepo;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private BranchMapper branchMapper;
    @Autowired
    private BranchRepo branchRepo;

    @Override
    public EmployeeDTO getById(Long id) {
        Employee employee = employeeeRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Employee with this id " + id + "does not exists"));
        EmployeeDTO employeeDTO=employeeMapper.toDto(employee);
        employeeDTO=setOthervalues(employee,employeeDTO);
        return employeeDTO;
    }

    @Override
    public List<EmployeeDTO> getAll() {
        List<EmployeeDTO> employeeDTOList=new ArrayList<>();
        for (Employee employee: employeeeRepo.findAll()){
            EmployeeDTO employeeDTO=employeeMapper.toDto(employee);
            employeeDTO = setOthervalues(employee,employeeDTO);
            employeeDTOList.add(employeeDTO);
        }

        return employeeDTOList;
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
    if(employeeDTO.getEmployeeId()!= null) throw new RuntimeException("Id must be null");
    Employee employee=employeeMapper.toEntity(employeeDTO);
    if (employeeDTO.getBranch()!=null){
        Branch branch = branchRepo.findById(employeeDTO.getBranch().getBranchId()).orElseThrow(()-> new RuntimeException("This branch doesnt exists    "));
        employee.setBranch(branch);
    }
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
        if (employeeDTO.getBranch()!=null){
            Branch branch = branchRepo.findById(employeeDTO.getBranch().getBranchId()).orElseThrow(()-> new RuntimeException("This branch doesnt exists    "));
            employee.setBranch(branch);
        }
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
    public EmployeeDTO setOthervalues(Employee employee,EmployeeDTO employeeDTO){
        if (employee.getBranch()!=null)
            employeeDTO.setBranch(branchMapper.toDto(employee.getBranch()));
        return employeeDTO;
    }
}
