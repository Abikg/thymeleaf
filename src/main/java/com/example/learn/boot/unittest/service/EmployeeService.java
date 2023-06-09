package com.example.learn.boot.unittest.service;

import com.example.learn.boot.unittest.model.EmployeeDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {
    ResponseEntity listAll();

    ResponseEntity save(EmployeeDTO dto);
    ResponseEntity getById(long id);
    ResponseEntity updateEmployee(Long id, EmployeeDTO updatedEmployee);

    ResponseEntity deleteEmployeeById(long id);


}
