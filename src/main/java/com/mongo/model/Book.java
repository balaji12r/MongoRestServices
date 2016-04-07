package com.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


/**
 * Created by a526903 on 3/21/16.
 */
@Document(collection = "books")
public class Book implements Serializable {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    private String name;
    private String author;

    public Book(String name,String author,Double price,String publication,Integer totalPages) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.publication = publication;
        this.totalPages = totalPages;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    private Double price;
    private String publication;

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    private Integer totalPages;

}
