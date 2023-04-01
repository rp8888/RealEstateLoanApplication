package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ApplicationRequestDto;
import com.example.demo.dto.ApplicationResponseDto;
import com.example.demo.dto.CustomerDto;
import com.example.demo.exception.CustomException;


public interface CustomerService {

	public void saveCustomerDetails(CustomerDto customerdto);

	public void applicationDetails(ApplicationRequestDto applicationRequestDto) throws CustomException;

	public List<ApplicationResponseDto> getAllApplicationDetails();

}
