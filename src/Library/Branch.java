package Library;

import java.util.*;
import java.util.logging.Logger;

import Book.Book;
import Patron.Patron;

public class Branch extends Library {
    protected List<Book> inventory;
    private int branchId;
    private Map<Patron, List<Book>> lendingMap;


    private final Logger logger;

    public Branch(int branchId) {
        super();
        this.branchId = branchId;
        this.inventory = new ArrayList<Book>();
        this.memberList = new ArrayList<Patron>();
        this.lendingMap = new HashMap<Patron, List<Book>>();
        this.logger = Logger.getLogger(Branch.class.getName());
    }

    public void addBookInInventory(Book book){
        if(this.inventory == null){
            this.inventory = new ArrayList<Book>();
        }
        for(Book b : this.inventory){
            if(b.getIsbn().equals(book.getIsbn())) {
                b.setNoOfAvailableCopies(b.getNoOfAvailableCopies() + book.getNoOfAvailableCopies());
                return;
            }
        }
        this.inventory.add(book);
    }

    public void removeBookFromInventory(Book book){
        if(this.inventory == null){
            this.logger.info("Not able to remove the book from the inventory as inventory is already empty.");
        }
        if(!this.inventory.contains(book)){
            this.logger.info("Not able to remove the book from the inventory as book is not present in the inventory.");
        }
        else {
            this.inventory.remove(book);
        }
    }

    public void addPatronInMemberList(Patron patron){
        if(this.memberList == null){
            this.memberList = new ArrayList<Patron>();
        }
        this.memberList.add(patron);
    }

    public void removePatronFromMemberList(Patron patron){
        if(this.memberList == null){
            this.logger.info("Not able to remove the patron from the member list as member list is already empty.");
        }
        if(!this.memberList.contains(patron)){
            this.logger.info("Not able to remove the patron from the member list as patron is not present in the member list.");
        }
        else {
            this.memberList.remove(patron);
        }
    }

    public void showInventory(){
        System.out.println("Available books in branch" + this.branchId + "-->");
        for(Book book : this.inventory){
            System.out.println("----------------------------------------------------------");
            System.out.println("\tTitle: " + book.getTitle());
            System.out.println("\tAuthor: " + book.getAuthor());
            System.out.println("\tPublication Year: " + book.getPublicationYear());
            System.out.println("\tISBN: " + book.getIsbn());
            System.out.println("\tNo. of books available: " + book.getNoOfAvailableCopies());
        }
    }

