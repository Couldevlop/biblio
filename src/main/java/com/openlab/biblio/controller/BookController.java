package com.openlab.biblio.controller;

import com.openlab.biblio.entity.Book;
import com.openlab.biblio.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@Tag(name = "Book Controller", description= "CRUD Operations for books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping
    @Operation(summary = "Get all books")
    public List<Book> getAllBooks(){
        return  bookService.findAll();
    }

    @PostMapping
    @Operation(summary = "Create a new book")
    public Book createBook(@RequestBody Book book){
        return bookService.save(book);
    }

    @GetMapping("/{id}")
    @Operation(summary ="Get book by ID")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        return bookService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update an existing book")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book){
        return bookService.findById(id)
                .map(existing ->{
                    existing.setAuthor(book.getAuthor());
                    existing.setTitle(book.getTitle());
                    existing.setUser(book.getUser());
                    return ResponseEntity.ok(bookService.save(existing));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book by ID")
    public ResponseEntity<Void> deleteBookById(@PathVariable Long id){
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
