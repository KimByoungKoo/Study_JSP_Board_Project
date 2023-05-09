package com.example.app.reply;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.example.app.Execute;
import com.example.app.reply.dao.ReplyDAO;
import com.example.app.reply.vo.ReplyVO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class ReplyListOkController implements Execute {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		int boardNumber = Integer.valueOf(req.getParameter("boardNumber"));
		ReplyDAO replyDAO = new ReplyDAO();
		Gson gson = new Gson()	;
		JsonArray replies = new JsonArray();
		
//		첫번째 방법
//		List<ReplyVO> replyList = replyDAO.selectAll(boardNumber);
//		
//		for(ReplyVO reply : replyList) {
//			String replyJson = gson.toJson(reply);
//			System.out.println(replyJson);
//			replies.add(JsonParser.parseString(replyJson));
//		}
		
//		두번째 방법
		replyDAO.selectAll(boardNumber).stream()
		.map(reply -> gson.toJson(reply))
		.map(replyJson -> JsonParser.parseString(replyJson))
		.forEach(r -> replies.add(r));
		
		resp.setContentType("application/json;charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		out.print(replies.toString());
		out.close();
	}

}





