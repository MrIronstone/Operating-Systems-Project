package librarianproblem;

import java.util.concurrent.locks.Lock;

public class Librarian {
    // by declaring it volatile, we prevent the problems from occurring
    // by writing to the caches of the threads instead of main memory and prevent false reads.
    private volatile boolean isBusy = false;
    private int librarianNumber;
    private Book[] books;

    public Librarian(int librarianNumber, Book[] books){
        this.librarianNumber = librarianNumber;
        this.books = books;
    }


    public boolean takeBook(int book) {
        //
        isBusy = true;
        System.out.println("Librarian " +this.librarianNumber + " has checked the book" + book +" to take");
        if(Library.books[book].getAccessibility()){
            Library.books[book].take();
            System.out.println("Librarian " +this.librarianNumber + " has taken the book" + book +" to give to the student who wants it");
            isBusy = false;
            return true;
        }
        else
            System.out.println("Librarian"+this.librarianNumber + " cannot take the book" + book +
                    " because it is not in place, it was borrowed to another student");
            isBusy = false;
            return false;
    }

    public void putDownBook(int book) {
        //
        isBusy = true;
        System.out.println("Librarian " +this.librarianNumber + " has put back the book" + book + " to the shelf");
        Library.books[book].putBack();
        isBusy = false;
    }

    public int getNumber(){
        return librarianNumber;
    }

    public boolean getIsBusy(){
        return isBusy;
    }
}

