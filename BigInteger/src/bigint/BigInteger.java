package bigint;

import java.util.NoSuchElementException;

/**
 * This class encapsulates a BigInteger, i.e. a positive or negative integer with 
 * any number of digits, which overcomes the computer storage length limitation of 
 * an integer.
 * 
 */
public class BigInteger {

	/**
	 * True if this is a negative integer
	 */
	boolean negative;
	
	/**
	 * Number of digits in this integer
	 */
	int numDigits;
	
	/**
	 * Reference to the first node of this integer's linked list representation
	 * NOTE: The linked list stores the Least Significant Digit in the FIRST node.
	 * For instance, the integer 235 would be stored as:
	 *    5 --> 3  --> 2
	 *    
	 * Insignificant digits are not stored. So the integer 00235 will be stored as:
	 *    5 --> 3 --> 2  (No zeros after the last 2)        
	 */
	DigitNode front;
	
	/**
	 * Initializes this integer to a positive number with zero digits, in other
	 * words this is the 0 (zero) valued integer.
	 */
	public BigInteger() {
		negative = false;
		numDigits = 0;
		front = null;
	}
	
	//Helper Methods
	
	private boolean zero (BigInteger i)
	{
		DigitNode ptr = i.front;
		boolean yes = true;
		int j = 0;
		while(j<i.numDigits)
		{
			if (ptr.digit != 0)
			{
				yes = false;
				break;
			}
			ptr=ptr.next;
			
			j++;
		}
		return yes;
		
	}
	
	

	private void addToFront (int newData)
	{
		front = new DigitNode(newData, front);
		numDigits += 1;
	}
	private void addToLast (int newData, BigInteger input)
	{	
		if(input.front == null)
		{
			input.front = new DigitNode(newData,null);
			return;
		}
		DigitNode previous = input.front;
		DigitNode last = input.front;
		while(last != null)
		{
			previous = last;
			last = last.next;
		}
		last = new DigitNode(newData, null);
		previous.next = last;
	}
	

	private void deleteFront()
	{
			if (front==null)
			{ //error, create object exception and throw back to the method that called deleteFront
				throw new NoSuchElementException("The Linked list is empty");
			}
			else
			{//when we get here we better be sure our front is not null otherwise we get a no-pointer exception
				front = front.next;
				numDigits -= 1;
			}
	}

	private void print()
	{
		for (DigitNode ptr = front; ptr != null; ptr = ptr.next)
		{
			System.out.print(ptr.digit + " -> ");
		}
		System.out.print("\\");
	}
	
	private static boolean search(DigitNode front, int target)
	{
		DigitNode ptr = front;
		while(ptr!=null)
		{
			// ptr is pointing to a node
			if(ptr.digit == target)
				{
					return true; //found target
				}
			ptr = ptr.next;
		}
		return false;
	}
	
	private static boolean addAfter(DigitNode front, int target, int newData)
	{
		for (DigitNode ptr = front; ptr!=null; ptr = ptr.next)
		{
			if(ptr.digit == target)
			{
				// found target, add new node
				DigitNode n = new DigitNode(newData, ptr.next); // make new node point where target is pointing to
				ptr.next = n; // make the target the node point to the new node
				return true;
				
			}
		}
		return false;
	}
	
	private static DigitNode delete (DigitNode front, int target)
	{
		// 1: traverse the linked list until target is found
		DigitNode ptr = front;
		DigitNode prev = null;
		while (ptr != null && ptr.digit != target)
			//didn't find target
		{
			prev = ptr; //prev is pointing from where ptr was pointing
			ptr = ptr.next;
		}
		// 2: delete node and return the front
		if (ptr == null)
		{
			return front;
		} else if (ptr == front)
		{
			return front.next;
		}else {
			prev.next = ptr.next;
			return front;
		}
	}

