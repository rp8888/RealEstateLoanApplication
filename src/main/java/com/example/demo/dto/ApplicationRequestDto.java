package com.example.demo.dto;

public class ApplicationRequestDto {
	private String customerSSN;
	private String fullName;
	private String loanAmount;
	private String equityAmount;
	private String salaryAmount;
	private String occupation;
	private String status;
	private String emailId;

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

	
}
