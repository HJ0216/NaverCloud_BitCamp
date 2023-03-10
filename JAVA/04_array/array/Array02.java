package array;

public class Array02 {

	public static void main(String[] args) {
		String[] ar_zoo = {"Tiger", "Lion", "Elephant", "Giraffa", "Hyena", "Gibbon", "Gorilla"};
		
		for(int i=0; i<ar_zoo.length; i++) {
			System.out.println("ar[" + i + "] = " + ar_zoo[i]);
			System.out.println("ar[" + i + "].length() " + ar_zoo[i].length());
			// Array.length, String.length()
			System.out.println("First Letter: " + ar_zoo[i].charAt(0));
			System.out.println("Last Letter: " + ar_zoo[i].charAt(ar_zoo[i].length()-1));
			// 총 글자수: ar_zoo[i].length(), array_start_num=0이므로 -1
			System.out.println();
		} // for
		
		System.out.println("Extension for");
		for(String animal : ar_zoo) {
			System.out.println(animal);
		}
	}
}
