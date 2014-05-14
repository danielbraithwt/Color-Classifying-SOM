package SOM;

import java.util.Random;

public class InitializationContainer
{

	public int rangeMin = 0;
	public int rangeMax = 0;
	Random r = new Random();
	
	private double normFunction(int i)
	{
		return 0.0;
	}
	
	public double getVal()
	{
		return normFunction(r.nextInt(rangeMax+1));
	}

}
