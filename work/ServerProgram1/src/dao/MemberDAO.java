package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.util.DBConnector;
import dto.MemberDTO;

public class MemberDAO {

	// field
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String sql = null;
	
	// singleton
	private static MemberDAO dao = new MemberDAO();
	private MemberDAO() {
		con = DBConnector.getInstance().getConnection();
		
	}
	public static MemberDAO getInstance() {
		if(dao == null) {
			dao = new MemberDAO();
		}
		return dao;
	}
	
	// 1. 로그인(login)
	public MemberDTO login(String id, String name) { // login.jsp에서 받아 온 dto
		MemberDTO dto = null;
		try {
			sql = "SELECT NO, ID, NAME, GRADE, POINT FROM MEMBER_TABLE WHERE ID=? AND NAME=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, name);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto = new MemberDTO();
				dto.setNo(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setGrade(rs.getString(4));
				dto.setPoint(rs.getInt(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getInstance().close(ps, rs);
		}
		return dto;
	}	
	
	// 2. 회원 정보 수정(updateMember)
	public int updateMember(MemberDTO dto) {
		int result = 0;
		try {
			sql = "UPDATE MEMBER_TABLE SET NAME=?, POINT=?, GRADE=? WHERE NO=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getName());
			ps.setInt(2, dto.getPoint());
			ps.setString(3, dto.getGrade());
			ps.setInt(4, dto.getNo());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getInstance().close(ps, null);
		}
		return result;
	}
	
	// 2-1. 회원 포인트 수정(updatePoint)
	public int updatePoint(MemberDTO dto) {
		int result = 0;
		try {
			sql = "UPDATE MEMBER_TABLE SET POINT=POINT + 10";
			ps = con.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getInstance().close(ps, null);
		}
		return result;
	}
	
	
	// 3. 회원가입
	public int joinMember(MemberDTO dto) { // join.jsp가 전달한 dto
		int result = 0;
		try {
			sql = "INSERT INTO MEMBER_TABLE VALUES (MEMBER_SEQ.NEXTVAL, ?, ?, 'bronze', 1000)";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getName());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getInstance().close(ps, null);
		}
		return result;
	}
	
	// 4. 회원탈퇴
	public int deleteMember(long no) {
		int result = 0;
		try {
			sql = "DELETE FROM MEMBER_TABLE WHERE NO=?";
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
	
}


