package min.board.control;

import java.io.BufferedReader;
import java.io.IOException;

public interface SJMAction {
	//공동사용 메서드를 만든다 글자를 입력할때 버퍼를 이용해서 받는다.
	public void execute(BufferedReader bufferedReader)throws IOException;

}
