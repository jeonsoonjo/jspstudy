package controller;

import command.BoardCommand;
import command.BoardListCommand;
import command.InsertBoardCommand;
import command.InsertPageCommand;
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
		case "boardList.do" :
			command = new BoardListCommand();
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
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		}
		return command;
	}
	
}

