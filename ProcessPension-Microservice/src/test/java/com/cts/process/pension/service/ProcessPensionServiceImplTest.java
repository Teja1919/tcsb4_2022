package com.cts.process.pension.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.text.ParseException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cts.process.pension.controller.log;
import com.cts.process.pension.exception.NotFoundException;
import com.cts.process.pension.feignClient.PensionerDetailsClient;
import com.cts.process.pension.model.Bank;
import com.cts.process.pension.model.PensionDetail;
import com.cts.process.pension.model.PensionerDetail;
import com.cts.process.pension.model.PensionerInput;
import com.cts.process.pension.repository.PensionDetailsRepository;
import com.cts.process.pension.util.DateUtil;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class ProcessPensionServiceImplTest {

	@Autowired
	private ProcessPensionService processPensionService;

	@MockBean
	private PensionerDetailsClient pensionerDetailClient;

//	@MockBean
//	private PensionDisbursementClient pensionDisbursementClient;

	@MockBean
	private PensionDetailsRepository pensionDetailsRepository;

	@Test
	@DisplayName(value = "Method to test Pensioner Input Detail for Self Pension Type")
	void test_checkDetailsForCorrectPensionerInputForSelf() throws ParseException {
		log.info("TEST EXECUTION START - test_checkDetailsForCorrectPensionerInputForSelf()");
		PensionerInput input = new PensionerInput("Himanshu", DateUtil.parseDate("11-06-1996"), "LKJHG1996",
				"914253867543", "self");
		Bank bank = new Bank("SBI", 852258, "public");

		PensionerDetail details = new PensionerDetail("Himanshu", DateUtil.parseDate("11-06-1996"), "LKJHG1996", 100000,
				10000, "self", bank);

		assertTrue(processPensionService.checkdetails(input, details));
		assertEquals(852258, bank.getAccountNumber());
		assertNotNull(details);
		log.info("TEST EXECUTION END - test_checkDetailsForCorrectPensionerInputForSelf()");
	}

	@Test
	@DisplayName(value = "Method to test Pensioner Input Detail for Family Pension Type")
	void test_checkDetailsForCorrectPensionerInputForFamily() throws ParseException {
		log.info("TEST EXECUTION START - test_checkDetailsForCorrectPensionerInputForFamily()");
		PensionerInput input = new PensionerInput("Himanshu", DateUtil.parseDate("11-06-1996"), "QSCZS2233",
				"917346821937", "family");
		Bank bank = new Bank("SBI", 753951, "private");

		PensionerDetail details = new PensionerDetail("Himanshu", DateUtil.parseDate("11-06-1996"), "QSCZS2233", 100000,
				10000, "family", bank);

		assertTrue(processPensionService.checkdetails(input, details));
		log.info("TEST EXECUTION END - test_checkDetailsForCorrectPensionerInputForFamily()");
	}

	@Test
	@DisplayName(value = "Method to test Pensioner Input Detail for incorrect Self Pension Type")
	void test_checkDetailsForIncorrectPensionerInputForSelf() throws ParseException {
		log.info("TEST EXECUTION START - test_checkDetailsForIncorrectPensionerInputForSelf()");
		PensionerInput input = new PensionerInput("Himanshu", DateUtil.parseDate("11-06-1996"), "GHTY1593",
				"917346821937", "self");
		Bank bank = new Bank("SBI", 789987, "public");
		PensionerDetail details = new PensionerDetail("Himanshu", DateUtil.parseDate("11-06-1996"), "ERWE1421", 100000,
				10000, "self", bank);

		assertFalse(processPensionService.checkdetails(input, details));
		log.info("TEST EXECUTION END - test_checkDetailsForIncorrectPensionerInputForSelf()");
	}

	@Test
	@DisplayName(value = "Method to test Pensioner Input Detail for incorrect Family Pension Type")
	void test_checkDetailsForIncorrectPensionerInputForFamily() throws ParseException {
		log.info("TEST EXECUTION START - test_checkDetailsForIncorrectPensionerInputForFamily()");
		PensionerInput input = new PensionerInput("Himanshu", DateUtil.parseDate("11-06-1996"), "GHTY1593",
				"917346821937", "family");
		Bank bank = new Bank("SBI", 789987, "public");
		PensionerDetail details = new PensionerDetail("Himanshu", DateUtil.parseDate("11-06-1996"), "ERWE1421", 100000,
				10000, "family", bank);

		assertFalse(processPensionService.checkdetails(input, details));
		log.info("TEST EXECUTION END - test_checkDetailsForIncorrectPensionerInputForFamily()");
	}

	@Test
	@DisplayName(value = "Method to test Pension detail via PensionerDetail for Self Pension Type")
	void test_checkPensionDetailByPassingPensionerDetalisForSelf() throws ParseException {
		log.info("TEST EXECUTION START - test_checkPensionDetailByPassingPensionerDetalisForSelf()");
		Bank bank = new Bank("SBI", 789987, "public");
		PensionerDetail details = new PensionerDetail("Himanshu", DateUtil.parseDate("11-06-1996"), "ERWE1421", 100000,
				10000, "self", bank);

		PensionDetail actualDetail = processPensionService.calculatePensionAmount(details);

		assertEquals(90000, actualDetail.getPensionAmount());
		log.info("TEST EXECUTION END - test_checkPensionDetailByPassingPensionerDetalisForSelf()");
	}

	@Test
	@DisplayName(value = "Method to test Pension detail via PensionerDetail for Family Pension Type")
	void test_checkPensionDetailByPassingPensionerDetalisForFamily() throws ParseException {
		log.info("TEST EXECUTION START - test_checkPensionDetailByPassingPensionerDetalisForFamily()");
		Bank bank = new Bank("SBI", 789987, "public");
		PensionerDetail details = new PensionerDetail("Himanshu", DateUtil.parseDate("11-06-1996"), "ERWE1421", 100000,
				10000, "family", bank);

		PensionDetail actualDetail = processPensionService.calculatePensionAmount(details);

		assertEquals(60000, actualDetail.getPensionAmount());
		log.info("TEST EXECUTION END - test_checkPensionDetailByPassingPensionerDetalisForFamily()");
	}

	@Test
	@DisplayName(value = "Method to test getPensionDetails()")
	void test_checkGetPensionDetails() throws ParseException {
		log.info("TEST EXECUTION START - test_checkGetPensionDetails()");
		PensionerInput pensionerInput = new PensionerInput("Himanshu", DateUtil.parseDate("11-06-1996"), "ERWE1421",
				"917346821937", "family");

		Bank bank = new Bank("SBI", 789987, "public");

		PensionerDetail details_family = new PensionerDetail("Himanshu", DateUtil.parseDate("11-06-1996"), "ERWE1421",
				100000, 10000, "family", bank);

		// mock the feign client
		when(pensionerDetailClient.getPensionerDetailByAadhaar(pensionerInput.getAadhaarNumber()))
				.thenReturn(details_family);

		// get the actual result
		PensionDetail pensionDetailFamily = processPensionService.getPensionDetails(pensionerInput);

		// test cases
		assertEquals(60000, pensionDetailFamily.getPensionAmount());
		assertNotNull(pensionDetailFamily);
		log.info("TEST EXECUTION END - test_checkGetPensionDetails()");
	}

	@Test
	@DisplayName(value = "Method to test getPensionDetails() for Self Pension type")
	void test_checkGetPensionDetailsforSelf() throws ParseException {
		log.info("TEST EXECUTION START - test_checkGetPensionDetailsforSelf()");
		PensionerInput pensionerInput = new PensionerInput("Himanshu", DateUtil.parseDate("11-06-1996"), "ERWE1421",
				"917346821937", "self");

		Bank bank = new Bank("SBI", 789987, "public");

		PensionerDetail details_family = new PensionerDetail("Himanshu", DateUtil.parseDate("11-06-1996"), "ERWE1421",
				100000, 10000, "self", bank);

		// mock the feign client
		when(pensionerDetailClient.getPensionerDetailByAadhaar(pensionerInput.getAadhaarNumber()))
				.thenReturn(details_family);

		// get the actual result
		PensionDetail pensionDetailFamily = processPensionService.getPensionDetails(pensionerInput);

		// test cases
		assertEquals(90000, pensionDetailFamily.getPensionAmount());
		assertNotNull(pensionDetailFamily);
		log.info("TEST EXECUTION END - test_checkGetPensionDetailsforSelf()");
	}

	@Test
	@DisplayName(value = "Method to test testCheckDetails() with incorrect Pensioner Input data")
	void test_CheckDetailsincorrectPensionerInput() throws ParseException {
		log.info("TEST EXECUTION START - test_CheckDetailsincorrectPensionerInput()");
		// name, DOB, pan, aadhaar, type
		PensionerInput pensionerInput = new PensionerInput("Himanshu", DateUtil.parseDate("11-06-1996"), "ASDFG3457",
				"917346821937", "self");

		Bank bank = new Bank("SBI", 789987, "public");

		PensionerDetail details_family = new PensionerDetail("Himanshu", DateUtil.parseDate("11-06-1996"), "ERWE1421",
				100000, 10000, "self", bank);

		// mock the feign client
		when(pensionerDetailClient.getPensionerDetailByAadhaar(pensionerInput.getAadhaarNumber()))
				.thenReturn(details_family);

		NotFoundException ex = assertThrows(NotFoundException.class,
				() -> processPensionService.getPensionDetails(pensionerInput));

		assertEquals("INCORRECT DETAILS", ex.getMessage());
		assertNotNull(ex);
		log.info("TEST EXECUTION END - test_CheckDetailsincorrectPensionerInput()");
	}

}
