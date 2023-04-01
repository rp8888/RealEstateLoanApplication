package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ApplicationRequestDto;
import com.example.demo.dto.ApplicationResponseDto;
import com.example.demo.dto.CustomerDto;
import com.example.demo.exception.CustomException;
import com.example.demo.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Api(value = "myAPI", tags = "My API")
@ApiResponses(@ApiResponse(code = 200, message = "Peforms the application operations"))
@Slf4j
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

	@ApiOperation(value = "Get All Application Details", response = List.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(path = "/getAllApplicationDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ApplicationResponseDto>> getAllApplicationDetails() {
		log.info("In CustomerController class for fetching all the applciation details");
		List<ApplicationResponseDto> applicationResponseDto = customerService.getAllApplicationDetails();
		log.info("In CustomerController -> Fetched all the application details and the count is ",
				applicationResponseDto.size());
		return new ResponseEntity<>(applicationResponseDto, HttpStatus.OK);
	}

	/*
	 * @ApiOperation(value = "Save customer details", response = String.class)
	 * 
	 * @ApiResponses({ @ApiResponse(code = 200, message =
	 * "Success"), @ApiResponse(code = 400, message = "Bad Request"),
	 * 
	 * @ApiResponse(code = 401, message = "Unauthorized"),
	 * 
	 * @ApiResponse(code = 500, message = "Internal Server Error") })
	 * 
	 * @PostMapping("/saveCustomer") public ResponseEntity<String>
	 * saveAdmin(@RequestBody CustomerDto customerdto) {
	 * log.info("In CustomerController class for saving the customer details"); if
	 * (null != customerdto) { customerService.saveCustomerDetails(customerdto);
	 * log.info("In CustomerController -> Customer Registered Successfully"); return
	 * new ResponseEntity<>("Customer Registered Successfully", HttpStatus.OK); }
	 * return new ResponseEntity<String>("Request Cannot Be Empty",
	 * HttpStatus.BAD_REQUEST); }
	 */

	@ApiOperation(value = "Save RealEstate Application Loan", response = String.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping("/saveApplicationDetails")
	public ResponseEntity<String> saveApplicationDetails(@RequestBody ApplicationRequestDto applicationRequestDto) throws CustomException {
		log.info("In CustomerController class for saving the application details");
		if (null != applicationRequestDto) {
			customerService.applicationDetails(applicationRequestDto);
			log.info("In CustomerController -> Application Submitted Successfully");
			return new ResponseEntity<>("Application Submitted Successfully", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Please Fill In The Application Details", HttpStatus.BAD_REQUEST);
	}
	
	

}
