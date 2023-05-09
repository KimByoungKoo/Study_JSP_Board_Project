package com.example.app.board;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.Execute;
import com.example.app.board.dao.BoardDAO;
import com.example.app.board.dto.BoardDTO;
import com.example.app.file.dao.FileDAO;
import com.example.app.file.dto.FileDTO;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

public class BoardUpdateOkController implements Execute {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
	      BoardDAO boardDAO = new BoardDAO();
	      BoardDTO boardDTO = new BoardDTO();
	      FileDAO fileDAO = new FileDAO();
	      FileDTO fileDTO = new FileDTO();
	      int  boardNumber = 0;
	      
	      System.out.println("ok컨트롤러 들어왔다!!!!");
	      System.out.println(req.getParameter("boardTitle"));
	      
	      String uploadPath = req.getSession().getServletContext().getRealPath("/") + "upload/";
	      int fileSize = 1024 * 1024 * 10; //5mb
	      
	      System.out.println(uploadPath);
	      
//	      ========================
//	      하나의 input으로 여러 첨부파일을 보낸 경우 아래와 같이 처리한다.
	      MultipartParser parser = new MultipartParser(req, fileSize);
	      parser.setEncoding("utf-8");
	      
	      while(true) {
//	         MultipartParser객체는 여러 폼데이터를 part객체로 분리할 수 있다.
	         
	         Part part = parser.readNextPart();
	         
	         if(part == null) { break; }
	         String fileSystemName = null;
	         String fileOriginalName = null;
	         
	         if(part.isFile()) {
	            FilePart filePart = (FilePart)part;
	            filePart.setRenamePolicy(new DefaultFileRenamePolicy());
	            fileOriginalName = filePart.getFileName();
	            
	            if(fileOriginalName != null) {
//	               파일을 저장하기 위한 정보를 가진 객체를 만든다.
	               File file = new File(uploadPath, fileOriginalName);
//	               파일을 저장한다.
	               filePart.writeTo(file);
//	               저장 후 파일 이름을 다시 뽑으면 저장될 때의 이름이 나온다.
	               fileSystemName = filePart.getFileName();
	               
	               fileDTO.setFileSystemName(fileSystemName);
	               fileDTO.setFileOriginalName(fileOriginalName);
	               fileDTO.setBoardNumber(boardNumber);
	               fileDAO.insert(fileDTO);
	            }
	         } else {
//	            part객체가 파일이 아닌 일반 파라미터인 경우
	            ParamPart paramPart = (ParamPart)part;
	            String param = paramPart.getName(); //파라미터 이름
	            String value = paramPart.getStringValue(); //값
	            
	            if(param.equals("boardTitle")) {
	               boardDTO.setBoardTitle(value);
	            }else if (param.equals("boardContent")) {
	               boardDTO.setBoardContent(value);
	            }else if(param.equals("boardNumber")) {
	            	boardNumber = Integer.valueOf(value);
	            	boardDTO.setBoardNumber(boardNumber);
	            }
	            
//	            만약 둘 중 하나라도 null이면 다음 반복으로 돌린다.
//	            이 처리를 하지않으면 게시글이 2번 생긴다.
	            if(boardDTO.getBoardTitle() == null || boardDTO.getBoardContent() == null) { continue; }
	            
	            boardDTO.setMemberNumber((Integer)req.getSession().getAttribute("memberNumber"));
	            boardDAO.update(boardDTO);
	            
	    		List<FileDTO> files = fileDAO.select(boardNumber);
	    		
	    		files.stream().map(file -> file.getFileSystemName())
	    		.map(name -> new File(uploadPath, name))
	    		.filter(temp -> temp.exists())
	    		.forEach(temp -> temp.delete());
	    		
	    		fileDAO.delete(boardNumber);
	    		
	         }
	         
	      }
	      
	      resp.sendRedirect("/board/boardListOk.bo");
	}

}