	//
		

		
	/**
	 * Parses an input integer string into a corresponding BigInteger instance.
	 * A correctly formatted integer would have an optional sign as the first 
	 * character (no sign means positive), and at least one digit character
	 * (including zero). 
	 * Examples of correct format, with corresponding values
	 *      Format     Value
	 *       +0            0
	 *       -0            0
	 *       +123        123
	 *       1023       1023
	 *       0012         12  
	 *       0             0
	 *       -123       -123
	 *       -001         -1
	 *       +000          0
	 *       
	 * Leading and trailing spaces are ignored. So "  +123  " will still parse 
	 * correctly, as +123, after ignoring leading and trailing spaces in the input
	 * string.
	 * 
	 * Spaces between digits are not ignored. So "12  345" will not parse as
	 * an integer - the input is incorrectly formatted.
	 * 
	 * An integer with value 0 will correspond to a null (empty) list - see the BigInteger
	 * constructor
	 * 
	 * @param integer Integer string that is to be parsed
	 * @return BigInteger instance that stores the input integer.
	 * @throws IllegalArgumentException If input is incorrectly formatted
	 */
	public static BigInteger parse(String integer) 
	throws IllegalArgumentException {
		//Checklist
		// Make sure to reject letters and non-numeric symbols
		//Reject - or + in the middle of the number such as 22+2 or 10000-231
		//Make sure you get the right sign when you have multiple signs
		
		//Part One: Create the LL and Transfer the String
		
		BigInteger parseNode= new BigInteger();
		parseNode.negative = false;
		int i;
		int character=0;//temp for converted char
		
		int sign = 1;//temp that determines sign of number
		boolean broken=false;//temp that asks if the number has non-numerical symbols (or signs in the middle)
		boolean complete = false; //temp that asks if the number is done
		
		int count = 0;
		int signCount = 0; //counts the amount of times a sign is used
		boolean zeroInFront = false; //temp that comes true if there's 0's in the front
		//this code is ridiculous
		for(i=0; i<=integer.length()-1; i++)

		{
			
			if (integer.charAt(i) != '0' && integer.charAt(i) != '1' && integer.charAt(i) != '2' && integer.charAt(i) != '3' && integer.charAt(i) != '4' && integer.charAt(i) != '5' && integer.charAt(i) != '6' && integer.charAt(i) != '7' && integer.charAt(i) != '8' && integer.charAt(i) != '9' && integer.charAt(i) != '-' && integer.charAt(i) != '+' && integer.charAt(i) != ' ')
			{
				broken=true;
				break;
			}
				if(integer.charAt(i)=='-'||integer.charAt(i)=='+'||integer.charAt(i)==' ')
			{
				
				for(int j = i; j>=0;j--)
				{
					if(integer.charAt(j)== ' ' || integer.charAt(j)== '-' || integer.charAt(j)== '+' || integer.charAt(j)== '0')
					{
						if(integer.charAt(j)== '-')
						{
							sign*=-1;
							signCount++;
						}
						if(integer.charAt(j)== '+')
						{
							sign*=1;
							signCount++;
						}
						
					}
					else
					{
						complete = false;
						broken = true;
						break;
						
					}complete = true;
				}

				if(broken==true)
				{
					break;
				}
				
				
			}
			if(integer.charAt(i) != ' ' && integer.charAt(i) != '-' && integer.charAt(i) != '+' && (count != 0 || integer.charAt(i)!='0'))
			{
				character = Character.getNumericValue(integer.charAt(i));
				parseNode.addToFront(character);
				count++;
			}
			
			
		}
		if (sign==-1)
		{
			parseNode.negative=true;
		}
		if (sign!=-1 && sign!=1 || signCount>1)
		{
			broken = true;
		}
		else if(parseNode.negative==false && broken==true)
		{
			throw new IllegalArgumentException();
		}
		if (broken == true)
		{
			throw new IllegalArgumentException();
		}
		
		// following line is a placeholder - compiler needs a return
		// modify it according to need
		parseNode.numDigits = count;
		return parseNode; 
		
	}
	
