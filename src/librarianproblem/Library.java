package librarianproblem;

import java.util.concurrent.ThreadLocalRandom;

public class Library {
    public static Book[] books;
    public static Student[] students;
    public static Librarian[] librarians;

    public static void main(String[] args) {
        students = new Student[40];
        librarians = new Librarian[3];
        books = new Book[6];

        // this  creates the lock objects that we use as a book
        for (int i = 0; i < 6; i++) {
            books[i] = new Book(i);
        }
        // this creates the librarian objects
        for (int i = 0; i < 3; i++) {
            librarians[i] = new Librarian(i+1,books);
        }
        // this creates the student objects
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student(i+1);
        }
        // this starts the student threads
        for (Student student : students) {
            student.start();
        }
    }
    /* this function is a static method of library, it returns one of the
       librarians that works in the library randomly   */
    public static Librarian selectLibrarian(){
        int randomNum = ThreadLocalRandom.current().nextInt(0, 3);
        return librarians[randomNum];
    }

}
