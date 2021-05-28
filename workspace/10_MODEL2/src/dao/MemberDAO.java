package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.MemberDTO;
import oracle.jdbc.driver.DBConversion;

public class MemberDAO {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	private static DataSource dataSource; // 커넥션풀을 관리하는 소스
	// static 필드의 초기화 : static 블록에서 처리한다
	static {
		try {
			Context context = new InitialContext();			
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
			// tomcat용("java:comp/env"), Resource이름("jdbc/oracle")
			// lookup은 casting해야 한다
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
	
	// 1. 접속 해제
	public void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if(con != null) {con.close();}
			if(ps != null) {ps.close();}
			if(rs != null) {rs.close();}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 2. 로그인
	// Connection은 메소드마다 열고 닫는 것이 가장 좋다
	public MemberDTO login(MemberDTO dto) {
		MemberDTO loginDTO = null;
		try {
			con = dataSource.getConnection(); // 커넥션 풀을 관리하는 dataSource로부터 connection을 가져온다
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
	
	// 3. 로그인 로그 남기기
	public void loginLog(MemberDTO dto) {
		try {
			con = dataSource.getConnection();
			sql = "INSERT INTO MEMBER_LOG VALUES(MEMBER_LOG_SEQ.NEXTVAL, ?, SYSDATE, NULL)";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
	}
	
	// 4. 로그아웃 로그 남기기
	public void logoutLog(String id) {
		try {
			con = dataSource.getConnection();
			sql = "UPDATE MEMBER_LOG SET LOGOUT = SYSDATE WHERE ID=? AND LOGOUT IS NULL";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
