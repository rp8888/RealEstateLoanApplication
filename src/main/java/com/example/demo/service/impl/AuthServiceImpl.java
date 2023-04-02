package com.example.demo.service.impl;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CustomerDao;
import com.example.demo.entities.Customer;

@Service
public class AuthServiceImpl implements UserDetailsService {
	private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
	@Autowired
	private CustomerDao customerDao;

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Load user by username : " + username);
		Customer customer;
		try {
			customer = customerDao.getCustomerByEmailId(username);
		} catch (Exception e) {
			throw new UsernameNotFoundException("User is not registered in this applicaation");
		}
		return new User(customer.getEmailId(), customer.getPassword(), Collections.emptyList());
	}

	public User findById(String userId) {
		log.info("AuthServiceImpl -> : " + userId);
		Customer customer;
		try {
			customer = customerDao.getCustomerByEmailId(userId);
		} catch (Exception e) {
			throw new UsernameNotFoundException("User is not registered in this applicaation");
		}
		return new User(customer.getId(), customer.getPassword(), Collections.emptyList());
	}
}