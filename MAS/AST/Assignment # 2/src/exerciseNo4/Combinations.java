/*
*	@author Muneeb Shahid
*/
package exerciseNo4;


public class Combinations
{
	private java.util.List<String> combinations = new java.util.ArrayList<String>();
	public java.util.List<String> getCombinations()
	{
		return this.combinations;
	}
	//http://exceptional-code.blogspot.de/2012/09/generating-all-permutations.html
	//Taken from the above mentioned link, Due to shortage of time.
	public void combine(char[] arr, int k, int startId, char[] branch, int numElem)
	{
	    if (numElem == k)
	    {
	        //System.out.println(java.util.Arrays.toString(branch));
	    	String combination = java.util.Arrays.toString(branch);
	    	combination = combination.substring(1, combination.length() - 1);
	        this.combinations.add(combination);
	        return;
	    }
	    
	    for (int i = startId; i < arr.length; ++i)
	    {
	        branch[numElem++] = arr[i];
	        combine(arr, k, ++startId, branch, numElem);
	        --numElem;
	    }
	}
}
