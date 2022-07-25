package com.cts.mfpe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.mfpe.model.Claim;
import com.cts.mfpe.service.ClaimServiceImpl;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class ClaimController {

	@Autowired
	ClaimServiceImpl service;
	
	@GetMapping("/health-check")
	public ResponseEntity<String> healthCheck() {
		return new ResponseEntity<>("auth-Ok", HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get Claim details",
			notes="Inputs are MemberId,PolicyId,ClaimId",
			response=Claim.class
					)
	@GetMapping("/getclaimstatus/{memberId}/{policyId}/{claimId}")
	public Claim  getClaimStatus(@PathVariable int policyId,@PathVariable int memberId,@PathVariable int claimId,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) throws Exception{
		return service.getClaimStatus(claimId, policyId, memberId,requestTokenHeader);
	}
	
	@ApiOperation(value = "Submit Claim",
			notes="User can Submit claim using this end-point",
			response=Claim.class
					)
	@PostMapping("/submitclaim/{memberId}/{policyId}")
	public Claim  submitClaim(@PathVariable int policyId,@PathVariable int memberId,@RequestBody Claim claim,
			@RequestHeader(value = "Authorization", required = false) String requestTokenHeader) throws Exception{
		log.debug(requestTokenHeader);
		return service.submitClaim(policyId, memberId, claim,requestTokenHeader);
	}
	
}
