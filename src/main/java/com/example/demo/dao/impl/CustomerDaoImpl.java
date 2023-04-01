package com.example.demo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.example.demo.dao.CustomerDao;
import com.example.demo.dto.ApplicationResponseDto;
import com.example.demo.entities.Application;
import com.example.demo.entities.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private DynamoDBMapper mapper;

	@Override
	public void saveCustomer(Customer customer) {
		mapper.save(customer);
	}

	@Override
	public void saveApplication(Application application) {
		mapper.save(application);

	}

	@Override
	public List<ApplicationResponseDto> getAllApplicationDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Application getCustomerBySSN(String ssn) {
		return mapper.load(Application.class, ssn);
	}

}
