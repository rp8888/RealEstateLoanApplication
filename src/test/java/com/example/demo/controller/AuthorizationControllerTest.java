package com.example.demo.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.security.JwtHelper;
import com.example.demo.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AuthorizationControllerTest {

	@InjectMocks
	private AuthorizationController authorizationController;
	@Mock
	private CustomerService customerService;

	@Mock
	AuthenticationManager authenticationManager;

	@Mock
	JwtHelper jwtHelper;
	
	
	
}
