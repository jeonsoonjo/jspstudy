package ex07;
/*
	자바 빈(Bean)
	1. 자바 빈 개발 규약에 의해 생성하는 자바 클래스이다
	2. 객체 저장용으로 사용된다
	3. 개발 규약
		1) 반드시 특정 패키지에 소속되어야 한다(default 패키지는 안 된다)
		2) default 생성자를 사용할 수 있어야 한다
			(1) 생성자를 안 만든다(안 만들면 default 자동 사용 된다)
			(2) 필드를 이용한 생성자를 만드는 경우, default를 함께 만든다
			즉, 아예 안 만들거나 default와 field를 이용한 생성자를 함께 만들어야 한다
		3) getter/setter를 추가해야 한다
*/
public class Person {
		
	// field
	private String name;
	private int age;
	
	// constructor(default와 field를 이용한 생성자 활용)
	public Person() {};
	public Person(String name, int age) {
		this.name=name;
		this.age=age;
	}
	
	// getter/setter생성
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}
