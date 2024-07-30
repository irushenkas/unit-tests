package com.example.test.repository;

import com.example.test.model.Book;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.sql.SQLException;
import static org.junit.Assert.*;

class BookRepositoryTest {

    @Test
    public void findBook_Found() {
        JdbcTemplate jdbcTemplate = Mockito.mock(JdbcTemplate.class);
        Book book = new Book(1, "test text1");
        Mockito.when(jdbcTemplate.queryForObject(Mockito.anyString(), Mockito.any(RowMapper.class), Mockito.anyInt())).thenReturn(book);
        BookRepository bookRepository = new BookRepository(jdbcTemplate);

        try {
            Book result = bookRepository.find(1);
            assertEquals(book, result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findBook_NotFound() {
        JdbcTemplate jdbcTemplate = Mockito.mock(JdbcTemplate.class);
        Book book = new Book(1, "test text1");
        Mockito.when(jdbcTemplate.queryForObject(Mockito.anyString(), Mockito.any(RowMapper.class), Mockito.anyInt())).thenReturn(null);
        BookRepository bookRepository = new BookRepository(jdbcTemplate);

        try {
            Book result = bookRepository.find(5);
            assertNotEquals(book, result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findBook_SqlException() {
        JdbcTemplate jdbcTemplate = Mockito.mock(JdbcTemplate.class);
        Mockito.when(jdbcTemplate.queryForObject(Mockito.anyString(), Mockito.any(RowMapper.class),
                Mockito.anyInt())).thenThrow(RuntimeException.class);
        BookRepository bookRepository = new BookRepository(jdbcTemplate);

        assertThrows(RuntimeException.class, () -> bookRepository.find(1));
    }
}