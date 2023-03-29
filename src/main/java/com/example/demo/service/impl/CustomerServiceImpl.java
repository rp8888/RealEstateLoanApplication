package com.example.demo.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CustomerDao;
import com.example.demo.dto.CustomerDto;
import com.example.demo.entities.Customer;
import com.example.demo.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	 private CustomerDao customerDao;
	private ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public List<Customer> getCustomerDetails() {
		return customerDao.getAllCustomerDetails();
	}
	@Override
	public void saveCustomerDetails(CustomerDto customerdto) {
		
		if(null!=customerdto) {
			Customer customer = new Customer();
			modelMapper.map(customerdto, customer);
			customerDao.saveCustomer(customer);
		}
		
	}


}
