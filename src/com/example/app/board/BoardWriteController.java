package com.example.app.board;

import java.io.IOException;				

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.app.Execute;
import com.example.app.member.dao.MemberDAO;

public class BoardWriteController implements Execute {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		MemberDAO memberDAO = new MemberDAO();
		HttpSession session = req.getSession();
		Integer memberNumber = (Integer)session.getAttribute("memberNumber");
		String path = null;
		
		if(memberNumber == null) {
			path = "/app/member/login.jsp";
		}else {
			path = "/app/board/boardWrite.jsp";
			req.setAttribute("memberId", memberDAO.getMemberId(memberNumber));
		}
		
		req.getRequestDispatcher(path).forward(req, resp);
	}
}
