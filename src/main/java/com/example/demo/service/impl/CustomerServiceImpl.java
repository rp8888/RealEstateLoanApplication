package com.example.demo.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.AmazonSimpleMailConfiguration;
import com.example.demo.dao.CustomerDao;
import com.example.demo.dto.ApplicationRequestDto;
import com.example.demo.dto.ApplicationResponseDto;
import com.example.demo.dto.CustomerDto;
import com.example.demo.entities.Application;
import com.example.demo.entities.Customer;
import com.example.demo.exception.CustomException;
import com.example.demo.service.CustomerService;
import com.example.demo.utils.RandomStringGenerator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private AmazonSimpleMailConfiguration emailConfig;

	private RandomStringGenerator randomStringGenerator;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public void saveCustomerDetails(CustomerDto customerdto) {
		if (null != customerdto) {
			Customer customer = new Customer();
			modelMapper.map(customerdto, customer);
			customerDao.saveCustomer(customer);
		}
	}

	@Override
	public void applicationDetails(ApplicationRequestDto applicationRequestDto) throws CustomException {
		log.info("In Service layer -> started ");
		if (null != applicationRequestDto.getCustomerSSN()) {
			Application application = customerDao.getCustomerBySSN(applicationRequestDto.getCustomerSSN());
			if (null != application) {
				Application applicationRequest = new Application();
				applicationRequest.setStatus("NEW");
				String password = randomStringGenerator.randomString();
				applicationRequest.setCustomer(prepareCustomerDetails(applicationRequestDto, password));
				modelMapper.map(applicationRequestDto, applicationRequest);
				customerDao.saveApplication(applicationRequest);
				emailConfig.createAndSendMailContent(applicationRequest.getEmailId(), applicationRequest.getFullName(),
						password);
				log.info("In Service layer -> Application created and email sent to the customer ");
			} else {
				throw new CustomException("Customer Already Exists");
			}
		}
		log.info("In Service layer -> ended ");
	}

	private Customer prepareCustomerDetails(ApplicationRequestDto applicationRequestDto, String password) {
		Customer customer = new Customer();
		customer.setEmailId(applicationRequestDto.getEmailId());
		customer.setName(applicationRequestDto.getFullName());
		customer.setCustomerType("Customer");
		customer.setOccupation(applicationRequestDto.getOccupation());
		customer.setPassword(password);
		return customer;

	}

	@Override
	public List<ApplicationResponseDto> getAllApplicationDetails() {
		return customerDao.getAllApplicationDetails();
	}

}
