package collection;

import java.util.Stack;
import static java.lang.System.out;

public class StackMain {

	public static void main(String[] args) {
		String[] groupA = {"Korea", "Uzbekistan", "Kuwait", "Saudi"};
		
		Stack<String> stack = new Stack<>();
		for(int i=0; i<groupA.length; i++) {stack.push(groupA[i]);}
		// push: add
		while(!stack.isEmpty()) {
			out.println(stack.pop());
			// pop: delete
		}
	}
}

/*
push 순서: Korea, Uzbek, Kuwait, Saudi
pop 순서(Result): Saudi, Kuwait, Uzbek, Korea
*/