	/**
	 * Adds the first and second big integers, and returns the result in a NEW BigInteger object. 
	 * DOES NOT MODIFY the input big integers.
	 * 
	 * NOTE that either or both of the input big integers could be negative.
	 * (Which means this method can effectively subtract as well.)
	 * 
	 * @param first First big integer
	 * @param second Second big integer
	 * @return Result big integer
	 */
	public static BigInteger add(BigInteger first, BigInteger second) {
		
		BigInteger f = first, s=second;
		Boolean yesF = f.zero(s), yesS = s.zero(f);
		if(yesF == true)
		{
			return f;
		}
		if(yesS == true)
		{
			return s;
		}
		
		DigitNode tempNode;
		
		DigitNode ptrFirst = first.front;
		DigitNode ptrSecond = second.front;
		
		
		BigInteger result = new BigInteger();
		BigInteger temp = new BigInteger(); //temp to put the smaller integer in when switching
		
		boolean subtract=false;
		
		int count = 0; //counter
		 
		//Subtraction?
		if (first.negative != second.negative)
		{
			subtract = true;
			
		}
		//First>Second
		
		
		
		if (second.numDigits > first.numDigits)
		{
			temp = first;
			first = second;
			second = temp;
			
		}
		if (first.numDigits == second.numDigits)
		{
			while (ptrFirst != null && ptrSecond != null)
			{
				if(ptrFirst.digit != ptrSecond.digit)
				{
					if(ptrFirst.digit < ptrSecond.digit)
					{
						temp = first;
						first = second;
						second = temp;
						
					}
				}
				ptrFirst = ptrFirst.next;
				ptrSecond = ptrSecond.next;
			}
		}
		
		
		
		ptrFirst = first.front;
		ptrSecond = second.front;
		
		//subtraction
		if (subtract == true) 
		{
			if (first.negative == true)
			{
				result.negative = true;
			}
			
			
			for(int i = 0; i<first.numDigits; i++)
			{
				if(ptrSecond.digit > ptrFirst.digit && ptrSecond.digit != ptrFirst.digit)
				{
					ptrFirst.digit+=10;
					tempNode = ptrFirst;
					
					while(ptrFirst.next.digit<=0)
					{
						ptrFirst.next.digit+=9;
						ptrFirst = ptrFirst.next;
						i++;
					}
					ptrFirst.next.digit-=1;
					ptrFirst = tempNode;
					
				}
				result.addToLast(ptrFirst.digit - ptrSecond.digit, result);
				//result.addToFront(ptrFirst.digit - ptrSecond.digit);
			
			
			
			if(ptrFirst !=null)
			{
				ptrFirst = ptrFirst.next;
			}
			
			
			
			
			if(ptrSecond.next==null)
			{
				ptrSecond.next = new DigitNode(0,null);
			}
				ptrSecond = ptrSecond.next;
			
			
			}
		}
		
		//addition
		
		if (subtract == false)
		{
			DigitNode prev;
			for(int i = 0; i<first.numDigits; i++)
			{
				
				
					
				if(ptrFirst.digit + ptrSecond.digit > 9)
				{
					result.addToLast((ptrFirst.digit + ptrSecond.digit)-10, result);
					if(ptrFirst.next ==null)
					{
						prev = new DigitNode(0, null);
						ptrFirst.next = prev;
						i--;
					}
					
					ptrFirst.next.digit+=1;
				}
				else
				{
					result.addToLast(ptrFirst.digit + ptrSecond.digit, result);
				}
				
			
			
			
			
			
				
				if(ptrFirst.next!=null)
				{
				ptrFirst = ptrFirst.next;
				}
				if(ptrSecond.next==null)
				{
					ptrSecond.next = new DigitNode(0,null);
				}
					ptrSecond = ptrSecond.next;
				
				
			}
			
		}
		
		//count
		DigitNode ptrResult = result.front;
		while(ptrResult != null)
		{
			count++;
			ptrResult =ptrResult.next;
		}
		result.numDigits = count;
		
		
		
		
		// following line is a placeholder - compiler needs a return
		// modify it according to need
		return result;
		
	}
	
	/**
	 * Returns the BigInteger obtained by multiplying the first big integer
	 * with the second big integer
	 * 
	 * This method DOES NOT MODIFY either of the input big integers
	 * 
	 * @param first First big integer
	 * @param second Second big integer
	 * @return A new BigInteger which is the product of the first and second big integers
	 */
	public static BigInteger multiply(BigInteger first, BigInteger second) {
		// following line is a placeholder - compiler needs a return
		// modify it according to need
		
		//for zero
		BigInteger f = first, s=second;
		Boolean yesF = f.zero(s), yesS = s.zero(f);
		BigInteger zero = new BigInteger();
		zero.front = new DigitNode(0,null);
		if(yesF == true)
		{
			return zero;
		}
		if(yesS == true)
		{
			return zero;
		}
		
		//everything else

		BigInteger result = new BigInteger();
		int count;
		BigInteger temp;
		
		DigitNode ptrF = first.front;
		DigitNode ptrS = second.front;
		
		if(first.negative != second.negative)
		{
			result.negative = true;
			result.front = new DigitNode(0, null);
			result.numDigits++;
		}
		
		
		if(first.numDigits<second.numDigits)
		{
			temp = first;
			first = second;
			second = temp;
		}
		
		ptrF = first.front;
		ptrS = second.front;
		
		temp= new BigInteger();
		
		DigitNode prev;
		int roundup = 0;
		
		//Multiplication
		
		//I'm stumped
		
		for(int i = 0; i<second.numDigits;i++)
		{
			ptrF = first.front;
			for(int j = i; j>0; j--)
			{
				temp.addToLast(0, temp);
				temp.numDigits++;

			}
			for(int k = 0; k<first.numDigits;k++)
			{
				if(ptrF.digit * ptrS.digit > 9)
				{
					temp.addToLast((roundup+(ptrF.digit*ptrS.digit))-(((ptrF.digit * ptrS.digit)/10)*10), temp);
					if(ptrF.next == null)
					{
						temp.addToLast((ptrF.digit * ptrS.digit)/10,temp);
						temp.numDigits++;
						break;
					}
					
					roundup=((ptrF.digit * ptrS.digit)/10);
					
				}
				else {
					temp.addToLast((ptrF.digit * ptrS.digit), temp);
				}temp.numDigits++;
				ptrF=ptrF.next;
			}
			
			result = add(temp,result);
			temp = new BigInteger();
			ptrS=ptrS.next;
			
		}
		
		return result; 
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if (front == null) {
			return "0";
		}
		String retval = front.digit + "";
		for (DigitNode curr = front.next; curr != null; curr = curr.next) {
				retval = curr.digit + retval;
		}
		
		if (negative) {
			retval = '-' + retval;
		}
		return retval;
	}
	
}
