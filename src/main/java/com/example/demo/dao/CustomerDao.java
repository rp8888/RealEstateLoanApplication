package com.example.demo.dao;

import com.example.demo.entities.Customer;

public interface CustomerDao {

	public void saveCustomer(Customer customer);

	public Customer getCustomerByEmailId(String username) throws Exception;

	public Customer getCustomerByUserId(String userId);

}
