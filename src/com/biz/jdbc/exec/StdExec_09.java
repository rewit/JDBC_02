package com.biz.jdbc.exec;

import java.util.Scanner;

import com.biz.jdbc.model.StudentVO;
import com.biz.jdbc.service.StdService;
import com.biz.jdbc.service.StdServiceImp_01;

public class StdExec_09 {

	/*
	 * 키보드에서 학생정보를 입력받아서 (학번, 이름, 학년, 학과) 계속해서 insert를 수행하는 코드 작성
	 */

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		StdService ss = new StdServiceImp_01();

		while (true) {
			System.out.println("학번>> (종료 : -E)");
			String strNo = scan.nextLine();
			if (strNo.equalsIgnoreCase("-E")) break;

			System.out.print("이름>>");
			String strName = scan.nextLine();

			System.out.print("학년>>");
			String strGrade = scan.nextLine();
			int intGrade = Integer.valueOf(strGrade);

			System.out.print("학과>>");
			String strDept = scan.nextLine();

			StudentVO vo = new StudentVO();

			vo.setSt_no(strNo);
			vo.setSt_grade(intGrade);
			vo.setSt_dept_name(strDept);
			vo.setSt_name(strName);

			ss.insert(vo);
		}
	}
}
