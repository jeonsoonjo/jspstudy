package controller;

import command.board.BoardCommand;
import command.board.InsertBoardPageCommand;
import command.board.SelectListBoardCommand;

public class BoardCommandMapper {

	// singleton
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
		case "selectListBoardPage.b" :
			command = new SelectListBoardCommand();
			break;
		case "insertBoardPage.b" :
			command = new InsertBoardPageCommand();
			break;
		
		
		
		
		
		
		
		
		
		
		
		}
		return command;
	}
	
}
