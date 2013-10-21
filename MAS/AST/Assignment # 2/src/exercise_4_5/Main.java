/*
 *	@author Muneeb Shahid
 */
package exercise_4_5;

public class Main
{
	public static void main(String[] args)
	{
		//Get the initial clean area in percentage.
		int cleanArea = UserInput.getInitialCleanArea();
		
		//Load up to 5 robots, by taking input from user.
		//String contains one of the 5 predefined names for robot. One for each. a,b,c etc
		java.util.HashMap<String, VacumCleaningRobot> robots = UserInput.loadRobots();
		
		//Create a string from robot names, permutations will be generated from this string.
		String robotNames = "";
		for (String name : robots.keySet())
		{
			robotNames = robotNames + name;
		}
		
		//Store all permutations
		java.util.List<String> permutations = Permutations.generateAllPermutations(robotNames);
		
		//Executes each permutation on clean area.
		StrategyExecutor executor = new StrategyExecutor(cleanArea, permutations, robots);
		executor.Execute();	
	}
}
