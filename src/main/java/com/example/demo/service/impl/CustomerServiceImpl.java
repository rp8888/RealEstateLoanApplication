package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ApplicationDao;
import com.example.demo.dao.CustomerDao;
import com.example.demo.dao.RefreshTokenDao;
import com.example.demo.dto.ApplicationRequestDto;
import com.example.demo.dto.ApplicationResponseDto;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.SignupDto;
import com.example.demo.dto.TokenDTO;
import com.example.demo.entities.Application;
import com.example.demo.entities.Customer;
import com.example.demo.entities.RefreshToken;
import com.example.demo.exception.CustomException;
import com.example.demo.security.JwtHelper;
import com.example.demo.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private ApplicationDao applicationDao;

	@Autowired
	private RefreshTokenDao refreshTokenDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private ModelMapper modelMapper = new ModelMapper();

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtHelper jwtHelper;

	@Override
	public TokenDTO saveCustomerDetails(SignupDto signupDto) {
		Customer customer = new Customer();
		if (null != signupDto) {
			customer.setEmailId(signupDto.getEmailId());
			customer.setPassword(passwordEncoder.encode(signupDto.getPassword()));
			customer.setCustomerType("Customer");
			customerDao.saveCustomer(customer);
		}
		RefreshToken refreshToken = saveRefreshTokenDetails(customer);
		String accessToken = jwtHelper
				.generateAccessToken(new User(customer.getEmailId(), customer.getPassword(), Collections.emptyList()));
		String refreshTokenString = jwtHelper.generateRefreshToken(
				new User(customer.getEmailId(), customer.getPassword(), Collections.emptyList()), refreshToken);
		return new TokenDTO(customer.getId(), accessToken, refreshTokenString);
	}

	private RefreshToken saveRefreshTokenDetails(Customer customer) {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setId(customer.getId());
		refreshToken.setOwner(customer);
		refreshTokenDao.saveRefreshToken(refreshToken);
		return refreshToken;
	}

	@Override
	public void saveApplicationDetails(ApplicationRequestDto applicationRequestDto) throws Exception {
		log.debug("In Service layer -> started ");
		if (null != applicationRequestDto.getEmailId()) {
			Customer customer = customerDao.getCustomerByEmailId(applicationRequestDto.getEmailId());
			if (null != customer) {
				Application applicationRequest = new Application();
				applicationRequest.setStatus("NEW");
				modelMapper.map(applicationRequestDto, applicationRequest);
				applicationRequest.setCustomerId(customer.getId());
				applicationDao.saveApplication(applicationRequest);
				// emailConfig.createAndSendMailContent(applicationRequest.getEmailId(),
				// applicationRequest.getFullName());
				log.debug("In Service layer -> Application created and email sent to the customer ");
			} else {
				throw new CustomException("Customer Already Exists");
			}
		}
		log.debug("In Service layer -> ended ");
	}

	@Override
	public List<Application> getAllApplicationDetails() {
		List<ApplicationResponseDto> applicationResponseDto = new ArrayList<>();
		List<Application> applicationDetails = applicationDao.getAllApplicationDetails();
		return applicationDetails;
	}

	@Override
	public TokenDTO verifyLogin(LoginDTO loginDTO) throws Exception {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		User user = (User) authentication.getPrincipal();
		Customer customer = customerDao.getCustomerByEmailId(user.getUsername());
		RefreshToken refreshToken = saveRefreshTokenDetails(customer);
		String accessToken = jwtHelper.generateAccessToken(user);
		String refreshTokenString = jwtHelper.generateRefreshToken(user, refreshToken);
		return new TokenDTO(customer.getId(), accessToken, refreshTokenString);
	}

	@Override
	public void logOut(TokenDTO dto) {
		String tokeId = jwtHelper.getTokenIdFromRefreshToken(dto.getRefreshToken());
		if (refreshTokenDao.findById(tokeId)) {
			refreshTokenDao.deleteById(tokeId);
		}

	}

	@Override
	public ApplicationResponseDto getApplicationDetailForCustomer(String emailId) throws Exception {
		Customer customer = customerDao.getCustomerByEmailId(emailId);
		ApplicationResponseDto applicationResponseDto = new ApplicationResponseDto();
		if(null!=customer) {
			Application application = applicationDao.getApplicationDetailsByCustomerId(customer.getId());
			modelMapper.map(application, applicationResponseDto);
		}
		return applicationResponseDto;
	}

}
