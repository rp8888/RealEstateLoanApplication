package com.example.demo.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.example.demo.dao.CustomerDao;
import com.example.demo.entities.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	private static final Logger log = LoggerFactory.getLogger(CustomerDaoImpl.class);
	@Autowired
	private DynamoDBMapper mapper;

	@Override
	public void saveCustomer(Customer customer) {
		mapper.save(customer);
	}

	@Override
	public Customer getCustomerByEmailId(String username) throws Exception {
		Customer customer = new Customer();
		try {
		DynamoDBScanExpression expression = new DynamoDBScanExpression();
		expression.addFilterCondition("emailId", new Condition().withComparisonOperator(ComparisonOperator.EQ)
				.withAttributeValueList(new AttributeValue().withS(username)));
		customer= mapper.scan(Customer.class, expression).get(0);
		log.info("In getCustomerByEmailId " + customer.getEmailId());
		}catch(Exception e) {
			throw new Exception("Customer is Not Registered with the Application");
		}
 
		return customer;
	}

	@Override
	public Customer getCustomerByUserId(String userId) {
		DynamoDBScanExpression expression = new DynamoDBScanExpression();
		expression.addFilterCondition("id", new Condition().withComparisonOperator(ComparisonOperator.EQ)
				.withAttributeValueList(new AttributeValue().withS(userId)));
		Customer customer = mapper.scan(Customer.class, expression).get(0);
		log.info("In getCustomerByUserId " + customer.getId());

		return customer;
	}

}
