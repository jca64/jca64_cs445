/*
	Deck class (for TopCardPlacer class of project #1
*/

import java.util.*;
import java.io.*;

public class Deck
{
	private int[] deck;
	private final int MAX_DECK_SIZE = 30;
	public Deck( int numCards )
	{	if ( numCards%2 != 0 || numCards > MAX_DECK_SIZE ) 
		{
			System.out.format("\nINVALID DECK SIZE: (" + numCards + "). Must be an small even number <= %d\n", MAX_DECK_SIZE);
			System.exit(0);
		}
		deck = new int[numCards];
		for ( int i=0 ; i<numCards ; i++ ) deck[i] = i;
	}
	
	public String toString()
	{
		String deckStr = "";
		for ( int i=0 ; i < deck.length ; ++i )
			deckStr += deck[i] + " ";
		return deckStr;
	}

	// ONLY WORKS ON DECK WITH EVEN NUMBER OF CARDS
	// MODIFIES THE MEMBER ARRAY DECK
	public void inShuffle()
	{
		int count = 0;
        int[] modifiedDeck = new int[deck.length];

        for(int i=0; i<deck.length; i+=2)
        {
            modifiedDeck[i] = deck[count + deck.length/2];
            modifiedDeck[i + 1] = deck[count];
            count++;
        }
        deck = modifiedDeck;
	}

	// ONLY WORKS ON DECK WITH EVEN NUMBER OF CARDS
	// MODIFIES THE MEMBER ARRAY DECK
	public void outShuffle()
	{
		int count = 0;
        int[] modifiedDeck = new int[deck.length];

        for(int i=0; i<deck.length; i+=2)
        {
            modifiedDeck[i] = deck[count];
            modifiedDeck[i + 1] = deck[count + deck.length/2];
            count++;
        }
        deck = modifiedDeck;
	}
	
	public String toBitString( int n ) 
	{
		if(n == 0)
			return "";
		else
		{
			String binaryValStr = "";
			char[] binaryValArr = new char[(int)(Math.log(n) / Math.log(2)) + 1];

			// Fills char array with '0' chars
			for(int c = 0; c < binaryValArr.length; c++)
			{
				binaryValArr[c] = '0';
			}

			while(n > 0)
			{
				int p = 0;
				while((int)(Math.pow(2, (p + 1))) <= n)
				{
					p++;
				}
				binaryValArr[binaryValArr.length - (p+1)] = '1';
				n = n - ((int)Math.pow(2, p));
			}

			StringBuilder sb = new StringBuilder();
			sb.append(binaryValArr);
			binaryValStr = sb.toString();
			return binaryValStr;
		}
	}
}	// END DECK CLASS