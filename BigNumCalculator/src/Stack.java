/*********************************************************************** 
  Student Name: Spencer Nguyen
  File Name: BigNumCalculator
  Assignment Number: Project 2
  Course: COP5416

  This class creates a Stack data structure specifically for use with ints.
  Please note following references:
  	Algorithms in Java (SedgeWick) 3rd Ed - Program 4.8
  	Dr. Coffey Lecture - Monday, March 22, 2021 at 3:33:12PM
************************************************************************/
public class Stack {

	private StackNode top;
	private int maxSize;
	private int numStacks;
	
	Stack(int size){
		top = null;
		this.maxSize = size;
		numStacks = 0;
	}
	
	private class StackNode{
		int num;
		StackNode next;
		
		StackNode(int num, StackNode next){
			this.num  = num;
			this.next = next;
		}
	}
	
	public boolean isEmpty() {
		return top == null;
	}
	
	public void push (int num) {
		this.top = new StackNode(num, this.top);
		numStacks++;
	}
	
	public int pop () {
		int topNum = top.num;
		StackNode tmp   = top.next;
		this.top = tmp;
		numStacks--;
		
		return topNum;
	}
	
	public int getNumStacks() {
		return this.numStacks;
	}


}
