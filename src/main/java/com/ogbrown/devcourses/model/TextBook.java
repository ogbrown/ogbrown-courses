/*
 * Copyright (c) 2017 - 2019 Oswald G. Brown, III
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.ogbrown.devcourses.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextBook textBook = (TextBook) o;
        return edition == textBook.edition &&
                Objects.equals(id, textBook.id) &&
                Objects.equals(title, textBook.title) &&
                Objects.equals(publisher, textBook.publisher) &&
                Objects.equals(author, textBook.author) &&
                Objects.equals(isbn, textBook.isbn) &&
                Objects.equals(publishedDate, textBook.publishedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, edition, publisher, author, isbn, publishedDate);
    }

    @Override
    public String toString() {
        return "TextBook [id=" + id + ", title=" + title + ", edition=" + edition + ", publisher=" + publisher
                + ", author=" + author + ", isbn=" + isbn + ", publishedDate=" + publishedDate + "]";
    }
    
    
}
