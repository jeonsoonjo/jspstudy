package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class StaffTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("test")
	void test() {
		System.out.println("test");
		/*
		 * StaffDAO staff = new StaffDAO(); if(StaffDAO.getInstance().insertStaff(Staff)
		 * == 0) { fail("없습니다."); } System.out.println("test");
		 */

	}

}


