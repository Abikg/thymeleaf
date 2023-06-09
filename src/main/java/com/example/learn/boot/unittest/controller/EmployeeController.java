package com.example.learn.boot.unittest.controller;

import com.example.learn.boot.unittest.model.EmployeeDTO;
import com.example.learn.boot.unittest.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping({"/", "/list"})
    @ResponseBody
    public ResponseEntity employeeList() {

        if(employeeService.listAll()==null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(employeeService.listAll());
        }
    }

    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity create(@RequestBody EmployeeDTO dto) {
        EmployeeDTO savedDTO = employeeService.save(dto);
        if(dto.getEmail() == null ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return  ResponseEntity.status(HttpStatus.CREATED).body(savedDTO);
    }

    @GetMapping( "get/{id}")
    @ResponseBody
    public ResponseEntity get(@PathVariable Long id){
        EmployeeDTO employeeDTO = employeeService.getById(id);
        if(employeeDTO != null){
            return ResponseEntity.status(HttpStatus.OK).body(employeeDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        EmployeeDTO employeeDTO= employeeService.getById(id);
        if(employeeDTO==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }else {
            employeeService.deleteEmployeeById(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDTO> update(@PathVariable long id, @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO toupdateemployee = employeeService.getById(id);
        if (toupdateemployee==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }else {
            EmployeeDTO updatedEmployeeDTO = employeeService.updateEmployee(id, employeeDTO);
            if (updatedEmployeeDTO.getEmail() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(updatedEmployeeDTO);
        }
    }
}
