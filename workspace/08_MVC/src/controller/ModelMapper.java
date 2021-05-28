package controller;

import model.StudentCommand;
import model.StudentScoreCommand;

public class ModelMapper {

	// singleton
	private static ModelMapper mapper=new ModelMapper();
	private ModelMapper() {}
	public static ModelMapper getInstance() {
		if(mapper==null) {
			mapper=new ModelMapper();
		}
		return mapper;
	}
	
	// result를 반환시키는  getModel() 메소드
	public StudentCommand getModel(String cmd) {
		StudentCommand command=null;
		switch(cmd) {
		case "inquiry.do" :
			command=new StudentScoreCommand();
			break;
		}
		return command;
	}
	
}
