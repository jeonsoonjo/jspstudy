package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
