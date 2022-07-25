package com.cts.mfpe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.mfpe.exception.Unauthorized;
import com.cts.mfpe.feign.AuthClient;
import com.cts.mfpe.feign.ClaimClient;
import com.cts.mfpe.model.Claim;
import com.cts.mfpe.model.MemberPolicy;
import com.cts.mfpe.service.MemberServiceImpl;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MemberController {

	@Autowired
	MemberServiceImpl service;

	@Autowired
	AuthClient client;

	@Autowired
	ClaimClient claimClient;

	@ApiOperation(value = "View Bills",
			notes="User can view bills with MemberId,PolicyId as Inputs",
			response=List.class
					)
	@GetMapping("/viewbills/{memberId}/{policyId}")
	public List<MemberPolicy> viewBills(@PathVariable int policyId, @PathVariable int memberId,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) throws Exception {
		if (client.authorizeTheRequest(requestTokenHeader)) {
			return service.viewBills(policyId, memberId);
		} else {
			throw new Unauthorized();
			//throw new Exception("no bills");
		}
	}

	@ApiOperation(value = "Get Claim Status",
			notes="User Can view status of Claim",
			response=Claim.class
					)
	@GetMapping("/getclaimstatus/{memberId}/{policyId}/{claimId}")
	public Claim getClaimStatus(@PathVariable int policyId, @PathVariable int memberId, @PathVariable int claimId,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) {
		return claimClient.getClaimStatus(policyId, memberId, claimId, requestTokenHeader);
	}

	@ApiOperation(value = "Submit claim",
			notes="User can submit Claim",
			response=Boolean.class
					)
	@PostMapping("/submitclaim/{memberId}/{policyId}/{amount}")
	public String submitClaim(@PathVariable int policyId, @PathVariable int memberId,@PathVariable int amount,
			@RequestHeader(value = "Authorization", required = false) String requestTokenHeader) {
		System.out.print("ohooo");
		//log.debug(requestTokenHeader);
		log.debug("log",requestTokenHeader);
		//client.authorizeTheRequest(requestTokenHeader);
		Claim claim = new Claim();
		claim.setAmountClaimed(amount);
		claim.setMemberId(memberId);
		claimClient.submitClaim(policyId, memberId, claim, requestTokenHeader);
		return "true";
	}
	@GetMapping("/health-check")
	public ResponseEntity<String> healthCheck() {
		return new ResponseEntity<>("Member-Ok", HttpStatus.OK);
	}
}
