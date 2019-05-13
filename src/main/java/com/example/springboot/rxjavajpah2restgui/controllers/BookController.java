package com.example.springboot.rxjavajpah2restgui.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import com.example.springboot.rxjavajpah2restgui.domain.Book;
import com.example.springboot.rxjavajpah2restgui.exceptions.BookNotFoundException;
import com.example.springboot.rxjavajpah2restgui.services.BookResourceAssemblerService;
import com.example.springboot.rxjavajpah2restgui.services.BookService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BookController {

    private final BookResourceAssemblerService assembler;

    private final BookService bookService;

    public BookController(
            BookResourceAssemblerService assembler,
            BookService bookService) {
        this.assembler = assembler;
        this.bookService = bookService;
    }

    // get all books
    @GetMapping("/books")
    public Resources<Resource<Book>> all() {

        List<Resource<Book>> books = bookService.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

       return assembler.toResource(books);
    }

    // get book id
    @GetMapping("/books/{id}")
    public Resource<Book> one(@PathVariable Long id) {

        Book book = bookService.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        return assembler.toResource(book);
    }

    // add new book
    @PostMapping("/books")
    public ResponseEntity<?> newBook(@RequestBody Book newBook) throws URISyntaxException {

        bookService.save(newBook);
        Resource<Book> resource = assembler.toResource(newBook);

        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    // update book id
    @PutMapping("/books/{id}")
    public ResponseEntity<?> replaceBook(@RequestBody Book newBook, @PathVariable Long id) throws URISyntaxException {

        Book updatedBook = bookService.findById(id)
                .map(book -> {
                    book.setAuthor(newBook.getAuthor());
                    book.setTitle(newBook.getTitle());
                    return bookService.save(book);
                })
                .orElseGet(() -> {
                    newBook.setId(id);
                    return bookService.save(newBook);
                });

        Resource<Book> resource = assembler.toResource(updatedBook);

        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    // delete book id
    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {

        bookService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
