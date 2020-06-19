package min.service.member;

import java.io.BufferedReader;
import java.io.IOException;

import min.board.control.SJMAction;
import min.board.dao.MemberDAO;
import min.board.model.MemberDTO;
import min.board.view.MemberMain;

public class MemberList implements SJMAction {
	@Override
	public void execute(BufferedReader bufferedReader) throws IOException {
		System.out.println();
		System.out.println("==회원정보 확인 페이지==");
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
				id = memberDTO.getId();
				passwd = memberDTO.getPasswd();
				String name = memberDTO.getName();
				int age = memberDTO.getAge();
				String email = memberDTO.getEmail();
				System.out.println("회원 정보입니다.");
				System.out.print("1.아이디: " + id + " ");
				System.out.print("2.비밀번호: " + passwd + " ");
				System.out.print("3.이름: " + name + " ");
				System.out.print("4.나이: " + age);
				System.out.print("4.이메일: " + email);
				System.out.println();
			}
		}

	}

}
