/*
 *	@author Muneeb Shahid
 */
package exercise;

import java.util.ArrayList;

public class Permutations
{	
	public static java.util.List<String> generate(String str)
	{
		return generate(str, str.length());
	}

	public static java.util.List<String> generate(String str, int choose)
	{
		return generate("", str.trim(), choose);
	}

	private static java.util.List<String> generate(String prefix, String str,
			int choose)
	{

		int length = str.length();
		String currentPermutation;
		java.util.List<String> permutations = new ArrayList<String>();
		if (choose == 0 || length == 0)
		{
			permutations.add(prefix);
			return permutations;
		}
		if (choose > length)
		{
			choose = length;
		}
		for (int i = 0; i < length; i++)
		{
			java.util.List<String> sub_Permutations;
			int endIndex = i + choose;
			int secondaryEndIndex = 0;
			if (endIndex > length)
			{
				secondaryEndIndex = endIndex - length;
				endIndex = length;
			}
			currentPermutation = str.substring(i, endIndex)
					+ str.substring(0, secondaryEndIndex);
			sub_Permutations = generate(currentPermutation.substring(0, 1),
					currentPermutation.substring(1), choose);
			for (int j = 0; j < sub_Permutations.size(); j++)
			{
				String permutation = prefix + sub_Permutations.get(j);
				permutations.add(permutation);
			}
		}
		return permutations;
	}
	
	public static java.util.List<String> generateAllPermutations(String str)
	{
		java.util.List<String> permutations = new java.util.ArrayList<String>();
		for(int i = str.length(); i >= 0; i--)
		{
			java.util.List<String> subPermutations = generate(str, i);
			for(String s :  subPermutations)
			{
				permutations.add(s);
			}
		}
		return permutations;
	}	
}