package dao;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.MemberDTO;
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

	// 1. 로그인(login)
	public MemberDTO login(MemberDTO memberDTO) {
		SqlSession ss = factory.openSession();
		MemberDTO loginUser = ss.selectOne("dao.member.login", memberDTO);
		ss.close();
		return loginUser;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}


