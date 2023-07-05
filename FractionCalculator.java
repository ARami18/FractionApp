/**
 * Program Name:  FractionCalculator.java
 * Purpose:		  A GUI class that displays the fraction calculator
 * @author        Saurabh Ashishkumar Darji, Alejandro Ramirez, CPA3
 * @version       1.0.0
 * @since         Jul. 26, 2021      
 */
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class FractionCalculator extends JFrame {
	
	//declare instance fields
	private JTextField numTxtField;
	private JTextField denTxtField;
	
	private JTextArea txtFraction;
	private JTextArea txtOperation;
	
	private JButton btnBuildFraction;
	private JButton btnStartOver;
	
	private JPanel enterFractPanel; 
	private JPanel displayFractPanel; 
	private JPanel opsSelectPanel; 
	private JPanel opsResultPanel;
	
	private JLabel numFieldLabel;
	private JLabel denFieldLabel;
	
	private ArrayList<Fraction> fractionArrayList;
	
	private JComboBox<String> cboOpsSelect;
	
	private int numerator = 0;
	private int denominator = 0;
	
	
	//Constructor for the frame Fraction Calculator
	public FractionCalculator()  throws EmptyOperandException {
		
		super("Fraction Calculator");
		
		fractionArrayList = new ArrayList<Fraction>();
		
		//Set the layout manager
		this.setLayout(new GridLayout(1, 4));
		
		//Build sub-panels
		buildEnterFractPanel();
		buildDisplayFractPanel();
		buildOpsSelectPanel();
		buildOpsDisplayPanel();
		
		//Add all panels
		this.add(enterFractPanel);
		this.add(displayFractPanel);
		this.add(opsSelectPanel);
		this.add(opsResultPanel);
		
		
		//Set up methods for the frame
		this.setSize(800, 500);							
		this.setLocationRelativeTo(null);										
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);				
		this.setVisible(true);
	}//end FractionCalculator()
	
	private void buildEnterFractPanel() throws EmptyOperandException {
		
		enterFractPanel = new JPanel();
		enterFractPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1), "Enter a fraction:"));
		
		//Create GUI components
		numFieldLabel = new JLabel("Numerator:");
		denFieldLabel = new JLabel("Denominator:");
		numTxtField = new JTextField(15);
		denTxtField = new JTextField(15);
		
		btnBuildFraction = new JButton("Build Fraction");
		btnStartOver = new JButton("Start Over!");
		
		//Build Fraction button functionality
		btnBuildFraction.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//include input validation, only integer values allowed
				try {
						if (numTxtField.getText().equals("")) {
							throw new EmptyOperandException(1);
	                    }
	                    if (denTxtField.getText().equals("")) {
							throw new EmptyOperandException(0);
	                    }
	                    if (numTxtField.getText().length() > 10) {
							throw new LongOperandException(1);
	                    }
	                    if (denTxtField.getText().length() > 10) {
							throw new LongOperandException(0);
	                    }
						
						//Get numerator and denominator
						numerator = Integer.parseInt(numTxtField.getText());
		                denominator = Integer.parseInt(denTxtField.getText());
		                
		                //Create a new fraction object, add to the arrayList
		                Fraction newFrac = new Fraction(numerator, denominator);
		                
						numTxtField.setText("");
						denTxtField.setText("");
	
						//add the Fraction object to the arrayList
						fractionArrayList.add(newFrac);
						
						//adding Fraction object to array list in string form
		                txtFraction.append(newFrac.toString().substring(newFrac.toString().lastIndexOf(" ") + 1, newFrac.toString().length()) + "\n");
				}
				catch (EmptyOperandException exObj1) {
						JOptionPane.showMessageDialog(null, exObj1.getMessage(), "EmptyOperandException", 0);
	                    if (exObj1.getValue() == 1) {
							numTxtField.setText("");
	                        numTxtField.requestFocusInWindow();
	                    }
	                    else {
							denTxtField.setText("");
	                        denTxtField.requestFocusInWindow();
	                    }
                }
                catch (LongOperandException exObj2) {
						JOptionPane.showMessageDialog(null, exObj2.getMessage(), "LongOperandException", 0);
	                    if (exObj2.getValue() == 1) {
							numTxtField.setText("");
	                        numTxtField.requestFocusInWindow();
	                    }
	                    else {
							denTxtField.setText("");
	                        denTxtField.requestFocusInWindow();
	                    }
                }
                catch (DivisionByZeroException exObj3) {
						JOptionPane.showMessageDialog(null, exObj3.getMessage(), "DenominatorOfZeroException", 0);
	                    denTxtField.setText("");
	                    denTxtField.requestFocusInWindow();
                }
                catch (NumberFormatException exObj4) {
	                    JOptionPane.showMessageDialog(null, "Invalid data type! Enter an integer", "NumberFormatException", 0);
	                    numTxtField.setText("");
	                    denTxtField.setText("");
	                    numTxtField.requestFocusInWindow();
                }
                
			}
		});
		
		//Start Over button functionality
		btnStartOver.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//Restore the decimal operation to display at the top of the combo box
				cboOpsSelect.setSelectedIndex(0);	
				
				//Clear all GUI components
				numTxtField.setText("");
                denTxtField.setText("");
                txtFraction.setText("");
                txtOperation.setText("");
				//Clear all class variables
                numerator = 0;
                denominator = 0;
                fractionArrayList.clear();
			} 
		});
		
		//Add components
		enterFractPanel.add(numFieldLabel);
		enterFractPanel.add(numTxtField);
		enterFractPanel.add(denFieldLabel);
		enterFractPanel.add(denTxtField);
		enterFractPanel.add(btnBuildFraction);
		enterFractPanel.add(btnStartOver);
	}//end buildEnterFractPanel()
	
	private void buildDisplayFractPanel() {
		
		displayFractPanel = new JPanel();
		displayFractPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1), "Here is your fraction:"));
		displayFractPanel.setLayout(new GridLayout(1,1));
		
		//Create JTextArea
		txtFraction = new JTextArea();    
		txtFraction.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		txtFraction.setLineWrap(true);
		txtFraction.setWrapStyleWord(true);
		txtFraction.setEditable(false);
		
		displayFractPanel.add(txtFraction);
		
	}//end buildDisplayFractPanel()
	
	private void buildOpsSelectPanel() {
		
		opsSelectPanel = new JPanel();
		opsSelectPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1), "Select an operation:"));
	  
		String[] operations = {"Select an Operation","Decimal", "Reciprocal", "Fraction1 + Fraction2", "Fraction1 x Fraction2", 
								"Is Fraction1 = Fraction2", "Is Fraction1 > Fraction2", "Lowest Terms", "SortList"};

	  	cboOpsSelect = new JComboBox<String>(operations);
	  	
	  	//Add listener event
		cboOpsSelect.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				try 
				{
					String caseChoice = (String)cboOpsSelect.getSelectedItem();
					
					//if a Fraction object has not been created and added to the list, warn and return focus to numerator text field
					if (fractionArrayList.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please enter a fraction.", "Input Error", 0);
						numTxtField.grabFocus();
					}
					else {
						if(caseChoice.equals("Decimal")){
							Fraction tempDecimalFraction = new Fraction(fractionArrayList.get(fractionArrayList.size()-1).getNum(), fractionArrayList.get(fractionArrayList.size()-1).getDen());
							
							txtOperation.setText(String.format("%.2f", tempDecimalFraction.toDecimal()));
						}
						else if(caseChoice.equals("Reciprocal")){
							Fraction tempReciprocalFraction = new Fraction(fractionArrayList.get(fractionArrayList.size()-1).getNum(), fractionArrayList.get(fractionArrayList.size()-1).getDen());
							
							tempReciprocalFraction.toReciprocal();
							txtOperation.setText(tempReciprocalFraction.toString());	
						}
						else if(caseChoice.equals("Fraction1 + Fraction2")){
							
							if (fractionArrayList.size() < 2) {
								JOptionPane.showMessageDialog(null, "You need at least two fractions to perform this operation.\nPlease build another.", "Input Error", 0);
								numTxtField.grabFocus();
							}
							else {
								Fraction tempAddFraction1 = new Fraction(fractionArrayList.get(fractionArrayList.size()-1).getNum(), fractionArrayList.get(fractionArrayList.size()-1).getDen());
								Fraction tempAddFraction2 = new Fraction(fractionArrayList.get(fractionArrayList.size()-2).getNum(), fractionArrayList.get(fractionArrayList.size()-2).getDen());
							
								txtOperation.setText(tempAddFraction1.add(tempAddFraction2).toString());
							}		
						}
						else if(caseChoice.equals("Fraction1 x Fraction2")){	
							if (fractionArrayList.size() < 2) {
								JOptionPane.showMessageDialog(null, "You need at least two fractions to perform this operation.\nPlease build another.", "Input Error", 0);
								numTxtField.grabFocus();
							}
							else {
								Fraction tempMultiFraction1 = new Fraction(fractionArrayList.get(fractionArrayList.size()-1).getNum(), fractionArrayList.get(fractionArrayList.size()-1).getDen());
								Fraction tempMultiFraction2 = new Fraction(fractionArrayList.get(fractionArrayList.size()-2).getNum(), fractionArrayList.get(fractionArrayList.size()-2).getDen());
							
								txtOperation.setText(tempMultiFraction1.multiply(tempMultiFraction2).toString());	
							}
						}
						else if(caseChoice.equals("Is Fraction1 = Fraction2")){
							if (fractionArrayList.size() < 2) {
								JOptionPane.showMessageDialog(null, "You need at least two fractions to perform this operation.\nPlease build another.", "Input Error", 0);
								numTxtField.grabFocus();
							}
							else {
								Fraction tempEqualFraction1 = new Fraction(fractionArrayList.get(fractionArrayList.size()-1).getNum(), fractionArrayList.get(fractionArrayList.size()-1).getDen());
								Fraction tempEqualFraction2 = new Fraction(fractionArrayList.get(fractionArrayList.size()-2).getNum(), fractionArrayList.get(fractionArrayList.size()-2).getDen());
								
								txtOperation.setText(tempEqualFraction1.equals(tempEqualFraction2)+"");
							}
						}
						else if(caseChoice.equals("Is Fraction1 > Fraction2")){
							
							if (fractionArrayList.size() < 2) {
								JOptionPane.showMessageDialog(null, "You need two fractions to perform this operation.\nPlease build another, "
															  + "then select your desired operation from the list.", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
								numTxtField.grabFocus();
							}
							else {
								Fraction tempGreaterFraction1 = new Fraction(fractionArrayList.get(fractionArrayList.size()-2).getNum(), fractionArrayList.get(fractionArrayList.size()-2).getDen());
								Fraction tempGreaterFraction2 = new Fraction(fractionArrayList.get(fractionArrayList.size()-1).getNum(), fractionArrayList.get(fractionArrayList.size()-1).getDen());
								
								txtOperation.setText(tempGreaterFraction1.greaterThan(tempGreaterFraction2)+"");	
							}
						}
						else if(caseChoice.equals("Lowest Terms")){
							txtOperation.setText("");
							txtFraction.setText("");
		
							for (Fraction value : fractionArrayList) {
								value.lowestTerms();
								txtOperation.append(value.toString() + "\n");
								txtFraction.append(value.toString() + "\n");
							}
							
						}
						else if(caseChoice.equals("SortList")){
							// clear the textArea 
							txtOperation.setText("");
							txtFraction.setText("");
							Collections.sort(fractionArrayList);

							// Output the sorted fraction List to the txtArea
							for (int i=0; i < fractionArrayList.size(); i++) {
								txtOperation.append(fractionArrayList.get(i).toString()+"\n");							
								txtFraction.append(fractionArrayList.get(i).toString()+"\n");							
							}
						}
					}
				}
				catch (LongOperandException exObj1){
					JOptionPane.showMessageDialog(null, exObj1.getMessage(), "LongOperandException", 0);
					if (exObj1.getValue() == 1) {
						numTxtField.setText("");
						numTxtField.requestFocusInWindow();
					}
					else {
						denTxtField.setText("");
						denTxtField.requestFocusInWindow();
					}
				}
				catch (DivisionByZeroException exObj2) {
					JOptionPane.showMessageDialog(null, exObj2.getMessage(), "DenominatorOfZeroException", 0);
				
				}
			}		
		});
		//add cboBox
	  	opsSelectPanel.add(cboOpsSelect);	 
	}//end buildOpsSelectPanel()
	
	private void buildOpsDisplayPanel() {
	
	  opsResultPanel = new JPanel();
	  opsResultPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1), "Here is your operation:"));
	
	  opsResultPanel.setLayout(new GridLayout(1,1));
	  
	  //Create JTextArea
	  txtOperation = new JTextArea();  
	  txtOperation.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
	  txtOperation.setEditable(false);

	  //Add JTextArea to JPanel
	  opsResultPanel.add(txtOperation);  
	}//end buildOpsDisplayPanel()
	
	
	public static void main(String[] args) throws EmptyOperandException{
		FractionCalculator frame1 = new FractionCalculator();
	}
	
}