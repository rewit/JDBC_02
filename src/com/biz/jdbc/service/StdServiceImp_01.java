package com.biz.jdbc.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.jdbc.config.DbConnection;
import com.biz.jdbc.model.StudentVO;

/*
 * StdService interface(설계도)에 기반한
 * 실제 클래스를 구현
 * 	1. DB Connection을 설정(구현)
 */
public class StdServiceImp_01 implements StdService {

	private List<StudentVO> stdList = null;
	private Connection dbConn = null;
//	private String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
//	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
//	private String userName = "user5";
//	private String passWord = "1234";

	public StdServiceImp_01() {
		// stdList = new ArrayList<StudentVO>();
		// 데이터리스트를 만들어서 외부로 전달하기 위해 사용하는
		// stdList를 생성자에서 초기화를 하고 계속 사용을 하면
		// selectAll()을 실행할때마다 리스트가 계속 쌓이게된다
		// 리스트를 초기화하는 코드는 리스트를 만들기 직전에
		// 위치해야 한다

		this.dbConn = DbConnection.getDbConnection();
	}

	// dbConnection은 외부에서 실행하지 못하도록 private으로 선언한다
	// dbConnection이 자주 실행되는것은 Driver를 계속 On Load시키고
	// 통로를 새로 설정하는 과정이 반복되어 문제를 일으킬 수 있기 때문이다
//	private void dbConnection() {
//		// 1. jdbcDriver ON load 시키기
//		try {
//			Class.forName(jdbcDriver);
//			// 2. 연결통로(port) 설정
//			dbConn = DriverManager.getConnection(url, userName, passWord);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	@Override
	public List<StudentVO> selcetAll() {
		// TODO 여기는 전체 리스트를 selset하는 메서드이다
		String sql = " SELECT * FROM tbl_student ";
		PreparedStatement pStr = null;
		// String으로 된 sql문장을 DBMS방식의 코드로 변환하여 DBMS에게 전달하는 역할
		try {
			pStr = dbConn.prepareStatement(sql);
			// 이 문장이 실행이 되면 sql문을 DBMS 코드로 변환(컴파일)하여 잠시 보관
			ResultSet rs = pStr.executeQuery();
			// Query를 실행하고 DBMS가 보낸 결과를 ResultSet 데이터 구조로 바꾸어 return
			// ResultSet 구조의 객체를 선언하여 데이터를 수신

			/*
			 * ResultSet : DBMS가 보낸 데이터를 배열형태로 보관을 하고 next() 메서드를 실행하면 한줄씩 데이터를 읽어 온다. 단 읽는
			 * 방향은 일방통행 (처음 ~ 끝)
			 */
//			if(rs.next()) System.out.println("데이터가 있다");
//			else System.out.println("데이터가 없다");
			// .next() : 데이터가 있으면 true, 없으면 false

			stdList = new ArrayList<StudentVO>();
			while (rs.next()) {
				StudentVO vo = new StudentVO();

				// old 코드
				vo.setSt_no(rs.getString(1));
				vo.setSt_name(rs.getString(2));
				vo.setSt_addr(rs.getString(3));
				vo.setSt_grade(rs.getInt(4));
				vo.setSt_height(rs.getInt(5));
				vo.setSt_weight(rs.getInt(6));
				vo.setSt_nick(rs.getString(7));
				vo.setSt_nick_rem(rs.getString(8));
				vo.setSt_dept_name(rs.getString(9));

				// new 코드
				vo.setSt_no(rs.getString("st_no"));
				vo.setSt_name(rs.getString("st_name"));
				vo.setSt_addr(rs.getString("st_addr"));
				vo.setSt_grade(rs.getInt("st_grade"));
				vo.setSt_height(rs.getInt("st_height"));
				vo.setSt_weight(rs.getInt("st_weight"));
				vo.setSt_nick(rs.getString("st_nick"));
				vo.setSt_nick_rem(rs.getString("st_nick_rem"));
				vo.setSt_dept_name(rs.getString("st_dept_name"));

				stdList.add(vo);
			}
			return stdList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return null;
	}

	@Override
	public StudentVO findByNum(String st_num) {
		// TODO 학번을 매개변수로 받아 한 학생의 정보를 리턴하는 메서드
		String sql = " SELECT * FROM tbl_student ";
		sql += " WHERE st_no = " + st_num;
		// sql문을 작성할때 시작되는 곳과 끝나는 곳에 스페이스 필수
		PreparedStatement ps = null;

		try {
			ps = dbConn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			// rs에 담긴 데이터는 1개
			StudentVO vo = null;
			if (rs.next()) {
				vo = new StudentVO();
				vo.setSt_no(rs.getString("st_no"));
				vo.setSt_name(rs.getString("st_name"));
				vo.setSt_addr(rs.getString("st_addr"));
				vo.setSt_grade(rs.getInt("st_grade"));
				vo.setSt_height(rs.getInt("st_height"));
				vo.setSt_weight(rs.getInt("st_weight"));
				vo.setSt_nick(rs.getString("st_nick"));
				vo.setSt_nick_rem(rs.getString("st_nick_rem"));
				vo.setSt_dept_name(rs.getString("st_dept_name"));
				return vo;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

		return null;
	}

	@Override
	public void insert(StudentVO vo) {
		// TODO 학생정보를 vo에 받아 db에 insert 수행
		String sql = " INSERT INTO tbl_student ( ";
		sql += " ST_NO, "; // 1
		sql += " ST_NAME, "; // 2
		sql += " ST_ADDR, "; // 3
		sql += " ST_GRADE, "; // 4
		sql += " ST_HEIGHT, "; // 5
		sql += " ST_WEIGHT, "; // 6
		sql += " ST_NICK, "; // 7
		sql += " ST_NICK_REM, "; // 8
		sql += " ST_DEPT_NAME ) "; // 9
		sql += " VALUES(?,?,?,?,?,?,?,?,?) ";

		PreparedStatement ps = null;

		try {
			ps = this.dbConn.prepareStatement(sql);
			ps.setString(1, vo.getSt_no());
			ps.setString(2, vo.getSt_name());
			ps.setString(3, vo.getSt_addr());
			ps.setInt(4, vo.getSt_grade());
			ps.setInt(5, vo.getSt_height());
			ps.setInt(6, vo.getSt_weight());
			ps.setString(7, vo.getSt_nick());
			ps.setString(8, vo.getSt_nick_rem());
			ps.setString(9, vo.getSt_dept_name());

			ps.executeUpdate();
			// Insert,Update,Delete 다 변경되는 것이므로 executeUpdate
			System.out.println("추가 성공");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(StudentVO vo) {
		// TODO vo에 값을 받아서 기존 데이터를 Update 수행
		String sql = " UPDATE tbl_student SET";

		sql += " ST_NAME = ?, "; // 2
		sql += " ST_ADDR = ?, "; // 3
		sql += " ST_GRADE = ?, "; // 4
		sql += " ST_HEIGHT = ?, "; // 5
		sql += " ST_WEIGHT = ?, "; // 6
		sql += " ST_NICK = ?, "; // 7
		sql += " ST_NICK_REM = ?, "; // 8
		sql += " ST_DEPT_NAME = ? "; // 9
		sql += " WHERE ST_NO = ? ";

		PreparedStatement ps = null;

		try {
			ps = this.dbConn.prepareStatement(sql);

			ps.setString(9, vo.getSt_no());
			ps.setString(1, vo.getSt_name());
			ps.setString(2, vo.getSt_addr());
			ps.setInt(3, vo.getSt_grade());
			ps.setInt(4, vo.getSt_height());
			ps.setInt(5, vo.getSt_weight());
			ps.setString(6, vo.getSt_nick());
			ps.setString(7, vo.getSt_nick_rem());
			ps.setString(8, vo.getSt_dept_name());

			ps.executeUpdate();

			System.out.println("업데이트 성공");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public void delete(String st_num) {
		// TODO 여기는 한개의 레코드(데이터)를 삭제하는 메서드이다

		String sql = " DELETE FROM tbl_student ";
		sql += " WHERE st_no = " + st_num;

		PreparedStatement ps = null;

		try {
			ps = this.dbConn.prepareStatement(sql);
			int ret = ps.executeUpdate();

			// 1번 검증 방법
			if (ret > 0)
				System.out.println(ret + "레코드 삭제 성공");
			else
				System.out.println("삭제할 데이터가 없음 ");
			// 2번 검증 방법
			if (ret == 0)
				System.out.println("삭제할 데이터가 없음");
			else
				System.out.println(ret + "레코드 삭제 성공");
			// 첫번쨰 코드(if문)에 비해 두번째 코드는 정확하지 않을 수 있음
			// 삭제 등등 이유로 ret가 - 값을 가질 수 있음
			// 그렇게 되면 2번째 코드의 경우 -x 레코드 삭제 성공이라는 메시지가 출력이 됨

			/*
			 * 삭제가 정상적으로 이루어졌는지 알아보는 방법으로 위의 2가지 코드가 있지만 DBMS 서버와 통신(데이터 주고 받기)과정에서 레코드가
			 * 정상적으로 삭제되면 반드시 0이상의 값을 return해서 ret값에 담아주지만 경우에 따라 레코드 삭제가 이루어지지 않았을 경우 삭제할
			 * 레코드가 없는 경우도 있고, 이 경우 0을 return할 것이다 하지만 어떠한 이유로 레코드가 있음에도 삭제가 이루어지지 않았을 경우 0
			 * 미만의 값을 return하는 경우도 있다 이런 경우 2번의 방법에서는 정상적으로 삭제가 이루어졌다는 메시지를 보이게 된다 따라서 1번
			 * 방법이 안전한 코드 작성법이다
			 */

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String makeStNo() {

		String sql = " SELECT LPAD(MAX(st_no) +1,3,'0') FROM tbl_student ";

		PreparedStatement ps = null;

		try {
			ps = this.dbConn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String strNum = rs.getString(1);
				return strNum;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
