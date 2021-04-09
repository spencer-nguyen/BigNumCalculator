/*********************************************************************** 
  Student Name: Spencer Nguyen
  File Name: BigNumCalculator
  Assignment Number: Project 2
  Course: COP5416

This class accepts an expression as a string as input and calculates
the result using stacks. The format of the expression but be entered as
such:

"operandOne + operandTwo" 
************************************************************************/
import java.util.Objects;

public class BigNumCalculator {
	
	private String expression;
	private String operation;
	private String operandOneString;
	private String operandTwoString;
	private String operandOneStringZeroes;// operand string with zeroes appended
	private String operandTwoStringZeroes;// operand string with zeroes appended
	
	private boolean add;
	private boolean subtract;
	private boolean operandOneIsNegative; // true is negative, false is positive
	private boolean operandTwoIsNegative; // true is negative, false is positive
	private boolean resultStackIsNegative;// if the result should be negative
	private boolean operandOneBigger;
	
	private int operandOneLength;
	private int operandTwoLength;
	
	private Stack operandOneStack;
	private Stack operandTwoStack;
	private Stack resultStack;

	BigNumCalculator (String expression){
		this.expression = expression;
		this.resultStackIsNegative = false;// initialize as false
		
		parseString(this.expression);
		
		determineOperation();
		
		this.operandOneLength = determineOperandLength(operandOneString);
		this.operandTwoLength = determineOperandLength(operandTwoString);
		
		this.operandOneIsNegative = determineNegOrPos(operandOneString);
		this.operandTwoIsNegative = determineNegOrPos(operandTwoString);
		
		appendZeroes();
		
		determineBiggerOrSmaller();
		
		this.operandOneStack = stringToStack(operandOneStringZeroes);
		this.operandTwoStack = stringToStack(operandTwoStringZeroes);

		determineAddOrSubtract();
	}
	
	/**
	 * This method parses the string into operandOne, operator, and operandTwo.
	 * @param expression
	 */
	private void parseString(String expression) {
		
		String[] expressionParts = this.expression.split(" ", 3);	
		this.operandOneString    = expressionParts[0];
		this.operation           = expressionParts[1];
		this.operandTwoString    = expressionParts[2];
		
	}
	
	/**
	 * This method determines if the operation is + or -.
	 */
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
	
	/**
	 * This method determines the length of the operands.
	 * It will be used to balance and append zeroes.
	 * @param operand
	 * @return
	 */
	private int determineOperandLength(String operand) {
		
		int operandLength = 0;
		
		// Do not account for the negative in the length.
		for (int i = 0; i < operand.length(); i++) {
			if(operand.charAt(i) != '-') {
				operandLength++;
			}
		}
		
		return operandLength;
	}
	
