/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Model class for a part made in-house.
 * @author Harrison Thacker
 */
public class InHouse extends Part {
    
    private int machineId; // The part's machine ID.
    
    /**
     * Constructor for the InHouse class.
     * 
     * @param id = The part ID.
     * @param name = The name of the part.
     * @param price = The part's price.
     * @param stock = The amount of the part that is currently in inventory.
     * @param min = The minimum quantity for the part.
     * @param max = The maximum quantity for the part.
     * @param machineId = The part's machine ID.
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId)
    {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }
    
    /**
     * Getter for the machine ID.
     * 
     * @return The parts machine ID.
     */
    public int getMachineId()
    {
        return machineId;
    }
    
    /**
     * The setter for the machine ID.
     * 
     * @param machineId of the part.
     */
    public void setMachineId(int machineId)
    {
        this.machineId = machineId;
    }

}