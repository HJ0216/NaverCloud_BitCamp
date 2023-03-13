package collection;

import java.util.LinkedList;
import static java.lang.System.out;

public class QueueMain {

	public static void main(String[] args) {
		String[] item = {"Sonata", "Rexton", "Jaguar"};
		LinkedList<String> q = new LinkedList<>();
		
		for(String n : item) {q.offer(n);} // offer: add
		
		out.println("Size of q: " + q.size() + "\n");
		String data="";
		
		while((data=q.poll()) != null) { // poll: q.delete data.add.q_data
			out.println(data + " delete");
			out.println("Size of q: " + q.size() + "\n");
		}
	}
}

/*
offer 순서: Sonata, Rexton, Jaguar
poll 순서(Result): Sonata, Rexton, Jaguar
*/
