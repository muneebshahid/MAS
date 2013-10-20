/*
 *	@author Muneeb Shahid
 */
package exercise;


public class Main
{


	public static void main(String[] args)
	{
		int cleanArea = UserInput.getInitialCleanArea();
		java.util.HashMap<String, VacumCleaningRobot> robots = UserInput.loadRobots();		
		String robotNames = "";
		for (String name : robots.keySet())
		{
			robotNames = robotNames + name;
		}
		java.util.List<String> permutations = Permutations.generateAllPermutations(robotNames);
		StrategyExecutor executor = new StrategyExecutor(cleanArea, permutations, robots);
		executor.Execute();	}
}
