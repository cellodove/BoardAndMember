package min.service.member;

import java.io.BufferedReader;
import java.io.IOException;

import min.board.dao.MemberDAO;
import min.board.model.MemberDTO;

//회원가입 클래스
public class MemberJoin {
	// 회원가입 메소드
	public static void join(BufferedReader bufferedReader) throws IOException {
		// 멤버DAO에 넣기위해 인스턴스 생성
		MemberDAO memberDAO = new MemberDAO();
		String id = null;
		System.out.println();
		System.out.println("===회원정보 입력페이지===");

		// 아이디가 있는 상태인지 확인하는 작업 DB에 데이터가 없으면 회원가입 진행됨
		do {
			System.out.println("아이디 (30자 이내)");
			id = bufferedReader.readLine();
		} while (!memberDAO.idUniqe(id));

		System.out.println("비밀번호 (16자이내)");
		String passwd = bufferedReader.readLine();
		
		System.out.println("이름");
		String name = bufferedReader.readLine();

		System.out.println("나이 (숫자만입력)");
		int age = Integer.parseInt(bufferedReader.readLine());

		System.out.println("이메일");
		String email = bufferedReader.readLine();

		// 받은 변수값을 멤버DTO에 넣어준다.
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId(id);
		memberDTO.setPasswd(passwd);
		memberDTO.setName(name);
		memberDTO.setAge(age);
		memberDTO.setEmail(email);
		// 조인메소드로 값을 넘긴다.
		memberDAO.join(memberDTO);

	}

}
