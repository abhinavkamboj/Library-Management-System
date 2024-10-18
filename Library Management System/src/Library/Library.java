package Library;

import Book.Book;
import Patron.Patron;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Library {
    protected List<Patron> memberList;
    abstract public void addBookInInventory(Book book);
    abstract public void removeBookFromInventory(Book book);
    abstract public void addPatronInMemberList(Patron patron);
    abstract public void removePatronFromMemberList(Patron patron);
    abstract public void showInventory();
    abstract public List<Book> searchBookInInventoryByTitle(String title);
    abstract public List<Book> searchBookInInventoryByAuthor(String author);
    abstract public Book searchBookInInventoryByISBN(String isbn);
    abstract public void updateBookInInventory(Book book, Optional<String> updatedTitle, Optional<String> updatedAuthor, Optional<Integer> updatedPublicationYear, Optional<Integer> updatedNoOfAvailableCopies);
    abstract public void checkoutBook(Patron patron, Book book);
    abstract public void returnBook(Patron patron, Book book);
    abstract public List<Book> getAvailableBooks();
    abstract public List<Book> getBorrowedBooks();











}
