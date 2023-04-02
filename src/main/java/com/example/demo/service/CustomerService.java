package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ApplicationRequestDto;
import com.example.demo.dto.ApplicationResponseDto;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.SignupDto;
import com.example.demo.dto.TokenDTO;
import com.example.demo.exception.CustomException;

public interface CustomerService {

	public TokenDTO saveCustomerDetails(SignupDto signupDto);

	public void saveApplicationDetails(ApplicationRequestDto applicationRequestDto) throws CustomException;

	public List<ApplicationResponseDto> getAllApplicationDetails();

	public TokenDTO verifyLogin(LoginDTO loginDTO);

	public void logOut(TokenDTO dto);

}
