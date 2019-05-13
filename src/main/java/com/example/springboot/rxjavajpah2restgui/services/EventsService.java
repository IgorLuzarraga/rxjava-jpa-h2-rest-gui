package com.example.springboot.rxjavajpah2restgui.services;

import com.example.springboot.rxjavajpah2restgui.domain.Book;
import io.reactivex.functions.Consumer;

public interface EventsService {
    public void send(Book book);
    public void receive(Consumer<? super Book> onNext);
}