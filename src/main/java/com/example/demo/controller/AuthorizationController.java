package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.SignupDto;
import com.example.demo.dto.TokenDTO;
import com.example.demo.security.JwtHelper;
import com.example.demo.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/authorization")
@Api(value = "myAPI", tags = "Authorization Controller")
@ApiResponses(@ApiResponse(code = 200, message = "Peforms the application operations"))
@Slf4j
public class AuthorizationController {
	private static final Logger log = LoggerFactory.getLogger(AuthorizationController.class);

	@Autowired
	private CustomerService customerService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtHelper jwtHelper;

	@ApiOperation(value = "Customer Registration for the Application", response = List.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping("/register")
	public ResponseEntity<TokenDTO> register(@RequestBody SignupDto signupDto) {
		log.info("In CustomerController class for registration of the customer");
		TokenDTO tokenDto = customerService.saveCustomerDetails(signupDto);
		log.info("In CustomerController class for registration of the customer");
		return ResponseEntity.ok(tokenDto);
	}

	@ApiOperation(value = "Login service in to the application", response = String.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) throws Exception {
		log.info("In CustomerController class for registration of the customer");
		TokenDTO tokenDto = customerService.verifyLogin(loginDTO);
		log.info("In CustomerController class for registration of the customer");
		return ResponseEntity.ok(tokenDto);
	}

	@ApiOperation(value = "Logout service for the application", response = String.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping("logout")
	public ResponseEntity<?> logout(@RequestBody TokenDTO dto) {
		String refreshTokenString = dto.getRefreshToken();
		if (jwtHelper.validateRefreshToken(refreshTokenString)) {
			customerService.logOut(dto);
			return ResponseEntity.ok().build();
		}
		throw new BadCredentialsException("invalid token");
	}

}
