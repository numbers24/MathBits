package PrimeBits;

public class PrimeSplitter {


	public static void splitter(long n, long num)
	{
		if(num==1)
			return;
		
		if(num%n==0)
		{
			System.out.println(n);
			splitter(n, num/n);
		}
		else if(n<num)
			splitter(n+1,num);
		
	}
	public static void main(String args[])
	{
		long num =256;
		long n= 2;
		while(n<=num)
		{
			if(num%n==0)
			{
				System.out.println(n);
				num/=n;
				continue;
			}
			else if(n%2==0)
			n++;
			else
			n+=2;
			
		}
	}
	
}
