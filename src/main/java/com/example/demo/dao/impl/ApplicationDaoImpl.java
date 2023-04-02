package com.example.demo.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.example.demo.dao.ApplicationDao;
import com.example.demo.entities.Application;

@Repository
public class ApplicationDaoImpl implements ApplicationDao{

	private static final Logger log = LoggerFactory.getLogger(ApplicationDaoImpl.class);
	@Autowired
	private DynamoDBMapper mapper;

	@Override
	public void saveApplication(Application application) {
		mapper.save(application);
		
	}

	@Override
	public List<Application> getAllApplicationDetails() {
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		return mapper.scan(Application.class, scanExpression);
	}

}
