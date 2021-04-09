import java.util.Objects;

/*********************************************************************** 
  Student Name: Spencer Nguyen
  File Name: BigNumCalculator
  Assignment Number: Project 2
  Course: COP5416


************************************************************************/
public class BigNumCalculator {
	
	private String expression;
	private String operation;
	private String operandOneString;
	private String operandTwoString;
	 String operandOneStringZeroes;
	 String operandTwoStringZeroes;
	
	private boolean add;
	private boolean subtract;
	private boolean operandOneIsNegative;// true is negative, false is positive
	private boolean operandTwoIsNegative;// true is negative, false is positive
	private boolean resultStackIsNegative;
	private boolean operandOneBigger;
	
	private int operandOneLength;
	private int operandTwoLength;
	
	Stack operandOneStack;
	Stack operandTwoStack;
	Stack resultStack;

	BigNumCalculator (String expression){
		this.expression = expression;
		this.resultStackIsNegative = false;
		
		parseString(this.expression);
		
		determineOperation();
		
		this.operandOneLength = determineOperandLength(operandOneString);
		this.operandTwoLength = determineOperandLength(operandTwoString);
		
		this.operandOneIsNegative = determineNegOrPos(operandOneString);
		this.operandTwoIsNegative = determineNegOrPos(operandTwoString);
		
		appendZeroes();
		
		this.operandOneStack = stringToStack(operandOneStringZeroes);
		this.operandTwoStack = stringToStack(operandTwoStringZeroes);

		determineAddOrSubtract();

		printCalculation();

	}
	
	private void parseString(String expression) {
		
		String[] expressionParts = this.expression.split(" ", 3);	
		this.operandOneString    = expressionParts[0];
		this.operation           = expressionParts[1];
		this.operandTwoString    = expressionParts[2];
		
	}
	
	private void determineOperation() {
		
		if(Objects.equals(this.operation, "-")) {
			this.add = false;
			this.subtract = true;
		}
		else {
			this.subtract = false;
			this.add = true;
		}
	}
	
	private int determineOperandLength(String operand) {
		
		int operandLength = 0;
		
		for (int i = 0; i < operand.length(); i++) {
			if(operand.charAt(i) != '-') {
				operandLength++;
			}
		}
		
		return operandLength;
	}
	
