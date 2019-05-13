package com.example.springboot.rxjavajpah2restgui.repositories;

import com.example.springboot.rxjavajpah2restgui.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
   List<Book> findByTitle(String title);
   List<Book> findByAuthor(String author);
   List<Book> findByTitleStartsWithIgnoreCase(String title);
   List<Book> findByAuthorStartsWithIgnoreCase(String author);
}