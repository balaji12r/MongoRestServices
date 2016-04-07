package com.mongo.controller;

import com.mongo.model.Book;
import com.mongo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rx.Observable;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by a526903 on 3/21/16.
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(method = RequestMethod.POST)

    public Map<String, Object> createBook(@RequestBody Map<String, Object> bookMap){

        Book book = new Book(bookMap.get("name").toString(),
                bookMap.get("author").toString(),
                (Double)bookMap.get("price"),
                bookMap.get("publication").toString(),
                (Integer)bookMap.get("totalPages"));
        bookRepository.saveBook(book);
        Map<String, Object> response = new LinkedHashMap<String, Object>();

        response.put("message", "Book created successfully");
        response.put("book", book);
        return response;

    }

    @RequestMapping(method = RequestMethod.GET, value="/Id={bookId}")
    public Book getBookDetails(@PathVariable("bookId") String bookId){
        return bookRepository.findBook(bookId);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"author","price"})
    public List<Book> getBookbyAuthorAndPrice(@RequestParam String author,@RequestParam Double price){
           return bookRepository.findBookByAuthorAndPrice(author, price);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Book> getBooks(){
        return bookRepository.getBooks();
    }


}
