package com.example.demo;

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

	@Bean
	public DynamoDBMapper mapper() {
		return new DynamoDBMapper(dynamoDbConfig());
	}
	
	private AmazonDynamoDB dynamoDbConfig() {
		return AmazonDynamoDBAsyncClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("dynamodb.eu-central-1.amazonaws.com","eu-central-1"))
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("AKIA44OXTLI5K27PJLHU","mrJu9dqHOQfbj3HAtUr2wTr6XJRx564Dja5QRyeW"))).build();
	}


}
