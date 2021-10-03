package librarianproblem;

import java.util.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Student extends Thread implements Runnable {
    // student id
    private int studentID;
    // books that need to be read to complete the research
    private ArrayList<Integer> booksToRead = new ArrayList<Integer>();

    // constructor of student which attach a studentID and randomized booksToRead array
    public Student(int index) {
        this.studentID = index;

        // this creates the array by mod 6 of studentID so it is not unique
        for (int i = 0; i < 6; i++) {
            this.booksToRead.add( (index+i) % 6 );
            // to make it unique
            Collections.shuffle(booksToRead);
        }
    }

    @Override
    public void run() {
        // Endless life of a Student
        while (booksToRead.size() != 0) {
            // this for loop is doing the research part for each book one by one
            for (int i = 0; i < booksToRead.size(); i++) {
                // assign the selected book to a int for simpler explanation
                int theSelectedBook = booksToRead.get(0);
                //System.out.println("This is the selected book for " + theSelectedBook + " , for student:" + this.studentID);
                // this method is trying to catch one of the librarians and assign it to a librarian type

                // ask for the book from the librarian
                askForTheBook(theSelectedBook);

                // read the book
                readBook(theSelectedBook);
                // return the book
                giveBackTheBook(theSelectedBook, findAvailableLibrarian());
                // remove the read book from the read list
                booksToRead.remove(0);
            }
        }
        sleep();
        System.out.printf("≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡Student#%d has completed the research≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡≡\n", this.studentID);
    }


    private Librarian findAvailableLibrarian() {
        while (true) {
            Librarian selectedLibrarianForAskingTheBook = Library.selectLibrarian();
            if (!selectedLibrarianForAskingTheBook.getIsBusy()) {
                return selectedLibrarianForAskingTheBook;
            }
            // if the librarian selected by student is busy, student should wait for a random time
            System.out.println("The Librarian"+selectedLibrarianForAskingTheBook.getNumber() + " chosen by student" + this.studentID +
                    " is busy at the time, Student" + this.studentID +" should wait for a little time and retry");
            sleep();
        }
    }

    private void askForTheBook(int bookNumber) {
        Librarian librarian = findAvailableLibrarian();
        System.out.printf("Student#%d has asked for the book%d from the librarian%d\n", this.studentID, bookNumber, librarian.getNumber());

        while(!librarian.takeBook(bookNumber)){
            System.out.printf("The Book%d that student%d asked is already grabbed by another student," +
                    " student%d should try again\n", bookNumber, this.studentID, this.studentID);
            sleep();
        }
        System.out.printf("Student#%d has grabbed the book%d from the librarian%d\n", this.studentID, bookNumber, librarian.getNumber());

//        if(librarian.takeBook(bookNumber)){
//            System.out.printf("Student#%d has grabbed the book%d from the librarian%d\n", this.studentID, bookNumber, librarian.getNumber());
//        }
//        else{
//            System.out.printf("The Book%d that student%d asked is already grabbed by another student," +
//                    " student%d should try again\n", bookNumber, this.studentID, this.studentID);
//            sleep();
//        }
    }

    private void readBook(int bookNumber) {
        System.out.printf("Student#%d is now reading the book%d\n", this.studentID, bookNumber);
        sleep();
    }
    private void giveBackTheBook(int bookNumber, Librarian librarian){
        System.out.printf("Student#%d has given back the book%d to librarian%d\n", this.studentID, bookNumber,librarian.getNumber());
        librarian.putDownBook(bookNumber);

    }


    private void sleep() {
        try {
            // random sleep time generator
            int randomNum = ThreadLocalRandom.current().nextInt(5, 15 + 1);
            // invoking the sleep func
            Thread.sleep(randomNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}