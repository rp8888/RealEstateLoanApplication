package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

@Configuration
public class AmazonSimpleMailConfiguration {

	@Value("${smtp.access.key}")
	private String accessKey;

	@Value("${smtp.secret.key}")
	private String secretKey;

	@Value("${smtp.service.endpoint}")
	private String smtpServiceEndPoint;

	@Value("${aws.region.value}")
	private String awsRegion;

	@Bean
	public AmazonSimpleEmailService emailConfig() {
		return AmazonSimpleEmailServiceClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(smtpServiceEndPoint, awsRegion))
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
				.build();
	}

	public void createAndSendMailContent(String mailId, String name) {
		SendEmailRequest sendMailRequest = new SendEmailRequest()
				.withDestination(new Destination().withToAddresses(mailId)).withMessage(
						new Message().withSubject(new Content().withData("RealEstate Loan Confirmation"))
								.withSubject(new Content().withData("Hi " + name
										+ ", To view your application status Please login using your userId and password. Thank you for visiting"
										)));
		new AmazonSimpleMailConfiguration().emailConfig().sendEmail(sendMailRequest);
	}

}
