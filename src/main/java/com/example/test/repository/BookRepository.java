package com.example.test.repository;

import com.example.test.model.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.*;

@Repository
public class BookRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final RowMapper<Book> bookRowMapper = (resultSet, rowNum) -> new Book(resultSet.getInt("id"), resultSet.getString("name"));

    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Book find(int id) throws SQLException {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM public.book WHERE id = ?",
                bookRowMapper, id);
    }
}
