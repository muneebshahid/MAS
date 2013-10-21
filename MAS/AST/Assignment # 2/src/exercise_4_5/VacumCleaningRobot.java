/*
*	@author Muneeb Shahid
*/
package exercise;

public class VacumCleaningRobot
{
	private double cleanRoomBy = 0D;
	private double dirtyRoomBy = 0D;  
	private int totalArea = 100;
	public final static String[] robotNames = { "a", "b", "c", "d", "e" };
	public VacumCleaningRobot(int cleanRoomByPercentage)
	{
		this.cleanRoomBy = cleanRoomByPercentage / 100D;
	}
	public VacumCleaningRobot(int cleanRoomByPercentage, int dirtyRoomByPercentage)
	{
		if ((cleanRoomByPercentage < 0 || cleanRoomByPercentage > 100) || 
				(dirtyRoomByPercentage < 0 || dirtyRoomByPercentage > 100))
		{
			
		}
		this.cleanRoomBy = cleanRoomByPercentage / 100D;
		this.dirtyRoomBy = dirtyRoomByPercentage / 100D;
	}
	
	public double clean(double percentCleanArea)
	{
		return this.cleanRoomBy * totalArea + 
				(1 - (this.cleanRoomBy + this.dirtyRoomBy)) * percentCleanArea;
	}
}
