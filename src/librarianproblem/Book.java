package librarianproblem;

public class Book {
    private int bookNumber;
    private volatile boolean available = true;

    Book( int i){
        this.bookNumber = i;
    }

//    public boolean take(){
//        if(getAccessibility()){
//            available = false;
//            System.out.println("Book"+this.bookNumber + " has been taken");
//            return true;
//        }
//        else
//            System.out.println("Book"+this.bookNumber + " isn't available to take");
//            return false;
//    }
//
//    public void putBack(){
//        if(!getAccessibility()){
//            available = true;
//            System.out.println("Book"+this.bookNumber + " has been put back");
//        }
//        else
//            System.out.println("Book"+this.bookNumber+ " is already on the shelf");
//    }



    public boolean getAccessibility(){
        return this.available;
    }

    public void take(){
        available = false;
        //System.out.println("Book"+this.bookNumber + " has been taken");
    }

    public void putBack(){
        available = true;
        //System.out.println("Book"+this.bookNumber + " has been put back");
    }



}
