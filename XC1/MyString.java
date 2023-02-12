public class MyString implements MyComparable
{
	private char[] letters;
	final int NOT_FOUND = -1;

	public MyString( String other )
	{
		letters = other.toCharArray();
	}

	public MyString( MyString other )
	{
		this( other.toString() );
	}

	public String toString()
	{
		return new String(letters);
	}

	public int length()
	{
		return letters.length;
	}

	public char charAt(int index)
	{
		return letters[index];
	}

///////////////// Y O U    M U S T    W R I T E    T H E S E    T W O    M E T H O D S //////////////

	//RETURNS 0 if strings are lexically identical in every way, +1 if this string greater, else -1
	public int myCompareTo(MyString other)
	{
		String s1 = this.toString();
		String s2 = other.toString();
		int min = Math.min(s1.length(), s2.length());
		int max = Math.max(s1.length(), s2.length());

		for(int i = 0; i < max; i++)
		{
			if(i >= min)
			{
				return 1;
			}
			else if(s1 == null && s2 != null)
			{
				return -1;
			}
			else if(s1 != null && s2 == null)
			{
				return 1;
			}
			else if(s1.charAt(i) < s2.charAt(i))
			{
				return -1;
			}
			else if(s1.charAt(i) > s2.charAt(i))
			{
				return 1;
			}
		}
		return 0;
	}

	//RETURNS 0 iff strings are lexically identical
	public boolean equals(MyString other)
	{
		return(myCompareTo(other) == 0); //just to make it compile
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////

} // END MYSTRING CLASS