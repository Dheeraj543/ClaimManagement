package com.cts.authorization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.authorization.config.JwtTokenUtil;
import com.cts.authorization.exception.AuthorizationException;
import com.cts.authorization.model.JwtRequest;
import com.cts.authorization.service.JwtUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	/**
	 * @param authenticationRequest
	 * @return
	 * @throws AuthorizationException
	 * @throws Exception
	 */
	@PostMapping(value = "/authenticate")
	@ApiOperation(value = "Provide Token",
	notes="It validates username and password first and generates Token ",
	response=String.class
			)
	public ResponseEntity<String> createAuthenticationToken(@ApiParam(value="Provide username and password in body in JSON format",required=true) @RequestBody JwtRequest authenticationRequest)
			throws AuthorizationException {
		Authentication auth=authenticate(authenticationRequest.getUserName(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
		System.out.println(userDetails);
		final String token = jwtTokenUtil.generateToken(userDetails);
		//return token;//
		//ResponseEntity.ok(new JwtResponse(token));
		return ResponseEntity.ok(token);
	}

	private Authentication  authenticate(String userName, String password) throws AuthorizationException {
		try {
			System.out.println("Inside authenticate Method==================================");
			Authentication auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
			System.out.println("Authentication Successful.....");
			System.out.println(auth.getCredentials()+"+++++++++++++++++++++++++++++++++");
			return auth;
			
		} catch (DisabledException e) {
			throw new AuthorizationException("USER_DISABLED");
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new AuthorizationException("INVALID_CREDENTIALS");
		}
		
	}

	/**
	 * @param requestTokenHeader
	 * @return
	 */
	@PostMapping(value = "/authorize")
	@ApiOperation(value = "Validate Token",
	notes="Provided token in authorization header with \"Bearer[ ]token\"",
	response=boolean.class
			)
	public boolean authorizeTheRequest(
			@ApiParam(value="Provide token value in Authorization header in Bearer[ ]token format",required=true) @RequestHeader(value = "Authorization", required = true) String requestTokenHeader) {
		System.out.println("Inside authorize =============="+requestTokenHeader);
		String jwtToken = null;
		String userName = null;
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			System.out.println("JWT Tocken ======================="+jwtToken);
			try {
				userName = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException | ExpiredJwtException e) {
				return false;
			}
		}
		return userName != null;

	}

	@GetMapping("/health-check")
	public ResponseEntity<String> healthCheck() {
		return new ResponseEntity<>("auth-Ok", HttpStatus.OK);
	}

}