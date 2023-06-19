package com.example.learn.boot.unittest.service.impl;

import com.example.learn.boot.unittest.converter.EmployeeConverter;
import com.example.learn.boot.unittest.domain.PersistentEmployeeEntity;
import com.example.learn.boot.unittest.model.EmployeeDTO;
import com.example.learn.boot.unittest.repository.EmployeeRepository;
import com.example.learn.boot.unittest.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeConverter employeeConverter;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDTO> listAll() {

            List<EmployeeDTO> employeeDTOList = employeeConverter.convertToDtoList(employeeRepository.findAll());
            return employeeDTOList;
    }

    @Override
    public EmployeeDTO save(EmployeeDTO dto) {

               EmployeeDTO savedDto =  employeeConverter.convertToDto(
                       employeeRepository.saveAndFlush(employeeConverter.convertToEntity(dto)));
               return savedDto;

    }

    @Override
    public EmployeeDTO getById(long id) {
        EmployeeDTO employeeDTO = employeeConverter.convertToDto(employeeRepository.findById(id).orElse(null));
        return employeeDTO;
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO updatedEmployee) {
        PersistentEmployeeEntity existingEntity = employeeRepository.findById(id).orElse(null);

                EmployeeDTO dto= employeeConverter.convertToDto(employeeRepository.save(employeeConverter.copyConvertToEntity(updatedEmployee, existingEntity)));
                return dto;

    }

    @Override
    public void deleteEmployeeById(long id) {
        employeeRepository.deleteById(id);

    }
}

