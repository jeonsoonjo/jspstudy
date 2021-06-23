package dto;

import java.sql.Date;

public class Staff {

	private String sno;
	private String name;
	private String dept;
	private Date regdate;
	
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "StaffDTO [sno=" + sno + ", name=" + name + ", dept=" + dept + ", regdate=" + regdate + "]";
	}
	
}


