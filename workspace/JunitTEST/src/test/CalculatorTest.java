package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.Calculator;

class CalculatorTest {

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("before");
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("after");
	}

	@Test
	@DisplayName("1 + 1 = 2") // test()을 의미한다
	void test() {
		Calculator calculator = new Calculator();
		// assertNull(calculator); // null인지 검사(null이면 통과)
		// assertNotNull(calculator); // null이 아닌지 검사(null이 아니면 통과)
		assertEquals(2, calculator.add(1, 1), "1+1=2 이어야 한다."); // expected: 기대한 값, actual: 실제로 발생한 값
		System.out.println("test");
		// fail("Not yet implemented"); // fail메시지 때문에 적색으로 보임
		
		/*
			if(DAO.getInstance().insert(person) == 0) { fail("삽입 실패"); }
		*/
		
		
	}
	
	

}