	private boolean determineNegOrPos(String operand) {
		
		if(operand.charAt(0) == '-' ) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	private void appendZeroes () {
		
		String zeroesString = "";
		
		if(this.operandOneString.charAt(0) == '-') {
			this.operandOneStringZeroes = "0" + this.operandOneString.substring(1);
		}
		else {
			this.operandOneStringZeroes = "0" + this.operandOneString;
		}
		
		if(this.operandTwoString.charAt(0) == '-') {
			this.operandTwoStringZeroes = this.operandTwoString.substring(1);
		}
		else {
			this.operandTwoStringZeroes = "0" + this.operandTwoString;
		}
				
		for (int i = 0; i < Math.abs(this.operandOneStringZeroes.length() - this.operandTwoStringZeroes.length()); i++) {
			zeroesString += "0";
		}
		
		if(this.operandOneLength > this.operandTwoLength) {
			this.operandTwoStringZeroes = zeroesString + this.operandTwoStringZeroes; 
		}
		else {
			this.operandOneStringZeroes = zeroesString + this.operandOneStringZeroes;
		}
	}
	
	private void determineBiggerOrSmaller() {
		
		if(this.operandOneLength > this.operandTwoLength) {
			this.operandOneBigger = true;
		}
		else if(this.operandTwoLength > this.operandOneLength){
			this.operandOneBigger = false;
		}
		else {
			for(int i = 0; i < operandOneLength; i++) {
				if(Character.getNumericValue(operandOneStringZeroes.charAt(i)) > Character.getNumericValue(operandTwoStringZeroes.charAt(i))) {
					this.operandOneBigger = true;
					break;
				}
				else if(Character.getNumericValue(operandOneStringZeroes.charAt(i)) > Character.getNumericValue(operandTwoStringZeroes.charAt(i))) {
					this.operandOneBigger = false;
					break;
				}
			}
		}
	}
	
	private Stack stringToStack (String operand) {
		
		Stack operandStack = new Stack(operand.length());

		for (int i = 0; i < operand.length(); i++) {
			operandStack.push(Character.getNumericValue(operand.charAt(i)));
		}
		
		return operandStack;
	}
	
	private void determineAddOrSubtract() {

		if(this.operandOneIsNegative == false && this.add && this.operandTwoIsNegative == false) {

			this.resultStack = calcSum(this.operandOneStack, this.operandTwoStack);
			this.resultStackIsNegative = false;
			
		}
		else if(this.operandOneIsNegative == true && this.add && this.operandTwoIsNegative == false){
			
			this.resultStack = calcDifference(this.operandOneStack, this.operandTwoStack);
			if(this.operandOneBigger == false) {
				this.resultStackIsNegative = false;
			}
			else {
				this.resultStackIsNegative = true; 
			}
		}
		else if(this.operandOneIsNegative == false && this.add && this.operandTwoIsNegative == true) {
			
			this.resultStack = calcDifference(this.operandOneStack, this.operandTwoStack);
			if(this.operandOneBigger == false) {
				this.resultStackIsNegative = true;
			}
			else {
				this.resultStackIsNegative = false; 
			}
		}
		else if(this.operandOneIsNegative == true && this.add && this.operandTwoIsNegative == true) {
			
			this.resultStack = calcSum(this.operandOneStack, this.operandTwoStack);
			this.resultStackIsNegative = true;
		}
		else if(this.operandOneIsNegative == false && this.subtract && this.operandTwoIsNegative == false) {
			
			this.resultStack = calcDifference(this.operandOneStack, this.operandTwoStack);
			if(this.operandOneBigger == false) {
				this.resultStackIsNegative = true;
			}
			else {
				this.resultStackIsNegative = false; 
			}
		}
		else if(this.operandOneIsNegative == true && this.subtract && this.operandTwoIsNegative == false) {
			
			this.resultStack = calcSum(this.operandOneStack, this.operandTwoStack);
			this.resultStackIsNegative = true;
		}
		else if(this.operandOneIsNegative == false && this.subtract && this.operandTwoIsNegative == true) {
			
			this.resultStack = calcSum(this.operandOneStack, this.operandTwoStack);
			this.resultStackIsNegative = false;
		}
		else if(this.operandOneIsNegative == true && subtract && this.operandTwoIsNegative == true) {
			
			this.resultStack = calcDifference(this.operandOneStack, this.operandTwoStack);
			if(this.operandOneBigger == false) {
				this.resultStackIsNegative = false;
			}
			else {
				this.resultStackIsNegative = true; 
			}
		}
	}
	

	public Stack calcSum(Stack operandOne, Stack operandTwo) {
		
		boolean carry = false;
		int tempSum = 0;
		int carryNum = 1;
		int stackSize = operandOne.getNumStacks();
		
		Stack sum = new Stack(operandOne.getNumStacks());
		Stack temp = new Stack(operandOne.getNumStacks());
		
		if(this.operandOneBigger == false) {
			temp = operandOne;
			operandOne = operandTwo;
			operandTwo = temp;
		}
		
		for(int i = 0; i < stackSize; i++) {
			
			if(carry == true) {
				tempSum = operandOne.pop() + operandTwo.pop() + carryNum;
				if (tempSum > 9) {
					carry   = true;
					tempSum = 10 % tempSum;
				}
				else {
					carry = false;
				}
			}
			else {
				tempSum = operandOne.pop() + operandTwo.pop();
				if (tempSum > 9) {
					carry   = true;
					tempSum = 10 % tempSum;
				}
				else {
					carry = false;
				}
			}		
			
			sum.push(tempSum);
		}

		return sum;
	}
	
	public Stack calcDifference(Stack operandOne, Stack operandTwo) {
		
		boolean borrow = false;
		int tempDiff = 0;
		int borrowNum = 1;
		int stackSize = operandOne.getNumStacks();
		
		Stack diff = new Stack(operandOne.getNumStacks());
		Stack temp = new Stack(operandOne.getNumStacks());
		
		if(this.operandOneBigger == false) {
			temp = operandOne;
			operandOne = operandTwo;
			operandTwo = temp;
		}
			
		for(int i = 0; i < stackSize; i++) {
			
			if(borrow == true) {
				if(operandOne.peekTop() < operandTwo.peekTop()) {
					borrow = true;
					tempDiff = (operandOne.pop() - borrowNum + 10 ) - operandTwo.pop();
				}
				else {
					tempDiff = (operandOne.pop() - borrowNum) - operandTwo.pop();
					borrow = false;
				}
			}
			else {
				if(operandOne.peekTop() < operandTwo.peekTop()) {
					borrow = true;
					tempDiff = (operandOne.pop() + 10) - operandTwo.pop();
					
				}
				else {
					tempDiff = operandOne.pop() - operandTwo.pop();
					borrow = false;
				}
			}
			
			diff.push(tempDiff);
		}

		return diff;
	}
	
	public void printCalculation() {
		
		int stackSize;
		
		String result = "";
		
		System.out.println(this.operandOneString);
		System.out.println(this.operation + " " + this.operandTwoString);
		System.out.println("----------------------------");
		
		if(resultStackIsNegative) {
			System.out.print("-");
		}
		
		while(resultStack.peekTop() == 0) {
			this.resultStack.pop();
		}
		
		stackSize = this.resultStack.getNumStacks();
		
		for(int i = 0; i < stackSize; i++) {
			result = result + Integer.toString(this.resultStack.pop());
		}
		
		System.out.println(result);
	}
	

}
