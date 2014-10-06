package com.jakefoot.mathtest;

public class MathTest
{
	private static MainGui gui;

	public static void main(String[] args)
	{
		gui = new MainGui();
		gui.setVisible(true);
		gui.setLocationRelativeTo(null);
	}
	
	public static void setHeader(String s)
	{
	    gui.setTitle(s);
	}
	
	public static void packWindow()
	{
	    gui.pack();
	}
}
