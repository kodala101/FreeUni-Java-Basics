/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends Program
					implements FacePamphletConstants {
	
	private JTextField txtFld;
	private JTextField statusFld;
	private JTextField pictureFld;
	private JTextField friendFld;
	
	private FacePamphletDatabase database;
	private FacePamphletProfile currentProfile;
	private FacePamphletCanvas canvas;
	
	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		JLabel name = new JLabel("Name");
		add(name, NORTH);
		
		txtFld = new JTextField(TEXT_FIELD_SIZE);
		add(txtFld, NORTH);
		
		JButton add = new JButton("Add");
		add(add, NORTH);
		
		JButton delete = new JButton("Delet");
		add(delete, NORTH);
		
		JButton lookup = new JButton("Lookup");
		add(lookup, NORTH);
		
		statusFld = new JTextField(TEXT_FIELD_SIZE);
		add(statusFld, WEST);
		statusFld.addActionListener(this);
		
		JButton changeStatus = new JButton("Change Status");
		add(changeStatus, WEST);
		
		JLabel void1 = new JLabel(EMPTY_LABEL_TEXT);
		add(void1, WEST);
		
		pictureFld = new JTextField(TEXT_FIELD_SIZE);
		add(pictureFld, WEST);
		pictureFld.addActionListener(this);
		
		JButton changePicture = new JButton("Change Picture");
		add(changePicture, WEST);
		
		JLabel void2 = new JLabel(EMPTY_LABEL_TEXT);
		add(void2, WEST);
		
		friendFld = new JTextField(TEXT_FIELD_SIZE);
		add(friendFld, WEST);
		friendFld.addActionListener(this);
		
		JButton addFriend = new JButton("Add Friend");
		add(addFriend, WEST);
		
		addActionListeners();
		
		database = new FacePamphletDatabase();
		
		canvas = new FacePamphletCanvas(); 
		add(canvas);
    }
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
    	if (e.getActionCommand().equals("Add") && !txtFld.getText().equals("")) {
    		add();
    	} else if (e.getActionCommand().equals("Delet") && !txtFld.getText().equals("")) {
    		delet();
    	} else if (e.getActionCommand().equals("Lookup") && !txtFld.getText().equals("")) {
    		lookup();
    	} else if ((e.getActionCommand().equals("Change Status") || e.getSource() == statusFld) && !statusFld.getText().equals("")) {
    		changeStatus();
    	} else if ((e.getActionCommand().equals("Change Picture") || e.getSource() == pictureFld) && !pictureFld.getText().equals("")) {
    		changePicture();
    	} else if ((e.getActionCommand().equals("Add Friend") || e.getSource() == friendFld) && !friendFld.getText().equals("")) {
    		addFriend();
    	}
	}
    
    private void add() {
    	String name = txtFld.getText();
		if (database.containsProfile(name)) {
			currentProfile = database.getProfile(name);
			canvas.displayProfile(currentProfile);
			canvas.showMessage("A profile with the name " + name + " already exists");
		} else {
			FacePamphletProfile prof = new FacePamphletProfile(name);
			currentProfile = prof;
			database.addProfile(prof);
			canvas.displayProfile(currentProfile);
			canvas.showMessage("New profile created");
		}
    }
    
    private void delet() {
    	String name = txtFld.getText();
		if (database.containsProfile(name)) {
			database.deleteProfile(name);
			currentProfile = null;
			canvas.removeAll();
			canvas.showMessage("Profile of " + name + " deleted");
		} else {
			if (currentProfile != null) {
				canvas.displayProfile(currentProfile);
			} else canvas.removeAll();
			canvas.showMessage("A profile with the name " + name + " does not exist");
		}
    }
    
    private void lookup() {
    	String name = txtFld.getText();
		if (database.containsProfile(name)) {
			currentProfile = database.getProfile(name);
			canvas.displayProfile(currentProfile);
			canvas.showMessage("Displaying " + name);
		} else {
			canvas.removeAll();
			canvas.showMessage("A profile with the name " + name + " does not exist");
		}
    }
    
    private void changeStatus() {
    	if (currentProfile != null) {
			currentProfile.setStatus(statusFld.getText());
			canvas.displayProfile(currentProfile);
			canvas.showMessage("Status updated to " + currentProfile.getStatus());
		} else {
			canvas.removeAll();
			canvas.showMessage("Please select a profil to change status");
		}
    }
    
    private void changePicture() {
    	GImage image = null; 
		try { 
			image = new GImage(pictureFld.getText()); 
		} catch (ErrorException ex) { 
			if (currentProfile != null) {
				canvas.displayProfile(currentProfile);
			} else canvas.removeAll();
			canvas.showMessage("Unable to open image file: " + pictureFld.getText());
		}
		if (image != null && currentProfile != null) {
			currentProfile.setImage(image);
			canvas.displayProfile(currentProfile);
			canvas.showMessage("Picture updated");
		} else if (image != null && currentProfile == null) {
			canvas.removeAll();
			canvas.showMessage("Please select a profile to change picture");
		}
    }
    
    private void addFriend() {
    	String adding = friendFld.getText();
		if (currentProfile != null) {
			if (database.containsProfile(adding)) {
				if (currentProfile.addFriend(adding)) {
					database.getProfile(adding).addFriend(currentProfile.getName());
					canvas.displayProfile(currentProfile);
					canvas.showMessage(adding + " added as a friend");
				} else {
					canvas.displayProfile(currentProfile);
					canvas.showMessage(currentProfile.getName() + " already has " + adding + " as a friend");
				}
			} else{
				canvas.displayProfile(currentProfile);
				canvas.showMessage(adding + " does not exist");
			}
		} else {
			canvas.removeAll();
			canvas.showMessage("Please select a profile to add friend");
		}
    }
    
}
