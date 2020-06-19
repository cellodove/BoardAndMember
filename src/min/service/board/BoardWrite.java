package min.service.board;

import java.io.BufferedReader;
import java.io.IOException;
import min.board.control.SJMAction;
import min.board.dao.BoardDAO;
import min.board.model.BoardDTO;

public class BoardWrite implements SJMAction {
	@Override
	public void execute(BufferedReader bufferedReader) throws IOException {
		System.out.println("----게시물 등록 페이지----");
		System.out.print("작성자 아이디: ");
		String id = bufferedReader.readLine();
		System.out.print("글 비밀번호: ");
		String passwd = bufferedReader.readLine();
		System.out.print("글 제목: ");
		String subject = bufferedReader.readLine();
		System.out.print("글 내용: ");
		String content = bufferedReader.readLine();
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setId(id);
		boardDTO.setPasswd(passwd);
		boardDTO.setSubject(subject);
		boardDTO.setContent(content);
		boardDAO.writeBoard(boardDTO);
	}
}