package com.biz.jdbc.service;

import java.util.List;

import com.biz.jdbc.model.StudentVO;

/*
 * CRUD를 구현
 * 1. DB Connection을 설정 
 * 		- 실제 구현할 클래스에서 만들 부분 (service에서는 구현 안함)
 * 2. SELECT 구현
 * 3. INSERT 구현
 * 4. UPDATE, DELETE 구현
 */
public interface StdService {

	//전체 리스트를 가져올 method
		// DBMS에게 SELECT * FROM [table]을 실행하여
		// 전체 리스트를 가져오는 일을 수행
		// 가져온 리스트를 java의 List 자료구조로 변화시켜서
		// 요청한 곳에 return
	public List<StudentVO> selcetAll();
	
	//학번을 기준으로 한 학생의 정보를 가져올 method
		// 학번을 매개변수로 전달하고 학번을 WHERE로 하여
		// SELECT를 수행한 후 한 학생의 정보를 가져와서
		// VO에 담아 요청한 곳에 return 
	public StudentVO findByNum(String st_num);

	//학생정보를 DB에 INSERT할 method
		// 추가하고자 하는 학생의 정보를 vo에 담아서
		// 매개변수로 전달해 주고 INSERT를 수행하도록 한다
	public void insert(StudentVO vo);
	
	//학생정보를 수정(UPDATE)할 method
		//UPDATE를 수행할 때는
		// 1. 학생정보(한 사람)를 조회하고 = VO에 받기
		// 2. 수정할 칼럼(항목)이 있으면 그 칼럼의 값만 변경
		// 3. 나머지 값은 그대로 유지
		// 4. VO에 담겨있는 값을 매개변수로 전달하여
		// 5. UPDATE를 수행
	public void update(StudentVO vo);
	
	//학생정보를 삭제(DELETE)할 method
		// 삭제할때는 학번 하나만 매개변수로 전달하고
		// DELETE를 수행한다.
	public void delete(String st_num);
	
	
}
