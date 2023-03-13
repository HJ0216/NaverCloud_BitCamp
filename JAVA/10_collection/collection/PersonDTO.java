package collection;

public class PersonDTO implements Comparable<PersonDTO> { // For Sorting
// Comparable<PersonDTO>: PersonDTO를 기준으로 정렬
	private String name;
	private int age;
	
	public PersonDTO(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public void setName(String name) {this.name=name;}
	public void setAge(int age) {this.age=age;}

	public String getName() {return name;}
	public int getAge() {return age;}
	
	@Override
	public String toString() { // age_ascending
		return name + " " + age;
	}
	
	@Override
	public int compareTo(PersonDTO pDTO) {
		if(this.age<pDTO.age) {return -1;}
		else if(this.age>pDTO.age) {return 1;}
		else return 0;
	}
	
}
