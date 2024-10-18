import Library.Library;
import Library.Branch;
import Book.Book;
import Patron.Patron;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome to the Library Management System!");

        Library libraryBranch1 = new Branch(1);
        Library libraryBranch2 = new Branch(2);

        Book book1 = new Book("Two States", "Chetan Bhagat", "1", 1990);
        Book book2 = new Book("Alchemist", "Paulo Coelho", "2", 2000);
        Book book3 = new Book("Zahir", "Paulo Coelho", "3", 2020, 7);
        Book book4 = new Book("Zero to One", "Peter Thiel", "4", 1997, 10);
        Book book5 = new Book("Zero to One", "Peter Thiel", "4", 1997, 20);

        Patron patron1 = new Patron("P1", "ABHINASCAV KAASDMBOJ", "abhinaghacsv.sgvhavs@gmail.com", "1234567891", "kullu h kullu");
        Patron patron2 = new Patron("P2", "AHASDANA CASDFANDA", "ahanasjva.hasdba@gmail.com", "1234567890", "kolkata h kolkata");

        libraryBranch1.addBookInInventory(book1);
        libraryBranch1.addBookInInventory(book2);
        libraryBranch1.addBookInInventory(book3);
        libraryBranch1.addBookInInventory(book4);
        libraryBranch1.addBookInInventory(book5);

        libraryBranch1.addPatronInMemberList(patron1);
        libraryBranch1.addPatronInMemberList(patron2);

        libraryBranch2.addPatronInMemberList(patron2);

        libraryBranch1.showInventory();

        libraryBranch2.addBookInInventory(book4);
        libraryBranch2.addBookInInventory(book5);

        libraryBranch2.showInventory();

        List<Book> bookFound = patron1.searchForBooksInABranchByAuthor(libraryBranch1, "Paulo Coelho");
        patron1.checkoutABookFromABranch(libraryBranch1, bookFound.get(0));

//        libraryBranch1.showInventory();

        Book book = patron1.searchForABookInABranchByISBN(libraryBranch1,"1");
        patron1.checkoutABookFromABranch(libraryBranch1, book);

        libraryBranch1.showInventory();

//        patron1.returnABookFromABranch(libraryBranch1, book);
        libraryBranch1.showInventory();

        libraryBranch1.getAvailableBooks();

        libraryBranch1.getBorrowedBooks();
//        System.out.println(patron1.getPatronName());
//        libraryBranch1.updatePatronInformation(patron1, Optional.empty(), Optional.of("aaaaaaaaaaa"), Optional.empty(), Optional.empty(), Optional.empty());
//        System.out.println(patron1.getPatronName());
    }
}