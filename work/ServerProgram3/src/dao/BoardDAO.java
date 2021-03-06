package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.util.DBConnector;
import dto.BoardDTO;
import dto.ReplyDTO;

public class BoardDAO {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	private static BoardDAO dao = new BoardDAO();
	private BoardDAO() {
		con = DBConnector.getInstance().getConnection();
	}
	public static BoardDAO getInstance() {
		if(dao == null) {
			dao = new BoardDAO();
		}
		return dao;
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
	
	// 1. 게시글 목록 반환 selectList()
	public List<BoardDTO> selectList(){
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			sql = "SELECT NO, AUTHOR, TITLE, CONTENT, HIT, IP, POSTDATE FROM BOARD";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setNo(rs.getLong(1));
				dto.setAuthor(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setHit(rs.getInt(5));
				dto.setIp(rs.getString(6));
				dto.setPostdate(rs.getDate(7));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getInstance().close(ps, rs);
		}
		return list;
	}
	
	// 2. 전체 게시글 개수 getTotalBoardCount()
	public int getTotalBoardCount() {
		int count = 0;
		try {
			sql = "SELECT COUNT(*) FROM BOARD";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getInstance().close(ps, rs);
		}
		return count;
	}
	
	
	// 3. 게시글 작성 insertBoard()
	public int insertBoard(BoardDTO dto) {
		int result = 0;
		try {
			sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, 0, ?, SYSDATE)";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getAuthor());
			ps.setString(2, dto.getTitle());
			ps.setString(3, dto.getContent());
			ps.setString(4, dto.getIp());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getInstance().close(ps, null);
		}
		return result;
	}
	
	// 4. 게시글 반환 viewBoardByIdx()
	public BoardDTO viewBoardByIdx(long no) {
		BoardDTO dto = null;
		try {
			sql = "SELECT NO, AUTHOR, TITLE, CONTENT, HIT, IP, POSTDATE FROM BOARD WHERE NO=?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto = new BoardDTO();
				dto.setNo(rs.getLong(1));
				dto.setAuthor(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setHit(rs.getInt(5));
				dto.setIp(rs.getString(6));
				dto.setPostdate(rs.getDate(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getInstance().close(ps, rs);
		}
		return dto;
	}
	
	// 5. 조회수 증가 updateHit()
	public void updateHit(long no) {
		try {
			sql = "UPDATE BOARD SET HIT=HIT+1 WHERE NO=?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getInstance().close(ps, null);
		}
	}
	
	// 6. 댓글 등록 insertReply()
	public int insertReply(ReplyDTO dto) {
		int result = 0;
		try {
			sql = "INSERT INTO REPLY VALUES(REPLY_SEQ.NEXTVAL, ?, ?, ?, ?, SYSDATE)";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getAuthor());
			ps.setString(2, dto.getContent());
			ps.setString(3, dto.getIp());
			ps.setLong(4, dto.getBoardNo());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getInstance().close(ps, null);
		}
		return result;
	}
	
	// 7. 댓글 목록 반환 selectListReply()
	public List<ReplyDTO> selectListReply(long no){
		List<ReplyDTO> replyList = new ArrayList<ReplyDTO>();
		try {
			sql = "SELECT NO, AUTHOR, CONTENT, IP, BOARD_NO, POSTDATE FROM REPLY WHERE BOARD_NO=?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			rs = ps.executeQuery();
			while(rs.next()) {
				ReplyDTO dto = new ReplyDTO();
				dto.setNo(rs.getLong(1));
				dto.setAuthor(rs.getString(2));
				dto.setContent(rs.getString(3));
				dto.setIp(rs.getString(4));
				dto.setBoardNo(rs.getLong(5));
				dto.setPostdate(rs.getDate(6));
				replyList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getInstance().close(ps, rs);
		}
		return replyList;
	}
	
	// 8. 게시글 삭제 deleteBoard()
	public int deleteBoard(long no) {
		int result = 0;
		try {
			sql = "DELETE FROM BOARD WHERE NO=?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getInstance().close(ps, null);
		}
		return result;
	}
	
	// 9. 가장 높은 조회수
	public List<BoardDTO> topHit(){
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			sql = "SELECT *" +
				  "  FROM BOARD" +
				  " WHERE HIT=(SELECT MAX(HIT)" +
				  "				 FROM BOARD";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getInstance().close(ps, rs);
		}
		return list;
	}
	
}

