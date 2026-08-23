/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

	public class NameSurfer extends Program implements NameSurferConstants {
	
		private JTextField txtFld;
		private JButton btn1;
		private JButton btn2;
		private JLabel lbl;
		private NameSurferGraph graph; 
		private NameSurferDataBase database = new NameSurferDataBase(NAMES_DATA_FILE);
		
	/**
	 * This method has the responsibility for reading in the data base
	 * and initializing the interactors at the bottom of the window.
	 */
		public void init() {
			graph = new NameSurferGraph(); 
			add(graph); 
			
		    lbl = new JLabel("Name");
		    add(lbl, SOUTH);
		    		
		    txtFld = new JTextField(20);
		    add(txtFld, SOUTH);
		    txtFld.addActionListener(this);
		    
		    btn1 = new JButton("Graph");
		    add(btn1, SOUTH);
		    
		    btn2 = new JButton("Clear");
		    add(btn2, SOUTH);
		    
		    addActionListeners();
		}
	
	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked, so you will have to define a method to respond to
	 * button actions.
	 */
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btn1 || e.getSource() == txtFld) {
				String s = txtFld.getText();
				s = (Character.toUpperCase(s.charAt(0)) + "").concat(s.substring(1).toLowerCase());
				graph.addEntry(database.findEntry(s)); 
				graph.update();
				txtFld.setText("");
			} else if (e.getSource() == btn2) {
				graph.clear();
				graph.update();
				txtFld.setText("");
			}
		}
				
	}