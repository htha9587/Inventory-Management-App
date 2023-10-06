/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Model class that handles the inventory for both products and parts.
 *
 * @author Harrison Thacker
 */
public class Inventory {
    
    //Creates a JavaFX Observable Array List of all the parts.
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    //Creates a JavaFX Observable Array List of all the products.
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    
    /**
     * When this method is called, a part is added to the Inventory class.
     * @param newPart = The new part added to the parts ObservableList.
     */
    public static void addPart(Part newPart)
    {
        if(newPart != null)
        {
            allParts.add(newPart);
        }
    }
    
    /**
     * When this method is called, a product is added to the inventory class.
     * @param newProduct = The new product that is added to the product ObservableList.
     */
    public static void addProduct(Product newProduct)
    {
        if(newProduct != null)
        {
            allProducts.add(newProduct);
        }
    }
     
    /**
     * Locates the specified part by searching for its ID.
     * @param partId = The ID of the specified part.
     * @return The part identification if its located. Otherwise, nothing is.
     */
    public static Part lookupPart(int partId)
    {
        if(!allParts.isEmpty())
        {
            for(int i = 0; i < allParts.size(); i++)
            {
                if(allParts.get(i).getId() == partId)
                {
                    return allParts.get(i);
                }
            }
        }
        return null;
    }
    
    /**
     * Locates the specified product by searching for its ID.
     * @param productId = The ID of the specified product.
     * @return The id of the product if found. Otherwise, nothing is.
     */
    public static Product lookupProduct(int productId)
    {
        if(!allProducts.isEmpty())
        {
            for(int i = 0; i < allProducts.size(); i++)
            {
                if(allProducts.get(i).getId() == productId)
                {
                    return allProducts.get(i);
                }
            }
        }
        return null;
    }
    
    /**
     * Uses a JavaFX ObservableList to locate the part.
     * @param partName = The name of the specified part.
     * @return An ObservableList of parts.
     */
    public static ObservableList<Part> lookupPart(String partName)
    {
        if(!allParts.isEmpty())
        {
            ObservableList searchPartsNameList = FXCollections.observableArrayList();
            for(Part p : getAllParts())
            {
                if(p.getName().contains(partName))
                {
                    searchPartsNameList.add(p);
                }
            }
            return searchPartsNameList;
        }
        return null;
    }
    
    /**
     * Uses a JavaFX ObservableList to locate the part.
     * @param productName = The name of the specified product.
     * @return An ObservableList of products.
     */
    public static ObservableList<Product> lookupProduct(String productName)
    {
        ObservableList<Product> searchProductsNameList = FXCollections.observableArrayList();
        for(Product product : getAllProducts())
            {
                if(product.getName().equals(productName))
                {
                    searchProductsNameList.add(product);
                }
            }
            return searchProductsNameList;
        }
    
    /**
     * Updates and overwrites a part in the part ObservableList.
     * This is to ensure the selected table row gets updated with the modified information.
     * @param selectedPart = The part that was selected.
     */
    public static void updatePart(int index, Part selectedPart)
    {
        for(index = 0; index < allParts.size(); index++)
        {
            if(allParts.get(index).getId() == selectedPart.getId())
            {
                allParts.set(index, selectedPart);
            }
        }
    }
    
    /**
     * Updates and overwrites a product in the product ObservableList.
     * This is to ensure the selected table row gets updated with the modified information.
     * @param index = An integer that is set and updated whenever this method is called.
     * @param newProduct = An instance of the Product object.
     */
    public static void updateProduct(int index, Product newProduct)
    {
        for(index = 0; index < allProducts.size(); index++)
        {
            if(allProducts.get(index).getId() == newProduct.getId())
            {
                allProducts.set(index, newProduct);
            }
        }
    }
    
    /**
     * Removes a part from the part ObservableList.
     * @param selectedPart = The part that is selected to be removed from the ObservableList.
     * @return  A boolean detailing whether or not the selected part has been removed.
     */
    public static boolean deletePart(Part selectedPart)
    {
        if(allParts.contains(selectedPart))
    {
        allParts.remove(selectedPart);
        return true;
    }
        else
    {
        return false;
    }
    }
    
    /**
     * Removes a product from the product ObservableList.
     * @param selectedProduct = The product selected to be removed from the ObservableList.
     * @return A boolean detailing whether or not the product has been removed.
     */
    public static boolean deleteProduct(Product selectedProduct)
    {
        if(allProducts.contains(selectedProduct))
    {
        allProducts.remove(selectedProduct);
        return true;
    }
        else
    {
        return false;
    }
    }
    
    /**
     * Getter for the part ObservableList.
     * @return allParts = The parts ObservableList.
     */
    public static ObservableList<Part> getAllParts()
    {
        return allParts;
    }
    
    /**
     * Getter for the product ObservableList.
     * @return allProducts = The products ObservableList.
     */
    public static ObservableList<Product> getAllProducts()
    {
        return allProducts;
    }

}
