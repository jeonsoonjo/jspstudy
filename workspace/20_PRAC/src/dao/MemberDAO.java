package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.MemberDTO;

public class MemberDAO {

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
	
	// singleton
	private static MemberDAO instance = new MemberDAO();
	private MemberDAO() {}
	public static MemberDAO getInstance() {
		if(instance == null) {
			instance = new MemberDAO();
		}
		return instance;
	}
	
	public void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if(con != null) {con.close();}
			if(ps != null) {ps.close();}
			if(rs != null) {rs.close();}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 1. 로그인(login)
	public MemberDTO login(MemberDTO dto) {
		MemberDTO loginDTO = null;
		try {
			con = dataSource.getConnection();
			sql = "SELECT NO, ID, PW, NAME, EMAIL, REGDATE FROM MEMBER WHERE ID=? AND PW=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getPw());
			rs = ps.executeQuery();
			if(rs.next()) {
				loginDTO = new MemberDTO();
				loginDTO.setNo(rs.getLong(1));
				loginDTO.setId(rs.getString(2));
				loginDTO.setPw(rs.getString(3));
				loginDTO.setName(rs.getString(4));
				loginDTO.setEmail(rs.getString(5));
				loginDTO.setRegdate(rs.getDate(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return loginDTO;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}


