/*
 *	@author Muneeb Shahid
 */
package exercise;

public class StrategyExecutor
{
	private int cleanArea;
	private java.util.List<String> permutations;
	private java.util.HashMap<String, VacumCleaningRobot> robots;

	public StrategyExecutor(int cleanArea, java.util.List<String> permutations,
			java.util.HashMap<String, VacumCleaningRobot> robots)
	{
		this.cleanArea = cleanArea;
		this.permutations = permutations;
		this.robots = robots;
	}

	public void Execute()
	{
		java.util.HashMap<String, Double> results = new java.util.HashMap<String, Double>();
		for (String permutation : permutations)
		{
			double currentCleanArea = cleanArea;
			for (char robotName : permutation.toCharArray())
			{
				currentCleanArea = robots.get(robotName + "").clean(
						currentCleanArea);
			}
			if (permutation == "")
			{
				permutation = "No Robot ";
			}
			results.put(permutation, currentCleanArea);
		}
		String bestPermutation = "";
		String[] strategies = new String[2];
		double percentCleanArea = 0;
		for (String result : results.keySet())
		{
			if (strategies[0] == null && result != "No Robot")
			{
				strategies[0] = result;
			}
			else if (strategies[1] == null)
			{
				strategies[1] = result;
			}
			if (percentCleanArea < results.get(result))
			{
				bestPermutation = result;
				percentCleanArea = results.get(result);
			}
			System.out.println("Clean Area for strategy: " + result + " "
					+ results.get(result) + "%");
		}
		System.out.println("Best strategy is to use robots: \"" + bestPermutation
				+ "\" with " + percentCleanArea + "% clean area.");
		//this.findBestStrategyForAGivenScenario(strategies);
	}

	private void findBestStrategyForAGivenScenario(String[] strategies)
	{
		System.out.println("Ref Exercise task 5.3");
		System.out.println("Selecting two strategies");
		System.out.println("Strategy " + strategies[0] + " and "
				+ strategies[1]);
		double previousCleanArea = 0D;
		String[] result = new String[2];
		for (int i = 0; i <= 100; i++)
		{
			for (String strategy : strategies)
			{
				double currentCleanArea = i;
				for (char robotName : strategy.toCharArray())
				{
					currentCleanArea = robots.get(robotName + "").clean(
							currentCleanArea);
				}
				if (previousCleanArea == 0D)
				{
					previousCleanArea = currentCleanArea;
				}
				else
				{
					if (previousCleanArea > currentCleanArea)
					{
						result[0] = i + "";
					}
					else if (currentCleanArea > previousCleanArea)
					{
						result[1] = i + "";
					}
				}
			}
		}
		if (result[0] != null)
		{
			System.out.println("Strategy " + strategies[0]
					+ " is better when initial clean area is " + result[0]);
		}
		if (result[1] != null)
		{
			System.out.println("Strategy " + strategies[1]
					+ " is better when initial clean area is " + result[1]);
		}
	}
}
