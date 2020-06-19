package min.board.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import min.service.member.MemberLogin;

public class LoginMain {
	public static void main(String[] args) throws IOException {

		// 입력정의
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		// do while문 종료하기위한 장치
		boolean isStop = false;

		do {
			System.out.println();
			System.out.println("========게시판========");
			System.out.print("1. 로그인 ");
			System.out.print("2. 회원가입 ");
			System.out.print("3. 게시판 ");
			System.out.print("4. 종료 ");

			System.out.println("번호를 선택하세요");
			System.out.print("번호입력");
			
			//스위치문을 사용하기위한 버튼
			String menu = null;
			//메뉴로 정수형 입력받는다.
			menu = bufferedReader.readLine();

			switch (menu) {
			case "1":
				MemberLogin memberLogin = new MemberLogin( );
				memberLogin.execute(bufferedReader);

				break;

			case "2":
				MemberMain.main(null);
				break;

			case "3":
				//BoardMain.boardmain( );
				break;

			case "4":
				System.exit(0);
				break;

			}

		} while (isStop);

		
	}

}
