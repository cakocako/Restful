package com.RESTful.demo.Controllers;

import java.util.List;
import java.util.Map;

import com.RESTful.demo.Entities.Book;
import com.RESTful.demo.Repositories.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestfulController {

    @Autowired
    BookRepository bookRepository;

    //Browse
    @GetMapping("/books")
    public List<Book> index(){
        return bookRepository.findAll();
    }

    //Read
    @GetMapping("/books/{id}")
    public Book show(@PathVariable int id){
        return bookRepository.findById(id).get();
    }

    //Add
    @PostMapping("/books/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return bookRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }


    @PostMapping("/books")
    public Book create(@RequestBody Book book){
        return bookRepository.save(book);
    }

    //Edit
    @PutMapping("/books/{id}")
    public Book update(@PathVariable int id, @RequestBody Book book){
        // getting book
        Book bookToUpdate = bookRepository.findById(id).get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setDescription(book.getDescription());
        return bookRepository.save(bookToUpdate);
    }

    //Delete
    @DeleteMapping("books/{id}")
    public boolean delete(@PathVariable int id){
        bookRepository.deleteById(id);
        return true;
    }
}