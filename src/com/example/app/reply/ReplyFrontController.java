package com.example.app.reply;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.board.BoardDeleteOkController;
import com.example.app.board.BoardListOkController;
import com.example.app.board.BoardReadOkController;
import com.example.app.board.BoardUpdateController;
import com.example.app.board.BoardUpdateOkController;
import com.example.app.board.BoardWriteController;
import com.example.app.board.BoardWriteOkController;

public class ReplyFrontController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String target = req.getRequestURI().substring(req.getContextPath().length());
		System.out.println(target);
		
		switch(target) {
		case "/reply/replyListOk.re" :
			new ReplyListOkController().execute(req, resp);
			break;
		case "/reply/replyWriteOk.re" :
			new ReplyWriteOkController().execute(req, resp);
			break;
		case "/reply/replyDeleteOk.re" :
			new ReplyDeleteOkController().execute(req, resp);
			break;
		case "/reply/replyUpdateOk.re" :
			new ReplyUpdateOkController().execute(req, resp);
			break;
		}
	}
	
}





