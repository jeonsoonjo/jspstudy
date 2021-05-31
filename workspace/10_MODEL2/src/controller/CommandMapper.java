package controller;

import command.member.JoinCommand;
import command.member.JoinPageCommand;
import command.member.LoginCommand;
import command.member.LoginPageCommand;
import command.member.LogoutCommand;
import command.member.MemberCommand;
import command.member.MyPageCommand;
import command.member.UpdateMemberCommand;
import command.member.UpdatePwCommand;
import command.member.UpdatePwPageCommand;

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
	// 모든 Command는 MemberCommand 타입을 가진다
	public MemberCommand getCommand(String cmd) {
		MemberCommand command = null;
		switch(cmd) {
		case "loginPage.m" :
			command = new LoginPageCommand();
			break;
		case "joinPage.m" :
			command = new JoinPageCommand();
			break;
		case "myPage.m" :
			command = new MyPageCommand();
			break;
		case "updatePwPage.m" :
			command = new UpdatePwPageCommand();
			break;
		case "login.m" :
			command = new LoginCommand();
			break;
		case "logout.m" :
			command = new LogoutCommand();
			break;
		case "join.m" :
			command = new JoinCommand();
			break;
		case "updatePw.m" :
			command = new UpdatePwCommand();
			break;
		case "updateMember.m" :
			command = new UpdateMemberCommand();
			break;
		}
		return command;
	}
			
}
