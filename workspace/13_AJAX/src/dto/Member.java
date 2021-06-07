package dto;

public class Member {

	// field
	private long no;
	private String id;
	private String name;
	private String gender;
	private String address;
	
	// getter, setter
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Member [no=" + no + ", id=" + id + ", name=" + name + ", gender=" + gender + ", address=" + address
				+ "]";
	}
	
}


