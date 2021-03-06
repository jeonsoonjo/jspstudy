package dto;

public class MemberDTO {

	// field
	private int no;
	private String id;
	private String name;
	private String grade;
	private int point;
	
	// constructor
	public MemberDTO() {}
	public MemberDTO(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	// getter, setter
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
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
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	
}


