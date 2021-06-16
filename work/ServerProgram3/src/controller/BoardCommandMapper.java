package controller;

import command.BoardCommand;
import command.DeleteBoardCommand;
import command.InsertBoardCommand;
import command.InsertPageCommand;
import command.InsertReplyCommand;
import command.ListBoardCommand;
import command.ViewBoardCommand;

public class BoardCommandMapper {

	private static BoardCommandMapper instance = new BoardCommandMapper();
	private BoardCommandMapper() {}
	public static BoardCommandMapper getInstance() {
		if(instance == null) {
			instance = new BoardCommandMapper();
		}
		return instance;
	}
	
	public BoardCommand getCommand(String cmd) {
		BoardCommand command = null;
		switch(cmd) {
		case "listBoard.do" :
			command = new ListBoardCommand();
			break;
		case "insertPage.do" :
			command = new InsertPageCommand();
			break;
		case "insertBoard.do" :
			command = new InsertBoardCommand();
			break;
		case "viewBoard.do" :
			command = new ViewBoardCommand();
			break;
		case "deleteBoard.do" :
			command = new DeleteBoardCommand();
			break;
		case "insertReply.do" :
			command = new InsertReplyCommand();
			break;
		}
		return command;
	}
	
}

