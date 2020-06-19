package min.board.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import min.board.dao.BoardDAO;
import min.board.dao.MemberDAO;
import min.board.find.FindID;
import min.board.model.MemberDTO;
import min.service.board.BoardDelete;
import min.service.board.BoardUpdate;
import min.service.board.BoardWrite;
import min.service.member.MemberJoin;

public class BoardMain {
	public static void boardmain() throws IOException {
		boolean isStop = false;
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		BoardDAO boardDAO = new BoardDAO();
		do {
			System.out.println();
			System.out.println("==게시판 로그인 페이지==");
			System.out.print("id: ");
			String id = bufferedReader.readLine();
			System.out.print("비밀번호: ");
			String passwd = bufferedReader.readLine();
			MemberDAO memberDAO = new MemberDAO();
			MemberDTO memberDTO = memberDAO.login(id, passwd);
			if (memberDTO == null) {
				System.out.println("아이디와 비밀번호가 존재하지 않습니다.");
				System.out.println("회원 가입을 해 주십시오.");
				System.out.print("회원 가입을 하시겠습니까?(y/n) : ");
				String choice = bufferedReader.readLine();
				switch (choice) {
				case "y":
					MemberJoin.join(bufferedReader);
					break;
				case "n":
					LoginMain.main(null);
					break;
				default:
					System.out.println("잘못 입력하셨습니다.");
				}
			} else {
				System.out.println("로그인이 성공하였습니다");
			}
			System.out.println();
			System.out.println("==게시판 페이지==");
			System.out.print("1.등록");
			System.out.print("\t2.검색(아이디)");
			System.out.print("\t3.목록");
			System.out.print("\t4.수정");
			System.out.print("\t5.삭제");
			System.out.print("\t6.회원관리");
			System.out.println("\t7.종료");
			System.out.println("번호를 선택하세요.");
			System.out.print("번호 입력: ");
			String menu = null;
			menu = bufferedReader.readLine();
			switch (menu) {
			case "1":
				BoardWrite writeArticle = new BoardWrite();
				writeArticle.execute(bufferedReader);
				BoardMainLogin.boardlogin();
				break;
			case "2":
				FindID findID = new FindID();
				findID.execute(bufferedReader);
				BoardMainLogin.boardlogin();
				break;
			case "3":
				boardDAO.showListBoard();
				BoardMainLogin.boardlogin();
				break;
			case "4":
				BoardUpdate updateArticle = new BoardUpdate();
				updateArticle.execute(bufferedReader);
				BoardMainLogin.boardlogin();
				break;
			case "5":
				BoardDelete deleteArticle = new BoardDelete();
				deleteArticle.execute(bufferedReader);
				BoardMainLogin.boardlogin();
				break;
			case "6":
				MemberMain.main(null);
				break;
			case "7":
				System.exit(0);
				break;
			}
		} while (!isStop);
	}
}