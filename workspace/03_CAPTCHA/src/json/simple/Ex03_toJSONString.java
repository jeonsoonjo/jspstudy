package json.simple;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Ex03_toJSONString {

	public static void main(String[] args) {
		
		// 단일 데이터를 JSON으로 생성하기
		/*
			JSONObject obj=new JSONObject();
			
			obj.put("name", "브레드"); // 데이터 저장하는 메소드 -> put
			obj.put("age", 50);
			
			String requestData=obj.toJSONString();
			System.out.println(requestData);
		*/
		
		// 복수 데이터를 JSONArray 배열로 생성하기
		JSONObject obj1=new JSONObject();
		obj1.put("name", "브레드");
		obj1.put("age", 50);
		
		JSONObject obj2=new JSONObject();
		obj2.put("name", "초코");
		obj2.put("age", 10);
		
		JSONArray list=new JSONArray();
		list.add(obj1);
		list.add(obj2);
		
		String requestData=list.toJSONString();
		System.out.println(requestData);
		
		// 내가 전달할 땐 toString, 문자열로 변환해 전달해 줌
		
	}

}
