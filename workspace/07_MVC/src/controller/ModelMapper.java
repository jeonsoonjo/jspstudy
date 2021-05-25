package controller;

import model.CircleCommand;
import model.RectangleCommand;
import model.ShapeCommand;
import model.TriangleCommand;

public class ModelMapper{
	
	// 한 번에 하나의 Model만 반환할 수 있도록  Singleton처리
	private static ModelMapper mapper=new ModelMapper(); // 자기 자신을 생성
	private ModelMapper() {}
	public static ModelMapper getInstance() { // modelMapper를 반환시켜주는 getInstance 사용
		if(mapper==null) {
			mapper=new ModelMapper();
		}
		return mapper;
	}

	// Model을 반환시키는 getModel() 메소드
	public ShapeCommand getModel(String cmd) {
		ShapeCommand command=null;
		switch (cmd) {
		case "rectangle.do" :
			command=new RectangleCommand();
			break;
		case "triangle.do" :
			command=new TriangleCommand();
			break;
		case "circle.do" :
			command=new CircleCommand();
			break;
		}
		return command;
		
	}
	
}
