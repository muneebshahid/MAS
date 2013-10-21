/*
 *	@author Muneeb Shahid
 */
package exerciseNo7;

import java.util.HashMap;

public class LookUp
{
	/**
	 * Look up table to match combinations key against participant name
	 */
	private final static java.util.HashMap<String, String> lookUp = new HashMap<String, String>();
	static
	{
		lookUp.put("A", "cynthia");
		lookUp.put("B", "eric");
		lookUp.put("C", "barbara");
		lookUp.put("D", "david");
		lookUp.put("E", "gonzo");
		lookUp.put("F", "irina");
		lookUp.put("G", "karla");
		lookUp.put("H", "fred");
		lookUp.put("I", "lydia");
		lookUp.put("J", "hosagi");
		lookUp.put("K", "jacky");
		lookUp.put("L", "adam");
	}
	
	public static String get(String key)
	{
		return lookUp.get(key);
	}
}
