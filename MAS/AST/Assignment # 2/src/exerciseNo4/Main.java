/*
 *	@author Muneeb Shahid
 */
package exerciseNo4;

import java.util.*;

public class Main
{
	private static java.util.HashMap<String, Participant> participants;
	private static java.util.List<String> combinations;

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

	public static void generateCombinations()
	{
		int k = 6;
		char[] input = "ABCDEFGHIJKL".toCharArray();
		// char[] input = "AB".toCharArray();
		char[] branch = new char[k];
		Combinations combination = new Combinations();
		combination.combine(input, k, 0, branch, 0);
		combinations = combination.getCombinations();
	}

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
				boolean referredToAsBlindFolded = participant
						.referredToAsBlindFolded();
				if (!referredToAsBlindFolded)
				{
					if (!checkIfExistsInArray(referredTo.trim(), notBlindFolded))
					{
						isValidSolution = false;
						break;
					}
					else
					// Special Condition it can be applied earlier to decrease
					// search space by a big margin
					// The logic behind this if two persons say about each other
					// that the other person is not blindfolded than they are not blindfolded for sure
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
		// TODO Auto-generated method stub

		populateParticipants();
		generateCombinations();
		findSolution();
	}
}
