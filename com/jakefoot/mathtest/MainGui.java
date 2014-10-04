package com.jakefoot.mathtest;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class MainGui extends JFrame
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel optionspanel;
	private static JRadioButton addradio;
	private static JRadioButton subradio;
	private static JRadioButton multradio;
	private static JRadioButton divradio;
	private ButtonGroup group;
	static Font radiofont = new Font (Font.SANS_SERIF, Font.PLAIN, 20);
	
	private JButton startbutton;
	
	private JTextField qtyfield;
	private static JTextField minfield;
	private static JTextField maxfield;
	private static Container test;
	private static Container contpanel;
	
	private JLabel qtylabel;
	private JLabel minlabel;
	private JLabel maxlabel;
	
	private boolean error = false;
	private String qtyerror = "";
	private String minerror = "";
	private String maxerror = "";
	
	private int qty;
	private int min;
	private int max;	
	
	public MainGui()
	{
		super ("Math Practice Options");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contpanel = getContentPane();
		
		optionspanel = new JPanel(new GridBagLayout());
		GridBagConstraints GBCar = new GridBagConstraints();
		addradio = new JRadioButton("Addition", true);
		addradio.setFont(radiofont);
		GBCar.fill = GridBagConstraints.HORIZONTAL;
		GBCar.gridx = 0;
		GBCar.gridy = 0;
		optionspanel.add(addradio, GBCar);
		
		GridBagConstraints GBCsr = new GridBagConstraints();
		subradio = new JRadioButton("Subtraction", false);
		subradio.setFont(radiofont);
		GBCsr.fill = GridBagConstraints.HORIZONTAL;
		GBCsr.gridx = 1;
		GBCsr.gridy = 0;
		optionspanel.add(subradio, GBCsr);
		
		GridBagConstraints GBCmr = new GridBagConstraints();
		multradio = new JRadioButton("Multiplication", false);
		multradio.setFont(radiofont);
		GBCmr.fill = GridBagConstraints.HORIZONTAL;
		GBCmr.gridx = 2;
		GBCmr.gridy = 0;
		optionspanel.add(multradio, GBCmr);
		
		GridBagConstraints GBCdr = new GridBagConstraints();
		divradio = new JRadioButton("Division", false);
		divradio.setFont(radiofont);
		GBCdr.fill = GridBagConstraints.HORIZONTAL;
		GBCdr.gridx = 3;
		GBCdr.gridy = 0;
		optionspanel.add(divradio, GBCdr);		
		
		group = new ButtonGroup();
		group.add(addradio);
		group.add(subradio);
		group.add(multradio);
		group.add(divradio);
		
		GridBagConstraints GBCql = new GridBagConstraints();
		qtylabel = new JLabel();
		qtylabel.setText("Number of problems");
		qtylabel.setFont(radiofont);
		GBCql.anchor = GridBagConstraints.WEST;
		GBCql.gridwidth = 2;
		GBCql.gridx = 0;
		GBCql.gridy = 1;
		optionspanel.add(qtylabel, GBCql);
		
		GridBagConstraints GBCqf = new GridBagConstraints();
		qtyfield = new JTextField("10");
		qtyfield.setColumns(3);
		qtyfield.setFont(radiofont);
		qtyfield.addFocusListener(new FieldHandlerClass());
		GBCqf.anchor = GridBagConstraints.WEST;
		GBCqf.gridx = 2;
		GBCqf.gridy = 1;
		optionspanel.add(qtyfield, GBCqf);
		
		GridBagConstraints GBCmnl = new GridBagConstraints();
		minlabel = new JLabel();
		minlabel.setText("Minimum");
		minlabel.setFont(radiofont);
		GBCmnl.anchor = GridBagConstraints.WEST;
		GBCmnl.gridwidth = 2;
		GBCmnl.gridx = 0;
		GBCmnl.gridy = 2;
		optionspanel.add(minlabel, GBCmnl);
		
		GridBagConstraints GBCmnf = new GridBagConstraints();
		minfield = new JTextField("10");
		minfield.setColumns(3);
		minfield.setFont(radiofont);
		minfield.addFocusListener(new FieldHandlerClass());
		GBCmnf.anchor = GridBagConstraints.WEST;
		GBCmnf.gridx = 2;
		GBCmnf.gridy = 2;
		optionspanel.add(minfield, GBCmnf);
		
		GridBagConstraints GBCmxl = new GridBagConstraints();
		maxlabel = new JLabel();
		maxlabel.setText("Maximum");
		maxlabel.setFont(radiofont);
		GBCmxl.anchor = GridBagConstraints.WEST;
		GBCmxl.gridwidth = 2;
		GBCmxl.gridx = 0;
		GBCmxl.gridy = 3;
		optionspanel.add(maxlabel, GBCmxl);
		
		GridBagConstraints GBCmxf = new GridBagConstraints();
		maxfield = new JTextField("20");
		maxfield.setColumns(3);
		maxfield.setFont(radiofont);
		maxfield.addFocusListener(new FieldHandlerClass());
		GBCmxf.anchor = GridBagConstraints.WEST;
		GBCmxf.gridx = 2;
		GBCmxf.gridy = 3;
		optionspanel.add(maxfield, GBCmxf);
		
		GridBagConstraints GBCsb = new GridBagConstraints();
		startbutton = new JButton("Start");
		startbutton.setFont(radiofont);
		startbutton.addActionListener(new ButtonHandlerClass());
		GBCsb.anchor = GridBagConstraints.PAGE_END;
		GBCsb.gridx = 3;
		GBCsb.gridy = 4;
		optionspanel.add(startbutton, GBCsb);
		
		
		contpanel.add(optionspanel);
		pack();
		setMinimumSize(this.getSize());
		
		//adjust max and min to fit radio selection
		addradio.addItemListener(new RadioHandlerClass ());
		subradio.addItemListener(new RadioHandlerClass ());
		multradio.addItemListener(new RadioHandlerClass ());
		divradio.addItemListener(new RadioHandlerClass ());
		
		
	}
	
	public static void showOptions()
	{
	    test.setVisible(false);
	    contpanel.remove(test);
	    test = null;
	    optionspanel.setVisible(true);
	    MathTest.setHeader("Math Practice Options");
	}	
	
	public static class RadioHandlerClass implements ItemListener
	{			
		@Override
		public void itemStateChanged(ItemEvent event)
		{		
			if (addradio.isSelected() || subradio.isSelected())
			{
				minfield.setText("10");
				maxfield.setText("20");
			}
			else
			{
				minfield.setText("1");
				maxfield.setText("12");
			}			
		}
	}
	
	private class ButtonHandlerClass implements ActionListener
	{			
		@Override
		public void actionPerformed(ActionEvent event)
		{
			try
			{
				qty = Integer.valueOf(qtyfield.getText());
			}
			catch (Exception e)
			{
				qtyerror = String.format("Invalid Number of Problems %n");
				qtyfield.setForeground(Color.RED);
				error = true;
			}
			
			
			try
			{
				min = Integer.valueOf(minfield.getText());
			}
			catch (Exception e)
			{
				minerror = String.format("Invalid Minimum %n");
				minfield.setForeground(Color.RED);
				error = true;
			}
			
			try
			{
				max = Integer.valueOf(maxfield.getText());
			}
			catch (Exception e)
			{
				maxerror = String.format("Invalid Maximum %n");
				maxfield.setForeground(Color.RED);
				error = true;
			}
			
			if (error == true)
			{
				JOptionPane.showMessageDialog
				(null, String.format("%s%s%sPlease enter an integer value.", qtyerror, minerror, maxerror), "Error", JOptionPane.ERROR_MESSAGE);
				qtyerror = "";
				minerror = "";
				maxerror = "";
				error = false;
			}
			else if	(min > max)		
			{
				JOptionPane.showMessageDialog
				(null, "Maximum must be greater than or equal to minimum", "Error", JOptionPane.ERROR_MESSAGE);
				maxfield.setForeground(Color.RED);
			}
			else if (qty < 1)
			{
				JOptionPane.showMessageDialog
				(null, "Number of Problems must be greater than zero.", "Error", JOptionPane.ERROR_MESSAGE);
				qtyfield.setForeground(Color.RED);
			}			
			else
			{	
				if (addradio.isSelected())
				{
					test = new AddTest(qty, min, max);
					test.setVisible(true);
					add(test);
					optionspanel.setVisible(false);					
					pack();
					MathTest.setHeader("Addition Practice");
				}
				else if (subradio.isSelected())
				{
					test = new SubTest(qty, min, max);				
					test.setVisible(true);
					add(test);
					optionspanel.setVisible(false);					
					pack();
					MathTest.setHeader("Subtraction Practice");
				}
				else if (multradio.isSelected())
				{
					test = new MultTest(qty, min, max);				
					test.setVisible(true);
					add(test);
					optionspanel.setVisible(false);
					pack();
					MathTest.setHeader("Multiplication Practice");
				}
				else if (divradio.isSelected())
				{
					test = new DivTest(qty, min, max);				
					test.setVisible(true);
					add(test);
					optionspanel.setVisible(false);
					pack();
					MathTest.setHeader("Division Practice");
				}
			}
		}
	}
	
	private class FieldHandlerClass implements FocusListener
	{

		@Override
		public void focusGained(FocusEvent e)
		{
			((JTextComponent) e.getComponent()).selectAll();
			((JTextComponent) e.getComponent()).setForeground(Color.BLACK);			
		}

		@Override
		public void focusLost(FocusEvent e){}		
	}	
}
