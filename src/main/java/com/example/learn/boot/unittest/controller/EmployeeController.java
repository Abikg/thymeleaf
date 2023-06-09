package com.example.learn.boot.unittest.controller;

import com.example.learn.boot.unittest.model.EmployeeDTO;
import com.example.learn.boot.unittest.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping( "/list")
    @ResponseBody
    public ResponseEntity employeeList() {
        ResponseEntity response=employeeService.listAll();
        return response;
    }

    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity create(@RequestBody EmployeeDTO dto) {
        ResponseEntity response = employeeService.save(dto);
        return response;
    }

    @GetMapping( "get/{id}")
    @ResponseBody
    public ResponseEntity get(@PathVariable Long id){
        ResponseEntity response = employeeService.getById(id);
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
         ResponseEntity response= employeeService.deleteEmployeeById(id);
        return response;
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDTO> update(@PathVariable long id, @RequestBody EmployeeDTO employeeDTO) {

            ResponseEntity response = employeeService.updateEmployee(id, employeeDTO);
            return response;
    }
}
