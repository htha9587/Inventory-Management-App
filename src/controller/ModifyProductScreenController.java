/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Part;
import model.Product;

/**
 * FXML Controller class for the Modifying Product screen.
 *
 * @author Harrison Thacker
 */
public class ModifyProductScreenController implements Initializable {

    public TableView<Part> productPartTable; //Product Part TableView.
    public TableColumn<Part, Integer> partID; //Product Part ID TableView Column.
    public TableColumn<Part, String> partName; //Product Part name TableView Column.
    public TableColumn<Part, Integer> partInventory; //Product Part Inventory TableView Column.
    public TableColumn<Part, Double> partPrice; //Product Part Price TableView Column.
    public TextField partSearchField; //Search field for the Product Part Table.
    public TableView<Part> associatedPartsTable; //Product associated part TableView.
    public TableColumn<Part, Integer> associatedPartID; //Part associated ID TableView Column.
    public TableColumn<Part, String> associatedPartName; //Part associated name TableView Column.
    public TableColumn<Part, Integer> associatedPartInventory; //Part associated Inventory TableView Column.
    public TableColumn<Part, Double> associatedPartPrice; //Part associated Price TableView Column.
    public TextField productIDField; //Field for the product ID.
    public TextField productNameField; //Field for the product name.
    public TextField productInvField; //Field for the product Inventory count.
    public TextField productPriceField; //Field for the product price.
    public TextField productMaxField; //Field for the product maximum quantity.
    public TextField productMinField; //Field for the product minimum quantity.
    public Button cancelButton; //Cancel button to close out the scene.

    private Product product;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int productIndex;

