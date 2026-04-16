/**
 * Gadget class represents a generic gadget with a model name,
 * price, weight, and size.
 *
 * CS4001 Coursework - Gadget Shop
 */
public class Gadget {

    private String model;
    private double price;
    private int weight;
    private String size;

    /**
     * Constructor for objects of class Gadget.
     *
     * @param model  the model name of the gadget
     * @param price  the price of the gadget
     * @param weight the weight of the gadget in grams
     * @param size   the size of the gadget
     */
    public Gadget(String model, double price, int weight, String size) {
        this.model  = model;
        this.price  = price;
        this.weight = weight;
        this.size   = size;
    }

    /**
     * Returns the model name of the gadget.
     */
    public String getModel() {
        return model;
    }

    /**
     * Returns the price of the gadget.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the weight of the gadget in grams.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Returns the size of the gadget.
     */
    public String getSize() {
        return size;
    }

    /**
     * Displays all details of the gadget to the console.
     */
    public void display() {
        System.out.println("Model:  " + model);
        System.out.println("Price:  £" + price);
        System.out.println("Weight: " + weight + "g");
        System.out.println("Size:   " + size);
    }
}
