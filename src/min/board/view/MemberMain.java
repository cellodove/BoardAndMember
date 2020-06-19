package min.board.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import min.service.member.MemberDelete;
import min.service.member.MemberJoin;
import min.service.member.MemberList;
import min.service.member.MemberUpdate;

public class MemberMain {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		boolean isStop = false;

		do {
			System.out.println();
			System.out.println("====회원 관리====");
			System.out.print("1.회원가입  ");
			System.out.print("2.회원정보 확인  ");
			System.out.print("3.회원정보 수정  ");
			System.out.print("4.회원정보 삭제  ");
			System.out.println("5.종료");
			System.out.println("번호를 입력하세요");
			System.out.print("번호입력:");

			String menu;
			menu = bufferedReader.readLine();

			switch (menu) {
			case "1":
				MemberJoin.join(bufferedReader);
				break;
			case "2":
				MemberList memberList = new MemberList();
				memberList.execute(bufferedReader);
				break;
			case "3":
				MemberUpdate memberUpdate = new MemberUpdate( );
				memberUpdate.execute(bufferedReader);
				break;
			case "4":
				MemberDelete memberDelete = new MemberDelete();
				memberDelete.execute(bufferedReader);
				break;
			case "5":
				System.exit(0);
				break;

			}

		} while (isStop);

	}

}
