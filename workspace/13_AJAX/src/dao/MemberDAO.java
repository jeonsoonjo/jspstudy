package dao;

import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.config.DBService;


public class MemberDAO {
	
	// field
	private SqlSessionFactory factory;
	private static MemberDAO instance = new MemberDAO();
	
	// constructor
	private MemberDAO() {
		factory = DBService.getInstance().getFactory();
	}
	public static MemberDAO getInstance() {
		if(instance == null) {
			instance = new MemberDAO();
		}
		return instance;
	}
	

}


