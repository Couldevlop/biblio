package com.openlab.biblio.service;


import com.openlab.biblio.entity.Book;
import com.openlab.biblio.entity.User;
import com.openlab.biblio.repository.BookRepository;
import com.openlab.biblio.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }


    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id){
        return bookRepository.findById(id);
    }


    public Book save(Book book){
        return bookRepository.save(book);
    }



    public Book update(Long id, Book book){
        Optional<Book>  bookExist = findById(id);
        Book updatedBook = null;
        if(bookExist.isPresent()){
            Book optionalBook = bookExist.get();
            optionalBook.setUser(book.getUser());
            optionalBook.setAuthor(book.getAuthor());
            optionalBook.setTitle(book.getTitle());
            updatedBook = bookRepository.save(optionalBook);
        }else {
            return null;
        }

        return updatedBook;
    }


    public void delete(Long id){
        bookRepository.deleteById(id);
    }


}
