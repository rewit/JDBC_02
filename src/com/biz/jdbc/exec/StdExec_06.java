package com.biz.jdbc.exec;

import com.biz.jdbc.model.StudentVO;
import com.biz.jdbc.service.StdService;
import com.biz.jdbc.service.StdServiceImp_01;

public class StdExec_06 {

	public static void main(String[] args) {

		StdService ss = new StdServiceImp_01();
		
		String stNo = "101";
		
		StudentVO vo = ss.findByNum(stNo);
		
		if(vo == null) System.out.println("학생 데이터가 없음");
		else {
			//조회한 vo에서 stNo를 추출하여 그 값을 기준으로 삭제
			ss.delete(vo.getSt_no());
			
			//선택(입력)한 학번을 기준으로 삭제 
			ss.delete(stNo);
		}
		
	}

}
