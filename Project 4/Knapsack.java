/*
	PrintSubSets.java
*/
import java.io.*;

public class Knapsack
{
	public static void main( String[] args ) throws Exception
	{	
		int[] set = new int[16];
		BufferedReader infile0 = new BufferedReader( new FileReader( args[0] ) );
		String[] splitLine = infile0.readLine().split(" ");
		for(int i = 0; i < 16; i++)
		{
			set[i] = Integer.parseInt(splitLine[i]);
		}
		int target = Integer.parseInt(infile0.readLine());
		infile0.close();
		// My first submission did not work because I failed to remove this line so I had 1 extra line. 
		/*System.out.print( "original set: { " );
		for ( int i=0 ; i<set.length ; ++i )
			System.out.print( set[i] + " " );
		System.out.println("}" );*/

		for ( int i=0 ; i<Math.pow(2, 16) ; ++i )
		{	
			String[] subSet = new String[16];
			int sum = 0;
			String bitmap = toBitString( i, set.length );
			for ( int bindx=0 ; bindx<set.length ; ++bindx )
				if ( bitmap.charAt(bindx)=='1' )
				{
					subSet[bindx] = ( set[bindx] + "" );
					sum += Integer.parseInt(subSet[bindx]);
				}
				else
					subSet[bindx] = ( "  " );
			
			if(sum == target)
			{
				for(String s : subSet)
				{
					if(s.equals("  ") == false)
						System.out.print(s + " ");
				}
				System.out.print("\n");
			}
		}
	} // END MAIN

	// i.e number 31 converted to a width of 5 bits = "11111"
	//     nuumber 7 converted to a width of 5 bits = "00111"
	static String toBitString( int number, int width )
	{
		String bitstring = "";
		while (number > 0)
		{	if (number % 2 == 0)
				bitstring = "0" + bitstring;
			else
				bitstring = "1" + bitstring;
			number /= 2 ;
		}
		while ( bitstring.length() < width )
				bitstring = "0" + bitstring;
		return bitstring;
	}
} // END CLASS