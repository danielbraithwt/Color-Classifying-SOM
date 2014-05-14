package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Panel;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import SOM.SelfOrganisingMap;


public class HelloWorldSOM extends Panel 
{
	private static boolean started = false;

	static SelfOrganisingMap SOM;
	private static int inputNeurons = 3;
	private static int outputNeurons = 20;
	private static double learningRate = 1;
	private static int epocs = 1300;
	
	private static int windowSize = 500;
	
	private static HelloWorldInit init = new HelloWorldInit();
		
	public static void main(String[] args) 
	{
		// Popup that gets number of epocs from user
		JFrame popup = null;
		Object[] options = {"200","600","1000"};
		int n = JOptionPane.showOptionDialog(popup, "How many epocs would you like the program to run for?","Epocs?", 
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[2]);
		
		if( n == 0 ) epocs = 200;
		else if( n == 1 ) epocs = 600;
		else if( n == 2 ) epocs = 1000;
		
		System.out.println("\n[*] Starting...");
		
		// Initilizes the self organising map
		SOM = new SelfOrganisingMap(inputNeurons, outputNeurons, epocs, learningRate, init );
		
		// Sets up the GUI
		JTextField text = new JTextField("Enter number of itterations here", 30);
		JButton startButton = new JButton("Start");
		HelloWorldSOM canvas = new HelloWorldSOM();
		JFrame frame = new JFrame();
		frame.setTitle("Hello World SOM");
		frame.setSize(windowSize, windowSize);
		frame.setPreferredSize( new Dimension(windowSize, windowSize) );
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(canvas);


		
		
		frame.setVisible(true);
	}
	
	public void paint( Graphics g )
	{
		//while( !started ) {}
		
		// Figure out how big to make each color square 
		double jump = windowSize / outputNeurons;
		
		// For each training vector
		for( int e = 0; e < epocs; e++ )
		{
			System.out.println("Epoc: " + e);
			double data[] = new double[inputNeurons];
			
			// Create random training data
			for( int i = 0; i < data.length; i++ )
			{
				data[i] = init.getVal();
			}
			
			// Train SOM using random training data
			SOM.train(data);
			
			// Draw all colors to the GUI
			for( int i = 0, x = 0; i < outputNeurons; i++, x += jump )
			{
				for( int j = 0, y = 0; j < outputNeurons; j++, y += jump )
				{
					double[] normcolors = SOM.get(i, j);
					int[] colors = new int[normcolors.length];
					
					for( int k = 0; k < normcolors.length; k++ )
					{
						colors[k] = (int) (255 * normcolors[k]);
					}
					
					g.setColor(new Color(colors[0], colors[1], colors[2]));
					g.fillRect(y, x, (int)(y+jump), (int)(x+jump));
				}
			}
		}
	}
}
