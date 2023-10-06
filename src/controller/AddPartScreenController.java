/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import javafx.scene.input.MouseEvent;
import model.*;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;

/**
 * FXML Controller class for the Add Part screen.
 *
 * @author Harrison Thacker
 */
public class AddPartScreenController implements Initializable {

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

    Random rng = new Random(); //Random number generator for the part ID.
    int idNumber;
    boolean doesMatch = false;
    private boolean selected;

    /**
     * Initializes the controller class. On startup, the radio buttons and part ID number are set.
     * The part ID field is set with the rng ID and made uneditable.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type = new ToggleGroup();
        this.inhouseRadio.setToggleGroup(type);
        this.outsourcedRadio.setToggleGroup(type);
        idNumber = newPartID();
        partIDField.setText(Integer.toString(idNumber));
        partIDField.setEditable(false);
        inhouseRadio.setSelected(true);
        outsourcedRadio.setSelected(false);
    }

    /**
     * Method that generates a random number, ranged from 1-10000, for the part ID number.
     * @return The random number generated for use with the part ID number.
     */
    public int newPartID()
    {
        int randomPartID;
        randomPartID = 1 + rng.nextInt(10000);

        for(Part part : Inventory.getAllParts())
        {
            if(part.getId() == randomPartID)
            {
                doesMatch = true;
                newPartID();
            }
        }
        return randomPartID;
    }

    /**
     * Sets the text fields with placeholder text instructing the user for what to put in the fields.
     */
    public void setAllFields()
    {
        partNameField.setText("Part Name");
        partInvField.setText("Inventory");
        partPriceField.setText("Part Price");
        partMinField.setText("Minimum");
        partMaxField.setText("Maximum");
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
        alert.setHeaderText("Cancel Adding Part?");
        alert.setContentText("Are you sure you wish to cancel adding a part?");
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
     * Event handler method for when the user clicks the Save button.
     * @param actionEvent = Clicking on the Save part button.
     * @throws NumberFormatException = Thrown when the text field doesn't match the number format.
     * @throws NullPointerException = Thrown should any part text fields be left empty by the user.
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
            Part inhousePart = new InHouse(partID, partName, partCost, partInventory, partMin, partMax, machineID);
            if(partValidation(inhousePart))
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Save");
                alert.setHeaderText("Save Part?");
                alert.setContentText("Are you sure you wish to save this part?");
                alert.showAndWait().ifPresent(response ->
                {
                    if(response == ButtonType.OK)
                    {
                        Inventory.addPart(inhousePart);
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
                Part outsourcedPart = new OutSourced(partID, partName, partCost, partInventory, partMin, partMax, companyName);
                if(partValidation(outsourcedPart))
                {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Save");
                    alert.setHeaderText("Save Part?");
                    alert.setContentText("Are you sure you wish to save this part?");
                    alert.showAndWait().ifPresent(response ->
                    {
                        if(response == ButtonType.OK)
                        {
                            Inventory.addPart(outsourcedPart);
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
}
