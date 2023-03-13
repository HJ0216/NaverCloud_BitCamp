package collection;

import java.util.Collection;
import java.util.ArrayList;


public class PersonMain {

	public Collection<PersonDTO> init() {
		PersonDTO aa = new PersonDTO("홍길동", 25);
		PersonDTO bb = new PersonDTO("프로도", 30);
		PersonDTO cc = new PersonDTO("라이언", 40);

		Collection<PersonDTO> col = new ArrayList<>();
		col.add(aa);
		col.add(bb);
		col.add(cc);
		return col; // return 1개
		
	}
	
	public static void main(String[] args) {
		PersonMain pm = new PersonMain();
		
		Collection<PersonDTO> col = pm.init(); // return col(arrayList): ref_address
		for(PersonDTO personDTO: col) {
			System.out.println(personDTO.getName() + "\t" + personDTO.getAge());
		}

		System.out.println(pm.init());
		// toString @Override
		
		for(PersonDTO personDTO: col) {
			System.out.println(personDTO);
		}

	}
	
}
