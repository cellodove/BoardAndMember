package min.service.member;

import java.io.BufferedReader;
import java.io.IOException;
import min.board.control.SJMAction;
import min.board.dao.MemberDAO;
import min.board.model.MemberDTO;
import min.board.view.MemberMain;

public class MemberUpdate implements SJMAction {
	@Override
	public void execute(BufferedReader bufferedReader) throws IOException {
		System.out.println();
		System.out.println("==회원정보 수정 페이지==");
		System.out.println("회원 정보를 수정합니다.");
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
			System.out.println("수정할 회원 정보입니다.");
			System.out.println("패스워드 : " + memberDTO.getPasswd());
			System.out.print("패스워드 수정 : ");
			passwd = bufferedReader.readLine();
			System.out.println("이름 : " + memberDTO.getName());
			System.out.print("이름 수정 : ");
			String name = bufferedReader.readLine();
			System.out.println("나이 : " + memberDTO.getAge());
			System.out.print("나이 수정 : ");
			int age = Integer.parseInt(bufferedReader.readLine());
			System.out.println("이 메일 : " + memberDTO.getEmail());
			System.out.print("이 메일 수정 : ");
			String email = bufferedReader.readLine();
			memberDAO.update(id, passwd, name, age, email);
		}
	}
}