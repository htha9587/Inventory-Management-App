# Inventory-Management-App
JavaFX desktop application that keeps track of inventory for both parts and products. Each of which can be added, edited, or removed altogether.  Products are made up of given parts.

### Purpose of Application
The Inventory Management System is a JavaFX desktop application designed to fulfill the requirements set forth by a small manufacturing company. They have been looking to upgrade from a spreadsheet program to a more robust application that keeps track of the inventory for their products, and the individual parts of those products.
The company's requirements include: Adding new products or parts, making edits to them, or deleting them altogether. 
The Iventory app makes use of JavaFX ObservableLists to manage the Part and Product TableViews. CSS Styling is used to provide a GUI reminiscient of a Windows 8/10 Metro appearance.


### Author Details
Author: Harrison Thacker  
Email: harrison.thacker69@gmail.com 

Version Of Application: 1.0  
Period Of Development: (May 2021 --- September 2021)


### IDE + Miscellaneous Dependencies
IntelliJ IDEA 2021.1.3 (Community Edition)  
Build #IC-211.7628.21, built on June 30, 2021.  
Runtime version: 11.0.11+9-b1341.60 amd64  
VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.  
JDK Version: Java SE 17.0.6  
JavaFX Version: JavaFX-SDK-17.0.6


### Directions on how to run Inventory Management App

1: Open up IntelliJ IDEA.  
2: Click on the File Tab and open up Settings. Look for the Path Variables Section.     
3: Under Path Variables, Create a new path called PATH_TO_FX.  
4: Have it set to the directory of where your JavaFX SDK library is located. (/javafx-sdk-17.0.6/lib.)  
5: Build the Project, and if successful, then click the green run button to start the application.


### Directions on how to operate Inventory Management App

Upon starting the application, you'll be greeted with the main page. 
From here, you can exit the program, view and search for Parts and Products in their existing TableViews, and add new ones. In addition, you can make edits to already existing ones, or delete them altogether. Sample data is already provided for you.


The Parts section has a TableView showing Parts stored using JavaFX ObservableLists. Here, the user can add new parts, make edits to existing ones, or remove parts from the tables. By typing in the Part ID or number in the search field, the TableView updates to view the user's input result. The same holds true for the Product search field. Clicking the add button in the part section redirects the user to the Add Part Page. Here the user can select if the part is In-house, or outsourced. If there are any input errors, popup dialogs will inform the user of the mistakes to be fixed. After correctly filling in all fields and creating a new part, it will show up in the respective TableView once the user is redirected to the main page.

Clicking the add button in the product section redirects the user to the Add Product Page. The user can make use of a TableView with a search field to find and choose parts they wish to add to their new product. Associated parts can also be removed as well. If there are any input errors, popup dialogs will inform the user of the mistakes to be fixed. After correctly filling in all fields and creating a new product, it will show up in the respective TableView once the user is redirected to the main page.


Both part and product Edit buttons redirect the user to respective pages, allowing the user to make any changes to a specific part or product. In addition, parts within a product can be added or deleted as well. If there are any input errors, popup dialogs will inform the user of the mistakes to be fixed. After correctly filling in all fields, changes to parts and products will be made visible within their respective TableViews.

To delete a part from the Inventory, select its row within the TableView and click the delete button. After saying yes to the confirmation popup dialog, The given part will be deleted from the inventory/TableView. To delete a product, select its row within the product TableView and click the delete button. However, keep in mind you cannot delete a product that currently has parts associated with it. If you try to click the delete button, the dialog will inform you that deletion is not possible. The user must first remove all associated parts from a product in order to delete it. After clicking the product delete button, a confirmation dialog will ask if the user is sure. Upon a confirmation from the user, the product will be removed from the inventory/TableView.
