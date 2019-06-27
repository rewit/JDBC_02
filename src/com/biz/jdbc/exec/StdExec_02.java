package com.biz.jdbc.exec;

import com.biz.jdbc.model.StudentVO;
import com.biz.jdbc.service.StdService;
import com.biz.jdbc.service.StdServiceImp_01;

public class StdExec_02 {

	public static void main(String[] args) {

		StdService ss = new StdServiceImp_01();
		
		StudentVO stdVo = ss.findByNum("001");
		
		if(stdVo == null) System.out.println("찾는 학생이 없습니다");
		else System.out.println(stdVo.toString());
		
	}

}
