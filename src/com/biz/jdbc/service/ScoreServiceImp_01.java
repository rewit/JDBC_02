package com.biz.jdbc.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.jdbc.config.DbConnection;
import com.biz.jdbc.model.ScoreVO;

/*
 * DBConnection 설정
 */
public class ScoreServiceImp_01 implements ScoreService {

	List<ScoreVO> sList = null;
	private Connection dbConn = null;
//	private String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
//	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
//	private String user = "user5";
//	private String passWord = "1234";

	public ScoreServiceImp_01() {
//		this.dbConnection();
		this.dbConn = DbConnection.getDbConnection();
	}

//	public void dbConnection() {
//		try {
//			Class.forName(jdbcDriver);
//			dbConn = DriverManager.getConnection(url, user, passWord);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	/*
	 * tbl_score 테이블의 모든 데이터를 select 한 후 List로 변환하여 return
	 */

	@Override
	public List<ScoreVO> selectAll() {
		// TODO 여기는 전체 성적데이터를 SELECT해서 List로 return하는 메서드
		String sql = " SELECT * FROM tbl_score ";
		PreparedStatement Pstr = null;
		try {
			Pstr = dbConn.prepareStatement(sql);
			ResultSet rs = Pstr.executeQuery();

			sList = new ArrayList<ScoreVO>();

			while (rs.next()) {
				ScoreVO vo = new ScoreVO();

				vo.setSc_seq(rs.getLong("SC_SEQ"));
				vo.setSc_date(rs.getString("SC_DATE"));
				vo.setSc_st_no(rs.getString("SC_ST_NO"));
				vo.setSc_subject(rs.getString("SC_SUBJECT"));
				vo.setSc_score(rs.getInt("SC_SCORE"));

				sList.add(vo);
			}
			return sList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ScoreVO findById(long sc_seq) {
		// TODO 여기는 일련번호 값을 매개변수로 받아 성적데이터 1개의 레코드를 return
		String sql = " SELECT * FROM tbl_score ";
		sql += " WHERE sc_seq = " + sc_seq;
		
		PreparedStatement Pstr = null;
		
		try {
			Pstr = dbConn.prepareStatement(sql);
			ResultSet rs = Pstr.executeQuery();

			List<ScoreVO> sList = new ArrayList<ScoreVO>();

			if(rs.next()) {
				ScoreVO vo = new ScoreVO();

				vo.setSc_seq(rs.getLong("SC_SEQ"));
				vo.setSc_date(rs.getString("SC_DATE"));
				vo.setSc_st_no(rs.getString("SC_ST_NO"));
				vo.setSc_subject(rs.getString("SC_SUBJECT"));
				vo.setSc_score(rs.getInt("SC_SCORE"));

				return vo;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return null;
	}

	@Override
	public int insert(ScoreVO vo) {
		// TODO 여기는 데이터 추가
		String sql = " INSERT INTO tbl_score ( ";
		sql += " SC_SEQ, ";
		sql += " SC_DATE, ";
		sql += " SC_ST_NO, ";
		sql += " SC_SUBJECT, ";
		sql += " SC_SCORE ) ";
		sql += " VALUES( SEQ_SCORE.NEXTVAL ,?,?,?,? ) ";
		return 0;
	}

	@Override
	public int update(ScoreVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long sc_seq) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ScoreVO> findByNum(String st_no) {
		// TODO Auto-generated method stub
		String sql = " SELECT * FROM tbl_score ";
		sql += " WHERE sc_st_no = "+ st_no;
		PreparedStatement Pstr = null;
		try {
			Pstr = dbConn.prepareStatement(sql);
			ResultSet rs = Pstr.executeQuery();

			List<ScoreVO> sList = new ArrayList<ScoreVO>();

			while (rs.next()) {
				ScoreVO vo = new ScoreVO();

				vo.setSc_seq(rs.getLong("SC_SEQ"));
				vo.setSc_date(rs.getString("SC_DATE"));
				vo.setSc_st_no(rs.getString("SC_ST_NO"));
				vo.setSc_subject(rs.getString("SC_SUBJECT"));
				vo.setSc_score(rs.getInt("SC_SCORE"));

				sList.add(vo);
			}
			return sList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	
	}



}
