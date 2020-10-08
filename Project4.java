import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.TreeMap;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


/*File Name: Project 4
 * @author Kwame Duodu
 * Date: July 10, 2020
 * Purpose:  a program to manage a real estate database.
 */

public class Project4 {   
	

    public static class realEstateDatabase extends JPanel {
    	
        //various components.
        //Create Labels
        private JLabel transactionlabel = new JLabel("Transaction No:");
        private JLabel addresslabel = new JLabel("Address:");
        private JLabel bedroomslabel = new JLabel("Bedrooms:");
        private JLabel squareftlabel = new JLabel("Square Footage:");
        private JLabel priceLabel = new JLabel("Price:");
        
        private String[] databaseOperations = {"Insert", "Delete", "Find"};
        private JComboBox databaseList = new JComboBox(databaseOperations);
        private Status[] statusAvailable = {Status.FOR_SALE, Status.UNDER_CONTRACT, Status.SOLD};
        private JComboBox statusList = new JComboBox(statusAvailable);
        
        // Create TextFields
        private JTextField transactionfield = new JTextField("");
        private JTextField addressfield = new JTextField("");
        private JTextField bedroomsfield = new JTextField("");
        private JTextField squareftfield = new JTextField("");
        private JTextField pricefield = new JTextField("");
        //transaction number field as the key and a Property object as the value.
        TreeMap<Integer, Property> propertyDatabase = new TreeMap<>();

      
        //constructor
        public realEstateDatabase() {
        //Set the layout for entire panel
        setLayout(new GridLayout(7,2, 5,6));
        
        //Populates entire panel
        this.add(transactionlabel); 
        this.add(transactionfield);
        this.add(addresslabel); 
        this.add(addressfield);
        this.add(bedroomslabel); 
        this.add(bedroomsfield );
        this.add(squareftlabel); 
        this.add(squareftfield );
        this.add(priceLabel);
        this.add(pricefield);
        
        //action listener to do the various tasks
    JButton processbutton = new JButton(new AbstractAction("Process") {
    
    	 @Override
        public void actionPerformed(ActionEvent e) {
    		 String processOption = String.valueOf(databaseList.getSelectedItem());
            try {
            	
                if(processOption.equalsIgnoreCase("Insert")){
                    checkForDuplicates(getTransactionId());
                    propertyDatabase.put(getTransactionId(), getPropertyInfo());
                    JOptionPane.showMessageDialog(null, "Property is successfully saved in database.");
                    
                }else if(processOption.equalsIgnoreCase("Delete")){
                    checkforExisting(getTransactionId());
                    propertyDatabase.remove(getTransactionId());
                    JOptionPane.showMessageDialog(null, "Property is successfully removed from database.");
                    
                }else if(processOption.equalsIgnoreCase("Find")){
                    checkforExisting(getTransactionId());
                    Property getProperty = propertyDatabase.get(getTransactionId());
                    JOptionPane.showMessageDialog(null, getProperty.toString());
                }
                
            }catch(NumberFormatException NumberformatExcepition) {
                JOptionPane.showMessageDialog(null, "Incorrect format for values entered.");
                
            }  catch(DuplicateProperty duplicatePropertyException) {
                JOptionPane.showMessageDialog(null, "Transaction id already exists in database.");
                
            } catch(PropertyNotFound propertyNotFoundException) {
                JOptionPane.showMessageDialog(null, "Transaction id is not found in database.");
            }
    	 }
    });
                   
        
    //action Listener to change the status 
    JButton changebutton = new JButton(new AbstractAction("Change Status") {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
            Status statusOption = (Status) statusList.getSelectedItem();
            checkforExisting(getTransactionId());
            Property changeProperty = propertyDatabase.get(getTransactionId());
            changeProperty.changeState(statusOption);
            propertyDatabase.put(getTransactionId(), changeProperty);
            JOptionPane.showMessageDialog(null, "Property status is successfully changed in database");
            
            } catch(PropertyNotFound propertyNotFoundException)  {
                JOptionPane.showMessageDialog(null, "Transaction id is not found in database.");
                
            } catch(NumberFormatException NumberFormatException) {
                JOptionPane.showMessageDialog(null, "Incorrect format for values entered.");
            }
        }
    });
    
    this.add(processbutton);
    this.add(databaseList);
    this.add(changebutton);
    this.add(statusList);
    }
    
        //get property information
    private Property getPropertyInfo() throws NumberFormatException {
        String address = addressfield.getText();
        int bedrooms = getInput(bedroomsfield);
        int squareFt = getInput(squareftfield);
        int price = getInput(pricefield);
        return new Property(address, bedrooms, squareFt, price);
    }
    
    //Check for wrong Format
    private int getTransactionId() throws NumberFormatException {
        return getInput(transactionfield);
    }
    
    //Duplicate entry check
    private void checkForDuplicates(int transactionId) throws DuplicateProperty{
        if(propertyDatabase.containsKey(transactionId)) {
        throw new DuplicateProperty();
        }
    }
    
    //Check before inserting and deleting
    private void checkforExisting(int transactionId) throws PropertyNotFound {
        if(!propertyDatabase.containsKey(transactionId)) {
        throw new PropertyNotFound();
        }
    }
    
    //Convert string to integer
    private int getInput(JTextField inputTextField) throws NumberFormatException {
        String inputString = inputTextField.getText();
        return Integer.parseInt(inputString);
    }
    
    
    private class DuplicateProperty extends Exception {
        public DuplicateProperty() {
        super();
        }
    }
    
    
    private class PropertyNotFound extends Exception {
        public PropertyNotFound() {
        super();
        }
    }
  }  
     //create frame
    public static void main(String[] args) {
        JFrame project4 = new JFrame("Real Estate Database");
        project4.setSize(325,250);
        project4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        project4.add(new realEstateDatabase());
        project4.setVisible(true);
    }
}