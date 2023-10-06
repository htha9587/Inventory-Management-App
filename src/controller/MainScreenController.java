/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.*;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.OutSourced;
import model.Part;
import model.Product;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class for the main page of the Inventory system.
 *
 * @author Harrison Thacker
 */
public class MainScreenController implements Initializable {


    public TableView<Part> partTable; //Part TableView.
    public TableColumn<Part, Integer> partID; //Part ID TableView Column.
    public TableColumn<Part, String> partName; //Part name TableView Column.
    public TableColumn<Part, Integer> partInventory; //Part Inventory TableView Column.
    public TableColumn<Part, Double> partPrice; //Part Price TableView Column.
    public TextField partSearchField; //Search field for the Part Table.
    public TableView<Product> productTable; //Product TableView.
    public TableColumn<Part, Integer> productID; //Product ID TableView Column.
    public TableColumn<Part, String> productName; //Product name TableView Column.
    public TableColumn<Part, Integer> productInventory; //Product Inventory TableView Column.
    public TableColumn<Part, Double> productPrice; //Product Price TableView Column.
    public TextField productSearchField; //Search field for the Product table.
    public Label errorMessageLabel; //Label that displays errors when searching.
    static boolean wasEntered; //Boolean to verify whether or not anything was searched in the text fields.
    
    /**
     * Initializes the controller class and fills the TableViews with test data on startup.
     *
     * RUNTIME_ERROR: java.lang.IllegalStateException.
     * I kept getting this runtime error as I was trying to have both main screen TableViews populate
     * with the test data I specified below. Nothing in the tables showed up, and all I got was the
     * IllegalStateException. I decided to spend some time in the model classes looking at the part and product
     * attributes. I then tried to have each PropertyValueFactory match the naming conventions of the part
     * and product data members. For example: The Part Table name column was supposed to match the data
     * member naming convention: private String "name". It then occurred to me, that the TableView PropertyValueFactory
     * variables weren't for just a string of text. They were accessors that had to refer back to the naming
     * conventions of the part and product data members. Once I ran the program again, both tables populated
     * with their test data.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(!wasEntered)
          {
              //Adds test part and product data to the Inventory class.
            Inventory.addPart(new InHouse(1, "PC Monitor", 99.99, 17, 1, 17, 100));
            Inventory.addPart(new InHouse(2, "Keyboard", 79.99, 12, 1, 12, 101));
            Inventory.addPart(new InHouse(3, "Desktop", 149.99, 9, 1, 9, 102));
            Inventory.addPart(new OutSourced(4, "Power Cord", 29.99, 24, 1, 24, "AXIOM.Co"));
            Inventory.addPart(new OutSourced(5, "Mouse", 79.99, 21, 1, 21, "NETGEAR.Co"));
            Inventory.addProduct(new Product(1000, "Gaming PC", 299.99, 7, 1, 7));
            Inventory.addProduct(new Product(1001, "Elite Bundle", 499.99, 10, 1, 10));
            Inventory.addProduct(new Product(1002, "Base Bundle", 199.99, 15, 1, 15));
            Inventory.addProduct(new Product(1003, "Quantum PC", 2999.99, 4, 1, 4));
            Inventory.addProduct(new Product(1004, "Work Bundle", 699.99, 11, 1, 11));
        }

        //Fills both TableViews with the respective part and product test data.
        partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        partTable.setItems(Inventory.getAllParts());
        productID.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        productTable.setItems(Inventory.getAllProducts());
    }

    /**
     * ActionEvent handler method for the exit button.
     * @param actionEvent = The exit button to close out the project.
     */
    public void exitProgram(ActionEvent actionEvent)
    {
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
        exitAlert.setTitle("Exit?");
        exitAlert.setHeaderText("Exit Inventory");
        exitAlert.setContentText("Are you sure you wish to exit the Inventory system?");
        exitAlert.showAndWait().ifPresent(response ->
        {
            if (response == ButtonType.OK)
            {
                Platform.exit();
            }
        });
    }

    /**
     * The ActionEvent handler method for when you use the search part field.
     * @param actionEvent = The part search field.
     */
    public void partSearch(ActionEvent actionEvent)
    {
        String partSearchText = partSearchField.getText();
        if(partSearchText.isEmpty())
        {
            errorMessageLabel.setText("Please enter name or ID number!");
        }

        ObservableList<Part> searchedParts = Inventory.lookupPart(partSearchText);
        if(searchedParts.size() == 0)
        {
            try
            {
                int partID = Integer.parseInt(partSearchText);
                Part part = Inventory.lookupPart(partID);
                if(part != null)
                {
                    searchedParts.add(part);
                    errorMessageLabel.setText("Part found.");
                }
            }
            catch(NumberFormatException e)
            {

            }
        }
        partTable.setItems(searchedParts);
        partSearchField.setText("");
        if(searchedParts.isEmpty())
        {
            errorMessageLabel.setText("No such part exists!");
            partTable.setItems(Inventory.getAllParts());
        }
    }

