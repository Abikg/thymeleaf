package com.example.learn.boot.unittest.service;

import com.example.learn.boot.unittest.model.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> listAll();

    EmployeeDTO save(EmployeeDTO dto);
    EmployeeDTO getById(long id);
    EmployeeDTO updateEmployee(Long id, EmployeeDTO updatedEmployee);

    void deleteEmployeeById(long id);


}
