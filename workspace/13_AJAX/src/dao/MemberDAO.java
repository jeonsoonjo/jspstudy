package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.Member;
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
	
	// 1. 회원 목록 가져오기
	public List<Member> selectMemberList() {
		SqlSession ss = factory.openSession();
		List<Member> list = ss.selectList("dao.member.selectMemberList");
		ss.close();
		return list;
	}
	
	
	
	
	
	
	
	
	
	

}


