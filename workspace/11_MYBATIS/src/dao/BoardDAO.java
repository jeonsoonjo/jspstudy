package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.BoardDTO;
import mybatis.config.DBService;

public class BoardDAO {

	// singleton
	
	// field
	private SqlSessionFactory factory;
	private static BoardDAO instance = new BoardDAO();
	
	// constructor
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
	// openSession(boolean) : 타입이 있을 때는 insert, update, delete 가능(직접 commit을 하기 때문에)
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
		int count = ss.selectOne("mybatis.mapper.board.getTotalRecord"); // parameterType이 없기 때문에 반환 타입을 적지 않는다
		ss.close();
		return count;
	}
	
	// 3. 게시글 목록 반환
	public List<BoardDTO> selectList(Map<String, Integer> map){
		SqlSession ss = factory.openSession();
		List<BoardDTO> list = ss.selectList("mybatis.mapper.board.selectList", map);
		ss.close();
		return list;
	}
	
	// 4. 같은 그룹 기존 댓글들의 groupord 1씩 증가하기
	public int increseGroupordPreviousReply(long groupno) {
		SqlSession ss = factory.openSession(false);
		int result = ss.update("mybatis.mapper.board.increseGroupordPreviousReply", groupno);
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	// 5. 댓글 삽입하기
	public int insertReply(BoardDTO replyDTO) {
		SqlSession ss = factory.openSession(false);
		int result = ss.insert("mybatis.mapper.board.insertReply", replyDTO);
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	// 6. 검색 결과 개수 반환
	public int getFindRecordCount(Map<String, Object> map) {
		SqlSession ss = factory.openSession();
		int count = ss.selectOne("mybatis.mapper.board.getFindRecordCount", map);
		ss.close();
		return count;
	}
	
	// 7. 검색 결과 목록 반환
	public List<BoardDTO> findList(Map<String, Object> map){
		SqlSession ss = factory.openSession();
		List<BoardDTO> list = ss.selectList("mybatis.mapper.board.findList", map);
		ss.close();
		return list;
	}
	
	// 8. 게시글 삭제
	public int delete(long no) {
		SqlSession ss = factory.openSession(false);
		int result = ss.delete("mybatis.mapper.board.delete", no);
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	// 9. 대댓글 목록 반환
	public List<BoardDTO> selectList3(Map<String, Integer> map){
		SqlSession ss = factory.openSession();
		List<BoardDTO> list = ss.selectList("mybatis.mapper.board.selectList3", map);
		ss.close();
		return list;
	}
	
	// 10. 원글 가져오기
	public BoardDTO selectBoard(long no) {
		SqlSession ss = factory.openSession();
		BoardDTO boardDTO = ss.selectOne("mybatis.mapper.board.selectBoard", no);
		ss.close();
		return boardDTO;
	}
	
	// 11. 원글의 groupord보다 큰 groupord를 가진 댓글의 groupord 증가
	public int increseGroupordOtherReply(BoardDTO boardDTO) {
		SqlSession ss = factory.openSession(false);
		int result = ss.update("mybatis.mapper.board.increseGroupordOtherReply", boardDTO);
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}


