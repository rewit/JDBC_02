package com.biz.jdbc.exec;

import com.biz.jdbc.model.StudentVO;
import com.biz.jdbc.service.StdService;
import com.biz.jdbc.service.StdServiceImp_01;

public class StdExec_04 {

	public static void main(String[] args) {

		StdService ss = new StdServiceImp_01();
		StudentVO stdVo = new StudentVO();
		
		stdVo.setSt_no("101");
		stdVo.setSt_name("이몽룡");
		stdVo.setSt_grade(3);
		stdVo.setSt_dept_name("컴공과");
		
		ss.insert(stdVo);
		
		
		
	}

}