    /**
     * The ActionEvent handler method for when you use the search product field.
     * @param actionEvent = The product search field.
     */
    public void productSearch(ActionEvent actionEvent)
    {
        String productSearchText = productSearchField.getText();
        if(productSearchText.isEmpty())
        {
            errorMessageLabel.setText("Please enter name or ID number for the product!");
        }

        ObservableList<Product> searchedProducts = Inventory.lookupProduct(productSearchText);
        if(searchedProducts.size() == 0)
        {
            try
            {
                int productID = Integer.parseInt(productSearchText);
                Product product = Inventory.lookupProduct(productID);
                if(product != null)
                {
                    searchedProducts.add(product);
                    errorMessageLabel.setText("Product found.");
                }
            }
            catch(NumberFormatException e)
            {

            }
        }
        productTable.setItems(searchedProducts);
        productSearchField.setText("");
        if(searchedProducts.isEmpty())
        {
            errorMessageLabel.setText("No such product exists!");
            productTable.setItems(Inventory.getAllProducts());
        }
    }

    /**
     * Event handler method for the add part button, opening up the Add Part Screen.
     * @param mouseEvent = The add part button.
     */
    public void onAddPart(MouseEvent mouseEvent)
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/view/AddPartScreen.fxml"));
            Stage addPartStage = new Stage();
            addPartStage.setTitle("Add Part");
            addPartStage.setScene(new Scene(root));
            addPartStage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Event handler method for the modify part button, opening up the Modify Part screen.
     * @param mouseEvent = The modify part button.
     */
    public void onModifyPart(MouseEvent mouseEvent)
    {
        if(partTable.getSelectionModel().getSelectedItem() != null)
        {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ModifyPartScreen.fxml"));
            Parent mainScreen = loader.load();
            Stage mainStage = new Stage();
            mainStage.setTitle("Modify Part");
            mainStage.setScene(new Scene(mainScreen));
            mainStage.show();
            ModifyPartScreenController partController = loader.getController();
            Part selectedPart = partTable.getSelectionModel().getSelectedItem();
            partController.findSelectedPart(selectedPart);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        }
        else
        {
            //JavaFX alert if you don't select a part from the part table before clicking Modify.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Select a Part");
            alert.setContentText("Please select a part from the table.");
            alert.show();
        }
    }

    /**
     * Event handler method for the delete part button, opening up a JavaFX alert specifying whether or not
     * you wish to delete the selected part from the table.
     * @param actionEvent = The part delete button.
     */
    public void onDeletePart(ActionEvent actionEvent)
    {
        Part deletePart = partTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Part?");
        alert.setContentText("Are you sure you wish to delete " + deletePart.getName() + " from the Inventory?");
        alert.showAndWait().ifPresent(response ->
        {
            if(response == ButtonType.OK)
            {
                Inventory.deletePart(deletePart);
            }
        });
    }

    /**
     * Event handler method for the add product button, opening up the Add Product Screen.
     * @param mouseEvent = The add product button.
     */
    public void onAddProduct(MouseEvent mouseEvent)
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/view/AddProductScreen.fxml"));
            Stage addProductStage = new Stage();
            addProductStage.setTitle("Add Product");
            addProductStage.setScene(new Scene(root));
            addProductStage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Event handler method for the modify product button, opening up the Modify Product Screen.
     * @param mouseEvent = The modify product button.
     */
    public void onModifyProduct(MouseEvent mouseEvent)
    {
        if(productTable.getSelectionModel().getSelectedItem() != null)
        {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ModifyProductScreen.fxml"));
                Parent mainScreen = loader.load();
                Stage mainStage = new Stage();
                mainStage.setTitle("Modify Product");
                mainStage.setScene(new Scene(mainScreen));
                mainStage.show();
                ModifyProductScreenController productScreenController = loader.getController();
                Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
                productScreenController.findSelectedProduct(selectedProduct);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            //JavaFX alert if you don't select a product from the product table before clicking Modify.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Select a Product");
            alert.setContentText("Please select a product from the table.");
            alert.show();
        }
    }

    /**
     * Event handler method for the delete product button, opening up a JavaFX alert specifying whether or not
     * you wish to delete the selected product from the table.
     * @param actionEvent = The product delete button.
     */
    public void onDeleteProduct(ActionEvent actionEvent)
    {
        Product deleteProduct = productTable.getSelectionModel().getSelectedItem();
        if(!deleteProduct.getAllAssociatedParts().isEmpty())
        {
            //JavaFX alert if you try to delete a product that already has associated parts.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Deletion Failed");
            alert.setContentText("Cannot remove product. It has associated parts.");
            alert.show();
        }
        else
        {
            //JavaFX alert confirming whether or not you wish to delete the product.
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Product");
            alert.setContentText("Are you sure you wish to delete " + deleteProduct.getName() + " from the Inventory?");
            alert.showAndWait().ifPresent(response ->
            {
                if(response == ButtonType.OK)
                {
                    Inventory.deleteProduct(deleteProduct);
                }
            });
        }
    }
}
