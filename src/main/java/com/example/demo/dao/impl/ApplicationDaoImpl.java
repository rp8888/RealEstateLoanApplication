package com.example.demo.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.example.demo.dao.ApplicationDao;
import com.example.demo.entities.Application;

@Repository
public class ApplicationDaoImpl implements ApplicationDao{

	private static final Logger log = LoggerFactory.getLogger(ApplicationDaoImpl.class);
	@Autowired
	private DynamoDBMapper mapper;
	
	@Autowired
	private AmazonDynamoDB dynamoDbConfig;

	@Override
	public void saveApplication(Application application) {
		mapper.save(application);
		
	}

	@Override
	public List<Application> getAllApplicationDetails() {
		List<Application> applicationList = new ArrayList<>();
		log.debug("In ApplicationDaoImpl() Fetchin all the application details");
		ScanRequest scanRequest = new ScanRequest()
		        .withTableName("Application");
		ScanResult result = dynamoDbConfig.scan(scanRequest);
		mapApplicationResultFromDB(result.getItems(),applicationList);
		return applicationList;
	}

	private void mapApplicationResultFromDB(List<Map<String, AttributeValue>> items, List<Application> applicationList) {
		for (Map<String, AttributeValue> item : items) {
		    Application application = new Application();
		    application.setCustomerId(item.get("customerId").getS());
		    application.setCustomerSSN(item.get("customerSSN").getS());
		    application.setEmailId(item.get("emailId").getS());
		    application.setEquityAmount(item.get("equityAmount").getS());
		    application.setFullName(item.get("fullName").getS());
		    application.setId(item.get("id").getS());
		    application.setLoanAmount(item.get("loanAmount").getS());
		    application.setOccupation(item.get("occupation").getS());
		    application.setSalaryAmount(item.get("salaryAmount").getS());
		    application.setStatus(item.get("status").getS());
		    applicationList.add(application);
		}
		
	}

	@Override
	public Application getApplicationDetailsByCustomerId(String id) {
		DynamoDBScanExpression expression = new DynamoDBScanExpression();
		expression.addFilterCondition("customerId", new Condition().withComparisonOperator(ComparisonOperator.EQ)
				.withAttributeValueList(new AttributeValue().withS(id)));
		return mapper.scan(Application.class, expression).get(0);
	}

}
