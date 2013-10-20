/*
 *	@author Muneeb Shahid
 */

package common.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reader
{
	private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;
	public Reader()
	{
		inputStreamReader = new InputStreamReader(System.in);
	    bufferedReader = new BufferedReader(inputStreamReader);
	}

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

	public String readString() throws Exception
	{
		try
		{
			return bufferedReader.readLine();
		}
		catch(IOException iE)
		{
			throw new Exception("Unable to read Integer");
		}		
	}
}