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
		return(count == deck.length);
	}
}	// END DECK CLASS