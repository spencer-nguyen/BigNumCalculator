/**
 * 
 * @author snguy
 *
 */
public class Stack {

	private StackNode top;
	
	Stack(int size){
		top = null;
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
	}
	
	public int pop () {
		int topNum = top.num;
		StackNode tmp   = top.next;
		this.top = tmp;
		return topNum;
	}
	
	
}
