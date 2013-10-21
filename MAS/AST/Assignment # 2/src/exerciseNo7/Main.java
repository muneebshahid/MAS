/**
 *	@author Muneeb Shahid
 */
package exerciseNo7;

import java.util.*;

public class Main
{
	private static java.util.HashMap<String, Participant> participants;
	private static java.util.List<String> combinations;

	/**
	 * Initialize all the participants with their name keys and name keys of the peson 
	 * they referred to.
	 * Name keys can be used to map to a participant using LookUp class
	 */
	public static void populateParticipants()
	{
		participants = new HashMap<String, Participant>();
		participants.put("A", new Participant("A", "D", false));
		participants.put("B", new Participant("B", "C", false));
		participants.put("C", new Participant("C", "H", false));
		participants.put("D", new Participant("D", "B", false));
		participants.put("E", new Participant("E", "J", false));
		participants.put("F", new Participant("F", "K", false));
		participants.put("G", new Participant("G", "L", false));
		participants.put("H", new Participant("H", "G", false));
		participants.put("I", new Participant("I", "A", false));
		participants.put("J", new Participant("J", "E", false));
		participants.put("K", new Participant("K", "F", false));
		participants.put("L", new Participant("L", "C", true));
	}

	/**
	 * Generates the combinations
	 */
	public static void generateCombinations()
	{
		/**
		 * choose value of the current combinations.
		 * nCk = 12C6
		 */
		int choose = 6;
		char[] input = "ABCDEFGHIJKL".toCharArray();
		char[] branch = new char[choose];
		Combinations combination = new Combinations();
		combination.combine(input, choose, 0, branch, 0);
		combinations = combination.getCombinations();
	}

	/**
	 * checks if the person who is being referred to as not blind folded is truly 
	 * blind folded or not by looking up in the current list of not blind folded people
	 * @param str
	 * @param strArray
	 * @return
	 */
	private static boolean checkIfExistsInArray(String str, String[] strArray)
	{
		for (String element : strArray)
		{
			if (element.trim().equals(str))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 */
	private static void findSolution()
	{
		String[] notBlindFolded;
		int solutionsFound = 0;
		for (String combination : combinations)
		{
			boolean isValidSolution = true;
			notBlindFolded = combination.split(",");
			for (String participantKey : notBlindFolded)
			{
				participantKey = participantKey.trim();
				Participant participant = participants.get(participantKey);
				String referredTo = participant.getReferredTo();
				boolean referredToAsBlindFolded = participant.referredToAsBlindFolded();
				if (!referredToAsBlindFolded)
				{
					if (!checkIfExistsInArray(referredTo.trim(), notBlindFolded))
					{
						isValidSolution = false;
						break;
					}
					else
					/**
					 * Special Condition it can be applied earlier to decrease
					 * search space by a big margin
					 * The logic is if two persons say about each other
					 * that the other person is not blindfolded than they are not blindfolded for sure
					 */					
					{
						Participant referredToParticipant = participants
								.get(referredTo);
						if (!(referredToParticipant.referredToAsBlindFolded()
								|| referredToParticipant.getReferredTo()
										.trim().equals(participantKey)))
						{
							isValidSolution = false;
							break;
						}

					}
				}
			}
			/**
			 * If a valid solution is found print it.
			 */
			if (isValidSolution)
			{
				StringBuilder solution = new StringBuilder();
				for (String participantKey : participants.keySet())
				{
					participantKey = participantKey.trim();
					if (!checkIfExistsInArray(participantKey, notBlindFolded))
					{
						solution.append(LookUp.get(participantKey))
								.append(", ");
					}
				}
				solutionsFound = solutionsFound + 1;
				System.out.println(solution.toString());
				System.out.println("----------------------------");
			}
		}
		System.out.println("Total solutions found: " + solutionsFound);
	}

	public static void main(String[] args)
	{
		populateParticipants();
		generateCombinations();
		findSolution();
	}
}
