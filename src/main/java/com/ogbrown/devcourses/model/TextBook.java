package com.ogbrown.devcourses.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class TextBook implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private short edition;
    private String publisher;
    private String author;
    private String isbn;
    @Temporal(TemporalType.DATE)
    private Date publishedDate;
    

    public TextBook() {
        // TODO Auto-generated constructor stub
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public short getEdition() {
        return edition;
    }


    public void setEdition(short edition) {
        this.edition = edition;
    }


    public String getPublisher() {
        return publisher;
    }


    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }


    public String getAuthor() {
        return author;
    }


    public void setAuthor(String author) {
        this.author = author;
    }


    public String getIsbn() {
        return isbn;
    }


    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    public Date getPublishedDate() {
        return publishedDate;
    }


    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }


  

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result + edition;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
        result = prime * result + ((publishedDate == null) ? 0 : publishedDate.hashCode());
        result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TextBook other = (TextBook) obj;
        if (author == null) {
            if (other.author != null)
                return false;
        } else if (!author.equals(other.author))
            return false;
        if (edition != other.edition)
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (isbn == null) {
            if (other.isbn != null)
                return false;
        } else if (!isbn.equals(other.isbn))
            return false;
        if (publishedDate == null) {
            if (other.publishedDate != null)
                return false;
        } else if (!publishedDate.equals(other.publishedDate))
            return false;
        if (publisher == null) {
            if (other.publisher != null)
                return false;
        } else if (!publisher.equals(other.publisher))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "TextBook [id=" + id + ", title=" + title + ", edition=" + edition + ", publisher=" + publisher
                + ", author=" + author + ", isbn=" + isbn + ", publishedDate=" + publishedDate + "]";
    }
    
    
}
