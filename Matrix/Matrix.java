package Matrix;

public class Matrix
{
	private MatrixType type;
	private int rows;
	private int cols;
	private double[][] matrix;
	
	/**
	 * Initilizes the newley created matrix class
	 * 
	 * @param t / type of the matrix
	 * @param r / num of rows ( note this value isnt used if type is ROW matrix )
	 * @param c / num of columns ( note this value isnt used if type is COL matrix )
	 */
	public Matrix(MatrixType t, int r, int c )
	{
		this.rows = r;
		this.cols = c;
		
		// Initilise matrix array depending on type of matrix
		switch( t )
		{
		case COL:
			matrix = new double[r][1];
			break;
		
		case ROW:
			matrix = new double[1][c];
			break;
			
		case GRID:
			matrix = new double[r][c];
		}
	}
	
	public MatrixType getType()
	{
		return type;
	}
	
	/**
	 * Adds the input vairable ( value ) to every element in the matrix
	 * 
	 * @param value / number to add
	 */
	public void add( double value )
	{
		for( int i = 0; i < matrix.length; i++ )
		{
			for( int j = 0; j < matrix[i].length; j++ )
			{
				matrix[i][j] += value;
			}
		}
	}
	
	/**
	 * Sets the value at the position (row, col) to the variable ( value )
	 * 
	 * @param row / position of row in matrix
	 * @param col / position of the column in matrix
	 * @param value / replacement value
	 */
	public void set( int row, int col, double value )
	{
		matrix[row][col] = value;
	}
	
	/**
	 * Gets the value contained in the matrix at (row, col)
	 * 
	 * @param row / postion of the row in matrix
	 * @param col / position of the column in matrix
	 * @return value at the postion
	 */
	public double get( int row, int col )
	{
		return matrix[row][col];
	}
	
	/**
	 * Sets every value in the matrix to 0.0
	 */
	public void clear()
	{
		for( int i = 0; i < matrix.length; i++ )
		{
			for( int j = 0; j < matrix[i].length; j++ )
			{
				matrix[i][j] = 0.0;
			}
		}
	}
	
	/**
	 * Clones the matrix so that if the new matrix is altered it dosent
	 * effect the oridgonal
	 */
	public Matrix clone()
	{
		Matrix clone = new Matrix(this.type, this.rows, this.cols);
		
		for( int i = 0; i < matrix.length; i++ )
		{
			for( int j = 0; j < matrix[i].length; i++ )
			{
				clone.set(i, j, matrix[i][j]);
			}
		}
		
		return clone;
	}
	
	/**
	 * Gets an entire column and returns it
	 * 
	 * @param c / postion in matrix of column to get
	 * @return the column as a column Matrix
	 */
	public Matrix getCol( int c )
	{
		Matrix column = new Matrix(MatrixType.COL, matrix.length, 0 );
		
		for( int i = 0; i < matrix.length; i++ )
		{
			column.set(0, i, matrix[i][c]);
		}
		
		return column;
	}
	
	/**
	 * Returns the number of columns
	 * 
	 * @return num of cols
	 */
	public int getCols()
	{
		return matrix[0].length;
	}
	
	/**
	 * Gets an entire row ( r ) and returns it
	 * 
	 * @param r / position of the row in the matrix
	 * @return the row as a row Matrix
	 */
	public Matrix getRow( int r )
	{
		Matrix row = new Matrix(MatrixType.ROW, 0, matrix[0].length );
		
		for( int i = 0; i < matrix[0].length; i++ )
		{
			row.set(0, i, matrix[r][i]);
		}
		
		return row;
	}
	
	/**
	 * Gets the number of rows contained in the matrix and returns it
	 * 
	 * @return num of rows
	 */
	public int getRows()
	{
		return matrix.length;
	}
	
	/**
	 * returns true if the Matrix is a vector ( a column or row matrix )
	 * 
	 * @return boolean
	 */
	public boolean isVector()
	{
		switch( type )
		{
		case COL:
			return true;
		
		case ROW:
			return true;
			
		case GRID:
			return false;
			
		default:
			return false;
		}
	}
	
	/**
	 * Takes the matrix and converts it into a single one dimentional array
	 * 
	 * @return array of values
	 */
	public double[] toPackedArray()
	{
		double[] packedArray = new double[matrix.length * matrix[0].length];
		
		for( int i = 0, k = 0; i < matrix.length; i++ )
		{
			for( int j = 0; j < matrix[0].length; j++ )
			{
				packedArray[k] = matrix[i][j];
				k++;
			}
		}
		
		return packedArray;
	}

}
