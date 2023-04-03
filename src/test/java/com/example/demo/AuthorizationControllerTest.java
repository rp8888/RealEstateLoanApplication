package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.controller.AuthorizationController;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.SignupDto;
import com.example.demo.dto.TokenDTO;
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

	@Test
	public void testRegister() throws Exception {
		TokenDTO tokenDTO =new TokenDTO("userId","accessToken","refreshToken");
		when(customerService.verifyLogin(Mockito.any(LoginDTO.class)))
		.thenReturn(tokenDTO);
		assertNotNull(authorizationController.register(new SignupDto()));

	}

	@Test
	public void testLogin() throws Exception {
		TokenDTO tokenDTO =new TokenDTO("userId","accessToken","refreshToken");
		when(customerService.verifyLogin(Mockito.any(LoginDTO.class)))
		.thenReturn(tokenDTO);
		assertNotNull(authorizationController.login(new LoginDTO()));

	}

}
