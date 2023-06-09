package com.example.learn.boot.unittest.Employee;

import com.example.learn.boot.unittest.UnitTestApplicationTests;
import com.example.learn.boot.unittest.controller.EmployeeController;
import com.example.learn.boot.unittest.domain.PersistentEmployeeEntity;
import com.example.learn.boot.unittest.model.EmployeeDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EmployeeTest extends UnitTestApplicationTests {
    private EmployeeTest Id;

    @Test
    public void createEmployeeTest() throws Exception {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("Employee");
        dto.setAddress("Kathmandu");
        dto.setEmail("user@user.com");
        dto.setPhone("9876554433");

        mockMvc.perform(MockMvcRequestBuilders.post("/employee/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Employee"))
                .andExpect(jsonPath("$.address").value("Kathmandu"))
                .andExpect(jsonPath("$.email").value("user@user.com"))
                .andExpect(jsonPath("$.phone").value("9876554433"));
    }
    @Test
    public void createEmployeeTest_null() throws Exception {
        EmployeeDTO dto = new EmployeeDTO();


        mockMvc.perform(MockMvcRequestBuilders.post("/employee/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());

    }


    @Test
    public void getEmployeeById() throws Exception {
        createEmployee("Abik", "BKT", "abk@gmail.com", "984382");


        mockMvc.perform(MockMvcRequestBuilders.get("/employee/get/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Abik"));

    }
    @Test
    public void getEmployeeById_notfound() throws Exception {



        mockMvc.perform(MockMvcRequestBuilders.get("/employee/get/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());

    }



    @Test
    public void deleteEmployeeById() throws Exception {
        createEmployee("Abik", "BKT", "abk@gmail.com", "984382");

        mockMvc.perform(MockMvcRequestBuilders.delete("/employee/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void deleteEmployeeById_notfound() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.delete("/employee/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }



    @Test
    public void listAllEmployees() throws Exception {
        createEmployee("Abik", "BKT", "abk@gmail.com", "9843821");

        mockMvc.perform(MockMvcRequestBuilders.get("/employee/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Abik"));

    }
    @Test
    public void listAllEmployees_notfound() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.get("/employee/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());


    }


    @Test
    public void updateEmployeeTest() throws Exception {
        // Create an employee
        createEmployee("Abik", "BKT", "abk@gmail.com", "984382");

        // Update the employee
        String updatedEmployeeJson = "{ \"name\": \"aani\", \"address\": \"nepl\", \"email\": \"aak@gmail.com\", \"phone\": \"676841888\" }";
        mockMvc.perform(MockMvcRequestBuilders.put("/employee/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedEmployeeJson))
                .andExpect(status().isOk());

        // Verify that the employee was updated
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/get/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("aani"))
                .andExpect(jsonPath("$.address").value("nepl"))
                .andExpect(jsonPath("$.email").value("aak@gmail.com"))
                .andExpect(jsonPath("$.phone").value("676841888"));
    }
    @Test
    public void updateEmployeeTest_badreq() throws Exception {
        // Create an employee
        createEmployee("Abik", "BKT", "abk@gmail.com", "984382");

        // Update the employee
        String updatedEmployeeJson = "{ \"name\":\"\", \"address\": \"nepl\", \"email\":null\"\", \"phone\": \"676841888\" }";
        mockMvc.perform(MockMvcRequestBuilders.put("/employee/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedEmployeeJson))
                .andExpect(status().isBadRequest());

        // Verify that the employee was updated
//        mockMvc.perform(MockMvcRequestBuilders.get("/employee/get/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("aani"))
//                .andExpect(jsonPath("$.address").value("nepl"))
//                .andExpect(jsonPath("$.email").value("aak@gmail.com"))
//                .andExpect(jsonPath("$.phone").value("676841888"));
    }  @Test
    public void updateEmployeeTestnotfound() throws Exception {
        // Create an employee
//        createEmployee("Abik", "BKT", "abk@gmail.com", "984382");

        // Update the employee
        String updatedEmployeeJson = "{ \"name\":\"\", \"address\": \"nepl\", \"email\":\"k@gmail.c\", \"phone\": \"676841888\" }";
        mockMvc.perform(MockMvcRequestBuilders.put("/employee/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedEmployeeJson))
                .andExpect(status().isNotFound());
}
}