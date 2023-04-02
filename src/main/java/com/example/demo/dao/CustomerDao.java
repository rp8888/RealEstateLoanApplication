package com.example.demo.dao;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Test;

public interface CustomerDao {

	public void saveCustomer(Customer customer);

	public Customer getCustomerByEmailId(String username);

	public Customer getCustomerByUserId(String userId);

}
