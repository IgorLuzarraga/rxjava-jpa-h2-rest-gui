package com.example.springboot.rxjavajpah2restgui.services;

import com.example.springboot.rxjavajpah2restgui.repositories.BookRepository;
import com.example.springboot.rxjavajpah2restgui.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private EventsService eventsService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository,
                           EventsService eventsService) {
        this.bookRepository = bookRepository;
        this.eventsService = eventsService;
    }

    @Override
    public Optional<Book> findById(Long id){
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findAll(){
        List<Book> bookList = bookRepository.findAll();
        return bookList;
    }

    @Override
    public List<Book> findByTitle(String title){
        return bookRepository.findByTitleStartsWithIgnoreCase(title);
    }

    @Override
    public List<Book>  findByAuthor(String author){
        return bookRepository.findByAuthorStartsWithIgnoreCase(author);
    }

    @Override
    public Book save(Book book)
    {
        Book bookSaved = bookRepository.save(book);

        // send the event to notify thet the book was added,
        // so the clients can update their book´s repository view
        eventsService.send(book);
        return bookSaved;
    }

    @Override
    public void delete(Book book){
        bookRepository.delete(book);

        // send the event to notify that the book was deleted,
        // so the clients can update their book´s repository view
        eventsService.send(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.findById(id)
                .ifPresent(book -> {
                    bookRepository.deleteById(id);

                    // send the event to notify that the book was deleted,
                    // so the clients can update their book´s repository view
                    eventsService.send(book);
                });
    }

    @Override
    public void deleteAll(){
        bookRepository.deleteAll();
    }

    @Override
    public Long count(){
        return bookRepository.count();
    }
}
