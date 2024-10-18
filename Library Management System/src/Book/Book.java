package Book;

import java.util.logging.Logger;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;
    private int noOfAvailableCopies;
    private Logger logger;

    public Book(String title, String author, String isbn, int publicationYear, int noOfAvailableCopies) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.noOfAvailableCopies = noOfAvailableCopies;
        this.logger = Logger.getLogger(Book.class.getName());
    }

    public Book(String title, String author, String isbn, int publicationYear) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.noOfAvailableCopies = 1;
        this.logger = Logger.getLogger(Book.class.getName());
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getNoOfAvailableCopies() {
        return noOfAvailableCopies;
    }

    public void setNoOfAvailableCopies(int noOfAvailableCopies) {
        this.noOfAvailableCopies = noOfAvailableCopies;
    }
}
