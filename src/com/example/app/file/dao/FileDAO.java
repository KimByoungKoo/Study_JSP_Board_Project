package com.example.app.file.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.example.app.file.dto.FileDTO;
import com.mybatis.config.MyBatisConfig;

public class FileDAO {
	
	public SqlSession sqlSession;
	
	public FileDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
	
	public void insert(FileDTO fileDTO) {
		sqlSession.insert("file.insert", fileDTO);
	}
	
	public List<FileDTO> select(int boardNumber) {
		return sqlSession.selectList("file.select", boardNumber);
	}
	
	public void delete(int boardNumber) {
		sqlSession.delete("file.delete", boardNumber);
	}

}
