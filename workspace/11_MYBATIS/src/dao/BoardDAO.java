package dao;

import java.util.List;
import java.util.Map;

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
	// SqlSession interface(ibatis)
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
	
	/*
	 * final String NAMESPACE = "mybatis.mapper.board";
	 * ss.insert(NAMESPACE + ".insertBoard")와 같다
	*/
	
	// 2. 전체 레코드 개수 구하기
	// selectList() : 여러 개 조회 
	// selectOne() : 하나만 조회
	public int getTotalRecord() {
		SqlSession ss = factory.openSession(); // commit이 필요 없는 select문
		int count = ss.selectOne("mybatis.mapper.board.getTotalRecord");
		ss.close();
		return count;
	}
	
	// 3. 목록
	public List<BoardDTO> selectList(Map<String, Integer> map){
		SqlSession ss = factory.openSession();
		List<BoardDTO> list = ss.selectList("mybatis.mapper.board.selectList", map);
		ss.close();
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}


