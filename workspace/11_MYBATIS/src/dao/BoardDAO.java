package dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.BoardDTO;
import mybatis.config.DBService;

public class BoardDAO {

	private SqlSessionFactory factory;
	
	// singleton
	private static BoardDAO instance = new BoardDAO();
	private BoardDAO() {
		factory = DBService.getInstance().getFactory();
	}
	public static BoardDAO getInstance() {
		if(instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}
	
	// MYBATIS로 작성!!
	
	// 1. 게시글 작성
	// SqlSession interface
	// openSession() : 타입이 비었을 때는 select만 가능(commit이 아니기 때문에)
	// openSession(boolean) : 타입이 있을 때는 insert, update, delete 가능(commit을 하기 때문에)
	public int insert(BoardDTO dto) {
		SqlSession ss = factory.openSession(false); // insert 후 수동 커밋하겠다는 의미
		int result = ss.insert("mybatis.mapper.board.insertBoard", dto); // ss.insert("SQL의 id", "인수"), sql문은 board.xml에서 작성
		if(result > 0) { // insert()가 성공하면
			ss.commit(); // 커밋하겠다
		}
		ss.close();
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}


