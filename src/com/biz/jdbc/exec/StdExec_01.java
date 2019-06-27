package com.biz.jdbc.exec;

import java.util.List;

import com.biz.jdbc.model.StudentVO;
import com.biz.jdbc.service.StdService;
import com.biz.jdbc.service.StdServiceImp_01;

public class StdExec_01 {

	public static void main(String[] args) {

		StdService ss = new StdServiceImp_01();
		List<StudentVO> stdList = null;

		ss.selcetAll();
		ss.selcetAll();
		stdList = ss.selcetAll();

		if (stdList == null)
			System.out.println("데이터가 없음");
		else {
			System.out.println("리스트개수" + stdList.size());
			for (StudentVO vo : stdList) {
				System.out.println(vo);
			}
		}

	}

}