    public List<Book> getAvailableBooks(){
        List<Book> availableBooks = new ArrayList<Book>();
        System.out.println("Available books in branch" + this.branchId + "-->");
        for(Book book : this.inventory){
            if(book.getNoOfAvailableCopies() > 0){
                System.out.println("----------------------------------------------------------");
                System.out.println("\tTitle: " + book.getTitle());
                System.out.println("\tAuthor: " + book.getAuthor());
                System.out.println("\tPublication Year: " + book.getPublicationYear());
                System.out.println("\tISBN: " + book.getIsbn());
                System.out.println("\tNo. of books available: " + book.getNoOfAvailableCopies());
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    public List<Book> getBorrowedBooks(){
        List<Book> borrowedBooks = new ArrayList<Book>();

        for(Patron patron: lendingMap.keySet()){
            borrowedBooks.addAll(lendingMap.get(patron));
        }
        System.out.println("Borrowed books in branch" + this.branchId + "-->");
        for(Book book : borrowedBooks){
            System.out.println("----------------------------------------------------------");
            System.out.println("\tTitle: " + book.getTitle());
            System.out.println("\tAuthor: " + book.getAuthor());
            System.out.println("\tPublication Year: " + book.getPublicationYear());
            System.out.println("\tISBN: " + book.getIsbn());
            System.out.println("\tNo. of books available: " + book.getNoOfAvailableCopies());
        }
        return borrowedBooks;
    }

    public List<Book> searchBookInInventoryByTitle(String bookTitle){
        List<Book> foundBooks = new ArrayList<Book>();
        for(Book book : this.inventory){
            if(book.getTitle().equals(bookTitle)){
                foundBooks.add(book);
            }
        }
        if(foundBooks.isEmpty()){
            this.logger.info("No book found with title: " + bookTitle);
            return null;
        }
        else{
            this.logger.info("Found " + foundBooks.size() + " books with the title: " + bookTitle);
            return foundBooks;
        }
    }

    public List<Book> searchBookInInventoryByAuthor(String bookAuthor){
        List<Book> foundBooks = new ArrayList<Book>();
        for(Book book : this.inventory){
            if(book.getAuthor().equals(bookAuthor)){
                if(book.getNoOfAvailableCopies() > 0) {
                    foundBooks.add(book);
                }
            }
        }
        if(foundBooks.isEmpty()){
            this.logger.info("No book found with author: " + bookAuthor);
            return null;
        }
        else{
            this.logger.info("Found " + foundBooks.size() + " books with the author: " + bookAuthor);
            return foundBooks;
        }
    }

    public Book searchBookInInventoryByISBN(String isbn){
        for(Book book : this.inventory){
            if(book.getIsbn().equals(isbn)){
                if(book.getNoOfAvailableCopies() > 0) {
                    return book;
                }
                else{
                    this.logger.info("Book: " + book.getTitle() + " is not in stock right now!!");
                    return null;
                }
            }
        }
        this.logger.info("No book found with ISBN: " + isbn);
        return null;
    }

    public void checkoutBook(Patron patron, Book book){
        if(book.getNoOfAvailableCopies()<=0){
            this.logger.info("Book not available right now. Please try again later!!");
            return;
        }
        if(!this.lendingMap.containsKey(patron)){
            List<Book> lentBooks = new ArrayList<Book>();
            lentBooks.add(book);
            this.lendingMap.put(patron, lentBooks);
        }
        else {
            this.lendingMap.get(patron).add(book);
        }
        updateBookInInventory(book, Optional.empty(), Optional.empty(), Optional.empty(), Optional.of(book.getNoOfAvailableCopies() - 1));
        this.logger.info("Book: " + book.getTitle() + " has been checked out.");
    }

    public void returnBook(Patron patron, Book book){
        if(!this.lendingMap.containsKey(patron)){
            this.logger.info("Patron: " + patron.getPatronName() + " has not lent any book from library branch: " + this.branchId);
        }
        else {
            List<Book> lentBooks = this.lendingMap.get(patron);
            if(lentBooks.removeIf(lentBook -> lentBook.getIsbn().equals(book.getIsbn()))){
                updateBookInInventory(book, Optional.empty(), Optional.empty(), Optional.empty(), Optional.of(book.getNoOfAvailableCopies() + 1));
                this.logger.info("Book: " + book.getTitle() + " has been returned to the library branch: " + this.branchId);
            }
            else{
                this.logger.info("Book: " + book.getTitle() + " has not been lent from the library branch: " + this.branchId);
            }
        }
    }

    public void updateBookInInventory(Book book, Optional<String> updatedTitle, Optional<String> updatedAuthor, Optional<Integer> updatedPublicationYear, Optional<Integer> updatedNoOfAvailableCopies){
        updatedTitle.ifPresent(book::setTitle);
        updatedAuthor.ifPresent(book::setAuthor);
        updatedPublicationYear.ifPresent(book::setPublicationYear);
        updatedNoOfAvailableCopies.ifPresent(book::setNoOfAvailableCopies);
    }

    public void updatePatronInformation(Patron patron, Optional<String> newPatronId, Optional<String> newPatronName, Optional<String> newPatronAddress, Optional<String> newPatronPhone, Optional<String> newPatronEmail){
        newPatronId.ifPresent(patron::setPatronId);
        newPatronName.ifPresent(patron::setPatronName);
        newPatronAddress.ifPresent(patron::setAddress);
        newPatronEmail.ifPresent(patron::setPatronEmail);
        newPatronPhone.ifPresent(patron::setPatronPhoneNumber);
    }







    public int getBranchId() {
        return branchId;
    }

    public void setBranchIdId(int branchId) {
        this.branchId = branchId;
    }



}
