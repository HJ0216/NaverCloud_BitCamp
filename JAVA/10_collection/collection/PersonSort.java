package collection;

import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class PersonSort {
	
	public static void main(String[] args) {
		String[] ar = {"orange", "apple", "banana", "pear", "peach", "applemango"};
		
		System.out.print("Before Sorting: ");
		for(String data : ar) {
			System.out.print(data + "  ");
		}
		System.out.println();
		
		Arrays.sort(ar);
		// String: char의 집합
		
		System.out.print("After Sorting: ");
		for(String data : ar) {
			System.out.print(data + "  ");
		}
		System.out.println("\n");
		
		
		//
		
		
		PersonDTO aa = new PersonDTO("홍길동", 25);
		PersonDTO bb = new PersonDTO("프로도", 40);
		PersonDTO cc = new PersonDTO("라이언", 35);
		// sort 시, age값만 sorting 되지 않도록 유의
		// obj value 전체가 sorting 되도록 유의

		ArrayList<PersonDTO> col = new ArrayList<>();
		col.add(aa);
		col.add(bb);
		col.add(cc);

		System.out.print("Before Sorting: ");
		for(PersonDTO personDTO : col) {
			System.out.print(personDTO + "  ");
		}	
		System.out.println("\n");
		
		Collections.sort(col);
		// Collection class sort() X
		
		System.out.print("After Sorting(age_ascending): ");
		for(PersonDTO personDTO : col) {
			System.out.print(personDTO + "  ");
		}
		System.out.println("\n");
		
		System.out.print("After Sorting(name_decending): ");

		Comparator<PersonDTO> comparator = new Comparator<PersonDTO>() {
		// Comparator 사용 시, Generics 필수
			@Override
			public int compare(PersonDTO pDTO1, PersonDTO pDTO2) { // 비교 대상이 pDTO
				return pDTO1.getName().compareTo(pDTO2.getName())*(-1);
			} // String type에 대해서도 compareTo method를 통해서 정렬 가능
			// Default: Ascending
			// Descending: pDTO2.getName().compareTo(pDTO1.getName())
			//			 : pDTO1.getName().compareTo(pDTO2.getName())*(-1)	
		};
		
		Collections.sort(col, comparator); // DTO 기준이 아닌, comparator로 정렬할 것을 안내
		
		for(PersonDTO personDTO : col) {
			System.out.print(personDTO + "  ");
		}
		System.out.println("\n");

		

	}
	
}
