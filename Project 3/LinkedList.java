import java.io.*;
import java.util.*;

import javax.lang.model.util.ElementScanner6;

// NOTICE THE "<T extends Comparable<T>>"
// using <T extends Comparable<T>> in here means compiler wont let the code in main send in any T type
// that does not implement Comparable.  Now we do not have to cast the incoming key to a Comparable
// in our insertInOrder() method. Compiler now lets us call .compareTo off the dot on the incoming key
// without throwing an error.

public class LinkedList<T extends Comparable<T>> 
{
	private Node<T> head;  // pointer to the front (first) element of the list

	public LinkedList()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FROM INCOMING FILE
	@SuppressWarnings("unchecked")
	public LinkedList( String fileName, boolean orderedFlag )
	{	head = null;
		try
		{
			BufferedReader infile = new BufferedReader( new FileReader( fileName ) );
			while ( infile.ready() )
			{
				if (orderedFlag)
					insertInOrder( (T)infile.readLine() );  // WILL INSERT EACH ELEM INTO IT'S SORTED POSITION
				else
					insertAtTail( (T)infile.readLine() );  // TACK EVERY NEWELEM ONTO END OF LIST. ORIGINAL ORDER PRESERVED
			}
			infile.close();
		}
		catch( Exception e )
		{
			System.out.println( "FATAL ERROR CAUGHT IN C'TOR: " + e );
			System.exit(0);
		}
	}

	//-------------------------------------------------------------

	// inserts new elem at front of list - pushing old elements back one place

	public void insertAtFront(T data)
	{
		head = new Node<T>(data,head);
	}

	// we use toString as our print

	public String toString()
	{
		String toString = "";

		for (Node<T> curr = head; curr != null; curr = curr.next )
		{
			toString += curr.data;		// WE ASSUME OUR T TYPE HAS toString() DEFINED
			if (curr.next != null)
				toString += " ";
		}

		return toString;
	}

	// ########################## Y O U   W R I T E    T H E S E    M E T H O D S ########################



	public int size() // OF COURSE MORE EFFICIENT to KEEP COUNTER BUT YOU MUST WRITE LOOP
	{
		Node<T> cur = head;
		int count = 0;

		if(head == null){return 0;}
		
		while(cur != null)
		{
			count++;
			cur = cur.next;
		}
		return count;
	}

	public boolean empty()
	{
		return(this.size() == 0);
	}

	public boolean contains( T key )
	{
		return(search(key) != null);
	}

	public Node<T> search( T key )
	{
		Node<T> cur = head;
		
		while(cur != null)
		{
			if(cur.data.equals(key))
			{
				return cur;
			}
			cur = cur.next;
		}
		return null;
	}

	// TACK A NEW NODE (CABOOSE) ONTO THE END OF THE LIST
	public void insertAtTail(T data)
	{
		if(head == null)
		{
			insertAtFront(data);
			return;
		}

		Node<T> cur = head;

		while(cur.next != null)
		{
			cur = cur.next;
		}

		Node<T> tail = new Node<T>(data, null);
		cur.next = tail;
	}

	// IF YOU DEFINE <T> at the top of this class as <T implements Comparable>
	// YOU DO NOT HAVE TO CAST TO COMPARABLE AND YOU DO NOT NEED TO SUPPRESS 
	public void insertInOrder(T  data)
	{
		if(head == null || data.compareTo(head.data) < 0)
		{
			insertAtFront(data);
			return;
		}

		Node<T> cur = head;

		while(cur.next != null && data.compareTo(cur.next.data) > 0)
		{
			cur = cur.next;
		}

		Node<T> newNode = new Node<T>(data, cur.next);
		cur.next = newNode;
	}

	public boolean remove(T key)
	{
		if(empty()){return false;}

		if(head.data.compareTo(key) == 0){return removeAtFront();}

		Node<T> cur = head;
		Node<T> prev = null;

		while(cur != null && cur.data.equals(key) == false)
		{
			prev = cur;
			cur = cur.next;
		}

		if(cur == null)
		{
			return false;
		}
		else
		{
			prev.next = cur.next;
		}

		return true;
	}

	public boolean removeAtTail()	// RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
		if(head == null){return false;}

		if(this.size() == 1){return removeAtFront();}

		Node<T> cur = head;

		while(cur.next.next != null)
		{
			cur = cur.next;
		}

		cur.next = null;

		return true;
	}

	public boolean removeAtFront() // RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
		if(head == null){return false;}

		head = head.next;
		return true;
	}

	public LinkedList<T> union( LinkedList<T> other )
	{
		LinkedList<T> union = new LinkedList<T>();

		Node<T> cur = head;
		Node<T> otherCur = other.head;

		while(cur != null)
		{
			union.insertInOrder(cur.data);
			cur = cur.next;
		}

		while(otherCur != null)
		{
			if(union.contains(otherCur.data) == false)
			{
				union.insertInOrder(otherCur.data);
			}
			otherCur = otherCur.next;
		}

		return union;
	}
	public LinkedList<T> inter( LinkedList<T> other )
	{
		LinkedList<T> inter = new LinkedList<T>();

		Node<T> cur = head;
		Node<T> otherCur = other.head;

		/*while(cur != null)
		{
			otherCur = other.head;
			while(otherCur != null)
			{
				if(cur.data.equals(otherCur.data))
				{
					inter.insertInOrder(cur.data);
				}
				otherCur = otherCur.next;
			}
			cur = cur.next;
		}*/

		/* The commented solution above would be used if lists were not sorted. It is a O(n^2) method. 
		 * Since both lists are sorted it is better to use the solution below since it is O(n).
		 */
		while(cur != null && otherCur != null)
		{
			if(cur.data.equals(otherCur.data))
			{
				inter.insertInOrder(cur.data);
				cur = cur.next;
				otherCur = otherCur.next;
			}
			else if(otherCur.data.compareTo(cur.data) == 1)
			{
				cur = cur.next;
			}
			else
			{
				otherCur = otherCur.next;
			}
		}

		return inter;
	}
	
	public LinkedList<T> diff( LinkedList<T> other )
	{
		LinkedList<T> diff = new LinkedList<T>();

		Node<T> cur = head;
		Node<T> otherCur = other.head;

		while(cur != null && otherCur != null)
		{
			int c = cur.data.compareTo(otherCur.data);
			if(c == 0)
			{
				cur = cur.next;
				otherCur = otherCur.next;
			}
			else if(c < 0)
			{
				diff.insertInOrder(cur.data);
				cur = cur.next;
			}
			else
			{
				otherCur = otherCur.next;
			}
		}

			while(cur != null)
			{
				diff.insertInOrder(cur.data);
				cur = cur.next;
			}

		return diff;
		
	}
	



	public LinkedList<T> xor( LinkedList<T> other )
	{
		return this.union(other).diff(inter(other));
	}

} //END LINKEDLIST CLASS 

// A D D   N O D E   C L A S S  D O W N   H E R E 
// R E M O V E  A L L  P U B L I C  &  P R I V A T E (except toString)
// R E M O V E  S E T T E R S  &  G E T T E R S 
// M A K E  T O  S T R I N G  P U B L I C

class Node<T extends Comparable<T>> // tells compiler our incoming T type implements Comparable
{
  T data;
  Node<T> next;

  Node()
  {
    this( null, null );
  }

  Node(T data)
  {
    this( data, null );
  }

  Node(T data, Node<T> next)
  {
    this.data = data;
    this.next = next;
  }
  public String toString()
  {
	  return ""+data;
  } 
	 
} //EOF