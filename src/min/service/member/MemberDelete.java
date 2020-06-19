package min.service.member;

import java.io.BufferedReader;
import java.io.IOException;
import min.board.control.SJMAction;
import min.board.dao.MemberDAO;
import min.board.model.MemberDTO;
import min.board.view.MemberMain;

public class MemberDelete implements SJMAction {
	@Override
	public void execute(BufferedReader bufferedReader) throws IOException {
		System.out.println();
		System.out.println("==회원정보 탈퇴 페이지==");
		System.out.println("회원에서 탈퇴합니다.");
		System.out.print("id: ");
		String id = bufferedReader.readLine();
		System.out.print("비밀번호: ");
		String passwd = bufferedReader.readLine();
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = memberDAO.login(id, passwd);
		if (memberDTO == null) {
			System.out.println("아이디와 비밀번호가 존재하지 않습니다.");
			MemberMain.main(null);
		} else {
			if (memberDTO.getId().equals(id)) {
				System.out.print("회원에서 탈퇴하시겠습니까?(y/n) : ");
				String choice = bufferedReader.readLine();
				switch (choice) {
				case "y":
					memberDAO.delete(id);
					break;
				case "n":
					System.out.println("프로그램이 종료됩니다.");
					break;
				default:
					System.out.println("잘못 입력하셨습니다.");
				}
			}
		}
	}
}