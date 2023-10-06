/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Model class for a part that was outsourced.
 *
 * @author Harrison Thacker
 */
public class OutSourced extends Part {
    
    private String companyName; //The name of the company that made the part.
    
    /**
     * Constructor for parts that were outsourced.
     * 
     * @param id = The part's id.
     * @param name = The name of the part.
     * @param price = The price of the part.
     * @param stock = The amount of the part that's currently in inventory.
     * @param min = The minimum quantity for the part.
     * @param max = The maximum quantity for the part.
     * @param companyName = The name of the company that made the part.
     */
    public OutSourced(int id, String name, double price, int stock, int min, int max, String companyName)
    {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }
    
    /**
     * The getter for the companyName.
     * 
     * @return The name of the company.
     */
    public String getCompanyName()
    {
        return companyName;
    }
    
    /**
     * The setter for the companyName.
     * 
     * @param companyName 
     */
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }
    
}
