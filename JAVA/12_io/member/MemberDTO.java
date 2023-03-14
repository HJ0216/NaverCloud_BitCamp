package member;

import java.io.Serializable;

public class MemberDTO implements Comparable<MemberDTO>, Serializable { 
	private String name;
	private int age;
	private String phone_num;
	private String address;
	
	public MemberDTO() {}
	
	public MemberDTO(String name, int age, String phone_num, String address) {
		this.name = name;
		this.age = age;
		this.phone_num = phone_num;
		this.address = address;
	}
	
	public void setName(String name) {this.name=name;}
	public void setAge(int age) {this.age=age;}
	public void setPhone_num(String phone_num) {this.phone_num=phone_num;}
	public void setAddress(String address) {this.address=address;}
	
	public String getName() {return name;}
	public int getAge() {return age;}
	public String getPhone_num() {return phone_num;}
	public String getAddress() {return address;}
	
	@Override
	public String toString() {
		return name		 + "\t"
			 + age 		 + "\t"
			 + phone_num + "\t"
			 + address;
	}

	@Override
	public int compareTo(MemberDTO mDTO) {
		return name.compareTo(mDTO.getName());
	}
	
}
