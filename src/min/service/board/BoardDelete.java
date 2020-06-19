package min.service.board;

import java.io.BufferedReader;
import java.io.IOException;
import min.board.control.SJMAction;
import min.board.dao.BoardDAO;

public class BoardDelete implements SJMAction {
	@Override
	public void execute(BufferedReader bufferedReader) throws IOException {
		BoardDAO boardDAO = new BoardDAO();
		System.out.print("삭제할 글 번호를 입력하세요.: ");
		int num = Integer.parseInt(bufferedReader.readLine());
		System.out.print("삭제할 글의 비밀번호를 입력하세요.: ");
		String passwd = bufferedReader.readLine();
		boardDAO.deleteBoard(num, passwd);
	}
}