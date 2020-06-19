package min.service.board;

import java.io.BufferedReader;
import java.io.IOException;
import min.board.control.SJMAction;
import min.board.dao.BoardDAO;
import min.board.model.BoardDTO;

public class BoardUpdate implements SJMAction {
	@Override
	public void execute(BufferedReader bufferedReader) throws IOException {
		System.out.println("수정할 글 번호를 입력하세요.");
		System.out.print("글 번호: ");
		int num = Integer.parseInt(bufferedReader.readLine());
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = boardDAO.showUpdateBoard(num);
		if (boardDTO == null) {
			System.out.println("수정할 글 번호(" + num + ")가 없습니다.");
		} else {
			System.out.println("수정할 데이터를 입력하세요.");
			System.out.println("원래 비밀번호 : " + boardDTO.getPasswd());
			System.out.print("수정할 비밀번호 : ");
			String passwd = bufferedReader.readLine();
			System.out.println("원래 제목 : " + boardDTO.getSubject());
			System.out.print("수정할 제목 : ");
			String subject = bufferedReader.readLine();
			System.out.println("원래 내용 : " + boardDTO.getContent());
			System.out.print("수정할 내용 : ");
			String content = bufferedReader.readLine();
			boardDTO.setPasswd(passwd);
			boardDTO.setSubject(subject);
			boardDTO.setContent(content);
			boardDAO.updateBoard(boardDTO);
		}
	}
}