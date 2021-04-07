import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BigNumCalculatorTest {

/*	@Test
	void stringToStacktest() {
		
		BigNumCalculator test = new BigNumCalculator("12345");
		
		assertEquals(test.operandOne.pop(), 5);
		assertEquals(test.operandOne.pop(), 4);
		assertEquals(test.operandOne.pop(), 3);
		assertEquals(test.operandOne.pop(), 2);
		assertEquals(test.operandOne.pop(), 1);
	}*/
	
	@Test
	void parseStringTest() {
		
		BigNumCalculator testExpression1 = new BigNumCalculator("-2 + -4");
		assertEquals(testExpression1.operandOneString, "-2");
		assertEquals(testExpression1.operation, "+");
		assertEquals(testExpression1.operandTwoString, "-4");
		
		BigNumCalculator testExpression2 = new BigNumCalculator("28 + 34");
		assertEquals(testExpression2.operandOneString, "28");
		assertEquals(testExpression2.operation, "+");
		assertEquals(testExpression2.operandTwoString, "34");
		
		BigNumCalculator testExpression3 = new BigNumCalculator("-2 - -4");
		assertEquals(testExpression3.operandOneString, "-2");
		assertEquals(testExpression3.operation, "-");
		assertEquals(testExpression3.operandTwoString, "-4");
	}
	
	@Test
	void negOrPosTest() {
		
		BigNumCalculator testExpression4 = new BigNumCalculator("-2 + 4");
		assertEquals(testExpression4.operandOneIsNegative, true);
		assertEquals(testExpression4.operandTwoIsNegative, false);
		
		BigNumCalculator testExpression5 = new BigNumCalculator("-20003449933443 + -44388383483843");
		assertEquals(testExpression5.operandOneIsNegative, true);
		assertEquals(testExpression5.operandTwoIsNegative, true);
		

	}
	
	@Test
	void opLengthTest() {
		
		BigNumCalculator testExpression6 = new BigNumCalculator("-2 + 4");
		assertEquals(testExpression6.operandOneLength, 1);
		assertEquals(testExpression6.operandTwoLength, 1);
		
		BigNumCalculator testExpression7 = new BigNumCalculator("20003449933443 + -4438838348384");
		assertEquals(testExpression7.operandOneLength, 14);
		assertEquals(testExpression7.operandTwoLength, 13);
		

	}
	
	@Test
	void swapTest() {
		
		BigNumCalculator testExpression8 = new BigNumCalculator("-2 + 400");
		assertEquals(testExpression8.operandOneString, "400");
		assertEquals(testExpression8.operandTwoString, "-2");
		
		BigNumCalculator testExpression9 = new BigNumCalculator("3003 + -90");
		assertEquals(testExpression9.operandOneString, "3003");
		assertEquals(testExpression9.operandTwoString, "-90");
		

	}
	
	@Test
	void zeroesTest() {
		
		BigNumCalculator testExpression10 = new BigNumCalculator("-39 + 2874");
		assertEquals(testExpression10.operandOneStringZeroes, "02874");
		assertEquals(testExpression10.operandTwoStringZeroes, "00039");
		
		//BigNumCalculator testExpression11 = new BigNumCalculator("3003 + -90");
		//assertEquals(testExpression11.operandOneStringZeroes, "03003");
		//assertEquals(testExpression11.operandTwoStringZeroes, "00090");
		

	}
	
}
