package min.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import min.board.dbcp.Context;
import min.board.model.BoardDTO;

public class BoardDAO { // 게시판 글 등록
	public void writeBoard(BoardDTO boardDTO) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = null;
		Context context = new Context();
		try {
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			sql = "insert into board ";
			sql += " values(board_seq.nextval,?,?,?,?) ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, boardDTO.getId());
			preparedStatement.setString(2, boardDTO.getPasswd());
			preparedStatement.setString(3, boardDTO.getSubject());
			preparedStatement.setString(4, boardDTO.getContent());
			int count = preparedStatement.executeUpdate();
			if (count > 0) {
				System.out.println("글을 등록하였습니다.");
				connection.commit();
			} else {
				connection.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	} // 조인을 통한 아이디 검색

	public void IDfind(String id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = null;
		Context context = new Context();
		try {
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			sql = "select b.num, b.id, b.passwd, b.subject, b.content, m.name ";
			sql += " from board b, member m ";
			sql += " where b.id=m.id and b.id=? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int num = resultSet.getInt("num");
				String name = resultSet.getString("name");
				String passwd = resultSet.getString("passwd");
				String subject = resultSet.getString("subject");
				String content = resultSet.getString("content");
				System.out.print("글 번호: " + num);
				System.out.print(", 아이디= " + id);
				System.out.print(", 작성자= " + name);
				System.out.print(", 비밀번호= " + passwd);
				System.out.print(", 글 제목= " + subject);
				System.out.print(", 글 내용= " + content);
				System.out.println();
			}
			resultSet.getRow();
			if (resultSet.getRow() == 0) {
				System.out.println("입력하신 아이디로 작성된 글이 없습니다.");
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
	} // 조인을 통한 게시판 리스트

	public void showListBoard() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = null;
		Context context = new Context();
		try {
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			sql = "select b.num, b.id, b.subject, b.content, m.name ";
			sql += " from board b,member m ";
			sql += " where b.id = m.id ";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int num = resultSet.getInt("num");
				String id = resultSet.getString("id");
				String name = resultSet.getString("name");
				String subject = resultSet.getString("subject");
				String content = resultSet.getString("content");
				System.out.print("글 번호: " + num);
				System.out.print(", 아이디= " + id);
				System.out.print(", 작성자= " + name);
				System.out.print(", 글 제목= " + subject);
				System.out.print(", 글 내용= " + content);
				System.out.println();
			}
			resultSet.getRow();
			if (resultSet.getRow() == 0) {
				System.out.println("작성된 글이 없습니다. 글을 등록해 주세요.");
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
	} // 게시판 글 수정

	public int updateBoard(BoardDTO boardDTO) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int updateCount = 0;
		String sql = null;
		Context context = new Context();
		try {
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			sql = "update board ";
			sql += " set id=?, passwd=?, subject=?, content=? where num=? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, boardDTO.getId());
			preparedStatement.setString(2, boardDTO.getPasswd());
			preparedStatement.setString(3, boardDTO.getSubject());
			preparedStatement.setString(4, boardDTO.getContent());
			preparedStatement.setInt(5, boardDTO.getNum());
			updateCount = preparedStatement.executeUpdate();
			if (updateCount > 0) {
				System.out.println("수정하였습니다.");
				connection.commit();
			} else {
				connection.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return updateCount;
	} // 게시판 글 수정 리스트

	public BoardDTO showUpdateBoard(int num) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = null;
		BoardDTO boardDTO = null;
		Context context = new Context();
		try {
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			sql = "select * from board ";
			sql += " where num=? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, num);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int upnum = resultSet.getInt("num");
				String id = resultSet.getString("id");
				String passwd = resultSet.getString("passwd");
				String subject = resultSet.getString("subject");
				String content = resultSet.getString("content");
				boardDTO = new BoardDTO();
				boardDTO.setNum(upnum);
				boardDTO.setId(id);
				boardDTO.setPasswd(passwd);
				boardDTO.setSubject(subject);
				boardDTO.setContent(content);
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
		return boardDTO;
	} // 게시판 글 삭제

	public int deleteBoard(int num, String passwd) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int deletecount = 0;
		String sql = null;
		Context context = new Context();
		try {
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();
			sql = "delete board ";
			sql += " where num=? and passwd=? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, num);
			preparedStatement.setString(2, passwd);
			deletecount = preparedStatement.executeUpdate();
			if (deletecount > 0) {
				System.out.println("삭제하였습니다.");
				connection.commit();
			} else {
				System.out.println("글 번호와 글 비밀번호를 확인 해주세요.");
				connection.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return deletecount;
	}
}
