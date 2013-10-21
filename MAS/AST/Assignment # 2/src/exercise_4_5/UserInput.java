/*
 *	@author Muneeb Shahid
 */
package exercise;

import java.util.HashMap;

public class UserInput
{

	public static int getInitialCleanArea()
	{
		Reader reader = new Reader();
		int cleanArea = 0;
		while (true)
		{
			try
			{
				System.out.println("Enter initial clean area in percentage\n");
				cleanArea = reader.readInt();
				if (cleanArea > 100 || cleanArea < 0)
				{
					System.out
							.println("Percentage must not be less than 0 or greater than 100");
					continue;
				}
				break;
			}
			catch (Exception e)
			{
				System.out.println("Unable to read data. Please retry");
			}
		}
		return cleanArea;
	}

	public static java.util.HashMap<String, VacumCleaningRobot> loadRobots()
	{
		Reader reader = new Reader();
		System.out.println("Enter functions for 5 robots");
		HashMap<String, VacumCleaningRobot> robots = new HashMap<>();
		int i = 0;

		while (i < 5)
		{
			int areaClean = 0;
			int areaDirty = 0;
			while (true)
			{
				try
				{
					System.out.println("Enter area cleaned by robot "
							+ VacumCleaningRobot.robotNames[i]);
					areaClean = reader.readInt();
					break;
				}
				catch (Exception e)
				{
					System.out.println("Unable to read data. Please retry");
				}
			}
			while (true)
			{
				try
				{
					System.out.println("Enter area made dirty by robot "
							+ VacumCleaningRobot.robotNames[i]);
					areaDirty = reader.readInt();
					break;
				}
				catch (Exception e)
				{
					System.out.println("Unable to read data. Please retry");
				}
			}
			if (areaClean + areaDirty > 100)
			{
				System.out
						.println("Sum of area cleaned and made dirty by robot cannot exceed 100. \nPlease retry");
				continue;
			}
			robots.put(VacumCleaningRobot.robotNames[i],
					new VacumCleaningRobot(areaClean, areaDirty));
			i++;
		}
		return robots;
	}

}
