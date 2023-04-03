package com.example.demo.controller.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.dao.ApplicationDao;
import com.example.demo.dao.CustomerDao;
import com.example.demo.dao.RefreshTokenDao;
import com.example.demo.dto.ApplicationRequestDto;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.SignupDto;
import com.example.demo.entities.Application;
import com.example.demo.entities.Customer;
import com.example.demo.entities.RefreshToken;
import com.example.demo.security.JwtHelper;
import com.example.demo.service.impl.CustomerServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

	@InjectMocks
	private CustomerServiceImpl customerServiceImpl;

	@Mock
	private CustomerDao customerDao;

	@Mock
	private ApplicationDao applicationDao;

	@Mock
	private RefreshTokenDao refreshTokenDao;

	@Mock
	private PasswordEncoder passwordEncoder;

	@Mock
	AuthenticationManager authenticationManager;
	
	@Mock
	private Authentication authentication;

	@Mock
	JwtHelper jwtHelper;

	@Test
	public void testSaveCustomerDetails() throws Exception {
		SignupDto signupdto = new SignupDto();
		signupdto.setEmailId("emailId");
		signupdto.setPassword("Password");
		Mockito.doNothing().when(customerDao).saveCustomer(Mockito.any(Customer.class));
		when(passwordEncoder.encode(signupdto.getPassword())).thenReturn("Password");
		Mockito.doNothing().when(refreshTokenDao).saveRefreshToken(Mockito.any(RefreshToken.class));
		when(jwtHelper.generateAccessToken(new User("emailId", "Password", Collections.emptyList())))
				.thenReturn("accessToken");
		when(jwtHelper.generateRefreshToken(new User("emailId", "Password", Collections.emptyList()),
				prepareRefreshToken())).thenReturn("refreshToken");
		assertNotNull(customerServiceImpl.saveCustomerDetails(signupdto));
	}

	@Test
	public void testSaveApplicationDetails() throws Exception {
		when(customerDao.getCustomerByEmailId(Mockito.anyString())).thenReturn(Mockito.any(Customer.class));
		Mockito.doNothing().when(applicationDao).saveApplication(Mockito.any(Application.class));
		customerServiceImpl.saveApplicationDetails(Mockito.any(ApplicationRequestDto.class));
	}

	@Test
	public void testVerifyLogin() throws Exception {
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setUsername("emailId");
		loginDTO.setPassword("Password");
		/*
		 * Mockito.doNothing().when(authenticationManager)
		 * .authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class));
		 */
		when(authentication.getPrincipal()).thenReturn(new User("emailId","Password",Collections.emptyList()));
		when(customerDao.getCustomerByEmailId(Mockito.anyString())).thenReturn(Mockito.any(Customer.class));
		Mockito.doNothing().when(refreshTokenDao).saveRefreshToken(prepareRefreshToken());
		when(jwtHelper.generateAccessToken(new User("emailId", "Password", Collections.emptyList())))
				.thenReturn("accessToken");
		when(jwtHelper.generateRefreshToken(new User("emailId", "Password", Collections.emptyList()),
				prepareRefreshToken())).thenReturn("refreshToken");
		assertNotNull(customerServiceImpl.verifyLogin(loginDTO));
	}

	private RefreshToken prepareRefreshToken() {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setId("id");
		Customer customer = new Customer();
		customer.setEmailId("emailId");
		customer.setPassword("Password");
		refreshToken.setOwner(customer);
		return refreshToken;
	}

}
