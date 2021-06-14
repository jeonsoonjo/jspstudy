package controller;

import service.DeleteService;
import service.JoinPageService;
import service.JoinService;
import service.LoginPageService;
import service.LoginService;
import service.LogoutService;
import service.ManagerService;
import service.MemberService;

public class MemberServiceMapper {

	// singleton
	private static MemberServiceMapper instance = new MemberServiceMapper();
	private MemberServiceMapper() {}
	public static MemberServiceMapper getInstance() {
		if(instance == null) {
			instance = new MemberServiceMapper();
		}
		return instance;
	}

	public MemberService getService(String ser) {
		MemberService service = null;
		switch(ser) {
		case "loginPage.do" :
			service = new LoginPageService();
			break;
		case "login.do" :
			service = new LoginService();
			break;
		case "joinPage.do" :
			service = new JoinPageService();
			break;
		case "join.do" :
			service = new JoinService();
			break;
		case "manager.do" :
			service = new ManagerService();
			break;
		case "logout.do" :
			service = new LogoutService();
			break;
		case "delete.do" :
			service = new DeleteService();
			break;
		
		
		
		
		
		
		
		}
		return service;
	}

}


