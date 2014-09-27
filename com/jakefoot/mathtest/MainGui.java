package com.jakefoot.mathtest;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class MainGui extends JFrame
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JRadioButton addradio;
	private static JRadioButton subradio;
	private static JRadioButton multradio;
	private static JRadioButton divradio;
	private ButtonGroup group;
	static Font radiofont = new Font (Font.SANS_SERIF, Font.PLAIN, 20);
	
	private JButton start;
	
	private JTextField qtyfield;
	private static JTextField minfield;
	private static JTextField maxfield;
	
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
		super ("Math Test");
		setSize(475, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setResizable(false);
		
		//create and add radio buttons to frame
		addradio = new JRadioButton("Addition", true);
		addradio.setFont(radiofont);		
		subradio = new JRadioButton("Subtraction", false);
		subradio.setFont(radiofont);
		multradio = new JRadioButton("Multiplication", false);
		multradio.setFont(radiofont);
		divradio = new JRadioButton("Division", false);
		divradio.setFont(radiofont);
		
		add(addradio);
		add(subradio);
		add(multradio);
		add(divradio);
		
		group = new ButtonGroup();
		group.add(addradio);
		group.add(subradio);
		group.add(multradio);
		group.add(divradio);
		
		
		//create and add input fields to frame
		qtylabel = new JLabel();
		qtylabel.setText("Number of problems            ");
		qtylabel.setFont(radiofont);
		add(qtylabel);
		qtyfield = new JTextField("10");
		qtyfield.setColumns(5);
		qtyfield.setFont(radiofont);
		add(qtyfield);
		qtyfield.addFocusListener(new FieldHandlerClass());
		
		minlabel = new JLabel();
		minlabel.setText("Minimum                             ");
		minlabel.setFont(radiofont);
		add(minlabel);		
		minfield = new JTextField("1");
		minfield.setColumns(5);
		minfield.setFont(radiofont);
		add(minfield);
		minfield.addFocusListener(new FieldHandlerClass());
		
		maxlabel = new JLabel();
		maxlabel.setText("Maximum                            ");
		maxlabel.setFont(radiofont);
		add(maxlabel);		
		maxfield = new JTextField("12");
		maxfield.setColumns(5);
		maxfield.setFont(radiofont);
		add(maxfield);
		maxfield.addFocusListener(new FieldHandlerClass());
		
		//create and add start button to frame
		start = new JButton("Start Test");
		start.setFont(radiofont);
		start.setSize(150, 20);
		add(start);
		
		//adjust max and min to fit radio selection
		//addradio.addItemListener(new RadioHandlerClass ());
		//subradio.addItemListener(new RadioHandlerClass ());
		//multradio.addItemListener(new RadioHandlerClass ());
		//divradio.addItemListener(new RadioHandlerClass ());
		
		start.addActionListener(new ButtonHandlerClass());
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
					AddTest addtest = new AddTest(qty, min, max);
					addtest.setVisible(true);
				}
				else if (subradio.isSelected())
				{
					SubTest subtest = new SubTest(qty, min, max);				
					subtest.setVisible(true);
				}
				else if (multradio.isSelected())
				{
					MultTest multtest = new MultTest(qty, min, max);				
					multtest.setVisible(true);
				}
				else if (divradio.isSelected())
				{
					DivTest divtest = new DivTest(qty, min, max);				
					divtest.setVisible(true);
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
