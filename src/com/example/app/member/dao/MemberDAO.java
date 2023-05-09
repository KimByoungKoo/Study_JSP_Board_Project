package com.example.app.member.dao;

import org.apache.ibatis.session.SqlSession;

import com.example.app.member.dto.MemberDTO;
import com.mybatis.config.MyBatisConfig;

public class MemberDAO {
	public SqlSession sqlSession;
	
	public MemberDAO() {
//		System.out.println("DAO생성자1");
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
//		System.out.println("DAO생성자2");
	}
	
	public void join(MemberDTO memberDTO) {
		sqlSession.insert("member.join", memberDTO);
	}
	
	public boolean checkId(String memberId) {
		return (Integer)sqlSession.selectOne("member.checkId", memberId) < 1;
	}
	
//	첫번째 방법
//	public MemberDTO login(MemberDTO memberDTO) {
//		MemberDTO member = sqlSession.selectOne("member.login", memberDTO);
//		
//		return member;
//	}
	
//	두번째 방법
//	public int login(MemberDTO memberDTO) {
//		Integer memberNumber = sqlSession.selectOne("member.login", memberDTO);
//		return memberNumber == null ? -1 : memberNumber;
//		
//	}
	
//	세번째 방법
	public int login(MemberDTO memberDTO) {
		return sqlSession.selectOne("member.login", memberDTO);
	}
	
	public String getMemberId(int memberNumber) {
		return sqlSession.selectOne("member.getMemberId", memberNumber);
	}
	
}