    /**
     * Initializes the controller class. On startup, both Tables are filled with part information.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        productPartTable.setItems(Inventory.getAllParts());
        associatedPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        productIDField.setEditable(false);
    }

    /**
     * Event handler method for when the user clicks the Cancel button.
     * @param actionEvent = Clicking on the cancel button.
     */
    public void cancelButtonClick(ActionEvent actionEvent)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel");
        alert.setHeaderText("Cancel Modifying Product?");
        alert.setContentText("Are you sure you wish to cancel modifying this product?");
        alert.showAndWait().ifPresent(response ->
        {
            if(response == ButtonType.OK)
            {
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
            }
        });
    }

    /**
     * Method that checks the text fields to make sure the product elements are all correct. A validation exception
     * is thrown if the format doesn't match what is specified.
     * @param product = The part currently being evaluated.
     * @return True
     */
    public boolean productValidation(Product product) throws NullPointerException
    {
        String productName = productNameField.getText();
        int productInventory = Integer.parseInt(productInvField.getText());
        Double productPrice = Double.parseDouble(productPriceField.getText());
        int productMin = Integer.parseInt(productMinField.getText());
        int productMax = Integer.parseInt(productMaxField.getText());

        if(productName.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Product Error!");
            alert.setContentText("Product name cannot be blank!");
            alert.showAndWait();
            throw new NullPointerException("Product name cannot be blank!");
        }

        if(productMin > productMax)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Product Error!");
            alert.setContentText("The minimum quantity of products cannot exceed the maximum!");
            alert.showAndWait();
            throw new NullPointerException("The minimum quantity of products cannot exceed the maximum!");
        }

        if(productPrice < 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Product Error!");
            alert.setContentText("Product price cannot be less than zero!");
            alert.showAndWait();
            throw new NullPointerException("Product price cannot be less than zero!");
        }

        if(productInventory < productMin)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Product Error!");
            alert.setContentText("The Product Inventory count cannot be less than the minimum!");
            alert.showAndWait();
            throw new NullPointerException("The Product Inventory count cannot be less than the minimum!");
        }

        if(productInventory > productMax)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Product Error!");
            alert.setContentText("The Product Inventory count cannot exceed the maximum!");
            alert.showAndWait();
            throw new NullPointerException("The Product Inventory count cannot exceed the maximum!");
        }
        return true;
    }

    /**
     * Event handler method for searching the part table for name and ID.
     * @param actionEvent = Entering text to search the part table.
     */
    public void productSearchPart(ActionEvent actionEvent)
    {
        String productPartSearch = partSearchField.getText();
        if(productPartSearch.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Product Error!");
            alert.setHeaderText("Empty search!");
            alert.setContentText("Please enter the name or ID of the part.");
            alert.showAndWait();
        }

        ObservableList<Part> searchedParts = Inventory.lookupPart(productPartSearch);
        if(searchedParts.size() == 0)
        {
            int partID = Integer.parseInt(productPartSearch);
            Part part = Inventory.lookupPart(partID);
            if(part != null)
            {
                searchedParts.add(part);
            }
        }
        productPartTable.setItems(searchedParts);
        partSearchField.setText("");
    }

    /**
     * Event handler method for clicking the add part button, sending the highlighted part to the bottom
     * Associated Parts table.
     * @param actionEvent = Clicking the add part button.
     */
    public void addAssociatedPart(ActionEvent actionEvent)
    {
        Part selectedPart = productPartTable.getSelectionModel().getSelectedItem();
        if(selectedPart != null )
        {
            associatedParts.add(selectedPart);
            associatedPartsTable.setItems(associatedParts);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Product Error!");
            alert.setHeaderText("No part selected!");
            alert.setContentText("Please select a part to add to the product.");
            alert.showAndWait();
        }
    }

    /**
     * Event handler method for clicking the remove associated part button, removing the selected part from
     * the bottom table.
     * @param actionEvent = Clicking the Remove Associated Part button.
     */
    public void removeAssociatedPart(ActionEvent actionEvent)
    {
        Part part = associatedPartsTable.getSelectionModel().getSelectedItem();
        if(part != null)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Remove Part");
            alert.setHeaderText("Remove Associated Part?");
            alert.setContentText("Are you sure you wish to remove this associated part?");
            alert.showAndWait().ifPresent(response ->
            {
                if(response == ButtonType.OK)
                {
                    associatedParts.remove(part);
                    product.deleteAssociatedPart(part);
                }
            });
        }
    }

    /**
     * Finds the selected product and its information from the main screen, and populates it in the Modify Product screen.
     * @param selectedProduct = The product the user selected on the product table, opening up in the Modify Product screen.
     */
    public void findSelectedProduct(Product selectedProduct)
    {
        this.product = selectedProduct;
        productIDField.setText(Integer.toString(product.getId()));
        productNameField.setText(product.getName());
        productInvField.setText(Integer.toString(product.getStock()));
        productPriceField.setText(Double.toString(product.getPrice()));
        productMinField.setText(Integer.toString(product.getMin()));
        productMaxField.setText(Integer.toString(product.getMax()));
        associatedParts = product.getAllAssociatedParts();
        associatedPartsTable.setItems(associatedParts);
    }

    /**
     * Event handler method for when the user clicks the Save button.
     * @param actionEvent = Clicking on the Save product button.
     * @throws NumberFormatException = Thrown when the text field doesn't match the number format.
     * @throws NullPointerException = Thrown should any part text fields be left empty by the user.
     */
    public void saveProduct(ActionEvent actionEvent) throws NumberFormatException, NullPointerException
    {
        int productID = Integer.parseInt(productIDField.getText());
        String productName = productNameField.getText();
        int productInventory = 0;
        double productCost = 0.0;
        int productMin = 0;
        int productMax = 0;

        try
        {
            productInventory = Integer.parseInt(productInvField.getText());
        }
        catch (NumberFormatException exception)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Please enter a number for the product Inventory quantity.");
            alert.show();
            throw new NumberFormatException("Please enter a number for the product Inventory quantity.");
        }
        catch (NullPointerException exception)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Please enter the Inventory quantity.");
            alert.show();
            throw new NullPointerException("Please enter the Inventory quantity.");
        }
        try
        {
            productCost = Double.parseDouble(productPriceField.getText());
        }
        catch (NumberFormatException exception)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Please enter product cost in this format: XX.XX");
            alert.show();
            throw new NumberFormatException("Please enter product cost in this format: XX.XX");
        }
        catch (NullPointerException exception)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Please enter the Product Price.");
            alert.show();
            throw new NullPointerException("Please enter the Product Price.");
        }
        try
        {
            productMin = Integer.parseInt(productMinField.getText());
        }
        catch (NumberFormatException exception)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Please enter a number for the product minimum quantity.");
            alert.show();
            throw new NumberFormatException("Please enter a number for the product minimum quantity.");
        }
        catch (NullPointerException exception)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Please enter the minimum quantity for the product.");
            alert.show();
            throw new NullPointerException("Please enter the minimum quantity for the product.");
        }
        try
        {
            productMax = Integer.parseInt(productMaxField.getText());
        }
        catch (NumberFormatException exception)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Please enter a number for the product maximum quantity.");
            alert.show();
            throw new NumberFormatException("Please enter a number for the product maximum quantity.");
        }
        catch (NullPointerException exception)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Please enter the maximum quantity for the product.");
            alert.show();
            throw new NullPointerException("Please enter the minimum quantity for the product.");
        }
        Product modifiedProduct = new Product(productID, productName, productCost, productInventory, productMin, productMax);
        for(Part part : associatedParts)
        {
            modifiedProduct.addAssociatedPart(part);
        }
        if(productValidation(modifiedProduct))
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Save");
            alert.setHeaderText("Save Modified Product?");
            alert.setContentText("Are you sure you wish to save this modified product?");
            alert.showAndWait().ifPresent(response ->
            {
                if(response == ButtonType.OK)
                {
                    Inventory.updateProduct(productIndex, modifiedProduct);
                    Stage stage = (Stage) (cancelButton.getScene().getWindow());
                    stage.close();
                }
            });
        }
    }


}
