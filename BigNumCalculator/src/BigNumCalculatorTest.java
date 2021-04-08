import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BigNumCalculatorTest {
	
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
		
		BigNumCalculator testExpression11 = new BigNumCalculator("3003 + -90");
		assertEquals(testExpression11.operandOneStringZeroes, "03003");
		assertEquals(testExpression11.operandTwoStringZeroes, "00090");
		
		BigNumCalculator test = new BigNumCalculator("2345 + 123");
		assertEquals(test.operandOneStringZeroes, "02345");
		assertEquals(test.operandTwoStringZeroes, "00123");

		

	}
	/*@Test
	void stringToStacktest() {
		
		BigNumCalculator test2 = new BigNumCalculator("2345 + 123");
		
		assertEquals(test2.operandOneStack.pop(), 5);
		assertEquals(test2.operandOneStack.pop(), 4);
		assertEquals(test2.operandOneStack.pop(), 3);
		
		assertEquals(test2.operandTwoStack.pop(), 3);
		assertEquals(test2.operandTwoStack.pop(), 2);
		assertEquals(test2.operandTwoStack.pop(), 1);
		
		BigNumCalculator test3 = new BigNumCalculator("7682 + -18239");
		
		assertEquals(test3.operandOneStack.pop(), 9);
		assertEquals(test3.operandOneStack.pop(), 3);
		assertEquals(test3.operandOneStack.pop(), 2);
		
		assertEquals(test3.operandTwoStack.pop(), 2);
		assertEquals(test3.operandTwoStack.pop(), 8);
		assertEquals(test3.operandTwoStack.pop(), 6);
		
		BigNumCalculator test4 = new BigNumCalculator("-945783 + -1823");
		
		assertEquals(test4.operandOneStack.pop(), 3);
		assertEquals(test4.operandOneStack.pop(), 8);
		assertEquals(test4.operandOneStack.pop(), 7);
		
		assertEquals(test4.operandTwoStack.pop(), 3);
		assertEquals(test4.operandTwoStack.pop(), 2);
		assertEquals(test4.operandTwoStack.pop(), 8);
		
		BigNumCalculator test5 = new BigNumCalculator("-945783 - -1823");
		
		assertEquals(test5.operandOneStack.pop(), 3);
		assertEquals(test5.operandOneStack.pop(), 8);
		assertEquals(test5.operandOneStack.pop(), 7);
		
		assertEquals(test5.operandTwoStack.pop(), 3);
		assertEquals(test5.operandTwoStack.pop(), 2);
		assertEquals(test5.operandTwoStack.pop(), 8);
		
		BigNumCalculator test6 = new BigNumCalculator("945783 - -1823");
		
		assertEquals(test6.operandOneStack.pop(), 3);
		assertEquals(test6.operandOneStack.pop(), 8);
		assertEquals(test6.operandOneStack.pop(), 7);
		
		assertEquals(test6.operandTwoStack.pop(), 3);
		assertEquals(test6.operandTwoStack.pop(), 2);
		assertEquals(test6.operandTwoStack.pop(), 8);
		
	}*/
	
	@Test
	void sumTest() {
		
		BigNumCalculator testExpression12 = new BigNumCalculator("-39 + 2874");
		assertEquals(testExpression12.sumOrDifference.pop(), 0);
		assertEquals(testExpression12.sumOrDifference.pop(), 2);

		BigNumCalculator testExpression13 = new BigNumCalculator("1 + 1");
		assertEquals(testExpression13.sumOrDifference.pop(), 0);
		assertEquals(testExpression13.sumOrDifference.pop(), 2);



		

	}
}
