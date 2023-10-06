/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Product class that details the functionality and attributes that a product has.
 *
 * @author Harrison Thacker
 */
public class Product {
    
    //An observable array list for the parts that each product has.
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList(); 
    private int id; //The ID of the product.
    private String name; //The product name.
    private double price; //The price of the product.
    private int stock; //How much of the product is in inventory.
    private int min; //The minimum quantity of the product.
    private int max; //The maximum quantity of the product.
    
    /**
     * Constructor for the product class.
     * 
     * @param id = The ID of the product.
     * @param name = The product name.
     * @param price = The price of the product.
     * @param stock = How much of the product is in inventory.
     * @param min = The minimum quantity of the product.
     * @param max = The maximum quantity of the product.
     */
   public Product(int id, String name, double price, int stock, int min, int max) 
   {
       this.id = id;
       this.name = name;
       this.price = price;
       this.stock = stock;
       this.min = min;
       this.max = max;
   }
   
   /**
    * Getter for the product ID.
    * @return id = The product ID.
    */
   public int getId()
   {
       return id;
   }    
   
   /**
    * Getter for the product name.
    * @return name = The name of the product.
    */
   public String getName()
   {
       return name;
   }
   
   /**
    * Getter for the product price.
    * @return price = The price of the product.
    */
   public double getPrice()
   {
       return price;
   }
   
   /**
    * Getter for the product stock.
    * @return stock = The current stock of the product.
    */
   public int getStock()
   {
       return stock;
   }
   
   /**
    * Getter for the product minimum quantity.
    * @return min = The minimum quantity.
    */
   public int getMin()
   {
       return min;
   }
   
   /**
    * Getter for the product maximum quantity.
    * @return max = The maximum quantity.
    */
   public int getMax()
   {
       return max;
   }
   
   /**
    * Setter for the product ID.
    * @param id = The ID of the product.
    */
   public void setId(int id)
   {
       this.id = id;
   }
   
   /**
    * Setter for the product name.
    * @param name = The name of the product.
    */
   public void setName(String name)
   {
       this.name = name;
   }
   
   /**
    * Setter for the product price.
    * @param price = The price of the product.
    */
   public void setPrice(double price)
   {
       this.price = price;
   }
   
   /**
    * Setter for the product stock.
    * @param stock = The current stock of the product.
    */
   public void setStock(int stock)
   {
       this.stock = stock;
   }
   
   /**
    * Setter for the product minimum quantity.
    * @param min = The minimum quantity.
    */
   public void setMin(int min)
   {
       this.min = min;
   }
   
   /**
    * Setter for the product maximum quantity.
    * @param max= The maximum quantity.
    */
   public void setMax(int max)
   {
       this.max = max;
   }
   
   /**
    * Adds a part that is associated to the product.
    * @param part = A component of the product.
    */
   public void addAssociatedPart(Part part)
   {
       associatedParts.add(part);
   }
   
   /**
    * Removes a part that is associated to the product.
    * @param selectedAssociatedPart
    * @return A boolean true/false that details whether the associated part was deleted or not.
    */
   public boolean deleteAssociatedPart(Part selectedAssociatedPart)
   {
       if(associatedParts.contains(selectedAssociatedPart))
       {
           associatedParts.remove(selectedAssociatedPart);
           return true;
       }
       return false;
   }
   
   /**
    * Getter for the products associated parts.
    * @return associatedParts = The associated parts of the specific product.
    */
   public ObservableList<Part> getAllAssociatedParts()
   {
       return associatedParts;
   }
}
