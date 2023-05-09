package com.example.app.file;

import java.io.File;	
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.Execute;

public class DownloadController implements Execute {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String fileSystemName = req.getParameter("fileSystemName");
		String fileOriginalName = req.getParameter("fileOriginalName");
		String uploadPath = req.getSession().getServletContext().getRealPath("/") + "upload/";
		
		InputStream in = null;
		OutputStream out = null;
		
		try {
			File file = new File(uploadPath, fileSystemName);
			
//		file.length()는 파일의 byte를 의미한다.
//		보통은 1024mb단위로 가져온다. -> 1kb
			byte[] buffer = new byte[1024];
			
//		클라이언트에게 보내는 응답이 이전과 다르게 파일(바이트) 데이터이므로 컨텐트 타입을 설정해야한다.
//		파일 다운로드는 아래와 같은 코드를 작성한다.
			resp.setContentType("application/octet-stream");
			
//		resp의 헤더영역의 파일에 대한 추가 정보를 설정한다.
			resp.setHeader("Content-Length", file.length() + "");
			resp.setHeader("Content-Disposition", "attachment; filename=" + fileOriginalName);
			
			in = new FileInputStream(file);
			
			out = resp.getOutputStream();
			
			int readCnt = 0;
			
			while( (readCnt = in.read(buffer)) != -1 )	{
				out.write(buffer, 0, readCnt);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}





