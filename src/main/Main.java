
package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;
import javafx.scene.image.Image;


/**
 * The Inventory Management System tracks inventory of parts, and products that
 * make use of said parts.
 *
 * The Javadoc folder is located in the src folder marked: javadoc.
 *
 * FUTURE_ENHANCEMENT: A glaring aspect or feature of this application that hasn't yet been implemented,
 * is saving data with it still being intact after the program is closed. At the moment, the application data
 * only resides in memory. Whenever the program is closed, all the test/created data is lost. A new functionality
 * I would implement would be to store the application data and state using XML. With the Java Preferences
 * class, you can save an application state. However, this won't be enough to store the entirety of the Inventory
 * system. The Java JAXB library for XML will allow us to save and persist the program data. This could be done by
 * having JAXB read and write the data for both parts and products. Without an implementation like this,
 * the program will be left unusable because the data only resides in memory.
 *
 *@author Harrison Thacker
 */
public class Main extends Application{

    /**
     * @param args 
     * Launches the JavaFX application.
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Initializes and loads up the scene for the main page.
     * 
     * @param mainStage
     * @throws Exception 
     */
    @Override
    public void start(Stage mainStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        mainStage.getIcons().add(new Image("file:src/images/App_Icon.png"));
        mainStage.setTitle("Inventory Management System");
        mainStage.setScene(new Scene(root));
        mainStage.show();
    }
}
