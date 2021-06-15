package controller;

import service.DeleteService;
import service.JoinPageService;
import service.JoinService;
import service.LoginPageService;
import service.LoginService;
import service.LogoutService;
import service.MemberService;
import service.UpdateService;

public class ServiceMapper {

	// singleton
	private static ServiceMapper instance = new ServiceMapper();
	private ServiceMapper() {}
	public static ServiceMapper getInstance() {
		if(instance == null) {
			instance = new ServiceMapper();
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
		case "logout.do" :
			service = new LogoutService();
			break;
		case "update.do" :
			service = new UpdateService();
			break;
		case "delete.do" :
			service = new DeleteService();
			break;
		case "joinPage.do" :
			service = new JoinPageService();
			break;
		case "join.do" :
			service = new JoinService();
			break;
		}
		return service;
	}

}


