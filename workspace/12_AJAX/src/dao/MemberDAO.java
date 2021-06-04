package dao;

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
	
	// 1. 로그인(select처리 openSession)
	public Member login(Member member) {
		SqlSession ss = factory.openSession();
		Member loginUser = ss.selectOne("dao.member.login", member);
		ss.close();
		return loginUser;
	}
	
	// 2. 아이디 체크
	public boolean idCheck(String id) {
		SqlSession ss = factory.openSession();
		Member member = ss.selectOne("dao.member.idCheck", id);
		ss.close();
		return member == null; // 일치하는 id를 가진 member가 없다. id 사용이 가능하다라는 의미.
	}
	
	// 3. 회원가입
	public int join(Member member) {
		SqlSession ss = factory.openSession(false);
		int result = ss.insert("dao.member.join", member);
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	// 4. 비밀번호 변경
	public int updatePw(Member member) {
		SqlSession ss = factory.openSession(false);
		int result = ss.update("dao.member.updatePw", member);
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}


