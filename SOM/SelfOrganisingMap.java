package SOM;

import java.util.Arrays;
import java.util.Random;
import Matrix.Matrix;

public class SelfOrganisingMap 
{
	private int numOfInputNeurons = 0;  // Number of neurons that probide input
	private int numOfOutputNeurons = 0; // will be numOfOutputNeurons X numOfOutputNeurons
	private static double timeConstant = 0.0;
	private static double currentItteration = 1;
	private static double initialLearningRate;
	InitializationContainer initCon;
	private static Random r = new Random();
	
	
	private Node[][] outputNeurons;
	
	public SelfOrganisingMap(int numInput, int numOutput, int totalItter, double ilr, InitializationContainer ic)
	{
		numOfInputNeurons = numInput;
		numOfOutputNeurons = numOutput;
		initialLearningRate = ilr;
		initCon = ic;
		
		//timeConstant = totalItter/(numOfOutputNeurons / 2); // What it should be
		timeConstant = 10000/(numOfOutputNeurons / 2); // Cheep Moderfacation For Color SOM
		
		outputNeurons = new Node[numOfOutputNeurons][numOfOutputNeurons];
		
		for( int i = 0; i < numOfOutputNeurons; i++ )
		{
			for( int j = 0; j < numOfOutputNeurons; j++ )
			{
				outputNeurons[i][j] = new Node(numOfInputNeurons);
			}
		}
		
		initilize();
	}
	
	/**
	 * Sets the nodes in the matrix to random data sets
	 */
	public void initilize()
	{
		for( int i = 0; i < numOfOutputNeurons; i++ )
		{
			for( int j = 0; j < numOfOutputNeurons; j++ )
			{	
				for( int k = 0; k < numOfInputNeurons; k++ )
				{
					outputNeurons[i][j].set(k, initCon.getVal());
				}
			}
		}
	}
	
	/**
	 * Trains the network using a data set ( the double array )
	 * 
	 * @param data
	 * @return
	 */
	public int[] train( double data[] )
	{
		int[] cords = new int[2];
		// training data should allready be normalized
		
		// Step 1 : calculate BMU
		
		double bestMatchScore = 10;
		int bestX = 0;
		int bestY = 0;
		
		// For Each Node Calculate how similar the input is to it
		for( int i = 0; i < numOfOutputNeurons; i++ )
		{
			for( int j = 0; j < numOfOutputNeurons; j++ )
			{
				double dist = 0.0;
				
				for( int k = 0; k < numOfInputNeurons; k++ )
				{
					//System.out.println(outputNeurons[i][j].get(k) * 255);
					dist += Math.pow(( data[k] - outputNeurons[i][j].get(k) ), 2);
				}
				
				dist = Math.sqrt(dist);
				
				if( dist < bestMatchScore )
				{
					bestMatchScore = dist;
					bestX = j;
					bestY = i;
				}
			}
		}
		
		cords[0] = bestX;
		cords[1] = bestY;
		
		// Calculate nabourhood size
		double nsize =  (numOfInputNeurons/2) * Math.pow(Math.E, (-(double) currentItteration/timeConstant) );
		
		// Calculate the learning rate
		double learningRate = initialLearningRate * Math.pow(Math.E, (-currentItteration/timeConstant));
		
		// Updata the BMU
		
		for( int k = 0; k < numOfInputNeurons; k++ )
		{
			double update = ( ( 1 * learningRate ) * ( ( data[k] - outputNeurons[bestY][bestX].get(k) ) ) );
			outputNeurons[bestY][bestX].set(k, outputNeurons[bestY][bestX].get(k) + update );
			
			if( outputNeurons[bestY][bestX].get(k) > 1 )
			{
				System.out.println("Greater Than 1");
			}
			else if( outputNeurons[bestY][bestX].get(k) < -1 )
			{
				System.out.println("Less Than -1");
			}
		}
		
		// Change all the nodes the the nabourhood
		
		for( int i = 0; i < numOfOutputNeurons; i++ )
		{
			for( int j = 0; j < numOfOutputNeurons; j++ )
			{
				double dist = Math.pow(( bestY - i ), 2) + Math.pow(( bestX - j ) , 2);
				double distanceFromBMU = Math.pow(Math.E, ( -dist/2 * nsize ));
				
				if( distanceFromBMU <= nsize )
				{
					for( int k = 0; k < numOfInputNeurons; k++ )
					{
						double update = ( ( distanceFromBMU * learningRate ) * ( data[k] - outputNeurons[i][j].get(k) ) );
						outputNeurons[i][j].set(k, outputNeurons[i][j].get(k) + update );
					}
				}
			}
		}
				
		outputNeurons[bestY][bestX].win();		
		currentItteration++;
		
		return cords;
		
	}
	 /**
	  * Returns the data in a node
	  * 
	  * @param i
	  * @param j
	  * @return
	  */
	public double[] get( int i, int j )
	{
		double[] data = new double[numOfInputNeurons];
		for( int k = 0; k < numOfInputNeurons; k++ )
		{
			data[k] = outputNeurons[i][j].get(k);
		}
		
		return data;
	}

}
