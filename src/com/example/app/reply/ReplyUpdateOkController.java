package com.example.app.reply;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.Execute;
import com.example.app.reply.dao.ReplyDAO;
import com.example.app.reply.dto.ReplyDTO;

public class ReplyUpdateOkController implements Execute {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		ReplyDTO replyDTO = new ReplyDTO();
		replyDTO.setReplyNumber(Integer.parseInt(req.getParameter("replyNumber")));
		replyDTO.setReplyContent(req.getParameter("replyContent"));
		
		new ReplyDAO().update(replyDTO);
	}

}
