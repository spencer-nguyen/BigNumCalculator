import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StackTest {

	@Test
	void test() {
		Stack intStack = new Stack(5);
		intStack.push(1);
		intStack.push(2);
		intStack.push(3);
		intStack.push(4);
		intStack.push(5);
		
		assertEquals(intStack.pop(),5);
		assertEquals(intStack.pop(),4);
		assertEquals(intStack.pop(),3);
		assertEquals(intStack.pop(),2);
		assertEquals(intStack.pop(),1);
		assertEquals(intStack.isEmpty(),true);
	}

}
