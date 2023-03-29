package com.example.demo.dao;

import java.util.List;

import com.example.demo.entities.Customer;

public interface CustomerDao {

	public List<Customer> getAllCustomerDetails();

	public void saveCustomer(Customer customer);

}
