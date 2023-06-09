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
    public ResponseEntity listAll() {

            List<EmployeeDTO> employeeDTOList = employeeConverter.convertToDtoList(employeeRepository.findAll());
            if (employeeDTOList.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }else {
                return ResponseEntity.status(HttpStatus.OK).body(employeeDTOList);
            }

    }

    @Override
    public ResponseEntity save(EmployeeDTO dto) {
           if(dto.getEmail()==null){



                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }else {
               EmployeeDTO savedDto =  employeeConverter.convertToDto(
                       employeeRepository.saveAndFlush(employeeConverter.convertToEntity(dto)));
                return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
            }
    }

    @Override
    public ResponseEntity getById(long id) {
        EmployeeDTO employeeDTO = employeeConverter.convertToDto(employeeRepository.findById(id).orElse(null));
        if (employeeDTO==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(employeeDTO);
        }
    }

    @Override
    public ResponseEntity updateEmployee(Long id, EmployeeDTO updatedEmployee) {
        PersistentEmployeeEntity existingEntity = employeeRepository.findById(id).orElse(null);
        if (existingEntity==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }else {
           EmployeeDTO dto= employeeConverter.convertToDto(employeeRepository.save(employeeConverter.copyConvertToEntity(updatedEmployee, existingEntity)));
           if(dto.getEmail()==null){
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
           }else {
               return ResponseEntity.status(HttpStatus.OK).body(dto);
           }

        }
    }

    @Override
    public ResponseEntity deleteEmployeeById(long id) {
        EmployeeDTO dto = employeeConverter.convertToDto(employeeRepository.findById(id).orElse(null));
        if(dto==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }else{
            employeeRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }
}

