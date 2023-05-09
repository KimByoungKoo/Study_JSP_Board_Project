package com.example.app.member;

import java.io.IOException;		

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.app.Execute;
import com.example.app.member.dao.MemberDAO;
import com.example.app.member.dto.MemberDTO;

public class LoginOkController implements Execute {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		String memberId = req.getParameter("memberId");
		String memberPassword = req.getParameter("memberPassword");
		int memberNumber = 0;
		String path = null;
		String remember = req.getParameter("remember");
		HttpSession session = req.getSession();

		memberDTO.setMemberId(memberId);
		memberDTO.setMemberPassword(memberPassword);

		try {
			memberNumber = memberDAO.login(memberDTO);
			path = "/board/boardListOk.bo";
			
			if(remember != null) {
				Cookie cookie = new Cookie("memberId", memberId);
				cookie.setMaxAge(60*60*24);
				
				resp.addCookie(cookie);
			}
			
			session.setAttribute("memberNumber", memberNumber);
			
		} catch (NullPointerException e) {
			path = "/member/login.me?result=false";
		} catch (Exception e) {
			System.out.println("예상치 못한 오류!");
			e.printStackTrace();
		}
		
		resp.sendRedirect(path);

//		memberNumber = memberDAO.login(memberDTO);
//		System.out.println(memberNumber);
//		if(memberNumber == -1) {
//			path = "/member/login.me?result=false";
//		}else {
//			 path = "/board/boardListOk.bo";
//		}

//		HttpSession session = req.getSession();

//		session.setAttribute("memberNumber", result.getMemberNumber());
//		session.setAttribute("memberName", result.getMemberName());

//		resp.sendRedirect(path);
	}

	
}
