package min.service.member;

import java.io.BufferedReader;
import java.io.IOException;

import min.board.control.SJMAction;
import min.board.dao.MemberDAO;
import min.board.model.MemberDTO;
import min.board.view.BoardMainLogin;
import min.board.view.LoginMain;

public class MemberLogin implements SJMAction {
	//인터페이스 추상메소드 물려받음
	@Override
	public void execute(BufferedReader bufferedReader) throws IOException {
		boolean isStop = false;

		do {
			System.out.println();
			System.out.println("===로그인 페이지===");

			System.out.println("ID");
			String id = bufferedReader.readLine();

			System.out.println("PassWord");
			String passwd = bufferedReader.readLine();

			MemberDAO memberDAO = new MemberDAO();
			MemberDTO memberDTO = memberDAO.login(id, passwd);

			if (memberDTO == null) {
				System.out.println("아이디와 비밀번호가 존재하지 않습니다.");
				System.out.println("회원가입을 해주세요");
				System.out.println("회원가입 하실래요?? [y/n]");
				String choice = bufferedReader.readLine();

				switch (choice) {
				case "y":
					MemberJoin.join(bufferedReader);
					break;

				case "n":
					LoginMain.main(null);
					break;

				default:
					System.out.println("잘못 입력하셨습니다");
					break;
				}

			} else {
				System.out.println("로그인 성공!");
				BoardMainLogin.boardlogin();
			}

		} while (isStop);

	}

}
