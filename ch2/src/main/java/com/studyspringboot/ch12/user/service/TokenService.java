package com.studyspringboot.ch12.user.service;

import com.studyspringboot.ch12.user.dao.TokenDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import java.util.Date;

public class TokenService implements PersistentTokenRepository {
//	@Autowired
//	TokenDao tokenDao;

	@Override
	public void createNewToken(PersistentRememberMeToken token) {

	}

	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {

	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		return null;
	}

	@Override
	public void removeUserTokens(String username) {

	}
}
