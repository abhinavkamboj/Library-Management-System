package Patron;

import Book.Book;
import Library.Branch;
import Library.Library;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Patron {
    private String patronId;
    private String patronName;
    private String patronEmail;
    private double patronPhoneNumber;
    private String patronAddress;
    private List<Book> booksLoaned;
    private Logger logger;
    private List<Book> borrowingHistory;

    public Patron(String patronId, String name, String email, double phoneNumber, String address) {
        this.patronId = patronId;
        this.patronName = name;
        this.patronEmail = email;
        this.patronPhoneNumber = phoneNumber;
        this.patronAddress = address;
        this.booksLoaned = new ArrayList<>();
        this.borrowingHistory = new ArrayList<>();
        this.logger = Logger.getLogger(Patron.class.getName());
    }

    public void checkoutABookFromABranch(Library branch, Book book) {
        branch.checkoutBook(this, book);
        booksLoaned.add(book);
    }

    public void returnABookFromABranch(Library branch, Book book) {
        branch.returnBook(this, book);
        if(booksLoaned.contains(book)) booksLoaned.remove(book);
        else this.logger.info("Book: " + book.getTitle() + "not loaned by patron: " + this.patronName);
        borrowingHistory.add(book);
    }

    public List<Book> searchForBooksInABranchByTitle(Library branch, String title){
        List<Book> booksFound = branch.searchBookInInventoryByTitle(title);
        if(booksFound.isEmpty()){
            System.out.println("Book not found with title: " + title);
            return null;
        }
        return booksFound;
    }

    public List<Book> searchForBooksInABranchByAuthor(Library branch, String author){
        return branch.searchBookInInventoryByAuthor(author);
    }

    public Book searchForABookInABranchByISBN(Library branch, String isbn){
        return branch.searchBookInInventoryByISBN(isbn);
    }

    public String getPatronId() {
        return patronId;
    }

    public void setPatronId(String patronId) {
        this.patronId = patronId;
    }

    public String getPatronName() {
        return patronName;
    }

    public void setPatronName(String patronName) {
        this.patronName = patronName;
    }

    public String getPatronEmail() {
        return patronEmail;
    }

    public void setPatronEmail(String patronEmail) {
        this.patronEmail = patronEmail;
    }

    public double getPatronPhoneNumber() {
        return patronPhoneNumber;
    }

    public void setPatronPhoneNumber(double patronPhoneNumber) {
        this.patronPhoneNumber = patronPhoneNumber;
    }

    public String getPatronAddress() {
        return patronAddress;
    }

    public void setAddress(String patronAddress) {
        this.patronAddress = patronAddress;
    }



}
