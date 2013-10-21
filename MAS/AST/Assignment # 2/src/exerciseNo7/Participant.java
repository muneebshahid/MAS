/**
 *	@author Muneeb Shahid
 */
package exerciseNo7;

/**
 * Participant class. 
 * @author muneeb
 */
public class Participant
{
	/**
	 * Name of the participant
	 */
	private String name;
	
	/**
	 * Name of the participant, current participant referred or talked about
	 */
	private String referredTo;
	
	/**
	 * boolean value. True if the current participant referred to other participant
	 * as blindfolded.
	 */
	private boolean referredToAsBlindFolded;
	
	public String getName()
	{
		return this.name;
	}
	public String getReferredTo()
	{
		return this.referredTo;
	}
	public boolean referredToAsBlindFolded()
	{
		return this.referredToAsBlindFolded;
	}
	
	/**
	 * 
	 * @param name 
	 * 			Name of the current participant
	 * @param referredTo 
	 * 			Name of the participant, current participant referred or talked about
	 * @param isBlindFolded
	 * 			boolean value. True if the current participant referred to other participant 
	 * 			as blindfolded.
	 */
	public Participant(String name, String referredTo, boolean isBlindFolded)
	{
		this.name = name;
		this.referredTo = referredTo;
		this.referredToAsBlindFolded = isBlindFolded;
	}
		
	public boolean equals(Participant participant)
	{
		return this.name == participant.name;
	}
}
