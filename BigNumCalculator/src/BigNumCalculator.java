import java.io.File;

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
	private String operandOneStringZeroes;
	private String operandTwoStringZeroes;
	
	private boolean operandOneIsNegative;// true is negative, false is positive
	private boolean operandTwoIsNegative;// true is negative, false is positive
	private boolean resultStackIsNegative;
	
	private int operandOneLength;
	private int operandTwoLength;
	
	Stack operandOneStack;
	Stack operandTwoStack;
	Stack resultStack;

	BigNumCalculator (String expression){
		
		this.operandOneLength = 0;
		this.operandTwoLength = 0;
		this.expression = expression;
		this.resultStackIsNegative = false;
		
		parseString(this.expression);
		
		this.operandOneLength = determineOperandLength(operandOneString);
		this.operandTwoLength = determineOperandLength(operandTwoString);
		
		this.operandOneIsNegative = determineNegOrPos(operandOneString);
		this.operandTwoIsNegative = determineNegOrPos(operandTwoString);
		
		appendZeroes();
		
		this.operandOneStack = stringToStack(operandOneStringZeroes);
		this.operandTwoStack = stringToStack(operandTwoStringZeroes);
		
		determineAddOrSubtract();
		

	}
	
	private void parseString(String expression) {
		
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
		
		boolean tempBool;
		//String tempString;
		Stack temp = new Stack(operandOneStack.getNumStacks());
	
		/*tempString = this.operandOneString; 
		this.operandOneString = this.operandTwoString;
		this.operandOneString = tempString;*/
		
		temp = this.operandOneStack;
		this.operandOneStack = this.operandTwoStack;
		this.operandTwoStack = temp;
		
		tempBool = this.operandOneIsNegative;
		this.operandOneIsNegative = this.operandTwoIsNegative;
		this.operandTwoIsNegative = tempBool;
		
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
	
	private void determineAddOrSubtract() {
		
		if(this.operandOneIsNegative == false && this.operation == "+" && this.operandTwoIsNegative == false) {
			
			this.resultStack = calcSum(this.operandOneStack, this.operandTwoStack);
			this.resultStackIsNegative = false;
		}
		else if(this.operandOneIsNegative == true && this.operation == "+" && this.operandTwoIsNegative == false){
			
			this.resultStack = calcDifference(this.operandOneStack, this.operandTwoStack);
			if(Math.abs(Integer.parseInt(operandTwoString)) > Math.abs(Integer.parseInt(operandOneString))) {
				this.resultStackIsNegative = false;
			}
			else {
				this.resultStackIsNegative = true; 
			}
		}
		else if(this.operandOneIsNegative == false && this.operation == "+" && this.operandTwoIsNegative == true) {
			
			this.resultStack = calcDifference(this.operandOneStack, this.operandTwoStack);
			if(Math.abs(Integer.parseInt(operandTwoString)) > Math.abs(Integer.parseInt(operandOneString))) {
				this.resultStackIsNegative = true;
			}
			else {
				this.resultStackIsNegative = false; 
			}
		}
		else if(this.operandOneIsNegative == false && this.operation == "-" && this.operandTwoIsNegative == false) {
			
			this.resultStack = calcDifference(this.operandOneStack, this.operandTwoStack);
			if(Math.abs(Integer.parseInt(operandTwoString)) > Math.abs(Integer.parseInt(operandOneString))) {
				this.resultStackIsNegative = true;
			}
			else {
				this.resultStackIsNegative = false; 
			}
		}
		else if(this.operandOneIsNegative == true && this.operation == "-" && this.operandTwoIsNegative == false) {
			
			this.resultStack = calcSum(this.operandOneStack, this.operandTwoStack);
			this.resultStackIsNegative = true;
		}
		else if(this.operandOneIsNegative == false && this.operation == "-" && this.operandTwoIsNegative == true) {
			
			this.resultStack = calcSum(this.operandOneStack, this.operandTwoStack);
			this.resultStackIsNegative = false;
		}
		else if(this.operandOneIsNegative == true && this.operation == "-" && this.operandTwoIsNegative == true) {
			
			this.resultStack = calcDifference(this.operandOneStack, this.operandTwoStack);
			if(Math.abs(Integer.parseInt(operandTwoString)) > Math.abs(Integer.parseInt(operandOneString))) {
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
		
		if(Math.abs(Integer.parseInt(operandOneString)) > Math.abs(Integer.parseInt(operandOneString))) {
			swapOperands();
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
		
		int stackSize = resultStack.getNumStacks();
		String result = "";
		
		System.out.println(this.operandOneString);
		System.out.println(this.operation + " " + this.operandTwoString);
		System.out.println("----------------------------");
		
		if(resultStackIsNegative) {
			System.out.print("-");
		}
		
		for(int i = 0; i < stackSize - 1; i++) {
			result = Integer.toString(this.resultStack.pop()) + result;
		}
		
		System.out.println(result);
	}
	

}
