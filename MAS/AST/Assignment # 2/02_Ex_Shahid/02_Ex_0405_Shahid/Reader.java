/*
 *	@author Muneeb Shahid
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 *
 * //A helper class for taking user input
 * @author muneeb
 *
 */
public class Reader
{
	private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;
	public Reader()
	{
		inputStreamReader = new InputStreamReader(System.in);
	    bufferedReader = new BufferedReader(inputStreamReader);
	}

	/**
	 * Reads Integer and throws exception if input was not integer
	 * @return
	 * @throws Exception
	 */	
	public int readInt() throws Exception
	{
		try
		{
			String str = bufferedReader.readLine();
			return Integer.valueOf(str).intValue();
		}
		catch(NumberFormatException nFE)
		{
			throw new Exception("Unable to read Integer");
		}
		catch(IOException iE)
		{
			throw new Exception("Unable to read Integer");
		}		
	}

	
	/**
	 * Reads string user input and throws exception if unable to read
	 * @return
	 * @throws Exception
	 */
	public String readString() throws Exception
	{
		try
		{
			return bufferedReader.readLine();
		}
		catch(IOException iE)
		{
			throw new Exception("Unable to read user input");
		}		
	}
}