	/**
	 * This method determines if the operand is negative or positive.
	 * @param operand
	 * @return
	 */
	private boolean determineNegOrPos(String operand) {
		
		if(operand.charAt(0) == '-' ) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * This method appends zeroes to each string until they are both equal size.
	 */
	private void appendZeroes () {
		
		String zeroesString = "";
		
		/* If the operands are negative, change to zero. */
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
		
		
		/* Add zeroes to a string for the length of the larger operand - the smaller operand. */
		for (int i = 0; i < Math.abs(this.operandOneStringZeroes.length() - this.operandTwoStringZeroes.length()); i++) {
			zeroesString += "0";
		}
		
		
		/* Append the zeroes string to the smaller operand. */
		if(this.operandOneLength > this.operandTwoLength) {
			this.operandTwoStringZeroes = zeroesString + this.operandTwoStringZeroes; 
		}
		else {
			this.operandOneStringZeroes = zeroesString + this.operandOneStringZeroes;
		}
	}
	
	/**
	 * This method determines if the absolute value of an operand is larger than the other. 
	 */
	private void determineBiggerOrSmaller() {
		
		/* First look at length to determine. */
		if(this.operandOneLength > this.operandTwoLength) {
			this.operandOneBigger = true;
		}
		else if(this.operandTwoLength > this.operandOneLength){
			this.operandOneBigger = false;
		}
		
		/* If both operands are the same length, go through each digit and compare. */
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
	
	/**
	 * This method accepts a string operand and returns a stack with each digit as an int in the stack.
	 * @param operand
	 * @return
	 */
	private Stack stringToStack (String operand) {
		
		Stack operandStack = new Stack(operand.length());

		for (int i = 0; i < operand.length(); i++) {
			operandStack.push(Character.getNumericValue(operand.charAt(i)));
		}
		
		return operandStack;
	}
	
	/**
	 * This method determines based on the conditions whether to use the add or subtract methods.
	 * If an answer is negative, it is determined and prepended to the printed result.
	 */
	private void determineAddOrSubtract() {

		/* operandOne + operandTwo*/
		if(this.operandOneIsNegative == false && this.add && this.operandTwoIsNegative == false) {

			this.resultStack = calcSum(this.operandOneStack, this.operandTwoStack);
			this.resultStackIsNegative = false;
			
		}
		
		/* -operandOne + operandTwo*/
		else if(this.operandOneIsNegative == true && this.add && this.operandTwoIsNegative == false){
			
			this.resultStack = calcDifference(this.operandOneStack, this.operandTwoStack);
			if(this.operandOneBigger == false) {
				this.resultStackIsNegative = false;
			}
			else {
				this.resultStackIsNegative = true; 
			}
		}
		
		/* operandOne + -operandTwo*/
		else if(this.operandOneIsNegative == false && this.add && this.operandTwoIsNegative == true) {
			
			this.resultStack = calcDifference(this.operandOneStack, this.operandTwoStack);
			if(this.operandOneBigger == false) {
				this.resultStackIsNegative = true;
			}
			else {
				this.resultStackIsNegative = false; 
			}
		}
		
		/* -operandOne + -operandTwo*/
		else if(this.operandOneIsNegative == true && this.add && this.operandTwoIsNegative == true) {
			
			this.resultStack = calcSum(this.operandOneStack, this.operandTwoStack);
			this.resultStackIsNegative = true;
		}
		
		/* operandOne - operandTwo*/
		else if(this.operandOneIsNegative == false && this.subtract && this.operandTwoIsNegative == false) {
			
			this.resultStack = calcDifference(this.operandOneStack, this.operandTwoStack);
			if(this.operandOneBigger == false) {
				this.resultStackIsNegative = true;
			}
			else {
				this.resultStackIsNegative = false; 
			}
		}
		
		/* -operandOne - operandTwo*/
		else if(this.operandOneIsNegative == true && this.subtract && this.operandTwoIsNegative == false) {
			
			this.resultStack = calcSum(this.operandOneStack, this.operandTwoStack);
			this.resultStackIsNegative = true;
		}
		
		/* operandOne - -operandTwo*/
		else if(this.operandOneIsNegative == false && this.subtract && this.operandTwoIsNegative == true) {
			
			this.resultStack = calcSum(this.operandOneStack, this.operandTwoStack);
			this.resultStackIsNegative = false;
		}
		
		/* -operandOne - -operandTwo*/
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
	
	/**
	 * This method accepts two operand stacks and returns an added result as a stack.
	 * @param operandOne
	 * @param operandTwo
	 * @return
	 */
	public Stack calcSum(Stack operandOne, Stack operandTwo) {
		
		boolean carry = false;
		
		int tempSum  = 0;
		int carryNum = 1;// this is the number that gets carried
		
		Stack sum  = new Stack(operandOne.getNumStacks());
		Stack temp = new Stack(operandOne.getNumStacks());
		
		/* If operandTwo is bigger than swap. */
		if(this.operandOneBigger == false) {
			temp = operandOne;
			operandOne = operandTwo;
			operandTwo = temp;
		}
		
		while(operandOne.isEmpty() == false) {
			
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
	
	/**
	 * This method accepts two operands as stacks and returns a results stack of the difference.
	 * @param operandOne
	 * @param operandTwo
	 * @return
	 */
	public Stack calcDifference(Stack operandOne, Stack operandTwo) {
		
		boolean borrow = false;
		
		int tempDiff  = 0;
		int borrowNum = 1;// this is the num that gets borrowed
		
		Stack diff = new Stack(operandOne.getNumStacks());
		Stack temp = new Stack(operandOne.getNumStacks());
		
		/* If operandTwo is bigger, then swap places. */
		if(this.operandOneBigger == false) {
			temp = operandOne;
			operandOne = operandTwo;
			operandTwo = temp;
		}
			
		while(operandOne.isEmpty() == false) {
			
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
	
	/**
	 * This method prints the operands, operations, and results stack.
	 */
	public void printCalculation() {
				
		String result = "";
		
		System.out.println(this.operandOneString);
		System.out.println(this.operation + " " + this.operandTwoString);
		System.out.println("----------------------------");
		
		if(resultStackIsNegative) {
			System.out.print("-");
		}
		
		/* Pop the excess zeroes off the stack. */
		while(resultStack.peekTop() == 0) {
			this.resultStack.pop();
		}
				
		while(resultStack.isEmpty() == false) {
			result = result + Integer.toString(this.resultStack.pop());
		}
		
		System.out.println(result);
	}
}
