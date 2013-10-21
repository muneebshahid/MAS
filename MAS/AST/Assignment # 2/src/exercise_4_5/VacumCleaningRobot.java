/**
 *	@author Muneeb Shahid
 */
package exercise_4_5;

/**
 * Defines functions of each robot.
 * Also contains their predefined names.
 */
public class VacumCleaningRobot
{
	/**
	 * Measure of how much robot cleans the room (percentage)
	 */
	private double cleanRoomBy = 0D;
	
	/**
	 * Measure of how much robot makes the room dirty (percentage)
	 */
	private double dirtyRoomBy = 0D;  
	
	private int totalArea = 100;
	/**
	 * Constant robot names.
	 * Used primarily for generating permutations.
	 */
	public final static String[] robotNames = { "a", "b", "c", "d", "e" };
	
	
	public VacumCleaningRobot(int cleanRoomByPercentage)
	{
		this.cleanRoomBy = cleanRoomByPercentage / 100D;
	}
	
	public VacumCleaningRobot(int cleanRoomByPercentage, int dirtyRoomByPercentage)
	{
		this.cleanRoomBy = cleanRoomByPercentage / 100D;
		this.dirtyRoomBy = dirtyRoomByPercentage / 100D;
	}
	
	/**
	 * Cleans the area, according to the given functions.
	 * @param percentCleanArea
	 * @return
	 */
	public double clean(double percentCleanArea)
	{
		/**
		 * Defines the performance measure of cleaning the room		 
		 */
		return this.cleanRoomBy * totalArea + (1 - (this.cleanRoomBy + this.dirtyRoomBy)) * percentCleanArea;
	}
}
