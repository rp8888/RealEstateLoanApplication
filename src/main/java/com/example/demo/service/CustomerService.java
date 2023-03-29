package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.CustomerDto;
import com.example.demo.entities.Customer;


public interface CustomerService {

	public List<Customer> getCustomerDetails();

	public void saveCustomerDetails(CustomerDto customerdto);

}
