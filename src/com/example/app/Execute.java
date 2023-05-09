package com.example.app;

import java.io.IOException;	

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Execute {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException;
	
}
