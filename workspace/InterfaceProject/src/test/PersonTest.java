package test;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.PersonDAO;


class PersonTest {

	int size = 0;
	
	@BeforeEach
	void setUp() throws Exception {
		// size = PersonDAO.getInstance().selectPersonList().size();
	}

	@Test
	void test() {
		// assertEquals(3, size, "등록된 사람은 1명이 아니다.");
		
		// 123456 민번을 가진 사람 검색하기
		assertNotNull(PersonDAO.getInstance().selectPersonBySno("123456"), "123456 주민번호는 없다.");
		
	}

}


