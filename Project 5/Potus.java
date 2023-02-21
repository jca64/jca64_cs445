import java.util.*;
import java.io.*;

public class Potus
{
	public static void main( String[] args )  throws Exception
	{
		BufferedReader state2PresidentsFile = new BufferedReader( new FileReader("state2Presidents.txt") );
		TreeMap<String,TreeSet<String>> state2Presidents= new TreeMap<String,TreeSet<String>>();
		buildMap(state2PresidentsFile, state2Presidents);

		BufferedReader state2PresidentsFile2 = new BufferedReader( new FileReader("state2Presidents.txt") );
		TreeMap<String,TreeSet<String>> president2states = new TreeMap<String,TreeSet<String>>();
		buildInverseMap(state2PresidentsFile2, president2states);

		BufferedReader allPresidentsFile = new BufferedReader( new FileReader("allPresidents.txt") );
		TreeSet<String> allPresidents = new TreeSet<String>();
		buildSet(allPresidentsFile, allPresidents);

		BufferedReader allStatesFile = new BufferedReader( new FileReader("allStates.txt") );
		TreeSet<String> allStates = new TreeSet<String>();
		buildSet(allStatesFile, allStates);

		System.out.println( "THESE STATES HAD THESE POTUS BORN IN THEM:\n");
		
		mapPrinter(allStates, state2Presidents);

		System.out.println( "\nLIST OF POTUS AND STATE THEY WERE BORN IN:\n");

		mapPrinter(allPresidents, president2states);
	
		System.out.println( "\nTHESE POTUS BORN BEFORE STATES WERE FORMED:\n");

		for(String p : allPresidents)
		{
			if(president2states.containsKey(p) == false)
			{
				System.out.println(p);
			}
		}
		

		System.out.println( "\nTHESE STATES HAD NO POTUS BORN IN THEM:\n");	

		for(String s : allStates)
		{
			if(state2Presidents.containsKey(s) == false)
			{
				System.out.println(s);
			}
		}
	} // END MAIN


	//       - - - - - - - - - - -  H E L P E R    M E T H O D S - - - - - - - -
	public static void buildSet(BufferedReader br, TreeSet<String> set) throws Exception
	{
		while(br.ready())
			set.add(br.readLine());
	}

	public static void buildMap(BufferedReader br, TreeMap<String, TreeSet<String>> map) throws Exception
	{
		while(br.ready())
		{
			String[] tokens = br.readLine().split(" ");
			String state = tokens[0];

			for(int i = 1; i < tokens.length; i++)
			{
				if(map.containsKey(state) == false)
				{
					TreeSet<String> presidents = new TreeSet<String>();
					presidents.add(tokens[i]);
					map.put(state, presidents);
				}
				else
				{
					TreeSet<String> presidents = map.get(state);
					presidents.add(tokens[i]);
					map.put(state, presidents);
				}
			}
		}
	}
	public static void buildInverseMap(BufferedReader br, TreeMap<String, TreeSet<String>> map) throws Exception
	{
		while(br.ready())
		{
			String[] tokens = br.readLine().split(" ");
			String state = tokens[0];

			for(int i = 1; i < tokens.length; i++)
			{
				if(map.containsKey(tokens[i]) == false)
				{
					TreeSet<String> states = new TreeSet<String>();
					states.add(state);
					map.put(tokens[i], states);
				}
				else
				{
					TreeSet<String> states = map.get(tokens[i]);
					states.add(state);
					map.put(tokens[i], states);
				}
			}
		}
	}
	public static void mapPrinter(TreeSet<String> set, TreeMap<String, TreeSet<String>> map)
	{
		for(String x : set)
		{
			TreeSet<String> tempSet = map.get(x);
			if(tempSet != null)
			{
				System.out.print(x + " ");
				for(String y : tempSet)
				{
					System.out.print(y + " ");
				}
				System.out.println();
			}
		}
	}
	
}	// END CLASS