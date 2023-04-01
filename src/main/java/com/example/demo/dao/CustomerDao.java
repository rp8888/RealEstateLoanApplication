package com.example.demo.dao;

import java.util.List;

import com.example.demo.dto.ApplicationResponseDto;
import com.example.demo.entities.Application;
import com.example.demo.entities.Customer;

public interface CustomerDao {

	public void saveCustomer(Customer customer);

	public void saveApplication(Application customer);

	public List<ApplicationResponseDto> getAllApplicationDetails();

	public Application getCustomerBySSN(String ssn);

}
