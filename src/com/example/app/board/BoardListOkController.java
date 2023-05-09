package com.example.app.board;

import java.io.IOException;	
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.board.dao.BoardDAO;
import com.example.app.board.vo.BoardVO;
import com.example.app.Execute;

public class BoardListOkController implements Execute {
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
	      BoardDAO boardDAO = new BoardDAO();

//	    처음으로 게시판 페이지에 진입하면 페이지에 대한 정보가 없다.
//	    그러므로 temp에는 null이 들어가게 된다.
	    String temp = req.getParameter("page");
	    
//	    null인 경우는 게시판에 처음 이동하는 것이므로 1페이지를 띄워주면 된다.
	    int page = temp==null ? 1 : Integer.valueOf(temp);
	    
//	    한 페이지에 몇 개의 게시물? 10개
	    int rowCount = 10;
//	    페이지 버튼은 최대 몇 개? 5개
	    int pageCount = 5;
	    
	    int startRow = (page-1) * rowCount;
	    
	    Map<String, Integer> pageMap = new HashMap<>();
	    pageMap.put("startRow", startRow);
	    pageMap.put("rowCount", rowCount);
	    
	    int total = boardDAO.getTotal();
	    
//	    Math.ceil()은 올림처리 이다.
	    int endPage = (int)(Math.ceil(page/(double)pageCount) * pageCount);
//	    endPage는 페이지 세트 당 마지막 번호를 의미한다.
	    
	    int startPage = endPage - (pageCount-1);
//	    startPage는 페이지 세트 당 첫 번째 번호를 의미한다.
	    
	    int realEndPage = (int)Math.ceil(total / (double)rowCount);
//	    realEndPage는 전체 페이지 중 가장 마지막 번호를 의미한다.
//	    total은 총 게시물 수
	    
	    boolean prev = startPage > 1;
//	    prev는 이전 버튼 표시 여부를 나타낸다.
	    
	    endPage = endPage > realEndPage ? realEndPage : endPage;
//	    첫 번째 페이지 세트가 1~5
//	    두 번째 페이지 세트가 6~10 이여도
//	    realEndPage가 7이라면 두 번째 페이지 세트의 마지막 번호는 7이어야 한다.
	    
	    boolean next = endPage != realEndPage;
//	    next는 다음 버튼 표시 여부를 나타낸다.
	    
	    List<BoardVO> boardList = boardDAO.selectAll(pageMap);
	    
	    req.setAttribute("boardList", boardList);
	    req.setAttribute("page", page);
	    req.setAttribute("startPage", startPage);
	    req.setAttribute("endPage", endPage);
	    req.setAttribute("prev", prev);
	    req.setAttribute("next", next);
	    
	    
	    req.getRequestDispatcher("/app/board/boardList.jsp").forward(req, resp);
	 }
}






