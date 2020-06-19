package min.board.model;

//게시판의 인스턴스 변수 모음
public class BoardDTO {
	private int num;
	//조인키 외래키연결 컬럼
	private String id;
	private String passwd;
	private String subject;
	private String content;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	// 값이 제대로 들어갔는지 확인하기위해 작성
	@Override
	public String toString() {
		return "BoardDTO [num=" + num + ", id=" + id + ", passwd=" + passwd + ", subject=" + subject + ", content="
				+ content + "]";
	}

}
