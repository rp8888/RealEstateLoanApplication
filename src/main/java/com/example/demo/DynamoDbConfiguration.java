package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
public class DynamoDbConfiguration {

	@Value("${Dynamo.DB.Service.Endpoint}")
	private String dynamoDbServiceEndPoint;

	@Value("${aws.region.value}")
	private String awsRegion;

	@Value("${Dynamo.DB.accessKey}")
	private String accessKey;

	@Value("${Dynamo.DB.secretKey}")
	private String secretKey;

	@Bean
	public DynamoDBMapper mapper() {
		return new DynamoDBMapper(dynamoDbConfig());
	}

	@Bean
	public AmazonDynamoDB dynamoDbConfig() {
		return AmazonDynamoDBAsyncClientBuilder.standard()
				.withEndpointConfiguration(
						new AwsClientBuilder.EndpointConfiguration(dynamoDbServiceEndPoint, awsRegion))
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
				.build();
	}

}
