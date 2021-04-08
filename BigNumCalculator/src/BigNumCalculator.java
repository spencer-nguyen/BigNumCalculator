import java.io.File;

/*********************************************************************** 
  Student Name: Spencer Nguyen
  File Name: BigNumCalculator
  Assignment Number: Project 2
  Course: COP5416


************************************************************************/
public class BigNumCalculator {
	
	String expression;
	String operation;
	String operandOneString;
	String operandTwoString;
	String operandOneStringZeroes;
	String operandTwoStringZeroes;
	
	boolean operandOneIsNegative;// true is negative, false is positive
	boolean operandTwoIsNegative;// true is negative, false is positive
	
	int operandOneLength;
	int operandTwoLength;
	
	Stack operandOneStack;
	Stack operandTwoStack;
	Stack sumOrDifference;

	BigNumCalculator (String expression){
		
		this.operandOneLength = 0;
		this.operandTwoLength = 0;
		this.expression = expression;
		
		parseString(this.expression);
		
		operandOneLength = determineOperandLength(operandOneString);
		operandTwoLength = determineOperandLength(operandTwoString);
		
		if(operandTwoLength > operandOneLength) {
			swapOperands();
		}
		
		operandOneIsNegative = determineNegOrPos(operandOneString);
		operandTwoIsNegative = determineNegOrPos(operandTwoString);
		
		appendZeroes();
		
		operandOneStack = stringToStack(operandOneStringZeroes);
		operandTwoStack = stringToStack(operandTwoStringZeroes);
		
		sumOrDifference = calcSum(operandOneStack, operandTwoStack);

	}
	
	private void parseString (String expression) {
		
		String[] expressionParts = this.expression.split(" ", 3);	
		this.operandOneString    = expressionParts[0];
		this.operation           = expressionParts[1];
		this.operandTwoString    = expressionParts[2];
		
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
	
	private void swapOperands() {
		
		String temp = "";
		
		temp = this.operandTwoString;
		this.operandTwoString = this.operandOneString;
		this.operandOneString = temp;
		
	}
	
	private boolean determineNegOrPos(String operand) {
		
		if(operand.charAt(0) == '-' ) {
			return true;
		}else {
			return false;
		}
		
	}
	
	private void appendZeroes() {
		
		String zeroesString = "";
		
		if(this.operandOneString.charAt(0) == '-') {
			this.operandOneStringZeroes = "0" + this.operandOneString.substring(1);
		}else {
			this.operandOneStringZeroes = "0" + this.operandOneString;
		}
		
		if(this.operandTwoString.charAt(0) == '-') {
			this.operandTwoStringZeroes = this.operandTwoString.substring(1);
		}else {
			this.operandTwoStringZeroes = this.operandTwoString;
		}
				
		for (int i = 0; i < (operandOneStringZeroes.length() - operandTwoStringZeroes.length()); i++) {
			zeroesString += "0";
		}
		
		this.operandTwoStringZeroes = zeroesString + this.operandTwoStringZeroes; 
	}
	
	private Stack stringToStack (String operand) {
		
		Stack operandStack = new Stack(operand.length());

		for (int i = 0; i < operand.length(); i++) {
			operandStack.push(Character.getNumericValue(operand.charAt(i)));
		}
		
		return operandStack;
	}
	
	public Stack calcSum (Stack operandOne, Stack operandTwo) {
		
		boolean carry = false;
		int tempSum;
		int carryNum = 1;
		int stackSize = operandOne.getNumStacks();
		
		Stack sum = new Stack(operandOne.getNumStacks());
			
		for(int i = 0; i < stackSize; i++) {
			
			if(carry == true) {
				tempSum = operandOne.pop() + operandTwo.pop() + carryNum;
				if (tempSum > 9) {
					carry   = true;
					tempSum = tempSum - 10;
				}else {
					carry = false;
				}
			}else {
				tempSum = operandOne.pop() + operandTwo.pop();
				if (tempSum > 9) {
					carry   = true;
					tempSum = tempSum - 10;
				}else {
					carry = false;
				}
			}		
			
			sum.push(tempSum);
			System.out.println(tempSum);
		}

		
		return sum;
	}
	
	
	
}
