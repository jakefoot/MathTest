package com.jakefoot.mathtest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SubTest extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField num1field;
	private JTextField num2field;
	private JTextField ansfield;
	
	private JLabel probnum;
	private JLabel operator;
	private JLabel equals;
	private JLabel msg;
	private JLabel scorelabel;
	
	private JButton resetbutton;
	private JButton menubutton;
	
	private int numB;
	private int numA;	
	private int num;
	private int num1;
	private int num2;
	private int ans;
	private int numright = 0;	
	private int quant;
	private int randhigh;
	private int randlow;
	private int givenans;
	private int counter = 1;
	
	private float score;
	
	private boolean error = false;	
	
	Random rand = new Random();
	
	
	public SubTest(int qty, int min, int max)
	{		
		super();
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JPanel probpane = new JPanel(new FlowLayout(FlowLayout.CENTER));
		probpane.setBackground(Color.WHITE);
		add(probpane);
		
		JPanel msgpane = new JPanel(new FlowLayout(FlowLayout.CENTER));
		msgpane.setBackground(Color.WHITE);
		add(msgpane);
		
		JPanel scorepane = new JPanel(new FlowLayout(FlowLayout.CENTER));
		scorepane.setBackground(Color.WHITE);
		add(scorepane);
		
		JPanel buttonpane = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10));
		buttonpane.setBackground(Color.WHITE);
		add(buttonpane);		
		
		//problem display
		probnum = new JLabel();
		probnum.setFont(MainGui.radiofont);
		probpane.add(probnum);		
		
		num1field = new JTextField();
		num1field.setFont(MainGui.radiofont);
		num1field.setColumns(2);
		num1field.setEditable(false);
		num1field.setHorizontalAlignment(JLabel.CENTER);
		num1field.setBackground(Color.WHITE);
		num1field.setBorder(null);
		probpane.add(num1field);
		
		operator = new JLabel(" - ");
		operator.setFont(MainGui.radiofont);
		operator.setHorizontalAlignment(JLabel.CENTER);
		probpane.add(operator);
		
		num2field = new JTextField();
		num2field.setFont(MainGui.radiofont);
		num2field.setColumns(2);
		num2field.setEditable(false);
		num2field.setHorizontalAlignment(JLabel.CENTER);
		num2field.setBackground(Color.WHITE);
		num2field.setBorder(null);
		probpane.add(num2field);
		
		equals = new JLabel("=");
		equals.setFont(MainGui.radiofont);
		probpane.add(equals);	
		
		ansfield = new JTextField();
		ansfield.setFont(MainGui.radiofont);
		ansfield.setColumns(2);
		ansfield.setEditable(true);
		ansfield.setHorizontalAlignment(JLabel.CENTER);
		ansfield.setBackground(Color.WHITE);
		probpane.add(ansfield);
		ansfield.addActionListener(new AnswerHandlerClass());
		
		
		//correct/incorrect message display
		msg = new JLabel();
		msg.setFont(MainGui.radiofont);
		msgpane.add(msg);
		
		//score display
		scorelabel = new JLabel();
		scorelabel.setFont(MainGui.radiofont);
		scorepane.add(scorelabel, BorderLayout.SOUTH);
		
		//define buttons
		resetbutton = new JButton("Try again");
		resetbutton.setFont(MainGui.radiofont);
		resetbutton.setEnabled(false);
		buttonpane.add(resetbutton);
		resetbutton.addActionListener(new ButtonHandlerClass());
		
		menubutton = new JButton("Menu");
		menubutton.setFont(MainGui.radiofont);
		menubutton.addActionListener(new ButtonHandlerClass());
		buttonpane.add(menubutton);
		
		//set variables initialize problem
		randlow = min;
		randhigh = max - min + 1;
		quant = qty;
		setProblem();
		++counter;
	}

	private int getFactor ()
	{
		num = randlow + rand.nextInt(randhigh);		
		return num;		
	}
	
	private void setProblem()
	{		
		probnum.setText(String.format("%s)", counter));
		operator.setText("-");
		equals.setText("=");
		numA = getFactor();
		numB = getFactor();
		if (numA > numB)
		{
			num1 = numA;
			num2 = numB;
		}
		else
		{
			num1 = numB;
			num2 = numA;
		}
		ans = num1 - num2;
		num1field.setText(String.format("%d", num1));
		num2field.setText(String.format("%d", num2));
		ansfield.setText(null);
	}
	
	private void evaluateAnswer(int given)
	{
		if (ans == given)
		{
			++numright;
			msg.setForeground(Color.GREEN);
			msg.setText("Correct!  " + num1 + " - " + num2 + " = " + ans);
		}
		else
		{
			msg.setForeground(Color.RED);
			msg.setText("Incorrect.  " + num1 + " - " + num2 + " = " + ans);
		}
	}	
	
	private class AnswerHandlerClass implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{				
			try
			{
				givenans = Integer.valueOf(ansfield.getText());		
			}
			catch (Exception e)
			{
				error = true;
				msg.setForeground(Color.RED);
				msg.setText("Invalid answer. Please enter an integer value");
			}
			
			if (error == true)
			{
				ansfield.setText(null);
				error = false;
			}
			else
			{			
				if (counter <= quant)
				{
					evaluateAnswer(givenans);
					setProblem();
					++counter;
				}
				else
				{
					evaluateAnswer(givenans);
					score = (float)numright/(float)quant*100;
					scorelabel.setText(String.format("Final score: %2.0f%%", score));
					probnum.setText(null);
					operator.setText(null);
					equals.setText(null);
					ansfield.setVisible(false);
					ansfield.setEnabled(false);
					num1field.setText(null);
					num2field.setText(null);
					resetbutton.setEnabled(true);
				}
			}
		}
	}
	
	private class ButtonHandlerClass implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == resetbutton)
			{
				resetbutton.setEnabled(false);
				counter = 1;
				numright = 0;
				setProblem();
				ansfield.setEnabled(true);
				ansfield.setVisible(true);
				scorelabel.setText(null);
				msg.setText(null);
				++counter;	
				ansfield.requestFocusInWindow();			
			}
			
			if (e.getSource() == menubutton)
			{			    
			    int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to return to the menu?\nAll progress will be lost", "Return To Menu", JOptionPane.YES_NO_OPTION);
				
				if(result == JOptionPane.YES_OPTION)
				{
				    MainGui.showOptions();				
				}
			}
		}		
	}
}
