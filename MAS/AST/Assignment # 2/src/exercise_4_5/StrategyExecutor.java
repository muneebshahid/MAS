/*
 *	@author Muneeb Shahid
 */
package exercise_4_5;

public class StrategyExecutor
{
	private int cleanArea;
	private java.util.List<String> permutations;
	private java.util.HashMap<String, VacumCleaningRobot> robots;

	
	/**
	 * @param cleanArea The initial clean area. 
	 * @param permutations List of all permutations that will be executed.
	 * @param robots List of the robots
	 */
	public StrategyExecutor(int cleanArea, java.util.List<String> permutations,
			java.util.HashMap<String, VacumCleaningRobot> robots)
	{
		this.cleanArea = cleanArea;
		this.permutations = permutations;
		this.robots = robots;
	}

	/**
	 *  Executes all permutations
	 */
	public void Execute()
	{
		java.util.HashMap<String, Double> results = new java.util.HashMap<String, Double>();
		for (String permutation : permutations)
		{
			//Since one permutations is to not use any robot equivalent of empty string
			//so replacing it with the string "No Robot"
			if (permutation == "")
			{
				permutation = "No Robot ";
				continue;
			}
			
			double currentCleanArea = cleanArea;
			for (char robotName : permutation.toCharArray())
			{				
				currentCleanArea = robots.get(robotName + "").clean(
						currentCleanArea);
			}
			//Store all results
			results.put(permutation, currentCleanArea);
		}
		
		String bestPermutation = "";
		double percentCleanArea = 0;
		//finally store the best permutation by checking area cleaned 
		for (String result : results.keySet())
		{
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
	}
}
