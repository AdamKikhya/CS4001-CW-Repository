/**
 * MP3 class represents an MP3 player gadget.
 * It extends Gadget and adds available memory functionality.
 *
 * CS4001 Coursework - Gadget Shop
 */
public class MP3 extends Gadget {

    private int availableMemory;

    /**
     * Constructor for objects of class MP3.
     *
     * @param model           the model name
     * @param price           the price in pounds
     * @param weight          the weight in grams
     * @param size            the size description
     * @param availableMemory the initial available memory in MB
     */
    public MP3(String model, double price, int weight, String size, int availableMemory) {
        super(model, price, weight, size);
        this.availableMemory = availableMemory;
    }

    /**
     * Returns the current available memory in MB.
     */
    public int getAvailableMemory() {
        return availableMemory;
    }

    /**
     * Downloads music, reducing available memory by the amount required.
     * Prints an error if there is not enough memory.
     *
     * @param memoryRequired the memory needed in MB
     */
    public void downloadMusic(int memoryRequired) {
        if (availableMemory >= memoryRequired) {
            availableMemory -= memoryRequired;
        } else {
            System.out.println("Insufficient memory to download this music");
        }
    }

    /**
     * Deletes music, freeing up the specified amount of memory.
     *
     * @param memoryFreed the amount of memory freed in MB
     */
    public void deleteMusic(int memoryFreed) {
        availableMemory += memoryFreed;
    }

    /**
     * Displays all gadget details plus the available memory remaining.
     */
    @Override
    public void display() {
        super.display();
        System.out.println("Available memory: " + availableMemory + " MB");
    }
}
