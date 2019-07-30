package com.RESTful.demo.Repositories;

import java.util.List;

import com.RESTful.demo.Entities.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

    List<Book> findByTitleContainingOrDescriptionContaining(String text, String textAgain);
}