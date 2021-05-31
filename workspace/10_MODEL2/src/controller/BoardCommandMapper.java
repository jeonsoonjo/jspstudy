package controller;

import command.board.BoardCommand;

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
		
		
		
		
		
		
		
		
		
		
		
		
		
		}
		return command;
	}
	
}
