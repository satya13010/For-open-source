package com.springSecurityForOrogeny.orogeny.constant;


public class SecurityConstant {//This class will generate JWT token for user

	public static final long EXPIRATION_TIME = 432_000_000;//expiration time to set in token like
	//5 days or 24 hrs so 5 days in milliseconds is 432000000 also from java 7 we can put _ for readability
	public static final String TOKEN_HEADER = "Bearer ";//whoever has this token I will just verify it
	//like ownership without any question I will take and verify it.
	public static final String JWT_TOKEN_HEADER = "Jwt-Token";//actual token header for JWT
	public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";//Just a error return
	public static final String GET_ARRAYS_LLC = "Get Arrays, LLC";//issuer of the token like facebook etc.
	public static final String GET_ARRAYS_ADMINISTRATION = "User Management Portal";//issued for
	//some audience like user or admin or finance or someone like president
	public static final String AUTHORITIES = "Authorities";
	public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page";//Token 
	//user provided but content is forbidden
	public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this page";
	public static final String OPTIONS_HTTP_METHOD = "OPTIONS";// This is like request method is not get, post
	// it is option we will let it get through because it will check what server will accept or not
	public static final String[] PUBLIC_URLS = {"/user/login", "/user/register", "/user/reserpassword/**", "/user/image/**"};//public urls
	//resetpassword/** here * means user will pass email here
	
}
