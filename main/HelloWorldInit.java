package main;
import java.util.Random;

import SOM.InitializationContainer;


public class HelloWorldInit extends InitializationContainer {

	public int rangeMin = 0;
	public int rangeMax = 255;
	Random r = new Random();
	
	/**
	 * Private function that takes an int ( i ) and normalises it
	 * in this case that means deviding it by 255
	 * 
	 * @param i
	 * @return
	 */
	private double normFunction(int i)
	{
		return (double) i/255.00;
	}

	/**
	 * Public function that a program will call to get a random
	 * normalised data point
	 * 
	 */
	@Override
	public double getVal() 
	{
		return normFunction(r.nextInt(rangeMax+1));
	}

}
