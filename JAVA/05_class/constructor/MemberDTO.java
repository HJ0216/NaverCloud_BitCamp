package constructor;

public class MemberDTO { // Data Transfer Object (1인분 class)
						 // MemberVO Value Object
	private String name;
	private int age;
	private String phone;
	private String address;
	
	public MemberDTO() {}
	
	public MemberDTO(String name, int age, String phone, String address) {
		this.name = name;
		this.age = age;
		this.phone = phone;
		this.address = address;		
	}
//	setData	
//	public void setData(String name, int age, String phone, String address) {
//		this.name = name;
//		this.age = age;
//		this.phone = phone;
//		this.address = address;
//	}

	
	public void setName(String name) {this.name = name;}
	public void setAge(int age) {this.age = age;}
	public void setPhone(String phone) {this.phone = phone;}
	public void setAddress(String address) {this.address = address;}

	
	public String getName() 	{return name;}
	public int getAge() 		{return age;}
	public String getPhone() 	{return phone;}
	public String getAddress() 	{return address;}
		
	
}
