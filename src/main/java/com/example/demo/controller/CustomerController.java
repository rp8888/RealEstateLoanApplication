package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CustomerDto;
import com.example.demo.entities.Customer;
import com.example.demo.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(value = "myAPI", tags = "My API")
public class CustomerController {;
	@Autowired
	CustomerService customerService;

	@ApiOperation(value = "Get All Customer", response = List.class)
	 @GetMapping("/getCustomerDetails")
	    public ResponseEntity<List<Customer>> getCustomer() {
		 List<Customer> customer = customerService.getCustomerDetails();
	        return new ResponseEntity<>(customer,HttpStatus.OK);
	    }
	 
	@ApiOperation(value = "Save customer details", response = String.class)
	 @PostMapping("/saveCustomer")
	    public ResponseEntity<String> saveCustomer(@RequestBody CustomerDto customerdto) {
		 customerService.saveCustomerDetails(customerdto);
	        return new ResponseEntity<String>(HttpStatus.OK);
	    }


}
