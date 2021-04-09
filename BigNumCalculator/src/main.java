
public class main {

	public static void main(String[] args) {
		
		
		String expression = new String("563 + 1000");
		
		
		BigNumCalculator test = new BigNumCalculator(expression);
		
		Stack testStack = new Stack(test.operandOneStack.getNumStacks());
		testStack = test.calcSum(test.operandOneStack,test.operandTwoStack);
		
		for (int i = 0; i < 10; i++) {
		System.out.println(testStack.pop());
		}

	}

}
