/*
*	@author Muneeb Shahid
*/
package exerciseNo4;

public class Participant
{
	private String name;
	private String referredTo;
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
