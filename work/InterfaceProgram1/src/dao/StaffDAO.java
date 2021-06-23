package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import dto.Staff;

public class StaffDAO {
	
		private Connection con;
		private PreparedStatement ps;
		private ResultSet rs;
		private String sql;
		
		private static StaffDAO instance;
		private StaffDAO() {}
		public static StaffDAO getInstance() {
			if (instance == null) {
				instance = new StaffDAO();
			}
			return instance;
		}
		private Connection getConnection() {
			Connection con = null;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "SERVER_USER", "1111");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return con;
		}
		private void close(Connection con, PreparedStatement ps, ResultSet rs) {
			try {
				if (con != null) con.close();
				if (ps != null) ps.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 사원 목록
		public List<Staff> selectStaffList() {
			List<Staff> list = new ArrayList<Staff>();
			try {
				con = getConnection();
				sql = "SELECT SNO, NAME, DEPT, REGDATE FROM STAFF";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					Staff s = new Staff();
					s.setSno(rs.getString(1));
					s.setName(rs.getString(2));
					s.setDept(rs.getString(3));
					s.setRegdate(rs.getDate(4));
					list.add(s);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(con, ps, rs);
			}
			return list;
		}
		
		// 사원 등록
		public int insertStaff(Staff staff) throws SQLIntegrityConstraintViolationException, SQLException {
			int count = 0;
			con = getConnection();
			sql = "INSERT INTO STAFF VALUES (?, ?, ?, SYSDATE)";
			ps = con.prepareStatement(sql);
			ps.setString(1, staff.getSno());
			ps.setString(2, staff.getName());
			ps.setString(3, staff.getDept());
			count = ps.executeUpdate();
			close(con, ps, null);
			return count;
		}

		// junit 테스트
		/*
		 * public void staffTest() {
		 * 	con = getConnection();
		 * 	sql = "SELECT SNO, NAME, DEPT, REGDATE FROM STAFF WHERE NO=99999";
		 * 	ps = con.prepareStatement(sql);
		 * 	rs = ps.executeQuery();
		 * 	close(con, ps, rs);
		 * 
		 * }
		 */

}


	
