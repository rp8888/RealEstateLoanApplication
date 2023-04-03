
package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.dto.ApplicationRequestDto;
import com.example.demo.entities.Application;
import com.example.demo.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

	@InjectMocks
	private CustomerController customerController;

	@Mock
	private CustomerService customerService;

	@Test
	public void testGetAllApplicationDetails() throws Exception {
		List<Application> applicationResponseDtoList = new ArrayList<>();
		when(customerService.getAllApplicationDetails()).thenReturn(applicationResponseDtoList);
		assertNotNull(customerController.getAllApplicationDetails(""));
	}

	@Test
	public void testSaveApplicationDetails() throws Exception {
		Mockito.doNothing().when(customerService).saveApplicationDetails(Mockito.any(ApplicationRequestDto.class));
		assertNotNull(customerController.saveApplicationDetails(Mockito.anyString(),
				new ApplicationRequestDto()));

	}

}
