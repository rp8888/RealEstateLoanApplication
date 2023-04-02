package com.example.demo.dao;

import com.example.demo.entities.RefreshToken;

public interface RefreshTokenDao {

	public void saveRefreshToken(RefreshToken refreshToken);

	public boolean findById(String tokenIdFromRefreshToken);

	public void deleteById(String tokeId);
}
