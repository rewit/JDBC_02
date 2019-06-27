package com.biz.jdbc.exec;

import java.util.Scanner;

import com.biz.jdbc.model.StudentVO;
import com.biz.jdbc.service.StdService;
import com.biz.jdbc.service.StdServiceImp_01;

public class StdExec_08 {

	/*
	 * 키보드에서 학번을 입력받고 해당하는 학번의 학생정보를 보여준 후 다시 키보드에서 주소를 입력받아서 학생정보를 Update 실행
	 */

	public static void main(String[] args) {

		StdService ss = new StdServiceImp_01();
		Scanner scan = new Scanner(System.in);

		while (true) {
			System.out.println("======================================");
			System.out.println("학번>> (종료 : -E)");
			String strNo = scan.nextLine();
			if (strNo.equalsIgnoreCase("-E"))
				break;

			StudentVO vo = ss.findByNum(strNo);

			if (vo == null) {
				System.out.println("학생정보 없음");
				continue;
			}
				System.out.println(vo);
				System.out.println("=================================");

				System.out.println("주소>>");
				String strAddr = scan.nextLine();
				if(strAddr.isEmpty()) continue;
				//주소를 입력하지 않으면 업데이트 하지 않고 처음으로
			
				vo.setSt_addr(strAddr);
				ss.update(vo);
		}
	}
}
