package com.example.springboot.rxjavajpah2restgui.assemblers;

import com.example.springboot.rxjavajpah2restgui.controllers.BookController;
import com.example.springboot.rxjavajpah2restgui.domain.Book;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class BooksResourceAssembler implements ResourceAssembler<List<Resource<Book>>, Resources<Resource<Book>>> {

    @Override
    public Resources<Resource<Book>> toResource(List<Resource<Book>> books) {
        return new Resources<>(books,
                linkTo(methodOn(BookController.class).all())
                        .withSelfRel());
    }
}
