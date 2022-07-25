package com.cts.mfpe.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.cts.mfpe.service.ClaimServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
class ClaimControllerTest{
	@Autowired
	private MockMvc mockMvc;
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	@MockBean
	ClaimServiceImpl service;
	@Test
	void testGetClaimStatus() throws Exception {
		int memberId=1;
		int policyId=1;
		int claimId=1;
		this.mockMvc.perform(get("/health-check"))
		.andExpect(MockMvcResultMatchers.status().isOk());
		//fail("Not yet implemented");
	}

//	@Test
//	void testSubmitClaim() {
//		fail("Not yet implemented");
//	}

}
