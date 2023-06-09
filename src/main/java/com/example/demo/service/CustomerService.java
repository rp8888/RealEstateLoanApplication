package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ApplicationRequestDto;
import com.example.demo.dto.ApplicationResponseDto;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.SignupDto;
import com.example.demo.dto.TokenDTO;
import com.example.demo.entities.Application;
import com.example.demo.exception.CustomException;

public interface CustomerService {

	public TokenDTO saveCustomerDetails(SignupDto signupDto);

	public void saveApplicationDetails(ApplicationRequestDto applicationRequestDto) throws CustomException, Exception;

	public List<Application> getAllApplicationDetails();

	public TokenDTO verifyLogin(LoginDTO loginDTO) throws Exception;

	public void logOut(TokenDTO dto);

	public ApplicationResponseDto getApplicationDetailForCustomer(String emailId) throws Exception;

}
