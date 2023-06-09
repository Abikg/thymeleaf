package com.example.learn.boot.unittest;



import com.example.learn.boot.unittest.controller.EmployeeController;
import com.example.learn.boot.unittest.model.EmployeeDTO;
import com.example.learn.boot.unittest.repository.EmployeeRepository;
import com.example.learn.boot.unittest.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UnitTestApplicationTests {

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	protected EmployeeService employeeService;

	@Autowired
	protected EmployeeRepository employeeRepository;

	@Autowired
	protected EmployeeController employeeController;

	protected ObjectMapper objectMapper;

	protected void createEmployee(String name, String address, String email, String phone){
		EmployeeDTO dto = new EmployeeDTO();
		dto.setName(name);
		dto.setAddress(address);
		dto.setEmail(email);
		dto.setPhone(phone);
		employeeService.save(dto);


    }

	@BeforeEach
	public void setUp() {
		objectMapper = new ObjectMapper();
	}

}
