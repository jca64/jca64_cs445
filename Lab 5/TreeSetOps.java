import java.io.*;
import java.util.*;

public class TreeSetOps
{
	public static void main( String args[] ) throws Exception
	{
		BufferedReader infile1 = new BufferedReader( new FileReader( args[0] ) );
		BufferedReader infile2 = new BufferedReader( new FileReader( args[1] ) );

		TreeSet<String> set1 = loadSet( infile1 );
		TreeSet<String> set2 = loadSet( infile2 );
		printSet( "set1: ",set1 );
		printSet( "set2: ",set2 );

		TreeSet<String> union = union( set1, set2 );
		printSet( "\nunion: ", union );

		TreeSet<String> intersection = intersection( set1, set2 );
		printSet( "\nintersection: ",intersection );

		TreeSet<String> difference = difference( set1, set2 );
		printSet( "\ndifference: ",difference );

		TreeSet<String> xor = xor( set1, set2 );
		printSet("\nxor: ", xor );

		System.out.println( "\nSets Echoed after operations.");

		printSet( "set1: ", set1 );
		printSet( "set2: ", set2 );

	}// END MAIN

	// Y O U    W R I T E   T H I S     M E T H O D

	static TreeSet<String> loadSet( BufferedReader infile ) throws Exception
	{
		TreeSet<String> temp = new TreeSet<>();
		while(infile.ready())
		{
			temp.add(infile.readLine());
		}
		return temp;
	}

	// Y O U    W R I T E   T H I S     M E T H O D
	static void printSet( String caption, TreeSet<String> set )
	{
		System.out.print(caption);
		for(String s : set)
		{
			System.out.print(s + " ");
		}
		System.out.print("\n");
	}

	// Y O U    W R I T E   T H I S     M E T H O D
	static TreeSet<String> union( TreeSet<String> set1, TreeSet<String> set2 )
	{
		// YOU ARE NOT ALLOWED TO MODIFY this set. MAKE A COPY OF this set
		// NO LOOPS ALLOWED

		// create a deep copy of this TreeSet
		// apply the built in method(s) to modify it to be the union or intersect or diff or xor
		// return the modified version of the this set that has been transformed into a union or diff etc
		// NO LOOP ALLOWED. should really be only about 3 lines or so of code
		TreeSet<String> yourModifiedSet = new TreeSet<>(set1);
		yourModifiedSet.addAll(set2);

		return yourModifiedSet;  // JUST TO MAKE IT COMPILE. REPLACE WITH YOUR CODE

	}

	static TreeSet<String> intersection( TreeSet<String> set1, TreeSet<String> set2 )
	{
		// YOU ARE NOT ALLOWED TO MODIFY this set. MAKE A COPY OF this set
		// NO LOOPS ALLOWED

		// create a deep copy of this TreeSet
		// apply the built in method(s) to modify it to be the union or intersect or diff or xor
		// return the modified version of the this set that has been transformed into a union or diff etc
		// NO LOOP ALLOWED. should really be only about 3 lines or so of code
		TreeSet<String> yourModifiedSet = new TreeSet<>(set1);
		yourModifiedSet.retainAll(set2);

		return yourModifiedSet;  // JUST TO MAKE IT COMPILE. REPLACE WITH YOUR CODE
	}

	static TreeSet<String> difference( TreeSet<String> set1, TreeSet<String> set2 )
	{
		// YOU ARE NOT ALLOWED TO MODIFY this set. MAKE A COPY OF this set
		// NO LOOPS ALLOWED

		// create a deep copy of this TreeSet
		// apply the built in method(s) to modify it to be the union or intersect or diff or xor
		// return the modified version of the this set that has been transformed into a union or diff etc
		// NO LOOP ALLOWED. should really be only about 3 lines or so of code
		TreeSet<String> yourModifiedSet = new TreeSet<>(set1);
		yourModifiedSet.removeAll(set2);

		return yourModifiedSet;  // JUST TO MAKE IT COMPILE. REPLACE WITH YOUR CODE
	}

	static TreeSet<String> xor( TreeSet<String> set1, TreeSet<String> set2 )
	{
		return(difference(union(set1, set2), intersection(set1, set2)));
	}
} // END CLASS