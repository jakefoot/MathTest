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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class PracticePane extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel probpane;
	private JPanel msgpane;
	private JPanel scorepane;
	private JPanel resultspane;
	
	private JTextField num1field;
	private JTextField num2field;
	private JTextField ansfield;
	
	private static JTextPane resultsarea;
	
	private JLabel probnum;
	private JLabel operatorlabel;
	private JLabel equals;
	private JLabel msg;
	private JLabel scorelabel;
	
	private JButton resetbutton;
	private JButton menubutton;
	private JButton resultsbutton;
	
	private int num;
	private int num1;
	private int num2;
	private int ans;
	private int numright = 0;	
	private static int quant;
	private int randhigh;
	private int randlow;
	private int givenans;
	private static int counter = 1;
	private static char operator;
	private static String opstring;
	
	private float score;
	
	private boolean error = false;
	
	static SimpleAttributeSet plain;
	static SimpleAttributeSet cor;
	static SimpleAttributeSet incor;	
	
	Random rand = new Random();

	
	
	public PracticePane(int qty, int min, int max, char op)
	{	    
	    	super();
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		plain = new SimpleAttributeSet();
		StyleConstants.setForeground(plain, Color.BLACK);
		StyleConstants.setFontSize(plain, 20);
		
		cor = new SimpleAttributeSet();
		StyleConstants.setForeground(cor, Color.GREEN);
		StyleConstants.setFontSize(cor, 14);
		
		incor = new SimpleAttributeSet();
		StyleConstants.setForeground(incor, Color.RED);
		StyleConstants.setFontSize(incor, 14);
		
		probpane = new JPanel(new FlowLayout(FlowLayout.CENTER));
		probpane.setBackground(Color.WHITE);
		add(probpane);
		
		msgpane = new JPanel(new FlowLayout(FlowLayout.CENTER));
		msgpane.setBackground(Color.WHITE);
		add(msgpane);
		
		resultspane = new JPanel(new FlowLayout(FlowLayout.CENTER));
		resultspane.setBackground(Color.WHITE);
		resultspane.setVisible(false);
		add(resultspane);
		
		scorepane = new JPanel(new FlowLayout(FlowLayout.CENTER));
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
		num1field.setEnabled(false);
		num1field.setDisabledTextColor(Color.BLACK);
		num1field.setHorizontalAlignment(JLabel.CENTER);
		num1field.setBackground(Color.WHITE);
		num1field.setBorder(null);
		probpane.add(num1field);
		
		operatorlabel = new JLabel();
		operatorlabel.setFont(MainGui.radiofont);
		operatorlabel.setHorizontalAlignment(JLabel.CENTER);
		probpane.add(operatorlabel);
		
		num2field = new JTextField();
		num2field.setFont(MainGui.radiofont);
		num2field.setColumns(2);
		num2field.setEnabled(false);
		num2field.setDisabledTextColor(Color.BLACK);
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
		ansfield.setBorder(null);
		probpane.add(ansfield);
		ansfield.addActionListener(new AnswerHandlerClass());		
		
		//correct/incorrect message display
		msg = new JLabel();
		msg.setFont(MainGui.radiofont);
		msgpane.add(msg);
		
		//results display		
		resultsarea = new JTextPane();
		resultsarea.setFont(MainGui.radiofont);
		JScrollPane resultsscroll = new JScrollPane(resultsarea);
		resultsscroll.setPreferredSize(MainGui.contpanel.getSize());
		resultspane.add(resultsscroll);
		
		//score display
		scorelabel = new JLabel();
		scorelabel.setFont(MainGui.radiofont);
		scorepane.add(scorelabel, BorderLayout.SOUTH);
		
		//define buttons
		resetbutton = new JButton("Restart");
		resetbutton.setFont(MainGui.radiofont);
		resetbutton.setFocusable(false);
		resetbutton.setEnabled(false);
		resetbutton.addActionListener(new ButtonHandlerClass());
		buttonpane.add(resetbutton);
		
		menubutton = new JButton("Menu");
		menubutton.setFont(MainGui.radiofont);
		menubutton.setFocusable(false);
		menubutton.addActionListener(new ButtonHandlerClass());
		buttonpane.add(menubutton);
		
		resultsbutton = new JButton("Show Results");
		resultsbutton.setFont(MainGui.radiofont);
		resultsbutton.setFocusable(false);
		resultsbutton.setEnabled(false);
		resultsbutton.addActionListener(new ButtonHandlerClass());
		buttonpane.add(resultsbutton);
		
		//set variables initialize problem
		operator = op;
		randlow = min;
		randhigh = max - min + 1;	
		quant = qty;
		ansfield.requestFocus();
		
		switch (operator)
		{
		case '+': 
		    {
			setAddProblem();
			opstring = "Addition";
			break;
		    }
		case '-': 
		    {
			setSubProblem();
			opstring = "Subtraction";
			break;
		    }
		case 'X': 
		    {
			setMultProblem();
			opstring = "Multiplication";
			break;
		    }
		case '/':
		    {
			setDivProblem();
			opstring = "Division";
			break;
		    }
		}
		setResultsHeader();
	}

	private void setResultsHeader()
	{
	    try
	    {
		appendString(String.format("%d Problem %s Practice%n", quant, opstring), plain);
	    }
	    catch (BadLocationException e)
	    {
		e.printStackTrace();
	    }
	}
	
	private int getFactor ()
	{
		num = randlow + rand.nextInt(randhigh);		
		return num;		
	}
	
	private void setAddProblem()
	{
	    operatorlabel.setText(Character.toString(operator));
	    probnum.setText(String.format("%s)", counter));
	    num1 = getFactor();
	    num2 = getFactor();
	    ans = num1 + num2;
	    num1field.setText(String.format("%d", num1));
	    num2field.setText(String.format("%d", num2));
	    ansfield.setText(null);
	    counter++;
	}
	
	private void setSubProblem()
	{
	    operatorlabel.setText(Character.toString(operator));
	    int numA;
	    int numB;
	    probnum.setText(String.format("%s)", counter));
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
	    counter++;
	}
	
	private void setMultProblem()
	{
	    operatorlabel.setText(Character.toString(operator));
	    probnum.setText(String.format("%s)", counter));
	    num1 = getFactor();
	    num2 = getFactor();
	    ans = num1 * num2;
	    num1field.setText(String.format("%d", num1));
	    num2field.setText(String.format("%d", num2));
	    ansfield.setText(null);
	    counter++;
	}
	
	private void setDivProblem()
	{
	    operatorlabel.setText(Character.toString(operator));
	    probnum.setText(String.format("%s)", counter));
	    num2 = getFactor();
	    ans = getFactor();
	    num1 = num2 * ans;
	    num1field.setText(String.format("%d", num1));
	    num2field.setText(String.format("%d", num2));
	    ansfield.setText(null);
	    counter++;
	}
	
	private static void appendString(String str, SimpleAttributeSet SAS) throws BadLocationException
	{	    
	    StyledDocument doc = (StyledDocument) resultsarea.getDocument();
	    doc.insertString(doc.getLength(), str, SAS);	                                                      
	}
	
	private void evaluateAnswer(int given)
	{
		if (ans == given)
		{
			++numright;
			msg.setForeground(Color.GREEN);
			msg.setText(String.format("Correct!  %d %c %d = %d%n", num1, operator, num2, ans));
			try
			{
			    appendString(String.format("%d) %d %c %d = %d%n", counter-1, num1, operator, num2, ans), cor);
			}
			catch (BadLocationException e)
			{
			    e.printStackTrace();
			}
		}
		else
		{
			msg.setForeground(Color.RED);
			msg.setText(String.format("Incorrect!  %d %c %d = %d%n", num1, operator, num2, ans));
			try
			{
			    appendString(String.format("%d) %d %c %d = %d (%d)%n", counter-1, num1, operator, num2, ans, given), incor);
			}
			catch (BadLocationException e)
			{
			    e.printStackTrace();
			}			
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
					switch (operator)
					{
					case '+': 
					    {
						setAddProblem();
						break;
					    }
					case '-': 
					    {
						setSubProblem();
						break;
					    }
					case 'X': 
					    {
						setMultProblem();
						break;
					    }
					case '/':
					    {
						setDivProblem();
						break;
					    }
					}
				}
				else
				{
					evaluateAnswer(givenans);
					score = (float)numright/(float)quant*100;
					scorelabel.setText(String.format("Final score: %2.0f%%", score));
					try
					{
					    appendString(String.format("Final score: %2.0f%%", score), plain);
					}
					catch (BadLocationException e)
					{
					    e.printStackTrace();
					}
					probnum.setText(null);
					operatorlabel.setText(null);
					equals.setText(null);
					ansfield.setVisible(false);
					ansfield.setEnabled(false);
					num1field.setText(null);
					num2field.setText(null);
					resetbutton.setEnabled(true);
					resultsbutton.setEnabled(true);
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
				ansfield.setEnabled(true);
				ansfield.setVisible(true);
				scorelabel.setText(null);
				msg.setText(null);
				scorepane.setVisible(true);
				probpane.setVisible(true);
				msgpane.setVisible(true);
				
				resultspane.setVisible(false);
				resultsarea.setText(null);
				setResultsHeader();
				resultsbutton.setEnabled(false);
				ansfield.requestFocusInWindow();
				MathTest.packWindow();
				switch (operator)
				{
				case '+': 
				    {
					setAddProblem();
					break;
				    }
				case '-': 
				    {
					setSubProblem();
					break;
				    }
				case 'X': 
				    {
					setMultProblem();
					break;
				    }
				case '/':
				    {
					setDivProblem();
					break;
				    }
				}
			}
			
			if (e.getSource() == menubutton)
			{			    
			    int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to return to the menu?\nAll progress will be lost", "Return To Menu", JOptionPane.YES_NO_OPTION);
				
				if(result == JOptionPane.YES_OPTION)
				{
				    MainGui.showOptions();
				    counter = 1;
				}
			}
			
			if (e.getSource() == resultsbutton)
			{
			    scorepane.setVisible(false);
			    probpane.setVisible(false);
			    msgpane.setVisible(false);
			    resultspane.setVisible(true);
			    resultsbutton.setEnabled(false);
			    MathTest.packWindow();
			}
		}		
	}
}
