package controller;

import command.LoginPageCommand;
import command.MemberCommand;

public class MemberCommandMapper {

	// singleton
	private static MemberCommandMapper instance = new MemberCommandMapper();
	private MemberCommandMapper() {}
	public static MemberCommandMapper getInstance() {
		if(instance == null) {
			instance = new MemberCommandMapper();
		}
		return instance;
	}
	
	// method(command 반환)
	public MemberCommand getCommand(String cmd) {
		MemberCommand command = null;
		switch(cmd) {
		case "loginPage.do" :
			command = new LoginPageCommand();
			break;
		
		
		
		
		
		
		
		
		}
		return command;
	}
	
}


