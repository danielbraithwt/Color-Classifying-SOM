package SOM;

import Matrix.Matrix;
import Matrix.MatrixType;

public class Node 
{
	private Matrix dataVector;
	private int wins = 1;
	
	/**
	 * Creation method for the neuron class, takes the number
	 * of input neurons
	 * 
	 * @param numInputNeurons
	 */
	public Node(int numInputNeurons)
	{
		dataVector = new Matrix(MatrixType.ROW, 0, numInputNeurons);
	}
	
	/**
	 * Returns the data in position i in the
	 * nodes data vector
	 * 
	 * @param i
	 * @return
	 */
	public double get(int i)
	{
		return dataVector.get(0, i);
	}
	
	/**
	 * Sets the data in position i in the data vector to data
	 * 
	 * @param i
	 * @param data
	 */
	public void set( int i, double data )
	{
		dataVector.set(0, i, data);
	}
	
	/**
	 * Returns the diffrence between the data in the data vector
	 * and the double array that gets passed to it
	 * 
	 * @param input
	 * @return
	 */
	public double getDiffrence(double[] input)
	{
		double sum = 0.0;
		
		for( int i = 0; i < dataVector.getCols(); i++ )
		{
			//System.out.println(input[i] - dataVector.get(0, i));
			sum += Math.abs(input[i] - dataVector.get(0, i));
			//sum += Math.abs(input[i] - dataVector.get(0, i) );
		}
		
		return sum;
	}
	
	/**
	 * Method has no purpos yet
	 */
	public void win()
	{
		wins++;
	}
}

