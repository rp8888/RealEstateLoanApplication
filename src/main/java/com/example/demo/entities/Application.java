package com.example.demo.entities;

import java.io.Serializable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.Data;

@DynamoDBDocument
@Data
@DynamoDBTable(tableName = "Application")
public class Application implements Serializable {
	private static final long serialVersionUID = 1L;

	@DynamoDBHashKey(attributeName = "id")
	@DynamoDBAutoGeneratedKey
	private String id;
	@DynamoDBAttribute
	private String customerSSN;
	@DynamoDBAttribute
	private String fullName;
	@DynamoDBAttribute
	private String loanAmount;
	@DynamoDBAttribute
	private String equityAmount;
	@DynamoDBAttribute
	private String salaryAmount;
	@DynamoDBAttribute
	private String occupation;
	@DynamoDBAttribute
	private String status;
	@DynamoDBAttribute
	private String emailId;
	@DynamoDBAttribute
	private String customerId;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the customerSSN
	 */
	public String getCustomerSSN() {
		return customerSSN;
	}

	/**
	 * @param customerSSN the customerSSN to set
	 */
	public void setCustomerSSN(String customerSSN) {
		this.customerSSN = customerSSN;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the loanAmount
	 */
	public String getLoanAmount() {
		return loanAmount;
	}

	/**
	 * @param loanAmount the loanAmount to set
	 */
	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	/**
	 * @return the equityAmount
	 */
	public String getEquityAmount() {
		return equityAmount;
	}

	/**
	 * @param equityAmount the equityAmount to set
	 */
	public void setEquityAmount(String equityAmount) {
		this.equityAmount = equityAmount;
	}

	/**
	 * @return the salaryAmount
	 */
	public String getSalaryAmount() {
		return salaryAmount;
	}

	/**
	 * @param salaryAmount the salaryAmount to set
	 */
	public void setSalaryAmount(String salaryAmount) {
		this.salaryAmount = salaryAmount;
	}

	/**
	 * @return the occupation
	 */
	public String getOccupation() {
		return occupation;
	}

	/**
	 * @param occupation the occupation to set
	 */
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	

}