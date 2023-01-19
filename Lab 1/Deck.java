/*
	Deck class
*/

import java.util.*;
import java.io.*;

public class Deck
{
	private int[] deck;
	private final int MAX_DECK_SIZE = 20;
	public Deck( int numCards )
	{	 
        if ( numCards%2 != 0 || numCards > MAX_DECK_SIZE ) 
		{
			System.out.format("\nINVALID DECK SIZE: (" + numCards + "). Must be an small even number <= %d\n", MAX_DECK_SIZE);
			System.exit(0);
		}
        deck = new int[numCards];
        for(int i=0; i<numCards; i++)
        {
            deck[i] = i;
        }
		// YOU DO THIS => init deck to be exactly numCards long
		// YOU DO THIS => fill deck with with 0 1 2 3 ... numCards-1 in order
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
		int tCount = 0;
        int mCount = deck.length/2;
        int[] modifiedDeck = new int[deck.length];

        for(int i=0; i<deck.length; i+=2)
        {
            modifiedDeck[i] = deck[mCount];
            modifiedDeck[i + 1] = deck[tCount];
            mCount++;
            tCount++;
        }
        deck = modifiedDeck;
	}

	// ONLY WORKS ON DECK WITH EVEN NUMBER OF CARDS
	// MODIFIES THE MEMBER ARRAY DECK
	public void outShuffle()
	{
		int tCount = 0;
        int mCount = deck.length/2;
        int[] modifiedDeck = new int[deck.length];

        for(int i=0; i<deck.length; i+=2)
        {
            modifiedDeck[i] = deck[tCount];
            modifiedDeck[i + 1] = deck[mCount];
            mCount++;
            tCount++;
        }
        deck = modifiedDeck;
	}
	
	// RETURNS TRUE IF DECK IN ORIGINAL SORTED:  0 1 2 3 ...
	public boolean inSortedOrder()
	{
        int count = 0;
		for(int i=0; i<deck.length; i++) 
        {
            if(deck[i] == count)
            {
                count++;
            }
        }
		return(count == deck.length); // JUST HERE TO COMPILE
	}
}	// END DECK CLASS