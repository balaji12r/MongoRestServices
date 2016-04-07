package com.mongo.repository;

import com.mongo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Field;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by a526903 on 3/21/16.
 */
@Repository
public class BookRepository {

    private MongoTemplate mongoTemplate;

    @Autowired
    void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void saveBook(Book book){
        mongoTemplate.save(book);
    }

    public Book findBook(String bookid){
        System.out.println("bookid " + bookid);
        Query query = Query.query(Criteria.where("_id").is(bookid));
        Book book = mongoTemplate.findOne(query, Book.class);
        return book;
    }

    public List<Book> findBookByAuthorAndPrice(String author,Double price){
        //Query query = Query.query(Criteria.where("author").is(author)).addCriteria(Criteria.where("price").gte(price));
        Query query = Query.query(Criteria.where("author").is(author).andOperator(Criteria.where("price").gte(price)
                , Criteria.where("totalPages").gte(100)));
        //Aggregation.group();
        //Field field =new Field();
        //field.elemMatch("author",Criteria.where("author"));
        List<Book> bookList = mongoTemplate.find(query, Book.class);
        return bookList;
    }

    public List<Book> getBooks(){
        System.out.println("********* Called");
        List<Book> bookList = mongoTemplate.findAll(Book.class);
        return bookList;
    }
}
