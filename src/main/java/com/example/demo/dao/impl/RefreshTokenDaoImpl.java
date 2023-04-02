package com.example.demo.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDeleteExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.example.demo.dao.RefreshTokenDao;
import com.example.demo.entities.RefreshToken;

@Repository
public class RefreshTokenDaoImpl implements RefreshTokenDao {

	private static final Logger log = LoggerFactory.getLogger(RefreshTokenDaoImpl.class);
	@Autowired
	private DynamoDBMapper mapper;

	@Override
	public void saveRefreshToken(RefreshToken refreshToken) {
		mapper.save(refreshToken);
	}

	@Override
	public boolean findById(String tokenIdFromRefreshToken) {
		DynamoDBScanExpression expression = new DynamoDBScanExpression();
		expression.addFilterCondition("id", new Condition().withComparisonOperator(ComparisonOperator.EQ)
                .withAttributeValueList(new AttributeValue().withS(tokenIdFromRefreshToken)));
		RefreshToken refreshToken = mapper.scan(RefreshToken.class, expression).get(0);
		log.info("In RefreshTokenDaoImpl fetching refresk token " + tokenIdFromRefreshToken);

		return null!=refreshToken?true:false;
	}

	@Override
	public void deleteById(String tokeId) {
		DynamoDBDeleteExpression expression = new DynamoDBDeleteExpression();
		expression.addExpressionAttributeNamesEntry("id",tokeId);
		mapper.delete(RefreshToken.class, expression);
	}

}
