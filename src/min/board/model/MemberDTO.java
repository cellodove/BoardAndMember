package min.board.model;

//회원관리의 인스턴스 변수모음
public class MemberDTO {

	private int num;
	//조인키 외래키연결 컬럼
	private String id;
	private String passwd;
	private String name;
	private int age;
	private String email;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// 값이 제대로 들어갔는지 확인하기위해 작성
	@Override
	public String toString() {
		return "MemberDTO [num=" + num + ", id=" + id + ", passwd=" + passwd + ", name=" + name + ", age=" + age
				+ ", email=" + email + "]";
	}

}
