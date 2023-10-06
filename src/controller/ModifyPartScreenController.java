/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.OutSourced;
import model.Part;

/**
 * FXML Controller class for the Modify Part screen.
 *
 * @author Harrison Thacker
 */
public class ModifyPartScreenController implements Initializable {

    public RadioButton outsourcedRadio; //RadioButton for an outsourced part.
    public RadioButton inhouseRadio; //RadioButton for a part that was made inhouse.
    public Label typeLabel; //Label that changes the part type based on what RadioButton was selected.
    public TextField partIDField; //Field for the part ID.
    public TextField partNameField; //Field for the part name.
    public TextField partInvField; //Field for the part Inventory count.
    public TextField partPriceField; //Field for the part price.
    public TextField partMaxField; //Field for the part maximum quantity.
    public TextField partTypeField; //Field for the part type.
    public TextField partMinField; //Field for the part minimum quantity.
    public Button cancelButton; //Cancel button to close out the scene.
    private ToggleGroup type; //RadioButton ToggleGroup to switch between the selected radio buttons.
    private Part part; //Instance of the abstract Part class.
    private int partIndex;

    /**
     * Initializes the controller class. On startup, the radio buttons are set.
     * The part ID field is made uneditable.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type = new ToggleGroup();
        this.inhouseRadio.setToggleGroup(type);
        this.outsourcedRadio.setToggleGroup(type);
        partIDField.setEditable(false);
    }

    /**
     * Method that changes the part type label to machine ID if the inhouse radio button is clicked.
     */
    public void inhousePart(MouseEvent mouseEvent)
    {
        if(type.getSelectedToggle().equals(inhouseRadio))
        {
            typeLabel.setText("Machine ID");
        }
    }

    /**
     * Method that changes the part type label to company name if the outsourced radio button is clicked.
     */
    public void outsourcedPart(MouseEvent mouseEvent)
    {
        if(type.getSelectedToggle().equals(outsourcedRadio))
        {
            typeLabel.setText("Company Name");
        }
    }

    /**
     * Event handler method for when the user clicks the Cancel button.
     * @param actionEvent = Clicking on the cancel button.
     */
    public void cancelButtonClick(ActionEvent actionEvent)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel");
        alert.setHeaderText("Cancel Modifying Part?");
        alert.setContentText("Are you sure you wish to cancel modifying this part?");
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
     * Method that checks the text fields to make sure the part elements are all correct. A validation exception
     * is thrown if the format doesn't match what is specified.
     * @param part = The part currently being evaluated.
     * @return True
     */
    public boolean partValidation(Part part) throws NullPointerException
    {
        String partName = partNameField.getText();
        int partInventory = Integer.parseInt(partInvField.getText());
        Double partPrice = Double.parseDouble(partPriceField.getText());
        int partMin = Integer.parseInt(partMinField.getText());
        int partMax = Integer.parseInt(partMaxField.getText());

        if(partName.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Part Error!");
            alert.setContentText("Part name cannot be blank!");
            alert.showAndWait();
            throw new NullPointerException("Part name cannot be blank!");
        }

        if(partMin > partMax)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Part Error!");
            alert.setContentText("The minimum quantity of parts cannot exceed the maximum!");
            alert.showAndWait();
            throw new NullPointerException("The minimum quantity of parts cannot exceed the maximum!");
        }

        if(partPrice < 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Part Error!");
            alert.setContentText("Part price cannot be less than zero!");
            alert.showAndWait();
            throw new NullPointerException("Part price cannot be less than zero!");
        }

