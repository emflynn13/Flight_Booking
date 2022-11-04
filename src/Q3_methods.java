import java.util.Scanner;
import java.security.SecureRandom;


public class Q3_methods {
    static final int totalSeats = 10;
    static int seatsBooked = 0;
    static final int seatsPerClass = 5;
    static int firstBooked = 0;
    static int secondBooked = 0;
    static boolean[] seats = new boolean[totalSeats];
    static Scanner userInput = new Scanner(System.in);
    static SecureRandom secRand = new SecureRandom();

    public static void startBooking() {
        while (seatsBooked < totalSeats) {
            System.out.println("Please type 1 for First Class and Please type 2 for Economy: ");
            int decision = Integer.parseInt(userInput.next());
            if (decision == 1) {
                String className = "First";
                String oppositeClass = "Second";
                if (firstBooked < seatsPerClass) {
                    bookClass(className, 0, seatsPerClass);
                } else {
                    System.out.println(className + " class is full. Would you like to travel economy instead?: ");
                    String swapChoice = userInput.next();
                    if (swapChoice.startsWith("y".toLowerCase()) && secondBooked < seatsPerClass) {
                            bookClass(oppositeClass, seatsPerClass, totalSeats); // seats 6-10
                    } else{
                        System.out.println("Next flight leaves in 3 hours.");
                    }
                }
            } else if (decision == 2) {
                String className = "Second";
                String oppositeClass = "First";
                if (secondBooked < seatsPerClass) {
                    bookClass(className, seatsPerClass, totalSeats);
                }
                else {
                    System.out.println(className + " class is full. Would you like to travel first instead?: ");
                    String swapChoice = userInput.next();
                    if (swapChoice.startsWith("y".toLowerCase()) && firstBooked < seatsPerClass) {
                        bookClass(oppositeClass, 0, seatsPerClass); // seats 1-5
                    } else {
                        System.out.println("Next flight leaves in 3 hours.");
                    }
                }
            }
        }
    }
    public static void bookClass(String className, int origin, int bound) {  // min and max seats in class
        while (true) {
            int possibleSeat = secRand.nextInt(origin, bound);
            if (!seats[possibleSeat]) {
                System.out.println("*Boarding Pass* \n" + className + " Class \nSeat Number: " + (possibleSeat + 1));
                seats[possibleSeat] = true;
                break;
            }
        }
        switch (className){
            case "First": firstBooked++;
            break;
            case "Second": secondBooked++;
            break;
        }
        seatsBooked++;
    }
    public static void main (String[]args){
        startBooking();
    }
}