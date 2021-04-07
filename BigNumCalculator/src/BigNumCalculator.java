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
	
	Stack operandOne;
	Stack operandTwo;
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
			this.operandOneStringZeroes = this.operandOneString.substring(1);
		}
		
		if(this.operandTwoString.charAt(0) == '-') {
			this.operandTwoStringZeroes = this.operandTwoString.substring(1);
		}
		
		this.operandOneStringZeroes = "0" + this.operandOneString;
		
		for (int i = 0; i < (operandOneStringZeroes.length() - operandTwoString.length() + 1); i++) {
			zeroesString += "0";
		}
		
		this.operandTwoStringZeroes = zeroesString + this.operandTwoStringZeroes; 
		
		System.out.println(this.operandOneStringZeroes);
		System.out.println(this.operandTwoStringZeroes);
	}
	
	
	
	private Stack stringToStack (String operand) {
		
		Stack operandStack = new Stack(operand.length());
		
		for (int i = 0; i < operand.length(); i++) {
			operandStack.push(Character.getNumericValue(operand.charAt(i)));
		}
		
		return operandStack;
	}
	
	
	
}
