package com.office.library.book.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.office.library.book.BookVo;

@Component
public class BookDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public boolean isISBN(String b_isbn) {
		System.out.println("[BookDao] isISBN()");
		String sql = "SELECT COUNT(*) FROM tbl_book " + "WHERE b_isbn = ?";
		int result = jdbcTemplate.queryForObject(sql, Integer.class, b_isbn);
		
		return result > 0 ? true : false;
	}

	public int insertBook(BookVo bookVo) {
		System.out.println("[BookDao] insertBook()");
		
		String sql = "INSERT INTO tbl_book(b_thumbnail, " 
					+ "b_name, " + "b_author, " + "b_publisher, "
					+ "b_publish_year, " + "b_isbn, " + "b_call_number, "
					+ "b_rantal_able, " + "b_reg_date, " + "b_mod_date) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
		int result = -1;
		
		try {
			result = jdbcTemplate.update(sql, 
					bookVo.getB_thumbnail(), bookVo.getB_name(),
					bookVo.getB_author(), bookVo.getB_publisher(),
					bookVo.getB_publish_year(), bookVo.getB_isbn(),
					bookVo.getB_call_number(), bookVo.getB_rantal_able());
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<BookVo> selectBooksBySearch(BookVo bookVo) {
		System.out.println("[BookDao] selectBooksBySearch()");
		String sql = "SELECT * FROM tbl_book "
				+ "WHERE b_name LIKE ? "
				+ "ORDER BY b_no DESC";
		
	List<BookVo> bookVos = null;
	
	try {
		bookVos = jdbcTemplate.query(sql,  new RowMapper<BookVo>() {
			@Override
			public BookVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				BookVo bookVo = new BookVo();
				
				bookVo.setB_no(rs.getInt("b_no"));
				bookVo.setB_thumbnail(rs.getString("b_thumbnail"));
				bookVo.setB_name(rs.getString("b_name"));
				bookVo.setB_author(rs.getString("b_author"));
				bookVo.setB_publisher(rs.getString("b_publisher"));
				bookVo.setB_publish_year(rs.getString("b_publish_year"));
				bookVo.setB_isbn(rs.getString("b_isbn"));
				bookVo.setB_call_number(rs.getString("b_isbn"));
				bookVo.setB_rantal_able(rs.getInt("b_rantal_able"));
				bookVo.setB_reg_date(rs.getString("b_reg_date"));
				bookVo.setB_mod_date(rs.getString("b_mod_date"));
				
				return bookVo;
			}
		}, "%" + bookVo.getB_name() + "%");
	}catch(Exception e) {
		e.printStackTrace();
	}
		return bookVos.size() > 0 ? bookVos : null;
	}

}
