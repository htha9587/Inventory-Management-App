package model;

/**
 * Part abstract class that details the functionality and attributes that a part has.
 *
 * @author Harrison Thacker
 */
public abstract class Part {

    private int id; //The ID of the part.
    private String name; //The part name.
    private double price; //The price of the part.
    private int stock; //How much of the part is in inventory.
    private int min; //The minimum quantity of the part.
    private int max; //The maximum quantity of the part.

    /**
     * Constructor for the part class.
     *
     * @param id = The ID of the given part.
     * @param name = The name of the given part.
     * @param price = The price of the given part.
     * @param stock = The inventory stock of the current part.
     * @param min = The minimum part quantity.
     * @param max = The maximum part quantity.
     */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Getter for the specific part ID.
     * @return The ID of the given part.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for the specific part ID.
     * @param id The ID of the given part.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for the part name.
     * @return The name of the given part.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the part name.
     * @param name The name of the given part.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the part price.
     * @return The price of the given part.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter for the part price.
     * @param price The price of the given part.
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * Getter for the part stock.
     * @return The inventory stock of the current part.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Setter for the part stock.
     * @param stock The inventory stock of the current part.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Getter for the part minimum quantity.
     * @return The minimum quantity.
     */
    public int getMin() {
        return min;
    }

    /**
     * Setter for the part minimum quantity.
     * @param min The minimum quantity.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Getter for the part maximum quantity.
     * @return The maximum quantity.
     */
    public int getMax() {
        return max;
    }

    /**
     * Setter for the part maximum quantity.
     * @param max The maximum quantity.
     */
    public void setMax(int max) {
        this.max = max;
    }
    
}