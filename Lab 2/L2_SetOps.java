import java.io.*;
import java.util.*;

public class L2_SetOps
{
	public static void main( String args[] ) throws Exception
	{
		BufferedReader infile1 = new BufferedReader( new FileReader( args[0] ) );
		BufferedReader infile2 = new BufferedReader( new FileReader( args[1] ) );

		String[] set1 = loadSet( infile1 );
		Arrays.sort( set1 );
		String[] set2 = loadSet( infile2 );
		Arrays.sort( set2 );
		printSet( "set1: ",set1 );
		printSet( "set2: ",set2 );

		String[] union = union( set1, set2 );
		Arrays.sort( union );
		printSet( "\nunion: ", union );


		String[] intersection = intersection( set1, set2 );
		Arrays.sort( intersection );
		printSet( "\nintersection: ",intersection );

		String[] difference = difference( set1, set2 );
		Arrays.sort( difference );
		printSet( "\ndifference: ",difference );

		String[] xor = xor( set1, set2 );
		Arrays.sort( xor );
		printSet("\nxor: ", xor );

		System.out.println( "\nSets Echoed after operations.");

		printSet( "set1: ", set1 );
		printSet( "set2: ", set2 );

	}// END MAIN

	// USE AS GIVEN - DO NOT MODIFY
	// CAVEAT: This method will not work *correctly* until you write a working doubleLength() method.

	static String[] loadSet( BufferedReader infile ) throws Exception
	{
		final int INITIAL_LENGTH = 5;
		int count=0;
		String[] set = new String[INITIAL_LENGTH];
		while( infile.ready() )
		{
				if (count >= set.length)
					set = doubleLength( set );
				set[ count++ ] = infile.readLine();
		}
		infile.close();
		return trimArray( set, count );
	}

	// USE AS GIVEN - DO NOT MODIFY
	static void printSet( String caption, String [] set )
	{
		System.out.print( caption );
		for ( String s : set )
			System.out.print( s + " " );
		System.out.println();
	}


	/* ###############################################################
		For each of the following set operations you must execute the following steps:
		1) dimension an array that is just big enough to handle the largest possible set for that operation.
		2) add the appropriate elements to the array as the operation prescribes.
		3) before returning the array, resize it to the exact size as the number of elements in it.
	*/

	static String[] union( String[] set1, String[] set2 )
	{
		int repeatCount = 0;
		int currentPos = 0;
		int newLen = set1.length + set2.length;
		String[] newArr = new String[newLen];

		for(int i = 0; i<set1.length; i++)
		{
			if(contains(set2, set1[i]) == false)
			{
				newArr[currentPos] = set1[i];
				currentPos++;
			}
			else
			{
				repeatCount++;
			}
		}

		for(String s : set2)
		{
			newArr[currentPos] = s;
			currentPos++;
		}

		return trimArray(newArr, newLen-repeatCount);
	}

	static String[] intersection( String[] set1, String[] set2 )
	{
		int repeatCount = 0;
		int currentPos = 0;
		int newLen = set1.length + set2.length;
		String[] newArr  = new String[newLen];

		for(int i = 0; i<set1.length; i++)
		{
			if(contains(set2, set1[i]) == true)
			{
				newArr[currentPos] = set1[i];
				currentPos++;
				repeatCount++;
			}
		}

		return trimArray(newArr, repeatCount);
	}

	static String[] difference( String[] set1, String[] set2 )
	{
		int currentPos = 0;
		int repeatCount = 0;
		String[] intersection = intersection(set1, set2);
		Arrays.sort(intersection);

		String[] newArr = new String[set1.length];

		for(int i = 0; i<set1.length; i++)
		{
			if(contains(intersection, set1[i]) == false)
			{
				newArr[currentPos] = set1[i];
				currentPos++;
			}
			else
			{
				repeatCount++;
			}
		}
		return trimArray(newArr, set1.length - repeatCount);
	}

	static String[] xor( String[] set1, String[] set2 )
	{
		return (difference(union(set1, set2), intersection(set1, set2)));
	}

	// return an array of length 2x with all data from the old array stored in the new array
	static String[] doubleLength( String[] old )
	{
		int oldLen = old.length;
		int newLen = oldLen * 2;

		String[] newArr = new String[newLen];

		for(int i = 0; i<oldLen; i++)
		{
			newArr[i] = old[i];
		}

		return newArr;
	}

	// return an array of length==count with all data from the old array stored in the new array
	static String[] trimArray( String[] old, int count )
	{
		String[] newArr = new String[count];

		for(int i = 0; i<count; i++)
		{
			newArr[i] = old[i];
		}

		return newArr;
	}

	// checks to see if a String (word) is contained in the String[] (set)
	static boolean contains(String[] set, String word)
	{
		for(String s : set)
		{
			if(word.equals(s))
			{
				return true;
			}
		}
		return false;
	}

} // END CLASS