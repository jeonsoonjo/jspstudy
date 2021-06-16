package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.BoardDTO;
import dto.ReplyDTO;

public class BoardDAO {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	private static DataSource dataSource;
	static {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static BoardDAO instance = new BoardDAO();
	private BoardDAO() {}
	public static BoardDAO getInstance() {
		if(instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}
	
	public void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if (con != null) { con.close(); }
			if (ps != null) { ps.close(); }
			if (rs != null) { rs.close(); }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 1. 게시글 목록 반환
	public List<BoardDTO> boardList(){
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			con = dataSource.getConnection();
			sql = "SELECT IDX, AUTHOR, TITLE, CONTENT, HIT, POSTDATE FROM BOARD";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setIdx(rs.getInt(1));
				dto.setAuthor(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setHit(rs.getInt(5));
				dto.setPostdate(rs.getDate(6));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return list;
	}
	
	// 2. 게시글 작성
	public int insertBoard(BoardDTO dto) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, 0, SYSDATE)";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getAuthor());
			ps.setString(2, dto.getTitle());
			ps.setString(3, dto.getContent());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return result;
	}
	
	// 3. 게시글 반환
	public BoardDTO viewBoardByIdx(int idx) {
		BoardDTO dto = null;
		try {
			con = dataSource.getConnection();
			sql = "SELECT IDX, AUTHOR, TITLE, CONTENT, HIT, POSTDATE FROM BOARD WHERE IDX=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, idx);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto = new BoardDTO();
				dto.setIdx(rs.getInt(1));
				dto.setAuthor(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setHit(rs.getInt(5));
				dto.setPostdate(rs.getDate(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return dto;
	}
	
	// 4. 조회수 증가
	public void updateHit(int idx) {
		try {
			con = dataSource.getConnection();
			sql = "UPDATE BOARD SET HIT=HIT+1 WHERE IDX=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, idx);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
	}
	
	// 5. 게시글 삭제
	public int deleteBoard(int idx) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "UPDATE BOARD SET STATE=-1 WHERE IDX=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, idx);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return result;
	}
	
	// 6. 댓글 등록
	public int insertReply(ReplyDTO dto) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "INSERT INTO REPLY VALUES(REPLY_SEQ.NEXTVAL, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getAuthor());
			ps.setString(2, dto.getContent());
			ps.setInt(3, dto.getBoardIdx());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return result;
	}
	
	// 7. 댓글 목록 반환
	public List<ReplyDTO> selectListReply(int idx){
		List<ReplyDTO> replyList = new ArrayList<ReplyDTO>();
		try {
			con = dataSource.getConnection();
			sql = "SELECT IDX, AUTHOR, CONTENT, BOARD_IDX FROM REPLY WHERE BOARD_IDX=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, idx);
			rs = ps.executeQuery();
			while(rs.next()) {
				ReplyDTO dto = new ReplyDTO();
				dto.setIdx(rs.getInt(1));
				dto.setAuthor(rs.getString(2));
				dto.setContent(rs.getString(3));
				dto.setBoardIdx(rs.getInt(4));
				replyList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return replyList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}


