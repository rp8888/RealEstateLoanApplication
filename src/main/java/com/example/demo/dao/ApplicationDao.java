package com.example.demo.dao;

import java.util.List;

import com.example.demo.entities.Application;

public interface ApplicationDao {

	public void saveApplication(Application application);

	public List<Application> getAllApplicationDetails();

	public Application getApplicationDetailsByCustomerId(String id);
}