        if(partInventory < partMin)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Part Error!");
            alert.setContentText("The Part Inventory count cannot be less than the minimum!");
            alert.showAndWait();
            throw new NullPointerException("The Part Inventory count cannot be less than the minimum!");
        }

        if(type.getSelectedToggle().equals(outsourcedRadio))
        {
            String companyName = partTypeField.getText();
            if(companyName.isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Part Error!");
                alert.setContentText("Company Name cannot be blank!");
                alert.showAndWait();
                throw new NullPointerException("Company Name cannot be blank!");
            }
        }

        if(partInventory > partMax)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Part Error!");
            alert.setContentText("The Part Inventory count cannot exceed the maximum!");
            alert.showAndWait();
            throw new NullPointerException("The Part Inventory count cannot exceed the maximum!");
        }
        return true;
    }

    /**
     * Event handler method for when the user clicks the Save button, modifying the selected part.
     * @param actionEvent = Clicking on the Save part button.
     * @throws NumberFormatException = Thrown when the text field doesn't match the number format.
     * @throws NullPointerException = Thrown should any part text fields are left empty by the user.
     */
    public void savePartButton(ActionEvent actionEvent) throws NumberFormatException, NullPointerException
    {
        int partID = Integer.parseInt(partIDField.getText());
        String partName = partNameField.getText();
        int partInventory = 0;
        double partCost = 0;
        int partMin = 0;
        int partMax = 0;
        int machineID = 0;
        String companyName = "";

        if(type.getSelectedToggle().equals(inhouseRadio))
        {
            try
            {
                partInventory = Integer.parseInt(partInvField.getText());
            }
            catch (NumberFormatException exception)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setContentText("Please enter a number for the part Inventory quantity.");
                alert.show();
                throw new NumberFormatException("Please enter a number for the part Inventory quantity.");
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
                partCost = Double.parseDouble(partPriceField.getText());
            }
            catch (NumberFormatException exception)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setContentText("Please enter part cost in this format: XX.XX");
                alert.show();
                throw new NumberFormatException("Please enter part cost in this format: XX.XX");
            }
            catch (NullPointerException exception)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setContentText("Please enter the Part Price.");
                alert.show();
                throw new NullPointerException("Please enter the Part Price.");
            }
            try
            {
                partMin = Integer.parseInt(partMinField.getText());
            }
            catch (NumberFormatException exception)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setContentText("Please enter a number for the part minimum quantity.");
                alert.show();
                throw new NumberFormatException("Please enter a number for the part minimum quantity.");
            }
            catch (NullPointerException exception)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setContentText("Please enter the minimum quantity for the part.");
                alert.show();
                throw new NullPointerException("Please enter the minimum quantity for the part.");
            }
            try
            {
                partMax = Integer.parseInt(partMaxField.getText());
            }
            catch (NumberFormatException exception)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setContentText("Please enter a number for the part maximum quantity.");
                alert.show();
                throw new NumberFormatException("Please enter a number for the part maximum quantity.");
            }
            catch (NullPointerException exception)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setContentText("Please enter the maximum quantity for the part.");
                alert.show();
                throw new NullPointerException("Please enter the minimum quantity for the part.");
            }
            try
            {
                machineID = Integer.parseInt(partTypeField.getText());
            }
            catch (NumberFormatException exception)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setContentText("Please enter a number for the part machine ID.");
                alert.show();
                throw new NumberFormatException("Please enter a number for the part machine ID.");
            }
            catch (NullPointerException exception)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setContentText("Please enter the machine ID for the part.");
                alert.show();
                throw new NullPointerException("Please enter the machine ID for the part.");
            }
            InHouse inhousePartModify = new InHouse(partID, partName, partCost, partInventory, partMin, partMax, machineID);
            if(partValidation(inhousePartModify))
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Save");
                alert.setHeaderText("Save Modified Part?");
                alert.setContentText("Are you sure you wish to modify this part?");
                alert.showAndWait().ifPresent(response ->
                {
                    if(response == ButtonType.OK)
                    {
                        Inventory.updatePart(partIndex, inhousePartModify);
                        Stage stage = (Stage) cancelButton.getScene().getWindow();
                        stage.close();
                    }
                });
            }
        }
        if(type.getSelectedToggle().equals(outsourcedRadio))
        {
            try
            {
                partInventory = Integer.parseInt(partInvField.getText());
            }
            catch (NumberFormatException exception)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setContentText("Please enter a number for the part Inventory quantity.");
                alert.show();
                throw new NumberFormatException("Please enter a number for the part Inventory quantity.");
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
                partCost = Double.parseDouble(partPriceField.getText());
            }
            catch (NumberFormatException exception)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setContentText("Please enter part cost in this format: XX.XX");
                alert.show();
                throw new NumberFormatException("Please enter part cost in this format: XX.XX");
            }
            catch (NullPointerException exception)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setContentText("Please enter the Part Price.");
                alert.show();
                throw new NullPointerException("Please enter the Part Price.");
            }
            try
            {
                partMin = Integer.parseInt(partMinField.getText());
            }
            catch (NumberFormatException exception)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setContentText("Please enter a number for the part minimum quantity.");
                alert.show();
                throw new NumberFormatException("Please enter a number for the part minimum quantity.");
            }
            catch (NullPointerException exception)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setContentText("Please enter the minimum quantity for the part.");
                alert.show();
                throw new NullPointerException("Please enter the minimum quantity for the part.");
            }
            try
            {
                partMax = Integer.parseInt(partMaxField.getText());
            }
            catch (NumberFormatException exception)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setContentText("Please enter a number for the part maximum quantity.");
                alert.show();
                throw new NumberFormatException("Please enter a number for the part maximum quantity.");
            }
            catch (NullPointerException exception)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setContentText("Please enter the maximum quantity for the part.");
                alert.show();
                throw new NullPointerException("Please enter the maximum quantity for the part.");
            }
            try
            {
                companyName = partTypeField.getText();
            }
            catch (NumberFormatException exception)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setContentText("Please enter a name for the part Company Name.");
                alert.show();
                throw new NumberFormatException("Please enter a name for the part Company Name.");
            }
            catch (NullPointerException exception)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setContentText("Please enter the Company Name for the part.");
                alert.show();
                throw new NullPointerException("Please enter the Company Name for the part.");
            }
            OutSourced outsourcedPart = new OutSourced(partID, partName, partCost, partInventory, partMin, partMax, companyName);
            if(partValidation(outsourcedPart))
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Save");
                alert.setHeaderText("Save Modified Part?");
                alert.setContentText("Are you sure you wish to modify this part?");
                alert.showAndWait().ifPresent(response ->
                {
                    if(response == ButtonType.OK)
                    {
                        Inventory.updatePart(partIndex, outsourcedPart);
                        Stage stage = (Stage) cancelButton.getScene().getWindow();
                        stage.close();
                    }
                });
            }
        }
        if(type.getSelectedToggle() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Please select the part type.");
            alert.show();
            throw new NullPointerException("Please enter the part type.");
        }
    }

    /**
     * Finds the selected part and its information from the main screen, and populates it in the Modify Part screen.
     * @param selectedPart = The part the user selected on the part table, opening up in the Modify Part screen.
     */
    public void findSelectedPart(Part selectedPart)
    {
       this.part = selectedPart;
       if(selectedPart instanceof InHouse)
       {
           typeLabel.setText("Machine ID");
           InHouse inHouse = (InHouse)selectedPart;
           partIDField.setText(Integer.toString(inHouse.getId()));
           partNameField.setText(inHouse.getName());
           partInvField.setText(Integer.toString(inHouse.getStock()));
           partPriceField.setText(Double.toString(inHouse.getPrice()));
           partMinField.setText(Integer.toString(inHouse.getMin()));
           partMaxField.setText(Integer.toString(inHouse.getMax()));
           partTypeField.setText(Integer.toString(inHouse.getMachineId()));
           inhouseRadio.setSelected(true);
       }
       else if(selectedPart instanceof OutSourced)
       {
           typeLabel.setText("Company Name");
           OutSourced outSourced = (OutSourced)selectedPart;
           partIDField.setText(Integer.toString(outSourced.getId()));
           partNameField.setText(outSourced.getName());
           partInvField.setText(Integer.toString(outSourced.getStock()));
           partPriceField.setText(Double.toString(outSourced.getPrice()));
           partMinField.setText(Integer.toString(outSourced.getMin()));
           partMaxField.setText(Integer.toString(outSourced.getMax()));
           partTypeField.setText(outSourced.getCompanyName());
           outsourcedRadio.setSelected(true);
           inhouseRadio.setSelected(false);
       }
    }
}
