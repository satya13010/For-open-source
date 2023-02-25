package com.springSecurityForOrogeny.orogeny.utilty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import static com.auth0.jwt.algorithms.Algorithm.*;
import static com.springSecurityForOrogeny.orogeny.constant.SecurityConstant.*;//here now you don't have to use SecurityConstant everytime.
import static java.util.stream.StreamSupport.stream;


import com.springSecurityForOrogeny.orogeny.domain.UserPrincipal;
import org.springframework.stereotype.Component;

@Component
public class JWTTokenProvider {// we are gonn'a generate token here
	
	@Value("${jwt.secret}")//spring will go into properties file and search for the value
	private String SECRET;// we will have this in secure server, or in property file of the application
	//SECRET key is what used to encode and then decode.
	
	public String generateJwtToken(UserPrincipal userPrincipal) {
		//it will take user credentials from UserPrincipal and go to check in DB if its correct it will generate JWT token.
		//once we got info we will create user principal and get all the authorities of principal
		String[] claims = getClaimsFromUser(userPrincipal);//get all claims like it is admin.
		
		//let's generate JWT
		return JWT.create().withIssuer(GET_ARRAYS_LLC)//passing issuer of token like company name
				.withAudience(GET_ARRAYS_ADMINISTRATION)//audience defined like admin
				.withIssuedAt(new Date())//date of issued
				.withSubject(userPrincipal.getUsername()).withArrayClaim(AUTHORITIES, claims)//actual user ID not name of user, it can identify user on database then claims
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))//like 5 days
				.sign(HMAC512(SECRET.getBytes()));//algorithm to sign the signature
		

	}
	public List<GrantedAuthority> getAuthorities(String token){//get all the authorities of user
		String[] claims = getClaimsFromToken(token);
		return stream(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}
	
	public Authentication getAuthentication(String username, List<GrantedAuthority> authorities, HttpServletRequest request)
	{
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
				new UsernamePasswordAuthenticationToken(username, null, authorities);
		usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		return usernamePasswordAuthenticationToken;
	}
	
	public boolean isTokenValid(String username, String token)
	{
		JWTVerifier verifier = getJWTVerifier();//check see if token is valid
		return StringUtils.isNotEmpty(username) && !isTokenExpired(verifier, token);
	}

	public String getSubject(String token)
	{
		JWTVerifier verifier = getJWTVerifier();//check see if token is valid
		return verifier.verify(token).getSubject();
	}


	private boolean isTokenExpired(JWTVerifier verifier, String token)
	{
		Date expiration = verifier.verify(token).getExpiresAt();
		return expiration.before(new Date());
	}
	
	public String[] getClaimsFromToken(String token){
		JWTVerifier verifier = getJWTVerifier();
		return verifier.verify(token).getClaim(AUTHORITIES).asArray(String.class);
	}

	private JWTVerifier getJWTVerifier() {
		JWTVerifier verifier;
		try {
			Algorithm algorithm = HMAC512(SECRET);
			verifier = JWT.require(algorithm).withIssuer(GET_ARRAYS_LLC).build();
		} catch (JWTVerificationException exception) {
			throw new JWTVerificationException(TOKEN_CANNOT_BE_VERIFIED);//
		}
		return verifier;
	}
	private String[] getClaimsFromUser(UserPrincipal user) {
		//just
		List<String> authorities = new ArrayList<>();
		//now get all the authorities from principal
		for(GrantedAuthority grantedAuthority: user.getAuthorities()) {
			authorities.add(grantedAuthority.getAuthority());
		}
		return authorities.toArray(new String[0]);
		
	}
}
