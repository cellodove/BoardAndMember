package min.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import min.board.dbcp.Context;
import min.board.model.MemberDTO;

public class MemberDAO {

	MemberDTO memberDTO = new MemberDTO();

	// 멤버 DTO에서 인스턴스 변수들을 다 가지고온다.
	public void join(MemberDTO memberDTO) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Context context = new Context();
		String sql = null;

		try {

			// 커넥션인스턴스를 커넥션풀이랑 연결시켜줌
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();

			sql = "Insert into member";
			sql += " values (member_seq.nextval,?,?,?,?,?)";

			preparedStatement = connection.prepareStatement(sql);

			// 물음표의 순서대로 넣을 값을입력 맴버DTO에서 값을 받아옴
			preparedStatement.setString(1, memberDTO.getName());
			preparedStatement.setString(2, memberDTO.getId());
			preparedStatement.setString(3, memberDTO.getPasswd());
			preparedStatement.setInt(4, memberDTO.getAge());
			preparedStatement.setString(5, memberDTO.getEmail());
			int count = preparedStatement.executeUpdate();
			if (count > 0) {
				System.out.println("회원가입이 완료되었습니다.");
				connection.commit();

			} else {
				connection.rollback();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}

	}

	public MemberDTO login(String id, String passwd) {
		// 초기화
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		MemberDTO memberDTO = null;
		String sql = null;
		Context context = new Context();

		try {
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			sql = "select * from member";
			sql += " where id=? and passwd=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, passwd);
			resultSet = preparedStatement.executeQuery();

			// 값이 있는동안
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				id = resultSet.getString("id");
				passwd = resultSet.getString("passwd");
				int age = resultSet.getInt("age");
				String email = resultSet.getString("email");

				memberDTO = new MemberDTO();
				memberDTO.setName(name);
				memberDTO.setId(id);
				memberDTO.setPasswd(passwd);
				memberDTO.setAge(age);
				memberDTO.setEmail(email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return memberDTO;
	}

	// 아이디가 있는지 중복확인을위한 메소드
	public boolean idUniqe(String id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = null;
		Context context = new Context();

		try {

			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();

			sql = "select * from member";
			sql += " where id = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();

			// 이미 아이디가 있다면
			if (resultSet.next()) {
				id = resultSet.getString("id");
				System.out.println("중복된 아이디입니다. 다시 입력 해주세요");
				System.out.println();
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return true;
	}

	public MemberDTO select() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = null;
		// 여기서만 사용되는 DTO 인스턴스
		MemberDTO memberDTO = null;

		try {

			Context context = new Context();
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			sql = "select num, name, id, passwd, age, email from member";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				memberDTO = new MemberDTO();
				String name = resultSet.getString("name");
				String id = resultSet.getString("id");
				String passwd = resultSet.getString("passwd");
				int age = resultSet.getInt("age");
				String email = resultSet.getString("email");

				memberDTO = new MemberDTO();
				memberDTO.setName(name);
				memberDTO.setId(id);
				memberDTO.setPasswd(passwd);
				memberDTO.setAge(age);
				memberDTO.setEmail(email);

			}
			resultSet.getRow();
			if (resultSet.getRow() == 0) {
				System.out.println("등록한 회원이 없습니다.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return memberDTO;

	}

	public MemberDTO update(String id, String passwd, String name, int age, String email) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = null;
		MemberDTO memberDTO = new MemberDTO();
		try {
			Context context = new Context();
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			sql = "update member set name = ?, passwd = ?, age =?, email =? ";
			sql += " where id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, passwd);
			preparedStatement.setInt(3, age);
			preparedStatement.setString(4, email);
			preparedStatement.setString(5, id);
			preparedStatement.executeUpdate();
			System.out.println("회원정보를 수정하였습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return memberDTO;

	}

	public MemberDTO delete(String id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = null;
		MemberDTO memberDTO = new MemberDTO();
		try {
			Context context = new Context();
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			sql = "delete from member ";
			sql += " where id = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			preparedStatement.executeUpdate();
			System.out.println("회원을 삭제하였습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return memberDTO;
	}

}
