package com.example.springboot.rxjavajpah2restgui.repositories;

import com.example.springboot.rxjavajpah2restgui.Application;
import com.example.springboot.rxjavajpah2restgui.domain.Book;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookRepositoryTests {
    private static final String TITLE = "Test-Driven Java Development";
    private static final String AUTHOR = "V. Farcic / A. Garcia";

    @Autowired
    BookRepository bookRepository;

    @Before
    public void init() {
        // delete the DB to start fresh.
        bookRepository.deleteAll();
    }

    @Test
    public void givenBook_WhenSaveBook_ThenStoreBook() {

        // Number of books before save a new book
        long bookCountStart = bookRepository.count();

        // given a new book
        Book book = new Book(TITLE, AUTHOR);

        // when
        bookRepository.save(book);

        // then
        long bookCountEnd = bookRepository.count();
        assertEquals(bookCountStart+1, bookCountEnd);
    }

    @Test
    public void givenBook_WhenDeleteBook_ThenDeleteBook() {
        // Number of books before save a new book
        long bookCountStart = bookRepository.count();

        // given a book
        Book book = new Book(TITLE, AUTHOR);
        bookRepository.save(book);

        // The number of books increments in one.
        long bookCountSave = bookRepository.count();
        assertEquals(bookCountStart+1, bookCountSave);

        // when
        bookRepository.delete(book);

        // then the book is deleted
        long bookCountEnd = bookRepository.count();
        assertEquals(bookCountStart, bookCountEnd);
    }

    @Test
    public void givenBook_WhenFindByAuthor_ThenFindBook() {

        // given a book
        Book book = new Book(TITLE, AUTHOR);
        bookRepository.save(book);

        // when
        List<Book> bookList = bookRepository.findByAuthor(AUTHOR);

        // then the book finded is the one saved
        Book bookFind = bookList.get(0);
        assertEquals(book, bookFind);
    }

    @Test
    public void givenBook_WhenFindByTitle_ThenFindBook() {

        // given a book
        Book book = new Book(TITLE, AUTHOR);
        bookRepository.save(book);

        // when
        List<Book> bookList = bookRepository.findByTitle(TITLE);

        // then the book finded is the one saved
        Book bookFind = bookList.get(0);
        assertEquals(book, bookFind);
    }
}

