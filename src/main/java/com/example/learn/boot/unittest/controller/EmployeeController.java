package com.example.learn.boot.unittest.controller;

import com.example.learn.boot.unittest.model.EmployeeDTO;
import com.example.learn.boot.unittest.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping( "/list")
    public String employeeList(Model model) {
        model.addAttribute("employeelist",employeeService.listAll());
        return "index";
    }
    @GetMapping("/addEmployee")
    public String addEmployeeForm(Model model){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        model.addAttribute("employee",employeeDTO);
        return "addEmployeeForm";
    }

    @PostMapping("/list")
    public String create(EmployeeDTO employeeDTO) {
        employeeService.save(employeeDTO);
        return "redirect:/employee/list";
    }

    @GetMapping( "get/{id}")
    public String editEmployeeForm(@PathVariable Long id,Model model){
        EmployeeDTO employeeDTO =employeeService.getById(id);
        model.addAttribute("employee",employeeService.getById(id));
        return "editEmployeeForm";
    }

    @GetMapping("/del/{id}")
    public String delete(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/employee/list";
    }


    @PostMapping("/update/{id}")
    public String update(@PathVariable long id, @ModelAttribute("employee") EmployeeDTO employeeDTO,
                         Model model) {

            employeeService.updateEmployee(id, employeeDTO);
            return "redirect:/employee/list";
    }
}
