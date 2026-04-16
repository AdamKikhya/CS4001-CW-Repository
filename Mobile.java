/**
 * Mobile class represents a mobile phone gadget.
 * It extends Gadget and adds calling credit functionality.
 *
 * CS4001 Coursework - Gadget Shop
 */
public class Mobile extends Gadget {

    private int callingCredit;

    /**
     * Constructor for objects of class Mobile.
     *
     * @param model         the model name
     * @param price         the price in pounds
     * @param weight        the weight in grams
     * @param size          the size description
     * @param callingCredit the initial calling credit in minutes
     */
    public Mobile(String model, double price, int weight, String size, int callingCredit) {
        super(model, price, weight, size);
        this.callingCredit = callingCredit;
    }

    /**
     * Returns the current calling credit in minutes.
     */
    public int getCallingCredit() {
        return callingCredit;
    }

    /**
     * Adds credit to the mobile phone.
     * If amount is not positive, displays an error message.
     *
     * @param amount the number of minutes to add
     */
    public void addCredit(int amount) {
        if (amount > 0) {
            callingCredit += amount;
        } else {
            System.out.println("Please enter a positive amount");
        }
    }

    /**
     * Makes a call to the given number for the given duration.
     * Deducts duration from credit if sufficient, otherwise prints error.
     *
     * @param phoneNumber the number to call
     * @param duration    the duration in minutes
     */
    public void makeCall(String phoneNumber, int duration) {
        if (callingCredit >= duration) {
            System.out.println("Calling " + phoneNumber + " for " + duration + " minutes");
            callingCredit -= duration;
        } else {
            System.out.println("Insufficient credit to make this call");
        }
    }

    /**
     * Displays all gadget details plus the calling credit remaining.
     */
    @Override
    public void display() {
        super.display();
        System.out.println("Calling credit: " + callingCredit + " minutes");
    }
}
