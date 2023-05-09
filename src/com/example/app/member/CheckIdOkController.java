package com.example.app.member;

import java.io.IOException;		
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.Execute;
import com.example.app.member.dao.MemberDAO;

public class CheckIdOkController implements Execute{
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		MemberDAO memberDAO = new MemberDAO();
		
		boolean result = memberDAO.checkId(req.getParameter("memberId"));
		
		resp.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		out.print(result ? "사용 가능한 아이디입니다." : "중복된 아이디 입니다.");
		out.close();
		
		System.out.println(req.getParameter("memberId"));
		System.out.println(result);
	}
}
