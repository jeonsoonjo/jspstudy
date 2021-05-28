package controller;

import command.member.LoginPageCommand;
import command.member.MemberCommand;

public class CommandMapper {

	// singleton
	private static CommandMapper instance = new CommandMapper();
	private CommandMapper() {}
	public static CommandMapper getInstance() {
		if(instance == null) {
			instance = new CommandMapper();
		}
		return instance;
	}
	
	// Command를 반환하는 메소드
	// 모든 Command의 인터페이스 MemeberCommand를 구현하는 클래스이므로
	// 모든 Command는 MemeberCommand 타입을 가진다
	public MemberCommand getCommand(String cmd) {
		MemberCommand command = null;
		switch(cmd) {
		case "loginPage.m" :
			command = new LoginPageCommand();
			break;
		}
		return command;
	}
			
}